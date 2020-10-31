#coding: utf-8
"""
爱彼迎租房信息爬取
"""
import re
import requests
from lxml import etree
import json
import time
def clear(x):
    pattern = re.compile('[,\./\\\*\?!\+，|"~。？ \-！()\n]+')
    return pattern.sub('',x)

pricelist = []
#翻页操作
for page in range(17):
    head = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"

    }
    head["referer"] = "https: // www.airbnb.cn / s / % E5 % 8C % 97 % E4 % BA % AC / homes?query = % E5 % 8C % 97 % E4 % BA % AC & click_referer = t % 3ASEE_ALL % 7Csid % 3A87ec67b1 - 9a55 - 4b86 - b28b - 83d6bd6574d1 % 7Cst % 3AMAGAZINE_HOMES & title_type = MAGAZINE_HOMES & allow_override % 5B % 5D = & s_tag = Y3VJFK1O & section_offset = 13 & cdn_cn = 1"

    url = "https://www.airbnb.cn/api/v2/explore_tabs?version=1.3.9&satori_version=1.0.4&_format=for_explore_search_web&experiences_per_grid=20&items_per_grid=18&guidebooks_per_grid=20&auto_ib=true&fetch_filters=true&has_zero_guest_treatment=false&is_guided_search=true&is_new_cards_experiment=true&luxury_pre_launch=false&query_understanding_enabled=false&show_groupings=true&supports_for_you_v3=true&timezone_offset=480&client_session_id=19700790-9e51-4ada-bd8f-c5cdaa9b9883&metadata_only=false&is_standard_search=true&refinement_paths%5B%5D=%2Fhomes&selected_tab_id=home_tab&click_referer=t%3ASEE_ALL%7Csid%3A3f397559-83a9-4a1d-8afb-2af65c68474f%7Cst%3AMAGAZINE_HOMES&title_type=MAGAZINE_HOMES&allow_override%5B%5D=&s_tag=ZXG_0_hn&section_offset={0}&cdn_cn=1&screen_size=medium&query=%E5%8C%97%E4%BA%AC&_intents=p1&key=d306zoyjsyarp7ifhu67rjxn52tv0t20&currency=CNY&locale=zh"
    response = requests.get(url.format(page),headers = head).text

    # json字符串解析
    json_dict = json.loads(response)
    # print(json_dict)

    #获取每一个出租用户的id
    rent_ids = json_dict["explore_tabs"][0]["home_tab_metadata"]["remarketing_ids"]

    # #遍历每一个id，拼接出租信息地址
    #
    total_num = 1
    for id in rent_ids:
        old_rent_url ="https://zh.airbnb.com/rooms/{0}?location=北京&s=CVUSPXop"
        rent_url = old_rent_url.format(id)
        print(rent_url)
    #
    #     response = requests.get(rent_url, headers=head).text
    #
    #     # 使用lxml进行解析
    #     html = etree.HTML(response)
    #     # # print(html)
    #     #
        print("{}页，第{}条租房信息".format(page+1,total_num))
    #     # 获取民宿的标题
    #     home_title = html.xpath("//*[@id='room']/div/div[2]/div/div/div[1]/div[1]/div/div/div[1]/div/div/section/div[1]/div[2]/div/h1/text()")[0]
    #     print("民宿的标题: ", home_title)
    #
    #     # 获取民宿的位置
    #     home_location = html.xpath("string(//div[@id='location']/div/div/div/section/div[3])")
    #     # print("民宿的位置:", home_location)
    #     # print(home_location)
    #     # 获取民宿的评价(列表)
    #     home_estimate = html.xpath(
    #         "//div[@style='margin-bottom:40px']/section/div[2]/div[4]/div/div/div/div[2]/div/div/div/text()")
    #     print("民宿的评价:", home_estimate)
    #     #
    #     # 获取民宿的价格
    #     """
    #     预定情况是一个动态加载的数据，只有点击预定和结束住宿的日期，最新的价格才会返回
    #     """
        priceurl = "https://zh.airbnb.com/api/v2/pdp_listing_booking_details?force_boost_unc_priority_message_type=" \
                   "&guests=1&listing_id={0}&non_refundable_policy_selected=0&show_smart_promotion=0" \
                   "&_format=for_web_with_date&_interaction_type=dateChanged&_intents=p3_book_it" \
                   "&_parent_request_uuid=12b75d03-825f-41df-99c0-1d138891ecc7&_p3_impression_id=p3_1536321765_f5FVoeSwh6kKJ8eu" \
                   "&check_in=2018-10-18&check_out=2018-10-19&number_of_adults=1&number_of_children=0&number_of_infants=0" \
                   "&key=d306zoyjsyarp7ifhu67rjxn52tv0t20&currency=CNY&locale=zh"
        json_str = requests.get(priceurl.format(id), headers=head).text

        # json字符串解析
        json_dict = json.loads(json_str)

        # 房屋总价格分为出租费和服务费

        detial = json_dict["pdp_listing_booking_details"][0]["price"]["price_items"]
        total_price = 0
        for i in detial:
            total_price += i["total"]["amount"]
        print("每晚合计费用为：",total_price)
        pricelist.append(total_price)
        with open("aibiying/租房价格.txt","a+",encoding="utf-8") as file:
            file.write(str(total_price)+"\n")
        # with open("E:/python/machine learn/回归算法/aibiying/爱彼迎租房信息.txt","a+",encoding="utf-8") as file:
        #     file.write(home_title+"::"+home_location+"::"+str(home_estimate)+"::"+str(total_price)+"\n")


        #获取民宿图片网址
        # picture_url = "https://zh.airbnb.com/api/v2/similar_listings?key=d306zoyjsyarp7ifhu67rjxn52tv0t20" \
        #               "&currency=CNY&locale=zh&_format=for_listing_card&guests=1&adults=1&children=0" \
        #               "&infants=0&listing_id={0}"
        # json_str = requests.get(picture_url.format(id), headers=head).text
        # json_dict = json.loads(json_str)
        # url = json_dict["similar_listings"][11]["listing"]["picture_url"]
        # # 获取照片并保存
        # imagName = clear(home_title)
        # response = requests.get(url, headers=head, stream=True).raw.read()
        # with open("picture/"+imagName+".jpg", "wb") as file:
        #     file.write(response)
        total_num+=1
        time.sleep(0.5)
