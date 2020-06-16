from bson import ObjectId
from pymongo import MongoClient


def check_id(func):
    def wrapper(self, id_, *args, **kwargs):
        if isinstance(id_, str):
            id_ = ObjectId(id_)
        return func(self, id_, *args, **kwargs)

    return wrapper


class SessionDAO:
    def __init__(self):
        self._db = MongoClient()["ai_cloud_platform"]

    def inert_one(self, po):
        inserted_id = self._db['session'].insert_one(po).inserted_id
        return inserted_id

    @check_id
    def delete_one_by_id(self, session_id):
        result = self._db['session'].delete_one({"_id": session_id}).raw_result
        return result

    @check_id
    def delete_many_by_user_id(self, user_id):
        result = self._db['session'].delete_many({'user_id': user_id}).raw_result
        return result

    def delete_many_by_criteria(self, criteria):
        result = self._db['session'].delete_many(criteria).raw_result
        return result

    @check_id
    def replace_one_by_id(self, session_id, po):
        result = self._db['session'].replace_one({"session_id": session_id}, po).raw_result
        return result

    @check_id
    def find_one_by_id(self, session_id):
        document = self._db['session'].find_one({"_id": session_id})
        return document

    @check_id
    def find_many_by_user_id(self, user_id):
        cursor = self._db['session'].find({"user_id": user_id})
        documents = list(cursor)
        return documents

    def find_many_by_criteria(self, criteria):
        cursor = self._db['session'].find(criteria)
        documents = list(cursor)
        return documents
