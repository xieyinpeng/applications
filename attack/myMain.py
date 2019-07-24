import keras
import numpy as np
import tensorflow as tf
from keras.datasets import fashion_mnist
from keras.models import load_model
from scipy.optimize import differential_evolution
from skimage.measure import compare_ssim as ssim

model=load_model("lenet.h5")

def aiTest(images,shape):
    attack_images=[]
    for image in images:
        _class=np.argmax(model.predict(image[np.newaxis,:,:,:])[0])
        attack_images.append(attack(image,_class))
    return attack_images

def attack(image,_class):
    old_one=np.array(image)
    print(model.predict(image[np.newaxis,:,:,:]))
    pixel_count=3
    func= lambda pixels: model.predict(generate(pixels,image)[np.newaxis,:,:,:])[0][_class]*np.power(10000,ssim(image,old_one,multichannel=True))
    callback=lambda x, convergence: print(x)
    bounds = [(0,28), (0,28), (0,256)] * pixel_count
    result = differential_evolution(
        func, bounds, maxiter=1, popsize=20,
        recombination=1, atol=-1,polish=False)
    print(model.predict(image[np.newaxis,:,:,:]))
    print(ssim(image,old_one,multichannel=True))
    return image

def generate(pixels,image):
    pixels=pixels.astype(np.int32)
    for i in range(len(pixels)//3):
        image[pixels[i*3+0],pixels[i*3+1]]=pixels[i*3+2]
    return image

if __name__ == '__main__':
    (x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()
    x_test=x_test[0:10,:,:,np.newaxis]
    x_test=np.array(x_test)
    print(aiTest(x_test,None))
