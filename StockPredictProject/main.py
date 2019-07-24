from Datamanager import get_data_windows
from Myregressor import Myregressor
from Myencoder import Myencoder
from Datamanager import *
from Mycluster import Mycluster
from sklearn.model_selection import train_test_split

if __name__=="__main__":
    print("begin!")
    in_length=30
    list=get_data_history("000858")
    list_norm=normalize_list(list)
    #print(list_norm)
    data_norm,target_norm=get_data_windows(list_norm,in_length+1)
    #print(data)
    print("data got!")
    encoder=Myencoder()
    #encoder.init(8)
    #print(data)
    #print(data_norm)
    encoder.fit(data_norm)
    data_code=encoder.encode(data_norm)
    #print(data_code)
    print("encoding finished!")

    data_train,data_test,target_train,target_test=train_test_split(data_code,target_norm,test_size=0.2,random_state=0)

    cluster=Mycluster()
    #print(data_train)
    cluster.fit(data_train)
    print("cluster finished!")

    success_count=0
    fail_count=0
    for i in range(data_test.shape[0]):
        center_indice,point_indices=cluster.predict([data_test[i]])

        regressor=Myregressor()
        regressor.fit(data_train[point_indices],target_train[point_indices])
        try:
            predict=regressor.predict([data_test[i]])
        except Exception as e:
            print(e)
            continue

        #print("data: "+str(data_test[i]))
        #print("target: "+str(target_test[i]))
        #print("predict: "+str(predict))
        if target_test[i]*predict>=0:
            success_count+=1
        else:
            fail_count+=1
    print(success_count)
    print(fail_count)
