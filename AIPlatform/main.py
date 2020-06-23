import logging

from federated_learning import FederatedLearning

logging.getLogger().setLevel(logging.INFO)
FederatedLearning().run()

if __name__ == '__main__':
    FederatedLearning().run()
