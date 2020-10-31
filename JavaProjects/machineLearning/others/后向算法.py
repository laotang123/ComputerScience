#coding: utf-8
"""
后向算法实现
"""
import numpy as np
#预测序列为白黑白白黑
pre_list = [0,1,0,0,1]
pi = [0.2,0.5,0.3]
B = [[0.4,0.6],[0.8,0.2],[0.5,0.5]]
A = [
    [0.5,0.4,0.1],
    [0.2,0.2,0.6],
    [0.2,0.5,0.3]
]
# 设初始值βT的概率值为1
beta = [1,1,1]
beta_list = [beta]
for k in reversed(pre_list[1:]):
    # print(k)
    new_beta = []
    for i in range(len(pi)):
        sum = 0
        for j in range(len(pi)):
            sum += A[i][j] * B[j][k] * beta[j]
        new_beta.append(sum)
        # 更新参数beta
    beta = new_beta
    beta_list.append(beta)
    # print(beta)
    # print(sum)
print(beta)
print(np.array(beta_list))

# 计算序列的概率值

final_proba = []
for i in range(len(pi)):
    sum = pi[i]*B[i][pre_list[0]]*beta[i]
    final_proba.append(sum)

print(np.sum(np.array(final_proba)))









def markov_backward(A,B,pi,observ):
    beta = [1 for _ in range(len(pi))]
    print(beta)
    for ob in reversed(observ[1:]):
        # print("ob",ob)
        for i in range(len(pi)):
            beta[i] *= B[i][ob]
        # print(beta)
        # print(A)
        print(np.matmul(A,beta))
        beta = np.matmul(A,beta)
    for i in range(len(pi)):
        beta[i]*=B[i][observ[0]]
    beta = np.matmul(pi,beta)
    return beta

print(markov_backward(A,B,pi,pre_list))
