#!/usr/bin/python3
# -*-coding:utf-8 -*-
# @Time     : Created in 2019/10/29 17:16
# @Author    : ljf
# @modified By:
# @description: 针对中文的词袋法加入语气符号如：！，？：等
# @Software   : PyCharm

from sklearn.feature_extraction.text import CountVectorizer
import jieba

# 一 语料库
corpus = [
    "大爷，中午好啊！吃过饭了吗？",
    "小王，我吃过了！",
    "媳妇，这是去干吗呢？吃过饭了吗？",
    "小王，我吃过了？"
]

# 匹配字符
vectorizer = CountVectorizer(analyzer="word",ngram_range=(1,1),token_pattern=r'\b\w+\b', min_df=1)

# print(list(map(lambda p: " ".join(jieba.cut(p)),corpus)))
#
jieba_corpus = list(map(lambda p: " ".join(jieba.cut(p)),corpus))
# print(jieba_corpus)
result = vectorizer.fit_transform(jieba_corpus)
print(result.toarray())
print(vectorizer.get_feature_names())
