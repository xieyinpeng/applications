import tensorflow as tf
import numpy as np

class Myencoder:
    def init(self,in_length):
        code_length=int(in_length*0.8)
        sess=tf.Session()
        learning_rate=0.01
        data_holder=tf.placeholder(tf.float32,[None,in_length])
        e1=self.add_layer(data_holder,in_length,in_length,tf.nn.relu)
        e2=self.add_layer(e1,in_length,code_length,tf.nn.relu)
        d1=self.add_layer(e2,code_length,in_length,tf.nn.relu)
        d2=self.add_layer(d1,in_length,in_length,tf.nn.relu)
        loss = tf.reduce_mean(tf.pow(d2 - data_holder, 2))
        optimizer=tf.train.AdadeltaOptimizer(learning_rate).minimize(loss)
        init = tf.global_variables_initializer()
        sess.run(init)

        self.e2=e2
        self.loss=loss
        self.optimizer=optimizer
        self.data_holder=data_holder
        self.sess=sess
        pass

    def add_layer(self,inputs,in_length,out_length,activation_function):
        weights = tf.Variable(tf.random_normal([in_length, out_length]))
        biases = tf.Variable(tf.zeros([1, out_length]) + 0.1)
        wx_plus_b = tf.matmul(inputs, weights) + biases
        outputs = activation_function(wx_plus_b)
        return outputs

    def fit(self,data):
        in_size=data.shape[0]
        in_length=data.shape[1]
        batch_size=4
        batch_count=0
        stop_count=0
        stop_count_limit=10
        self.init(in_length)
        sess=self.sess
        #print("what fuck!!!")
        stop_place=sess.run(self.loss,feed_dict={self.data_holder:data})
        while True:
            #break
            start=np.random.randint(0,in_size-1)
            end=np.min([start+batch_size,in_size-1])
            data_train=data[start:end]
            sess.run(self.optimizer,feed_dict={self.data_holder:data_train})
            loss_new=sess.run(self.loss,feed_dict={self.data_holder:data})
            if loss_new<stop_place:
                stop_place=loss_new
                #print(stop_place)
            else:
                stop_count+=1
                if stop_count>stop_count_limit:
                    break

    def encode(self,data):
        return self.sess.run(self.e2,feed_dict={self.data_holder:data})
