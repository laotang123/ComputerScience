#coding: utf-8
"""
爬取酷狗音乐
"""
from lxml import etree
import requests
head = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"
}
url = "http://103.237.181.58/fs.w.kugou.com/201808291939/c02e186f13ef05f7aaf0d515ca63f7de/G117/M05/14/09/VZQEAFpfZSqAPP3BAEHun0d8rZw177.mp3"
response = requests.get(url,headers = head,stream = True).raw.read()
print(response)
with open("music/aa.m4a","wb") as file:
    file.write(response)