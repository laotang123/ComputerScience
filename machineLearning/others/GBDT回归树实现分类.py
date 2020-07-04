# coding: utf-8
import numpy as np
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
from sklearn.tree import DecisionTreeRegressor

import jieba

np.random.seed(18)
x = np.random.randn(10,2)*5
y1 = np.array([0] * 6 + [1] * 4)
y2 = np.array([1] * 6 + [0] * 4)
# 二分类的真实类别
y_true = y2
# 哑编码之后对应的y标签
y_label = [y1,y2]

# 单个分类树的分类效果
one_tree = DecisionTreeClassifier(max_depth=1)
one_tree.fit(x,y_true)
one_tree_pre = one_tree.predict(x)

print("单个决策树的分类效果：{}".format(accuracy_score(y_true,one_tree_pre)))

# GBDT分类树的构建过程
models = []
algo = DecisionTreeClassifier(max_depth=1)# 基模型为分类决策树
# 模型迭代次数n
n = 2

for i in  range(n):
    k_model = []
    for k in range(2):
        # 定义下一次迭代所用的决策树
        model = DecisionTreeRegressor()

        model.fit(x,y_label[k])

        # 预测结果并将其进行指数转换为概率的形式
        y_pre = model.predict(x)
        dy = np.exp(y_pre)/np.sum(np.exp(y_label),axis=0)
        # 更新对应类别的y标签为概率的残差值d
        y_label[k] = y_label[k]-dy

        # 异常将每一个类别构建好的决策树添加到列表中
        k_model.append(model)

    # 将每一次迭代的k个模型列表添加到最终的融合模型中
    models.append(k_model)
print("模型构建完成！")
print("开始预测：")


y_hat = np.zeros_like(y_label)
print(y_hat)
for k_model in models:
    # 遍历模型中的类别模型列表
    for k in range(2):
        # 遍历类别模型列表中的每一个模型，使该模型对相应的类别做出预测值
        k_model[k].fit(x,y_label[k])
        y_pre = k_model[k].predict(x)
        y_hat[k] += y_pre

# 预测值中含有负值是因为使用的概率的残差作为预测值，残差是存在负数的，预测值为对应哑编码中最大的值
print(np.argmax(np.array(y_hat),axis=0))
print("GBDT得分类效果：{}".format(accuracy_score(np.argmax(np.array(y_hat),axis=0),y_true)))




