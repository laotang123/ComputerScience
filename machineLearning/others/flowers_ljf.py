# coding: utf-8
"""
create by ljf on 2018/11/18 20:55
"""
from tflearn.datasets import oxflower17
import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np


def learn_rate_func(base_lr, epoch):
    """
    动态学习率传入的参数，每个100次迭代调整学习率
    :param base_lr: 最初的学习率
    :param epoch: 迭代次数
    :return: 迭代次数对应的学习率
    """
    result = base_lr * 0.5 ** int(epoch / 100)
    return result


def get_variable(name, shape=None, dtype=tf.float32, initializer=tf.random_normal_initializer(mean=0.0, stddev=0.1)):
    """
    定义获取w,b随机初始值得函数封装，w值对应卷积的卷积窗口大小
    :param name: 变量的命名值
    :param shape: 变量的形状
    :param dtype: 变量的数据类型
    :param initializer: 变量的初始化
    :return: tensor的初始化对象
    """
    return tf.get_variable(name, shape, dtype, initializer)


# 一 加载数据
X, Y = oxflower17.load_data(dirname="17flowers", one_hot=True)
print(X.shape)  # (1360,224, 224, 3)
print(Y.shape)  # (1360,17)

# 二 构建网络

# 1.超参数
# 2.变量类型w,b,占位符
x_input = tf.placeholder(shape=[None, 224, 224, 3], dtype=tf.float32)
y_out = tf.placeholder(shape=[None, 17], dtype=tf.float32)
learn_rate = tf.placeholder(dtype=tf.float32)


# 3.VGG网络
def VGG():
    # 第一层卷积层(224,224,64)
    with tf.variable_scope("conv1"):
        # 卷积
        net = tf.nn.conv2d(x_input, filter=get_variable("w", shape=[3, 3, 3, 64]), strides=[1, 1, 1, 1], padding="SAME")
        # 偏置项b
        net = tf.nn.bias_add(net, bias=get_variable("b", shape=[64]))
        # 激活函数
        net = tf.nn.relu(net)
        print("第一层卷积激活后的形状：{}".format(net.shape))
    """第二层池化层(112,112,64)
    """
    with tf.variable_scope("max_pool2"):
        net = tf.nn.max_pool(value=net, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding="SAME")
        print("第二层池化的形状：{}".format(net.shape))
    """
    第三层卷积层(112,112,128)
    """
    with tf.variable_scope("conv3"):
        # 卷积
        net = tf.nn.conv2d(net, filter=get_variable("w", shape=[3, 3, 64, 128]), strides=[1, 1, 1, 1],
                           padding="SAME")
        # 偏置项b
        net = tf.nn.bias_add(net, bias=get_variable("b", shape=[128]))
        # 激活函数
        net = tf.nn.relu(net)
        print("第三层卷积激活后的形状：{}".format(net.shape))
    """第四层池化层(56,56,128)
    """
    with tf.variable_scope("max_pool4"):
        net = tf.nn.max_pool(value=net, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding="SAME")
        print("第四层池化的形状：{}".format(net.shape))
    """
       第五层卷积层(56,56,256)
       """
    with tf.variable_scope("conv5"):
        # 卷积
        net = tf.nn.conv2d(net, filter=get_variable("w", shape=[3, 3, 128, 256]), strides=[1, 1, 1, 1],
                           padding="SAME")
        # 偏置项b
        net = tf.nn.bias_add(net, bias=get_variable("b", shape=[256]))
        # 激活函数
        net = tf.nn.relu(net)
        print("第五层卷积激活后的形状：{}".format(net.shape))
    """第六层池化层(28,28,256)
    """
    with tf.variable_scope("max_pool6"):
        net = tf.nn.max_pool(value=net, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding="SAME")
        print("第六层池化的形状：{}".format(net.shape))
    """
         第七层卷积层(28,28,512)
    """
    with tf.variable_scope("conv7"):
        # 卷积
        net = tf.nn.conv2d(net, filter=get_variable("w", shape=[3, 3, 256, 512]), strides=[1, 1, 1, 1],
                           padding="SAME")
        # 偏置项b
        net = tf.nn.bias_add(net, bias=get_variable("b", shape=[512]))
        # 激活函数
        net = tf.nn.relu(net)
        print("第七层卷积激活后的形状：{}".format(net.shape))
    """第八层池化层(14,14,512)
    """
    with tf.variable_scope("max_pool8"):
        net = tf.nn.max_pool(value=net, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding="SAME")
        print("第八层池化的形状：{}".format(net.shape))
    """
        第九层卷积层(14,14,512)
    """
    with tf.variable_scope("conv9"):
        # 卷积
        net = tf.nn.conv2d(net, filter=get_variable("w", shape=[3, 3, 512, 512]), strides=[1, 1, 1, 1],
                           padding="SAME")
        # 偏置项b
        net = tf.nn.bias_add(net, bias=get_variable("b", shape=[512]))
        # 激活函数
        net = tf.nn.relu(net)
        print("第七层卷积激活后的形状：{}".format(net.shape))
    """第十层池化层(7,7,512)
    """
    with tf.variable_scope("max_pool10"):
        net = tf.nn.max_pool(value=net, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding="SAME")
        print("第十层池化的形状：{}".format(net.shape))
    """
    第十一层全连接层
    """
    with tf.variable_scope("fc11"):
        fc_shape = net.shape[1] * net.shape[2] * net.shape[3]
        net = tf.reshape(net, shape=[-1, fc_shape])
        net = tf.add(tf.matmul(net, get_variable("fc_w1", shape=[fc_shape, 4096])), get_variable("fc_b1", shape=[4096]))
        net = tf.add(tf.matmul(net, get_variable("fc_w2", shape=[4096, 1000])), get_variable("fc_b2", shape=[1000]))
        print("第十一层全连接层的形状：{}".format(net.shape))
    """
    第十二层全连接层
    """
    with tf.variable_scope("fc12"):
        net = tf.add(tf.matmul(net, get_variable("fc_w3", shape=[1000, 17])), get_variable("fc_b3", shape=[17]))
        print("第十二层全连接层的形状：{}".format(net.shape))
    return net


# 4.定义损失函数，softmax交叉熵损失函数，优化器
y_pre = VGG()
cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=y_pre, labels=y_out))
# 优化器
optimizer = tf.train.AdamOptimizer(learning_rate=learn_rate).minimize(cost)

# 5.定义准确率
boolean = tf.equal(tf.argmax(y_out, axis=1), tf.argmax(y_pre, axis=1))
accuracy = tf.reduce_mean(tf.cast(boolean, tf.float32))

# 三 开启会话，迭代训练模型
# 模型参数
max_iter = 30  # 模型的最大迭代次数
display = 1  # 没迭代3次打印模型效果
batch_size = 16  # 批量传输图片数量
base_lr = 0.00001  # 动态学习率的初始学习率
total_epoch = X.shape[0] // batch_size  # 需要传输的照片次数
# acg_cost_list = []
# acg_accr_list = []
saver = tf.train.Saver()
with tf.Session() as sess:
    sess.run(tf.global_variables_initializer())
    iter = 0
    while True:
        avg_cost = 0.0
        avg_accr = 0.0

        for epoch in range(total_epoch):
            # 截取批量传输的照片数据
            train_img = X[epoch * batch_size:(epoch + 1) * batch_size]
            train_label = Y[epoch * batch_size:(epoch + 1) * batch_size]
            # 训练模型
            feed_dict = {x_input: train_img, y_out: train_label, learn_rate: learn_rate_func(base_lr, iter)}
            accr,loss, _ = sess.run([accuracy,cost, optimizer], feed_dict=feed_dict)
            # avg_cost += loss / total_epoch
            # avg_accr += accr/total_epoch
            if epoch % 5==0:
                avg_cost+=loss/17
                avg_accr += accr / 17
                print("迭代次数：{}-{}，loss：{},accuracy:{}".format(iter,epoch, loss, accr))

        if iter % display == 0:

            print("迭代次数：{}，loss：{},test-accuracy:{}".format(iter,avg_cost,avg_accr))
            # 使用全部数据进行测试时准确率高于0.95时进行模型保存
            if avg_accr>0.95:
                saver.save(sess,save_path="./model/17_flowers.model",global_step=iter*total_epoch,)
                break
        iter+=1