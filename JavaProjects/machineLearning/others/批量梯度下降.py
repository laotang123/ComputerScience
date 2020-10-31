#coding: utf-8
"""
参数的变化，b和c，衡量指标：学习步长theta，迭代次数n_iter，最终一步的y的差值，学习率sturate
"""
import time
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt
#一个参数b，c的时候,b为均值是-1，标准差为2，c为均值是0，标准差是1
n = 10000
b_values = np.random.normal(loc=-1,scale=2,size=n)
c_values = np.random.normal(loc=0,scale=1,size=n)
print("当前的样本数据为：")
print("b_values={}, b的均值为:{}".format(b_values, np.mean(b_values)))
print("c_values={}, c的均值为:{}".format(c_values, np.mean(c_values)))
#数据的可视化，直方图显示每个范围出现的频数分布
#设置中文字符显示
mpl.rcParams["font.sans-serif"] = ["SimHei"]
mpl.rcParams["axes.unicode_minus"] = False
# # 添加图布和子图
# plt.figure(facecolor="w")
# plt.subplot(121)
# plt.hist(b0,1000,color="red",label="均值-1，方差10")
# plt.legend(loc=2)
# plt.subplot(122)
# # 2 填充数据，绘制图像
#
# plt.hist(c0,1000,color="y",label="均值0，方差1")
# plt.legend(loc=2)
# plt.suptitle("随机数据可视化")
#
# #2 显示图像
# plt.show()


def cal_with_one_samples(b1,c1,max_iter=1000,min_step=1e-6,theta=0.01):

    # 定义函数表达式
    def f1(x):
        return x ** 2 + b1 * x + c1

    # 定义偏导数表达式
    def h1(x):
        return 2 * x + b1

    #设置学习步长和梯度下降的x初始值

    x = np.arange(-10,10,0.1)
    #取梯度下降的初始值x为10
    current_x = x[-1]
    temp_y = f1(current_x)
    current_y= f1(current_x-theta*h1(current_x))
    step_change = np.fabs(current_y-temp_y)
    n_iter = 0

    #当最终的学习率大于0.000001时并且迭代次数小于1000次时执行
    while step_change>min_step and n_iter<max_iter:
        temp_y = f1(current_x)
        current_x = current_x-theta*h1(current_x)
        current_y = f1(current_x)
        #每一次迭代现在的y值和迭代前的y值的绝对值，学习率
        step_change = np.fabs(current_y-temp_y)
        #每迭代一次，n_iter 进行自加1
        n_iter+=1

    print("最终的迭代次数为：{}".format(n_iter))
    print("b的值为{}，c的值为{}".format(b1,c1))
    print("最终的学习率为：{}".format(step_change))

#含有两个数据样本的时候
def cal_with_two_samples(b1,c1,b2,c2,max_iter=1000,min_step=1e-6,theta=0.01):

    # 定义函数表达式
    def f1(x):
        f1 =  x ** 2 + b1 * x + c1
        f2 = x ** 2 + b2 * x + c2
        return f1+f2
    # 定义偏导数表达式
    def h1(x):
        h1 = 2 * x + b1
        h2 = 2 * x + b2
        return h1 +h2

    #设置学习步长和梯度下降的x初始值

    x = np.arange(-10,10,0.1)
    #取梯度下降的初始值x为10
    current_x = x[-1]
    temp_y = f1(current_x)
    current_y= f1(current_x-theta*h1(current_x))
    step_change = np.fabs(current_y-temp_y)
    n_iter = 0

    #当最终的学习率大于0.000001时并且迭代次数小于1000次时执行
    while step_change>min_step and n_iter<max_iter:
        temp_y = f1(current_x)
        current_x = current_x-theta*h1(current_x)
        current_y = f1(current_x)
        #每一次迭代现在的y值和迭代前的y值的绝对值，学习率
        step_change = np.fabs(current_y-temp_y)
        #每迭代一次，n_iter 进行自加1
        n_iter+=1

    print("最终的迭代次数：{}，学习率：{}".format(n_iter,step_change))

    print("最终的结果为：{}——————>{}".format(current_x,current_y))

#含有十,n个数据样本的时候
def cal_with_ten_samples(b,c,max_iter=1000,min_step=1e-6,theta=0.01):

    #assert函数条件成立时程序继续执行，条件不成立时会报错
    assert len(b)==n and len(c) ==n
    # 定义函数表达式

    def f1(x):
        result = 0
        for i in range(n):
            f = x ** 2 + b[i] * x + c[i]
            result += f / n
        return result
    # 定义偏导数表达式
    def h1(x):
        result = 0
        for i in range(len(b)):
            f = x * 2 + b[i]
            result += f/n
        return result

    #设置学习步长和梯度下降的x初始值

    x = np.arange(-10,10,0.1)
    y = f1(x)
    #取梯度下降的初始值x为10
    current_x = x[-1]
    temp_y = f1(current_x)
    current_y= f1(current_x-theta*h1(current_x))
    step_change = np.fabs(current_y-temp_y)
    n_iter = 0
    gradx=[]
    grady = []
    #当最终的学习率大于0.000001时并且迭代次数小于1000次时执行
    while step_change>min_step and n_iter<max_iter:
        temp_y = f1(current_x)
        gradx.append(current_x)
        grady.append(temp_y)
        current_x = current_x-theta*h1(current_x)
        current_y = f1(current_x)
        #每一次迭代现在的y值和迭代前的y值的绝对值，学习率
        step_change = np.fabs(current_y-temp_y)
        #每迭代一次，n_iter 进行自加1
        n_iter+=1
        # #数据可视化
    plt.plot(x, y, "r-", label="目标函数")
    plt.plot(gradx, grady, "b-", label="梯度值")

    plt.legend(loc=2)
    plt.title("随机梯度下降")
    plt.show()

    print("最终的迭代次数：{}，学习率：{}".format(n_iter, step_change))
    print("最终的结果为：{}——————>{}".format(current_x, current_y))
if __name__ == "__main__":
    #cal_with_one_samples(b_values[0],c_values[0])
    #cal_with_two_samples(b_values[0], c_values[0],b_values[1], c_values[1])
    cal_with_ten_samples(b_values,c_values)
