from bson import ObjectId
from pymongo import MongoClient


def check_id(func):
    def wrapper(self, id_, *args, **kwargs):
        if isinstance(id_, str):
            id_ = ObjectId(id_)
        return func(self, id_, *args, **kwargs)

    return wrapper


class UserDAO:
    def __init__(self):
        self._db = MongoClient()["ai_cloud_platform"]

    def insert_one(self, po):
        inserted_id = self._db['user'].insert_one(po).inserted_id
        return inserted_id

    @check_id
    def delete_one_by_id(self, user_id):
        result = self._db['user'].delete_one({"_id": user_id}).raw_result
        return result

    def delete_one_by_email(self, email):
        result = self._db['user'].delete_one({"email": email}).raw_result
        return result

    def delete_many_by_criteria(self, criteria):
        result = self._db['user'].delete_many(criteria).raw_result
        return result

    @check_id
    def replace_one_by_id(self, user_id, po):
        result = self._db['user'].replace_one({"_id": user_id}, po).raw_result
        return result

    @check_id
    def find_one_by_id(self, user_id):
        document = self._db["user"].find_one({"_id": user_id})
        return document

    def find_one_by_email(self, email):
        document = self._db["user"].find_one({"email": email})
        return document

    def find_many_by_name(self, name):
        cursor = self._db["user"].find({"name": name})
        documents = list(cursor)
        return documents

    @check_id
    def find_one_by_global_model_id(self, global_model_id):
        cursor = self._db["user"].find_one({"globalModelIds": global_model_id})
        documents = list(cursor)
        return documents

    @check_id
    def find_one_by_local_model_id(self, local_model_id):
        document = self._db["user"].find_one({"localModelIds": local_model_id})
        return document

    def find_many_by_criteria(self, criteria):
        cursor = self._db["user"].find(criteria)
        documents = list(cursor)
        return documents
