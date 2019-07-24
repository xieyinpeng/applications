from sklearn.cluster import AffinityPropagation
import numpy as np

class Mycluster:
    def __init__(self):
        damping=0.5
        conv_iter=10
        self.ap=AffinityPropagation(damping=damping,convergence_iter=conv_iter,preference=-50)

    def fit(self,data):
        self.ap.fit(data)

    def predict(self,data):
        center_number=self.ap.predict(data)
        center_indice=self.ap.cluster_centers_indices_[center_number]
        point_indices=[]
        labels=self.ap.labels_
        for i in range(len(labels)):
            if labels[i]==center_number:
                point_indices.append(i)
        return center_indice,point_indices
