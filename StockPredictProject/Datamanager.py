import tushare as ts
import numpy as np

def get_data_history(code):
    return ts.get_hist_data(code=code)['close']

def get_data_windows(list,length_one):
    data=[]
    target=[]
    length_all=len(list)
    start=0
    while start+length_one<length_all:
        data.append(list[start:start+length_one-1])
        target.append(list[start+length_one-1])
        start+=1
    return np.matrix(data),np.array(target)

def normalize_list(list):
    list_new=[]
    for i in range(len(list)):
        if i==0:
            list_new.append(0)
            continue
        list_new.append((list[i]-list[i-1])/list[i-1])
    return list_new

def normalize_matrix(data):
    data_new=[]
    for m in range(data.shape[0]):
        temp=[]
        for n in range(data.shape[1]):
            if n==0:
                temp.append(0)
                continue
            temp.append((data[m,n]-data[m,n-1])/data[m,n-1])
        data_new.append(temp)
    return np.matrix(data_new)
