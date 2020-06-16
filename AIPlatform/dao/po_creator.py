import json


class POCreator:
    path = 'static/json/'
    extension = '.json'

    def __init__(self):
        self._load()

    def _load(self):
        with open(self.path + 'global_model_po' + self.extension, 'r') as f:
            self.global_model_po = json.load(f)
        with open(self.path + 'local_model_po' + self.extension, 'r') as f:
            self.local_model_po = json.load(f)
        with open(self.path + 'session_po' + self.extension, 'r') as f:
            self.session_po = json.load(f)
        with open(self.path + 'user_po' + self.extension, 'r') as f:
            self.user_po = json.load(f)
        with open(self.path + 'event' + self.extension, 'r') as f:
            self.event = json.load(f)

    def is_po(self):
        pass

    def create_global_model_po(self):
        copy = self.global_model_po.copy()
        return copy

    def create_local_model_po(self):
        copy = self.local_model_po.copy()
        return copy

    def create_session_po(self):
        copy = self.session_po.copy()
        return copy

    def create_user_po(self):
        copy = self.user_po.copy()
        return copy


po_creator = POCreator()
