#coding: utf-8
import numpy as np
from numpy import matrix
"""
求解观测序列为“白黑白白黑”的最大可能的状态序列
"""

#预测序列为白黑白白黑
box_list = ["盒子一","盒子二","盒子三"]
pre_list = [0,1,0,0,1]
pi = [0.2,0.5,0.3]

A = [
    [0.5,0.4,0.1],
    [0.2,0.2,0.6],
    [0.2,0.5,0.3]
]

B = [[0.4,0.6],[0.8,0.2],[0.5,0.5]]
# 状态一的概率值
deta =[]
for i in range(len(pi)):
    deta.append(pi[i]*B[i][pre_list[0]])
# print(state)

# 状态二到状态五的概率值矩阵
state_list = [deta]

for m in range(1,len(pre_list)):
    max_states = []
    for i in range(len(pi)):
        states = []
        for j in range(len(pi)):
            states.append(deta[j] * A[j][i])
        # print(states)
        max_states.append(max(states)*B[i][pre_list[m]])
    # 将当前序列值下的状态最大的概率添加到列表中
    state_list.append(max_states)
    # 更新deta值
    deta = max_states
# print(max_states)
print(np.array(state_list))
print(np.argmax(np.array(state_list),axis=1))

# 反向查找出所有最大可能的状态值
k = state_list[len(pre_list)-1].index(max(state_list[len(pre_list)-1]))
# 盒子的下标索引
states_list_index = [k]
# for i in range(len(pre_list)-2,-1,-1):
for i in reversed(range(len(pre_list)-1)):
    # 获取最后状态的最大值，反向推导出所有状态的最大值，k是对应的状态下标
    states_index = []
    for j in range(len(pi)):
        # print(state_list[i][j])
        # k是第i+1个所有可能状态的最大值的索引下标
        # 由第i+1个状态的最大值推出第i个状态的最大值，链式求法，求出第一个状态的最大值对应的盒子
        states_index.append(state_list[i][j]*A[j][k])
    states_list_index.append(states_index.index(max(states_index)))
    # print(states_index)
    # 由当前状态最大值的索引下标对应转移矩阵A的列下标
    k = states_index.index(max(states_index))
    # print(k)
# 将盒子的下标进行反转
box_index = []
for i in range(len(states_list_index)-1,-1,-1):
    box_index.append(states_list_index[i])
# print(box_index)
# print(box_list)
print(" ".join(map(lambda t:box_list[t],box_index)))
