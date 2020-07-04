#coding: utf-8
"""
线性回归实现波斯顿房屋租赁预测
"""
import warnings
import numpy as np
import pandas as pd
import matplotlib as mpl
import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LinearRegression
from sklearn.linear_model.coordinate_descent import ConvergenceWarning
from sklearn.preprocessing import PolynomialFeatures

# 一 数据加载
path = "datas/boston_housing.data"
names = ['CRIM','ZN', 'INDUS','CHAS','NOX','RM','AGE','DIS','RAD','TAX','PTRATIO','B','LSTAT']
df = pd.read_csv(path,header = None)
print(df.head(3))

def notEmpty(s):
    return s != ''
# #数据中间是数目不定的空格隔开，数据很难读取
data = np.empty((len(df), 14))
for i, d in enumerate(df.values):  # enumerate生成一列索 引i,d为其元素
    d = map(float, filter(notEmpty, d[0].split(' ')))  # filter一个函数，一个list

    # 根据函数结果是否为真，来过滤list中的项。
    data[i] = list(d)
print(pd.DataFrame(data,columns=names))