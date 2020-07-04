#conding: utf-8
"""
模拟GBDT的构建过程
"""
#模拟数据
import numpy as np
from sklearn.tree import DecisionTreeRegressor
from sklearn.metrics import r2_score


np.random.seed(18)
x = np.random.randn(100,2)
y = np.random.randn(100,1)
y_true = y

#GBDT的创建
models = []

#迭代的次数
n = 10000
# 学习率
lr = 0.5
#目的：是models含有m个子模型
for i in range(n):
        #这里的y是上一个子模型的残差
        algo = DecisionTreeRegressor(max_depth=1)
        model = algo.fit(x,y)
        # 产生下一个子模型的输入值,随着迭代次数的增加y——>0
        y = y - algo.predict(x).reshape(y.shape)*lr
        # 将该子模型保存
        models.append(model)
print("GBDT迭代次数为：",n,end="      ")
print("模型构建完成！")
print("="*100)
print("开始预测：")
# 模型的预测，将所有的模型回溯一遍，用第m，m-1,m-2,,,,,1个模型预测，将所有的预测值加起来
# 最后一个模型的预测值近似为0
y_hat=np.zeros_like(y)
for i in range(n):
    y_hat += models[i].predict(x).reshape(y.shape)*lr
# print("实际值为：{}".format(y_true.reshape(-1)))
print("GBDT的R2值：{}".format(r2_score(y_true=y_true,y_pred=y_hat)))
# print("GBDT的预测值为：",y_hat.reshape(-1))


#样本数据的预测
print("="*100)
print("单个数据的预测：")
x1 =x[0].reshape(1,2)
print("样本数据为：",x1)
print("实际值为：",y_true[0])
y_hat=np.zeros_like(y_true[0])
for i in range(n):
    y_hat += models[i].predict(x1)*lr
print("预测值为：",y_hat)