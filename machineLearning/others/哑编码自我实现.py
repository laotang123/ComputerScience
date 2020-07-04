#coding: utf-8
"""
哑编码的操作实现
基本思路，利用列表追加的方式，将A转化为向量的方式，双重for循环将所有的列属性转换为列表嵌套方式，便于转化为DataFrame
两个for循环实现
"""
import pandas as pd
df01 = pd.DataFrame({
    "x1":[1,5,6,9,5,6,8,2,3,1,20],
    "x2":[5,6,7,8,5,63,2,4,1,1,4],
    "x3":[5,6,7,8,5,63,2,4,1,1,4],
    "sort":["A","B","C","B","B","A","A","B","C","C","B"]
})
#通过哑编码把sort中的类别用向量表示
#哑编码定义：变量中的取值有k个，通过1~K进行排序，其中在该类下面为1，其他类型下面为0
sortunique = df01["sort"].unique()
print(df01["sort"])
n = len(sortunique)
a = []
b = []
for j in range(n):
    for i in range(n):
        if sortunique[i] ==sortunique[j]:
            a.append(1)
        else:
            a.append(0)
    b.append(a)
    a = []
#2将df01中的sort列项中的A,B，C分别用相应的向量对应起来
c = []
sort = df01.iloc[:,3]
for i in range(len(sort)):
    for j in range(n):
        if sort[i] == sortunique[j]:
            c.append(b[j])
df02 = pd.DataFrame(c,columns=["A","B","C"])
#3 删除sort列，将df01和df02进行合并操作
df01.drop("sort",axis=1,inplace=True)
print(pd.concat([df02,df01],axis=1))

