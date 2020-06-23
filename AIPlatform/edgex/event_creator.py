import json
import logging
import threading

import requests
import tensorflow as tf

from static import config


class EventCreator:
    def __init__(self):
        mnist = tf.keras.datasets.mnist
        (x_train, y_train), (x_test, y_test) = mnist.load_data()
        x_train, x_test = x_train / 255.0, x_test / 255.0
        # size 60000
        self.x_train = x_train[0:config.train_size]
        self.y_train = y_train[0:config.train_size]
        # size 10000
        self.x_test = x_test
        self.y_test = y_test

        with open("static/json/event.json", 'r') as f:
            event = json.load(f)
        self.event = event

    def create_photo_train_events(self, batch_size):
        batch_size = int(batch_size)
        events = []
        batches_size = len(self.x_train) / batch_size
        for batch_index in range(0, int(batches_size)):
            event = self.event.copy()
            event['device'] = 'device' + str(batch_index % 4 + 1)
            value = {
                "x_train": self.x_train[batch_index * batch_size:(batch_index + 1) * batch_size].tolist(),
                "y_train": self.y_train[batch_index * batch_size:(batch_index + 1) * batch_size].tolist()
            }
            event['readings'] = [
                {
                    "name": "train",
                    "value": json.dumps(value)
                }
            ]
            events.append(json.dumps(event))
        return events


event_creator = EventCreator()


class EventThread(threading.Thread):

    def __init__(self):
        threading.Thread.__init__(self)
        self.protocol = "http://"
        self.host = "192.168.56.101"
        self.port = "48080"

    def run(self):
        logging.info("EventThread is posting events")
        batch_size = config.batch_size
        events = event_creator.create_photo_train_events(batch_size)
        for event in events:
            requests.post(self.protocol + self.host + ":" + self.port + '/api/v1/event', data=event)
        logging.info("EventThread finished posting events")