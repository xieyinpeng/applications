import threading

import bottle
from bottle import route, run, template, get, post, put, delete, request, Bottle
from service.global_model_service import GlobalModelService
from service.local_model_service import LocalModelService
from service.user_service import UserService

bottle.BaseRequest.MEMFILE_MAX = 1024 * 1024 * 1024
app = Bottle()


@app.post('/global_models')
def global_models_post():
    """
    submit one or many globals models
    :return:a status message
    """
    global_model_service = GlobalModelService()
    content = request.json
    subscriptions = content['subscriptions']
    labels = content['labels']
    global_model_id = global_model_service.create_global_model(
        session_id=request.params['session_id'],
        name=content['name'],
        version=content['version'],
        type=content['type'],
        status=content['status'],
        description=content['description']
    )
    for subscription in subscriptions:
        global_model_service.insert_subscribe_global_model(
            session_id=request.params['session_id'],
            global_model_id=global_model_id,
            address=subscription['address'],
            condition=subscription['condition']
        )
    for key in labels:
        global_model_service.insert_label_global_model(
            session_id=request.params['session_id'],
            global_model_id=global_model_id,
            key=key,
            value=labels[key]
        )
    return str(global_model_id)


@app.get('/global_models')
def global_models_get():
    """
    find many global models by criteria
    :return: a json array of models
    """
    global_model_service = GlobalModelService()
    content = request.params
    return {
        "documents": global_model_service.find_all_global_models(
            session_id=content['session_id']
        )
    }


@app.get('/global_models/<global_model_id>')
def global_model_get(global_model_id):
    """
    find one global model by id
    :return: a status message
    """
    global_model_service = GlobalModelService()
    content = request.params
    return global_model_service.find_one_global_models_by_id(
        session_id=content['session_id'],
        global_model_id=global_model_id
    )


@app.post('/global_models/files')
def global_models_files_post():
    """
    submit many global models files
    :return: a status message
    """
    global_model_service = GlobalModelService()
    content = request.forms
    return str(global_model_service.upload_global_model_file(
        session_id=content['session_id'],
        global_model_id=content['global_model_id'],
        file_upload=request.files['file'].file
    ))


@app.get('/global_models/files/<global_model_id>')
def global_model_file_get(global_model_id):
    global_model_service = GlobalModelService()
    content = request.params
    file = global_model_service.download_global_model_file(
        session_id=content['session_id'],
        global_model_id=global_model_id
    )
    return file


@app.post('/local_models')
def local_models_post():
    """
    submit one or many locals models
    :return:a status message
    """
    local_model_service = LocalModelService()
    content = request.forms
    local_model_id = local_model_service.create_local_model(
        session_id=content['session_id'],
        global_model_id=content['global_model_id'],
        message=content['message']
    )
    return str(local_model_id)


@app.post('/local_models/files')
def local_models_files_post():
    """
    submit many local models files
    :return: a status message
    """
    local_model_service = LocalModelService()
    content = request.forms
    return str(local_model_service.upload_local_model_file(
        session_id=content['session_id'],
        local_model_id=content['local_model_id'],
        file_upload=request.files['file']
    ))


@app.get('/local_models/files/<local_model_id>')
def local_model_file_get(local_model_id):
    """
    fetch one file
    :return:a file
    """
    local_model_service = LocalModelService()
    content = request.params
    return local_model_service.download_local_model_file(
        session_id=content['session_id'],
        local_model_id=local_model_id
    )


@app.post('/users')
def users_post():
    """
    register one or many user info
    :return: a status message
    """
    user_service = UserService()
    content = request.forms
    user_id = user_service.create_user(
        email=content['email'],
        password=content['password'],
        name=content['name']
    )
    print('created user ', user_id)
    return str(user_id)


@app.post('/sessions')
def sessions_post():
    """
    apply a session
    :return:a session id
    """
    user_service = UserService()
    content = request.forms
    return str(user_service.create_session(
        user_id=content['user_id'],
        password=content['password']
    ))


#############################3
@app.get('/')
def introduce():
    """
    make a short introduction
    :return: html
    """
    return "<a href='https://documenter.getpostman.com/view/7356810/Szt8c9Vz?version=latest'>see document</a>"


@app.put('/globals_models/<global_model_id>')
def global_model_put(global_model_id):
    """
    replace one global model by id
    :return: a status message
    """
    global_model_service = GlobalModelService()
    content = request.forms
    return global_model_service.update_global_model(
        session_id=request.params['session_id'],
        global_model_id=global_model_id,
        name=content['name'],
        version=content['version'],
        type=content['type'],
        status=content['status'],
        description=content['description']
    )


@app.delete('/global_models/<global_model_id>')
def global_model_delete(global_model_id):
    """
    delete one global model by id
    :return: a status message
    """
    global_model_service = GlobalModelService()
    content = request.forms
    return global_model_service.remove_global_model(
        session_id=content['session_id'],
        global_model_id=global_model_id
    )


@app.get('/local_models')
def local_models_get():
    """
    find many local models by criteria
    :return: a json array of models
    """
    local_model_service = LocalModelService()
    content = request.params
    return local_model_service.find_all_local_models_by_global_model_id(
        session_id=content['session_id'],
        global_model_id=content['global_model_id']
    )


@app.get('/local_models/<local_model_id>')
def local_model_get(local_model_id):
    """
    find one local model by id
    :return: a status message
    """
    local_model_service = LocalModelService()
    content = request.params
    return local_model_service.find_one_local_model_by_id(
        session_id=content['session_id'],
        local_model_id=content['local_model_id']
    )


@app.delete('/local_models/<local_model_id>')
def local_model_delete(local_model_id):
    """
    delete one local model by id
    :return: a status message
    """
    local_model_service = LocalModelService()
    content = request.forms
    return local_model_service.remove_local_model(
        session_id=content['session_id'],
        local_model_id=content['local_model_id']
    )


@app.put('/users/<user_id>')
def user_put():
    """
    modify user info by id
    :return: a status message
    """
    user_service = UserService()
    content = request.forms
    return user_service.update_user(
        session_id=content['session_id'],
        user_id=content['user_id'],
        email=content['email'],
        password=content['email'],
        name=content['name']
    )


@app.get('/users/<user_id>')
def user_get(user_id):
    """
    find a user info by id
    :return: a json of user info
    """
    user_service = UserService()
    content = request.params
    return user_service.find_one_user_by_id(
        session_id=content['session_id']
    )


@app.delete('users/<user_id>')
def user_delete(user_id):
    """
    delete a user info by id
    :return: a status message
    """
    user_service = UserService()
    content = request.forms
    return user_service.remove_user(
        session_id=content['session_id'],
        user_id=content['user_id']
    )


@app.get('/sessions/<session_id>')
def session_get(session_id):
    """
    find a session by id
    :return:
    """
    user_service = UserService()
    content = request.params
    return user_service.find_one_session_by_id(
        session_id=content['session_id']
    )


class APIServerThread(threading.Thread):

    def __init__(self):
        threading.Thread.__init__(self)
        self.host = "localhost"
        self.port = "8080"

    def run(self):
        app.run(host=self.host, port=self.port)
