import keras
import numpy as np
from keras import optimizers
from keras.datasets import fashion_mnist
from keras.models import Sequential, load_model
from keras.layers import Conv2D, Dense, Flatten, MaxPooling2D,Dropout
from keras.callbacks import LearningRateScheduler, TensorBoard, ModelCheckpoint
from keras.preprocessing.image import ImageDataGenerator
from keras.regularizers import l2


# Code taken from https://github.com/BIGBALLON/cifar-10-cnn
class LeNet:
    def __init__(self, epochs=10, batch_size=128, load_weights=True):
        self.name               = 'lenet'
        self.model_filename     = 'lenet.h5'
        self.num_classes        = 10
        self.input_shape        = 28,28,1
        self.batch_size         = batch_size
        self.epochs             = epochs
        self.iterations         = 1000
        self.weight_decay       = 0.0001

        if load_weights:
            try:
                self._model = load_model(self.model_filename)
                print('Successfully loaded', self.name)
            except (ImportError, ValueError, OSError):
                print('Failed to load', self.name)

    def count_params(self):
        return self._model.count_params()

    def color_preprocessing(self, x_train, x_test):
        x_train = x_train.astype('float32')
        x_test = x_test.astype('float32')
        x_train=x_train/255
        x_test=x_test/255
        return x_train, x_test

    def build_model(self):
        cnn1 = Sequential()
        cnn1.add(Conv2D(32, kernel_size=(3, 3), activation='relu', input_shape=self.input_shape))
        cnn1.add(MaxPooling2D(pool_size=(2, 2)))
        cnn1.add(Dropout(0.2))
        cnn1.add(Flatten())
        cnn1.add(Dense(128, activation='relu'))
        cnn1.add(Dense(10, activation='softmax'))

        cnn1.compile(loss=keras.losses.categorical_crossentropy,
              optimizer=keras.optimizers.Adam(),
              metrics=['accuracy'])
        return cnn1

    def myLoss(y_true,y_pred):
        _class=np.argmax(y_true)
        model.predict(image)[_class]*(1-ssim(image_old,image))


    def scheduler(self, epoch):
        if epoch <= 60:
            return 0.05
        if epoch <= 120:
            return 0.01
        if epoch <= 160:
            return 0.002
        return 0.0004

    def train(self):
        (x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()
        x_train=x_train[:,:,:,np.newaxis]
        x_test=x_test[:,:,:,np.newaxis]

        y_train = keras.utils.to_categorical(y_train, self.num_classes)
        y_test = keras.utils.to_categorical(y_test, self.num_classes)

        # color preprocessing
        x_train, x_test = self.color_preprocessing(x_train, x_test)

        # build network
        model = self.build_model()
        model.summary()

        # using real-time data augmentation
        print('Using real-time data augmentation.')
        history1 = model.fit(x_train, y_train,
          batch_size=256,
          epochs=20,
          verbose=1,
          validation_data=(x_test, y_test))
        # save model
        model.save(self.model_filename)

        self._model = model

    def color_process(self, imgs):
        if imgs.ndim < 4:
            imgs = np.array([imgs])
        imgs = imgs.astype('float32')
        for img in imgs:
            img=img/255
        return imgs

    def predict(self, img):
        processed = self.color_process(img)
        return self._model.predict(processed, batch_size=self.batch_size)

    def predict_one(self, img):
        return self.predict(img)[0]

    def accuracy(self):
        (x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()
        x_train=x_train[:,:,:,np.newaxis]
        x_test=x_test[:,:,:,np.newaxis]

        y_train = keras.utils.to_categorical(y_train, self.num_classes)
        y_test = keras.utils.to_categorical(y_test, self.num_classes)

        # color preprocessing
        x_train, x_test = self.color_preprocessing(x_train, x_test)

        return self._model.evaluate(x_test, y_test, verbose=0)[1]
