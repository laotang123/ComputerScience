#coding: utf-8

import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt

# 设置字符集，防止中文乱码
mpl.rcParams['font.sans-serif'] = [u'simHei']
mpl.rcParams['axes.unicode_minus'] = False

#随机数的产生，b为均值-1，方差1，c为均值0，方差1的数据
n = 10000
b_values = np.random.normal(loc=-1,scale=1,size=n)
c_values = np.random.normal(loc=0,scale=1,size = n)
# print("数据的样本为: {},b的均值为: {}".format(b_values,np.mean(b_values)))
print("b的均值为： {}".format(np.mean(b_values)))
# print("数据的样本为: {},c的均值为: {}".format(c_values,np.mean(c_values)))
print("c的均值为： {}".format(np.mean(c_values)))
#当样本为1的时候
def with_one_samples(b,c,step=0.3,n=1):
    """
    当样本数目为1时
    :param b: 样本特征属性b
    :param c: 截距项c
    :param n: 样本的数量
    :param step: 梯度下降的学习率
    :return:
    """
    def f(x,b,c):
        # 原始函数
        return x ** 2 + b * x + c

    def h(x,b):
        # 原始函数对应的导函数
        return 2 * x + b
    #x,y函数的取值
    x = np.arange(-10,10,0.10)
    y = f(x,b,c)

    #梯度下降x的初始值
    x_curret = x[-1]

    #函数值y的下降程度
    step_change = y[-1]

    theta = step
    #随机梯度下降
    for i in range(n):
        y_temp = f(x_curret, b, c)
        x_curret = x_curret-theta*h(x_curret,b)
        y_current = f(x_curret, b, c)
        step_change = np.fabs(y_current-y_temp)

    print("样本数量为: {},最终的变化率为:{}".format(n,step_change))
    print("最终的结果为:{}---->{}".format(x_curret, y_current))


#当样本为2的时候
def with_two_samples(b,c,step=0.3,n=2):
    """
    当样本数目为1时
    :param b: 样本特征属性b
    :param c: 截距项c
    :param n: 样本的数量
    :param step: 梯度下降的学习率
    :return:
    """
    def f(x,b,c):
        # 原始函数
        return x ** 2 + b * x + c

    def h(x,b):
        # 原始函数对应的导函数
        return 2 * x + b
    #x,y函数的取值
    x = np.arange(-10,10,0.1)
    for i in range(n):
        y1 = f(x,b[0],c[0])
        y2 = f(x,b[1],c[1])
        y = y1+y2
        # y = f(x,b[i],c[i])/n

    #梯度下降x的初始值
    x_curret = x[-1]

    #函数值y的下降程度
    step_change = y[-1]

    theta = step
    #随机梯度下降
    for i in range(n):
        y_temp = f(x_curret, b[i], c[i])
        x_curret = x_curret-theta*h(x_curret,b[i])
        y_current = f(x_curret, b[i], c[i])
        step_change = np.fabs(y_current-y_temp)

    print("样本数量为: {},最终的变化率为:{}".format(n,step_change))
    print("最终的结果为:{}---->{}".format(x_curret, y_current))


#当样本为10的时候
def with_ten_samples(b,c,step=0.3,n=10):
    """
    当样本数目为1时
    :param b: 样本特征属性b
    :param c: 截距项c
    :param n: 样本的数量
    :param step: 梯度下降的学习率
    :return:
    """
    def f(x,b,c):
        # 原始函数
        return x ** 2 + b * x + c

    def h(x,b):
        # 原始函数对应的导函数
        return 2 * x + b
    #x,y函数的取值
    x = np.arange(-10,10,0.1)
    for i in range(n):
        y1 = f(x,b[0],c[0])
        y2 = f(x,b[1],c[1])
        y3 = f(x, b[2], c[2])
        y4 = f(x, b[3], c[3])
        y5 = f(x, b[4], c[4])
        y6 = f(x, b[5], c[5])
        y7 = f(x, b[6], c[6])
        y8 = f(x, b[7], c[7])
        y9 = f(x, b[8], c[8])
        y10 = f(x, b[9], c[9])
        y = y1+y2+y3+y4+y5+y6+y7+y8+y9+y10
        # y = f(x,b[i],c[i])/n

    #梯度下降x的初始值
    x_curret = x[-1]

    #函数值y的下降程度
    step_change = y[-1]

    theta = step
    #随机梯度下降
    for i in range(n):
        y_temp = f(x_curret, b[i], c[i])
        x_curret = x_curret-theta*h(x_curret,b[i])
        y_current = f(x_curret, b[i], c[i])
        step_change = np.fabs(y_current-y_temp)

    print("样本数量为: {},最终的变化率为:{}".format(n,step_change))
    print("最终的结果为:{}---->{}".format(x_curret, y_current))

#当样本为n的时候
def with_n_samples(b,c,step=0.001,n=n):
    """
    当样本数目为1时
    :param b: 样本特征属性b
    :param c: 截距项c
    :param n: 样本的数量
    :param step: 梯度下降的学习率
    :return:
    """
    def f(x,b,c):
        # 原始函数
        return x ** 2 + b * x + c

    def h(x,b):
        # 原始函数对应的导函数
        return 2 * x + b
    #x,y函数的取值
    x = np.arange(-10,10,0.1)
    y=0
    for i in range(n):
        y += f(x,b[i],c[i])/n
    print(y)
    #梯度下降x的初始值
    gradx = []
    grady = []
    x_curret = x[-1]

    #函数值y的下降程度
    step_change = y[-1]

    theta = step
    #随机梯度下降
    for i in range(n):
        y_temp = f(x_curret, b[i], c[i])
        gradx.append(x_curret)
        grady.append(y_temp)
        x_curret = x_curret-theta*h(x_curret,b[i])
        y_current = f(x_curret, b[i], c[i])

        step_change = np.fabs(y_current-y_temp)
    print("样本数量为: {},最终的变化率为:{}".format(n,step_change))
    print("最终的结果为:{}---->{}".format(x_curret, y_current))

    # #数据可视化
    plt.plot(x,y,"r-",label = "目标函数")
    plt.plot(gradx, grady, "b-", label="梯度值")

    plt.legend(loc=2)
    plt.title("随机梯度下降")
    plt.show()
if __name__ == "__main__":
    #with_one_samples(b_values,c_values)
    #with_two_samples(b_values, c_values)
    #with_ten_samples(b_values,c_values)
    with_n_samples(b_values,c_values)