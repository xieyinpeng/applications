from lenet import LeNet
from attack import PixelAttacker
from keras.datasets import fashion_mnist
import numpy as np

def aiTest(images,shape):
    model=LeNet()
    y_test=[]
    x_test=images
    generate_images=[]
    for image in x_test:
        confidence = model.predict(image)[0]
        predicted_class = np.argmax(confidence)
        y_test.append(predicted_class)
    attacker=PixelAttacker((x_test, y_test))
    for i in range(len(x_test)):
        generate_images.append(attacker.attack(i,model,verbose=False)[10])
    return generate_images

if __name__ == '__main__':
    LeNet().train()
