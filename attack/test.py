import numpy as np

def generate(num,add):
    num+=add
    return num

if __name__ == '__main__':
    num=[[1,2],[3,4]]
    num=np.array(num)
    print(num)
    print(generate(num,5))
    print(num)
