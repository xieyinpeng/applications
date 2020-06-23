from bson import ObjectId

from dao import GlobalModelDAO, LocalModelDAO, SessionDAO, UserDAO
from dao.po_creator import po_creator


def check_session(func):
    def wrapper(self, session_id, *args, **kwargs):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        return func(self, session_id, *args, **kwargs)

    return wrapper


class LocalModelService:
    def __init__(self):
        self.global_model_dao = GlobalModelDAO()
        self.local_model_dao = LocalModelDAO()
        self.session_dao = SessionDAO()
        self.user_dao = UserDAO()

    def create_local_model(self, session_id, global_model_id, message="default"):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        local_model_po = po_creator.create_local_model_po()
        local_model_po.pop("_id")
        local_model_po.update({
            "message": message
        })
        local_model_id = self.local_model_dao.insert_one(local_model_po)
        global_model = self.global_model_dao.find_one_by_id(global_model_id)
        global_model['local_model_ids'].append(local_model_id)
        self.global_model_dao.replace_one_by_id(global_model_id, global_model)
        user_po = self.user_dao.find_one_by_id(user_id)
        user_po['local_model_ids'].append(local_model_id)
        self.user_dao.replace_one_by_id(user_id, user_po)
        return local_model_id

    def remove_local_model(self, session_id, local_model_id):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        result = self.local_model_dao.delete_one_by_id(local_model_id)
        global_model_po = self.global_model_dao.find_one_by_local_model_id(local_model_id)['local_model_ids']
        global_model_po.pop(
            local_model_id)
        self.global_model_dao.replace_one_by_id(global_model_po['_id'], global_model_po)
        user_po = self.user_dao.find_one_by_id(user_id)
        user_po['local_model_ids'].pop(local_model_id)
        self.user_dao.replace_one_by_id(user_id, user_po)
        return result

    @check_session
    def find_one_local_model_by_id(self, session_id, local_model_id):
        return self.local_model_dao.find_one_by_id(local_model_id)

    @check_session
    def find_all_local_models_by_global_model_id(self, session_id, global_model_id):
        local_model_ids = self.global_model_dao.find_one_by_id(global_model_id)['local_model_ids']
        return self.local_model_dao.find_many_by_criteria({"_id": {"$in": local_model_ids}})

    @check_session
    def insert_label_local_model(self, session_id, local_model_id, key, value):
        local_model_po = self.local_model_dao.find_one_by_id(local_model_id)
        local_model_po['labels'][key] = value
        return self.local_model_dao.replace_one_by_id(local_model_id, local_model_po)

    @check_session
    def remove_label_local_model(self, session_id, local_model_id, key):
        local_model_po = self.local_model_dao.find_one_by_id(local_model_id)
        local_model_po['labels'].pop(key)
        return self.local_model_dao.replace_one_by_id(local_model_id, local_model_po)

    @check_session
    def upload_local_model_file(self, session_id, local_model_id, file_upload):
        file = file_upload.file
        file_id = self.local_model_dao.insert_file_one(file)
        local_model_po = self.local_model_dao.find_one_by_id(local_model_id)
        local_model_po['file_id'] = file_id
        self.local_model_dao.replace_one_by_id(local_model_id, local_model_po)
        return file_id

    @check_session
    def download_local_model_file(self, session_id, local_model_id):
        file_id = self.local_model_dao.find_one_by_id(local_model_id)['file_id']
        return self.local_model_dao.find_file_one_by_id(file_id)
