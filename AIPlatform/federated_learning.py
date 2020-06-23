import json
import logging

import requests
import tensorflow as tf
import numpy as np
from edgex.edgex_client import ClientThread
from ui.api_server import APIServerThread
from edgex.event_creator import EventThread, event_creator


class FederatedLearning:
    def __init__(self):
        self.global_model_id = ""
        self.session_id = "5ec75970948e08da1d6921fd"
        self.server_url_prefix = "http://localhost:8080"
        self.proxy_ports = ['8081'
            , '8082'
            , '8083', '8084'
                            ]
        self.local_model_ids = []
        self.local_models = []
        self.global_model = tf.keras.models.load_model("static/h5/global_model.h5")
        self.protocol = "http://"
        self.host = "localhost"
        APIServerThread().start()

    def upload_global_model(self):
        logging.info("FederatedLearning is uploading global model")
        file_name = "static/h5/global_model.h5"
        r = requests.post(
            url=self.server_url_prefix + "/global_models",
            params={
                "session_id": self.session_id
            },
            json={
                "subscriptions": [
                    {
                        "address": "deafult",
                        "condition": "default"
                    }],
                "labels": {
                    "default": "default"
                },
                "name": "default",
                "version": "default",
                "type": "default",
                "status": "default",
                "description": "default"
            }
        )
        self.global_model_id = r.text
        requests.post(url=self.server_url_prefix + "/global_models/files",
                      data={
                          'session_id': self.session_id,
                          'global_model_id': self.global_model_id
                      },
                      files={
                          'file': open(file_name, 'rb')
                      })
        logging.info("FederatedLearning finished uploading global model")

    def run_clients(self):
        threads = []

        for port in self.proxy_ports:
            thread = ClientThread(port=port, global_model_id=self.global_model_id)
            thread.start()
            threads.append(thread)
        for thread in threads:
            thread.join()

        event_thread = EventThread()
        event_thread.start()
        event_thread.join()

        for thread in threads:
            thread.upload_local_model()

    def download_local_models(self):
        logging.info("FederatedLearning is downloading local models")

        r = requests.get(
            self.server_url_prefix + "/global_models" + "/" + self.global_model_id, {"session_id": self.session_id}
        )
        global_model_po = json.loads(r.text)

        for local_model_id in global_model_po['local_model_ids']:
            local_model_id = local_model_id['$oid']
            download_url = self.server_url_prefix + "/local_models" + "/files" + "/" + local_model_id
            r = requests.get(download_url, {
                "session_id": self.session_id
            }, stream=True)
            file_name = "static/h5/" + local_model_id + "_model" + ".h5"
            with open(file_name, 'wb')as fd:
                for chunk in r.iter_content():
                    fd.write(chunk)
            self.local_models.append(tf.keras.models.load_model(file_name))

        logging.info("FederatedLearning finished downloading local models")

    def federated_average(self):
        logging.info("FederatedLearning evaluates current global model")
        self.global_model.evaluate(event_creator.x_test, event_creator.y_test)

        logging.info("FederatedLearning is federating")
        for layer_i in range(0, len(self.global_model.layers)):
            current_layer = self.local_models[0].layers[layer_i]
            size = len(self.local_models)
            average_weights = np.array(current_layer.get_weights())
            for model_i in range(1, len(self.local_models)):
                temp_weights = np.array(self.local_models[model_i].layers[layer_i].get_weights())
                average_weights = average_weights + temp_weights
            average_weights = average_weights / size
            average_weights = average_weights.tolist()
            self.global_model.layers[layer_i].set_weights(average_weights)

        self.global_model.save("static/h5/global_model.h5")

        logging.info("FederatedLearning finished federating")

        logging.info("FederatedLearning evaluates current global model")
        self.global_model.evaluate(event_creator.x_test, event_creator.y_test)

    def run(self):
        self.upload_global_model()
        self.run_clients()
        self.download_local_models()
        self.federated_average()
