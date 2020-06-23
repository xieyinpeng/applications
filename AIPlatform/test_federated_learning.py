import logging

import numpy as np

from edgex.event_creator import event_creator
from federated_learning import FederatedLearning
import tensorflow as tf

fl=FederatedLearning()

filenames=["5ee209b12f19340527ba25cb_model.h5","5ee209ab2f19340527ba25b8_model.h5",
           "5ee209a42f19340527ba25a5_model.h5","5ee2099e2f19340527ba2592_model.h5"]

models=[]
model=tf.keras.models.load_model("static/h5/"+"global_model.h5")
for filename in filenames:
    models.append(tf.keras.models.load_model("static/h5/"+filename))


logging.getLogger().setLevel(logging.INFO)
logging.info("FederatedLearning evaluates current global model")
model.evaluate(event_creator.x_test, event_creator.y_test)
logging.info("FederatedLearning is federating")
for layer_i in range(0, len(model.layers)):
    current_layer = models[0].layers[layer_i]
    size = len(models)
    average_weights = np.array(current_layer.get_weights())
    for model_i in range(1, len(models)):
        temp_weights = np.array(models[model_i].layers[layer_i].get_weights())
        average_weights = average_weights + temp_weights
    average_weights = average_weights / size
    average_weights = average_weights.tolist()
 #   current_layer.set_weights(average_weights)
    #print(average_weights)
    model.layers[layer_i].set_weights(average_weights)
    #print(model.layers[layer_i].get_weights())

#model.save("static/h5/global_model.h5")

logging.info("FederatedLearning finished federating")

logging.info("FederatedLearning evaluates current global model")
model.evaluate(event_creator.x_test, event_creator.y_test)

