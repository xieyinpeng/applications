import numpy as np
from sklearn.neighbors import KNeighborsRegressor
from sklearn.model_selection import train_test_split

class Myregressor:
    def __init__(self):
        self.knn=KNeighborsRegressor()

    def fit(self,data,target):
        self.knn.fit(data,target)

    def predict(self,data):
        return self.knn.predict(data)
