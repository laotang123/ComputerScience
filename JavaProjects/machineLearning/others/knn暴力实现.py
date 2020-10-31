#coding: utf-8
"""
暴力实现knn
"""
import numpy as np
def fetch_k_neighbors(data, x, k):
    #最近的样本值为k个
    size = k
    #k个样本的列表
    k_neighbors = []
    #对应的k个样本的距离列表
    k_neighbors_distance = []
    for i in range(len(data)):

        if len(k_neighbors)<size:
            #将样本中的前k个先放入列表中
            k_neighbors.append(data[i])

            #算的前k个样本的欧几里德距离，并找出最大的距离
            distance = np.sqrt((data[i][0]-x)**2)
            k_neighbors_distance.append(distance)

        else:
            max_distance_index = np.argmax(k_neighbors_distance)
            #计算剩下的样本中的欧几里德距离
            distance = np.sqrt((data[i][0] - x) ** 2)

            #如果距离比k个样本中最大的距离还小，就进行相应的替换
            max_distance = k_neighbors_distance[max_distance_index]
            if distance<max_distance:
                #将最大距离对应的样本进行替换
                k_neighbors[max_distance_index] = data[i]
                k_neighbors_distance[max_distance_index] =distance
    return k_neighbors



def knn(data, x, k):
    # # ,1从数据中找到离待测样本距离最近的k个样本，距离为欧几里德距离
    k_neighbors = fetch_k_neighbors(data, x, k)
    # print(k_neighbors)
    # ,2将k个最近样本中进行分类计数，找出数量最多的类别，返回待测样本的类别
    result_dict = {}
    label_list = []
    #先将k_neighbors样本中对应的类别放入一个列表中
    for i in range(len(k_neighbors)):
        label_list.append(k_neighbors[i][1])

    #对类别数量的列表进行遍历，如果字典中没有该类别，进行添加，有进行+1操作
    #最终字典的keys为每一个类别，对应的values为该类别出现的次数
    for i in label_list:
        if i not in result_dict.keys():
            result_dict[i] = 1
        else:
            result_dict[i] += 1
    # print(result_list)
    #找出出现类别最多的标签，返回该标签
    max_count_label = np.max(list(result_dict.values()))
    for i in list(result_dict.keys()):
        if result_dict[i] == max_count_label:
            return int(i)


if __name__ == "__main__":
    data = np.array([
        [8,0],
        [6,0],
        [7,0],
        [5.5,0],
        [4,1],
        [3,1],
        [2,1]
    ])
    sample = float(input("请输入待测样本数据："))
    result = knn(data,[sample],3)
    print("预测样本属于{}类别".format(result))