#coding: utf-8
"""
自我理解的解决观测值序列概率问题
pi为初始概率
A为隐状态转移矩阵
B为状态向观测值转移矩阵
"""
from numpy import matrix

import numpy as np

#预测序列为白黑白白黑
pre_list = [0,1,0,0,1]
pi = matrix((0.2,0.5,0.3))

A = matrix([
    [0.5,0.4,0.1],
    [0.2,0.2,0.6],
    [0.2,0.5,0.3]
])

B = matrix([[0.4,0.6],[0.8,0.2],[0.5,0.5]])

# 第i个预测序列值的概率计算公式为o = pi*A的i次方*b
o = 1

for i in range(len(pre_list)):
    o *= (pi * (A ** i) * B[:,pre_list[i]])

print(o[0,0])




"""
课件前向算法实现

"""
#预测序列为白黑白白黑

N = len(pre_list)

# 最终输出的序列概率值
final_alpha = []

# 第一个序列为白的概率值
alpha1 = []
for i in range(pi.shape[1]):
    alpha1.append(pi[0,i]*B[i,pre_list[0]])

# 遍历所有的观测序列值

for k in range(1,len(pre_list)):
    final_alpha = []
    for j in range(len(alpha1)):
        alpha = 0

        for i in range(len(alpha1)):
            # 第i个盒子转移到第j个盒子的概率之和
            alpha += alpha1[i] * A[i, j]
        final_alpha.append(alpha * B[j, pre_list[k]])
        # print(final_alpha)
    alpha1 = final_alpha

print(sum(final_alpha))


"""
下面是使用完全的python基础代码实现自我理解的思路进行求解，待续，，，，
循环计算次数太多
"""
# pi = [0.2,0.5,0.3]
# B = [[0.4,0.6],[0.8,0.2],[0.5,0.5]]
# A = [
#     [0.5,0.4,0.1],
#     [0.2,0.2,0.6],
#     [0.2,0.5,0.3]
# ]
# # 遍历所有的序列值
# for k in range(len(pre_list)):
#     if k==0:
#         o = []
#         for h in range(len(pi)):
#             o.append(pi[h]*B[h][pre_list[k]])
#         final_out = o
#     else:
#         trans_mat = []
#         for i in range(len(pi)):
#             trans_list = []
#             for m in range(len(pi)):
#                 sum=0
#                 for j in range(len(pi)):
#                     sum+=A[i][j]*A[j][m]
#                 trans_list.append(sum)
#             trans_mat.append(trans_list)
#         trans_list = []
#         for m in range(len(pi)):
#             sum = 0
#             for j in range(len(pi)):
#                 sum += o[j] * A[j][m]
#             trans_list.append(sum)
#         print(trans_list)
#         final_out  = np.sum(trans_list)
#         A = trans_mat
# print(matrix(trans_mat))
# print(o)
# print(final_out)
