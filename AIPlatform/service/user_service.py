import time
from dao import GlobalModelDAO, LocalModelDAO, SessionDAO, UserDAO
from dao.po_creator import po_creator


def check_session(func):
    def wrapper(self, session_id, *args, **kwargs):
        user_id = self.session_dao.find_one_by_id(session_id).get('user_id')
        if not user_id:
            return None
        return func(self, session_id, *args, **kwargs)

    return wrapper


class UserService:
    def __init__(self):
        self.global_model_dao = GlobalModelDAO()
        self.local_model_dao = LocalModelDAO()
        self.session_dao = SessionDAO()
        self.user_dao = UserDAO()

    def create_user(self, email, password, name="default"):
        user_po = po_creator.create_user_po()
        user_po.pop("_id")
        user_po.update({
            'email': email,
            'password': password,
            'name': name
        })
        return self.user_dao.insert_one(user_po)

    @check_session
    def remove_user(self, session_id, user_id):
        return self.user_dao.delete_one_by_id(user_id)

    def update_user(self, session_id, user_id, email=None, password=None, name=None):
        user_po = self.user_dao.find_one_by_id(user_id)
        user_po.update({
            'email': email if email else user_po['email'],
            'password': email if password else user_po['password'],
            'name': email if name else user_po['name'],
        })
        return self.user_dao.replace_one_by_id(user_id, user_po)

    def find_one_user_by_id(self, session_id):
        user_id = self.session_dao.find_one_by_id(session_id)['user_id']
        return self.user_dao.find_one_by_id(user_id)

    def create_session(self, user_id, password):
        user_po = self.user_dao.find_one_by_id(user_id)
        if not user_po['password'] == password:
            return None
        session_po = po_creator.create_session_po()
        session_po.pop('_id')
        session_po.update({
            'user_id': user_id,
            'created_time': time.time()
        })
        inserted_id = self.session_dao.inert_one(session_po)
        return inserted_id

    def find_one_session_by_id(self, session_id):
        return self.session_dao.find_one_by_id(session_id)
