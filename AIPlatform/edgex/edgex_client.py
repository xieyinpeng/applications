import json
import logging
import os
import threading
import _thread
import bottle
from bottle import Bottle, request
import tensorflow as tf
import requests
import numpy as np
import paho.mqtt.client as mqtt

bottle.BaseRequest.MEMFILE_MAX = 1024 * 1024 * 1024

server_url_prefix = "http://localhost:8080"
threadLock = threading.Lock()
session_id = "5ec75970948e08da1d6921fd"
data = {
    "8081": {},
    "8082": {},
    "8083": {},
    "8084": {}
}

app = Bottle()
app.config.load_dict({"log.level":"error"})


@app.post('/model/uploader')
def upload():
    port = request.url.split(":")[2][0:4]
    global_model_id = request.forms['global_model_id']
    post_url = server_url_prefix + "/local_models"
    upload_url = server_url_prefix + "/local_models/files"
    file_name = "static/h5/" + port + "_model" + ".h5"
    data[port]['global_model'].save(file_name)
    files = {
        "file": open(file_name, 'rb')
    }
    r = requests.post(
        url=post_url,
        data={
            'session_id': session_id,
            'global_model_id': global_model_id,
            'message': 'hello'
        }
    )
    data[port]['local_model_id'] = r.text
    requests.post(
        url=upload_url,
        data={
            "session_id": session_id,
            "local_model_id": data[port]['local_model_id']
        },
        files=files)
    logging.info("client %s uploads the local model" % port)
    return "ok"


@app.post('/model')
def model_download_and_deploy():
    """
    download and deploy one model
    :return: status
    """
    port = request.url.split(":")[2][0:4]
    global_model_id = request.forms['global_model_id']
    download_url = server_url_prefix + "/global_models" + "/files" + "/" + global_model_id
    file_name = "static/h5/" + port + "_model" + ".h5"
    if not os.path.exists(file_name):
        r = requests.get(download_url, {
            "session_id": session_id
        }, stream=True)
        with open(file_name, 'wb')as fd:
            for chunk in r.iter_content():
                fd.write(chunk)
    threadLock.acquire()
    model = tf.keras.models.load_model(file_name)
    data[port]['global_model'] = model
    threadLock.release()

    logging.info("client %s downloads the global model" % port)
    return "ok"


@app.post('/model/trainer')
def train():
    """
    train
    :return:status
    """
    port = request.url.split(":")[2][0:4]
    event = request.json
    data[port]['global_model'].fit(np.array(event['readings']['x_train']), np.array(event['readings']['y_train']),
                                   epochs=1)

    logging.info("client %s trains on the local model" % port)
    return "ok"


##########################
@app.route('/')
def introduce():
    port = request.url.split(":")[2][0:4]
    return "<a href='https://documenter.getpostman.com/view/7356810/SztBcU44?version=latest'>see document</a>"


@app.get('/model')
def model_state_get():
    """
    model state
    :return: state
    """
    port = request.url.split(":")[2][0:4]
    return port


@app.post('/model/predictor')
def predict():
    """
    predict
    :return:status
    """
    port = request.url.split(":")[2][0:4]
    event = request.json
    result = data[port]['global_model'].predict(event['readings']['x_test'])
    logging.info("client %s predicts on the local model" % port)
    return result


class ClientThread(threading.Thread):

    def __init__(self, port, global_model_id=""):
        threading.Thread.__init__(self)
        self.server_url_prefix = "http://localhost:8080"
        self.protocol = "http://"
        self.host = "localhost"
        self.port = port
        self.session_id = "5ec75970948e08da1d6921fd"
        self.global_model_id = global_model_id

    def run(self):
        logging.info("client %s starts" % self.port)
        def func(): app.run(host=self.host, port=self.port)
        _thread.start_new_thread(func, ())
        self.deploy_local_model()
        self.train_local_model_mqtt()

    def deploy_local_model(self):
        requests.post(self.protocol + self.host + ":" + self.port + "/model", {
            "global_model_id": self.global_model_id
        })

    def train_local_model_mqtt(self):
        def on_message(mqttc, obj, msg):
            logging.info("client %s is training on the local model" % self.port)
            port = self.port
            event = json.loads(msg.payload)
            device_name = "device" + port[3:4]
            if not device_name == event['device']:
                return
            value = event['readings'][0]['value'].replace("'", '"')
            value = json.loads(value)
            data[port]['global_model'].fit(np.array(value['x_train']),
                                           np.array(value['y_train']),
                                           epochs=1)
            logging.info("client %s finished training on the local model" % self.port)

        mqttc = mqtt.Client()
        mqttc.on_message = on_message
        mqttc.connect("localhost", 1883, 60)
        mqttc.subscribe("device_event_topic", 0)

        mqttc.loop_start()

    def upload_local_model(self):
        r = requests.post(self.protocol + self.host + ":" + self.port + "/model/uploader", {
            "global_model_id": self.global_model_id
        })
        return 'ok'
