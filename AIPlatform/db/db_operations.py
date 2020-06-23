from os import system

mongod_dir = 'C:\Program Files\MongoDB\Server\4.2\bin'
mongod_name = '\mongod'
command = mongod_dir + mongod_name


def db_initial():
    system(command)
