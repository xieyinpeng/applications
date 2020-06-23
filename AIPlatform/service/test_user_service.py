import unittest
from service.user_service import UserService


class TestUserService(unittest.TestCase):
    def __init__(self, *args, **kwargs):
        unittest.TestCase.__init__(self, *args, **kwargs)
        self.user_service = UserService()

    def test_create_user(self):
        self.user_id = self.user_service.create_user(email='default', password='default', name='default')

    def test_create_session(self):
        self.session_id = self.user_service.create_session(user_id=self.user_id, password='default')

    def test_find_one_user_by_id(self):
        self.user_service.find_one_user_by_id(session_id=self.session_id)

    def test_find_one_session_by_id(self):
        self.user_service.find_one_session_by_id(session_id=self.session_id)

    def runTest(self):
        self.test_create_user()
        self.test_create_session()
        self.test_find_one_user_by_id()
        self.test_find_one_session_by_id()
