#coding: utf-8
#导包
import numpy as np
import pandas as pd
import matplotlib as mpl
import matplotlib.pyplot as plt

# 一 加载数据
path = "datas/household_power_consumption_1000.txt"
df01 = pd.read_csv(path,sep=";",low_memory=False)
# print(df01.info())
# print(df01.head(5))

# 二 数据的清洗
df01.replace("?",np.nan,inplace=True)
data = df01.dropna(how="any",axis=0)

# 三 特征属性和目标属性的提取
X = data.iloc[:,2:4]
Y = data.iloc[:,5]
# print(X.shape)
# print(X.info())
# print(Y.shape)

# 四 数据的划分,将数据划分为训练集800条，测试集200条
n = int(len(X)*0.8)
x_train = X[0:n]
x_test = X[n:len(X)]
y_train = Y[0:n]
y_test = Y[n:len(X)]


#步长设置
step = 0.1
theta0 = 0
theta1 = 0
theta = np.mat([[theta0],[theta1]])
def h0(n,theta):
    hy = 0
    for i in range(n):
        x = np.mat([x_train.iloc[i]])
        hy += (y_train[i]-x*theta)*x_train.iloc[i,0]/n
    return hy
def h1(n,theta):
    hy = 0
    for i in range(n):
        x = np.mat([x_train.iloc[i]])
        hy += (y_train[i]-x*theta)*x_train.iloc[i,1]/n
    return hy
tol = 1e-4
step_change = 1
max_iter = 1000
n_iter = 1
while step_change>tol and n_iter < max_iter:
    thetatemp = theta0
    theta0 = theta0 + step*h0(len(x_train),theta)
    theta1 = theta1 + step*h1(len(x_train),theta)
    theta0 = float(theta0)
    theta1 = float(theta1)
    theta = np.mat([[theta0], [theta1]])
    step_change = np.fabs(thetatemp-theta0)
    n_iter+=1
    print([theta0, theta1])
theta = np.mat([[theta0], [theta1]])
print("最优参数θ：",theta)
print("最终的迭代次数为：",n_iter)
#使用训练好的模型进行测试集预测,将测试集中的数据转为mat，方便做矩阵的乘法
x_test = np.mat(np.array(x_test))
y_test = np.mat(np.array(y_test).reshape(-1,1))
y_predict = x_test*theta

#计算测试集中预测值和真实值的误差
error = np.array(y_predict-y_test)

#计算均方差，先平方再求和再均值
MSE = np.sum(np.power(error,2))/len(x_train)

#计算测试集的均值
ymean = np.mean(y_test)

#计算数据的RSS
RSS = np.sum(np.power(error,2)/len(x_train))

#计算测试集的TSS
TSS = np.sum(np.power((y_test-ymean),2))/len(x_train)

#计算测试集的R2
R2 = 1-RSS/TSS
print("模型的MSE值：{}".format(MSE))
print("模型的R2值：{}".format(R2))
