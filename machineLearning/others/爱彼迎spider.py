#coding: utf-8
"""
爱彼迎民俗网站的爬取
信息包括：标题，照片，位置，价格，评价，预定情况
分析：其中照片是获取其中一个
      评价为当前版面的七条
      预定情况中已住和未住的class属性不一致
      其他的基本的xpath都可以解决
"""
#一家住宿的信息
import requests
from lxml import etree
import json

head = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"
}
url = "https://zh.airbnb.com/rooms/8888557?location=北京&s=CVUSPXop"
response = requests.get(url,headers = head).text
#
# #使用lxml进行解析
html = etree.HTML(response)
print(html)
#
# #获取民宿的标题
home_title = html.xpath("//div[@style='margin-bottom:8px'][1]/div[1]/h1/text()")[0]
print("民宿的标题: ",home_title)
#
# #获取民宿的位置
# home_location = html.xpath("string(//div[@id='location']/div/div/div/section/div[3])")
# print("民宿的位置:",home_location)
#
#
# #获取民宿的评价(列表)
# home_estimate = html.xpath("//div[@style='margin-bottom:40px']/section/div[2]/div[4]/div/div/div/div[2]/div/div/div/text()")
# print("民宿的评价:",home_estimate)