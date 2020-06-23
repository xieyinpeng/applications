from bson import json_util

from dao import GlobalModelDAO, LocalModelDAO, SessionDAO, UserDAO
from dao.po_creator import po_creator


def check_session(func):
    def wrapper(self, session_id, *args, **kwargs):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        return func(self, session_id, *args, **kwargs)

    return wrapper


class GlobalModelService:
    def __init__(self):
        self.global_model_dao = GlobalModelDAO()
        self.local_model_dao = LocalModelDAO()
        self.session_dao = SessionDAO()
        self.user_dao = UserDAO()

    def create_global_model(self, session_id, name="default",
                            version="0", type="default", status="default",
                            description="default"):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        global_model_po = po_creator.create_global_model_po()
        global_model_po.pop("_id", None)
        global_model_po.update({
            "name": name,
            "version": version,
            "type": type,
            "status": status,
            "description": description
        })
        global_model_id = self.global_model_dao.insert_one(global_model_po)
        user_po = self.user_dao.find_one_by_id(user_id)
        user_po['global_model_ids'].append(global_model_id)
        self.user_dao.replace_one_by_id(user_id, user_po)
        return global_model_id

    def update_global_model(self, session_id, global_model_id, name=None, version=None,
                            type=None, status=None, description=None):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        if global_model_id not in self.user_dao.find_one_by_id(user_id)['global_model_ids']:
            return None
        global_model_po = self.global_model_dao.find_one_by_id(global_model_id)
        global_model_po.update({
            "name": name if name else global_model_po.name,
            "version": version if version else global_model_po.version,
            "type": type if type else global_model_po.type,
            "status": status if status else global_model_po.status,
            "description": description if description else global_model_po.description,
        })
        return self.global_model_dao.replace_one_by_id(global_model_id, global_model_po)

    def find_one_global_models_by_id(self, session_id, global_model_id):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        global_model = self.global_model_dao.find_one_by_id(global_model_id)
        global_model = json_util.dumps(global_model)
        return global_model

    @check_session
    def find_all_global_models(self, session_id):
        global_model_pos = self.global_model_dao.find_many_by_criteria({})
        global_model_pos = json_util.dumps(global_model_pos)
        return global_model_pos

    def remove_global_model(self, session_id, global_model_id):
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        if global_model_id in self.user_dao.find_one_by_id(user_id)["global_model_ids"]:
            user_po = self.user_dao.find_one_by_id("user_id")
            user_po['global_model_ids'].pop(global_model_id)
            self.user_dao.replace_one_by_id(user_id, user_po)
            global_model_po = self.global_model_dao.find_one_by_id(global_model_id)
            local_model_ids = global_model_po['local_model_ids']
            for local_model_id in local_model_ids:
                self.local_model_dao.delete_one_by_id(local_model_id)
            self.global_model_dao.delete_one_by_id(global_model_id)
        else:
            return None

    def insert_subscribe_global_model(self, session_id, global_model_id,
                                      address, condition):
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        if not user_id:
            return None
        global_model = self.global_model_dao.find_one_by_id(global_model_id)

        return self.global_model_dao.replace_one_by_id(global_model_id, global_model)

    def remove_subscribe_global_model(self, session_id, global_model_id,
                                      address, condition):
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        if not user_id:
            return None
        global_model = self.global_model_dao.find_one_by_id(global_model_id)
        global_model['subscriptions'].remove({
            "user_id": user_id,
            "address": address,
            "condition": condition
        })
        return self.global_model_dao.replace_one_by_id(global_model_id, global_model)

    def insert_label_global_model(self, session_id, global_model_id, key, value):
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        if global_model_id not in self.user_dao.find_one_by_id(user_id):
            return None
        global_model = self.global_model_dao.find_one_by_id(global_model_id)
        global_model['labels'][key] = value
        return self.global_model_dao.replace_one_by_id(global_model_id, global_model)

    def remove_label_global_model(self, session_id, global_model_id, key):
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        if global_model_id not in self.user_dao.find_one_by_id(user_id):
            return None
        global_model = self.global_model_dao.find_one_by_id(global_model_id)
        global_model['labels'].pop(key)
        return self.global_model_dao.replace_one_by_id(global_model_id, global_model)

    def upload_global_model_file(self, session_id, global_model_id, file_upload):
        file = file_upload.file
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        if not user_id:
            return None
        file_id = self.global_model_dao.insert_file_one(file)
        global_model = self.global_model_dao.find_one_by_id(global_model_id)
        global_model['file_id'] = file_id
        self.global_model_dao.replace_one_by_id(global_model_id, global_model)
        return file_id

    def download_global_model_file(self, session_id, global_model_id):
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        if not user_id:
            return None
        file_id = self.global_model_dao.find_one_by_id(global_model_id)['file_id']
        return self.global_model_dao.find_file_one_by_id(file_id)
