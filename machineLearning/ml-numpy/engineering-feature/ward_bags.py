#!/usr/bin/python3
# -*-coding:utf-8 -*-
# @Time     : Created in 2019/10/29 16:29
# @Author    : ljf
# @modified By:
# @description: 词袋法
# @Software   : PyCharm
from sklearn.feature_extraction.text import CountVectorizer,TfidfTransformer,TfidfVectorizer,HashingVectorizer


# 一构建数据集
corpus = [
   'This is the first document.',
    'This is the second second document.',
    'And the third one.',
    'Is this the first document?' ]

# TODO 这里我们通过观测第一个句子和最后一个句子明显语义是不一样的，但是经过词袋法计算后的词向量是一样的。


# 二创建vectorizer模型
vectorizer = CountVectorizer()

# 三模型训练并转换预料库
result = vectorizer.fit_transform(corpus)

print("训练后输出稀疏矩阵：\n",result)
print("词袋法转换后的矩阵：\n",result.toarray())
print(vectorizer.get_feature_names())

# TODO 我们这里延伸为ngram的范围到2个词，这样一定程度能够减缓。那中文呢？比如“我吃过啦！”，“我吃过啦？” 加入符号

ngram_2 = CountVectorizer(ngram_range=(1, 2),token_pattern=r'\b\w+\b', min_df=1)
result2 = ngram_2.fit_transform(corpus)

print("*************************ngram=2**********************************")

print("训练后输出稀疏矩阵：\n",result2)
print("词袋法转换后的矩阵：\n",result2.toarray())
print(ngram_2.get_feature_names())







