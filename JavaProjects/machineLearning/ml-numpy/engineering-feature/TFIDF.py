#!/usr/bin/python3
# -*-coding:utf-8 -*-
# @Time     : Created in 2019/10/29 18:05
# @Author    : ljf
# @modified By:
# @description: 
# @Software   : PyCharm

from sklearn.feature_extraction.text import TfidfVectorizer,TfidfTransformer
# 一 词袋法计算频率
counts = [[3, 0, 1],
           [2, 0, 0],
           [3, 0, 0],
           [4, 0, 0],
           [3, 2, 0],
           [3, 0, 2]]

# 二 创建tfidftransform对象
transformer = TfidfTransformer(smooth_idf=False)
tfidf = transformer.fit_transform(counts)

# 三 结果展示
print(tfidf)
print(tfidf.toarray())


print("*******************************直接使用词袋法+tfidf**************")


corpus = [
    'This is the first document.',
    'This document is the second document.',
    'And this is the third one.',
    'Is this the first document?']
# corpus = [
#     "大爷，中午好啊！吃过饭了吗？",
#     "小王，我吃过了！",
#     "媳妇，这是去干吗呢？吃过饭了吗？",
#     "小王，我吃过了？"
# ]

# vectorizer = TfidfVectorizer(analyzer="char")
vectorizer = TfidfVectorizer(analyzer="word")
X = vectorizer.fit_transform(corpus)
print("词袋法的特征词：",vectorizer.get_feature_names())
print(X.shape)
print(X.toarray())
