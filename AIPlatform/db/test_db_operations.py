import unittest
import db.db_operations as ops


class TestDBOperations(unittest.TestCase):
    def test_db_initial(self):
        ops.db_initial()
