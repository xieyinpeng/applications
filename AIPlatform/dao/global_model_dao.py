from pymongo import MongoClient
from bson import objectid, ObjectId
from gridfs import GridFS
import gridfs


def check_id(func):
    def wrapper(self, id_, *args, **kwargs):
        if isinstance(id_, str):
            id_ = ObjectId(id_)
        return func(self, id_, *args, **kwargs)

    return wrapper


class GlobalModelDAO:
    def __init__(self):
        self._db = MongoClient()["ai_cloud_platform"]
        self._fs = GridFS(MongoClient()["ai_cloud_platform_fs"])

    def insert_one(self, po):
        inserted_id = self._db["global_models"].insert_one(po).inserted_id
        return inserted_id

    def insert_file_one(self, data):
        inserted_id = self._fs.put(data)
        return inserted_id

    @check_id
    def delete_one_by_id(self, global_model_id):
        result = self._db["global_models"].delete_one({"_id": global_model_id}).raw_result
        return result

    def delete_many_by_name(self, name):
        result = self._db["global_models"].delete_many({"name": name}).raw_result
        return result

    def delete_one_by_name_and_version(self, name, version):
        result = self._db["global_models"].delete_one({"name": name, "version": version}).raw_result
        return result

    def delete_many_by_labels(self, labels):
        labels = {"labels." + key: labels[key] for key in labels}
        result = self._db["global_models"].delete_many(labels).raw_result
        return result

    def delete_many_by_criteria(self, criteria):
        result = self._db["global_models"].delete_many(criteria).ras_result
        return result

    @check_id
    def delete_file_one_by_id(self, file_id):
        self._fs.delete(file_id)
        return {'ok': 1.0}

    @check_id
    def replace_one_by_id(self, global_model_id, po):
        result = self._db["global_models"].replace_one({"_id": global_model_id}, po).raw_result
        return result

    @check_id
    def find_one_by_id(self, global_model_id):
        document = self._db["global_models"].find_one({"_id": global_model_id})
        return document

    def find_many_by_name(self, name):
        cursor = self._db["global_models"].find({"name": name})
        documents = list(cursor)
        return documents

    def find_one_by_name_and_version(self, name, version):
        document = self._db["global_models"].find_one({"name": name, "version": version})
        return document

    def find_many_by_labels(self, labels):
        labels = {"labels." + key: labels[key] for key in labels}
        cursor = self._db["global_models"].find(labels)
        documents = list(cursor)
        return documents

    def find_one_by_local_model_id(self, global_model_id):
        document = self._db["local_models"].find_one({"_id": global_model_id})
        return document

    def find_many_by_criteria(self, criteria):
        cursor = self._db["global_models"].find(criteria)
        documents = list(cursor)
        return documents

    def find_many_by_type(self, type_):
        cursor = self._db["global_models"].find({"type": type})
        documents = list(cursor)
        return documents

    def find_many_by_subscription_user_id(self, user_id):
        cursor = self._db["global_models"].find({"subscriptions.userId": user_id})
        documents = list(cursor)
        return documents

    @check_id
    def find_file_one_by_id(self, file_id):
        out_stream = self._fs.get(file_id)
        return out_stream

    def find_file_many_by_id(self, file_ids):
        if isinstance(file_ids, list):
            file_ids = ([file_ids])
        for index in range(0, len(file_ids)):
            if isinstance(file_ids[index], str):
                file_ids[index] = ObjectId(file_ids[index])
        out_streams = [self._fs.get(file_ids[index]) for index in file_ids]
        return out_streams

    def find_file_many_by_criteria(self, criteria):
        cursor = self._db["global_models"].find(criteria, "file_id:1")
        file_ids = list(cursor)
        out_streams = [self._fs.get(file_id) for file_id in file_ids]
        return out_streams
