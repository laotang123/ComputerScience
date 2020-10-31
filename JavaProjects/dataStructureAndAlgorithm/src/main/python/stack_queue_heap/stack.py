# -*- coding: utf-8 -*-
# @Time    : 2019/10/14 9:21
# @Author  : liujunfeng
import heapq
import random
import time
from copy import copy,deepcopy

def stack_vs_heap():
    def stack_waste(H):
        temp = copy(H)
        start = time.time()
        heapq.heapify(temp)
        heapq.heappush(temp, -1)
        print(temp[0])
        print("最大堆构建花费时长：{}".format(time.time() - start))

    def sort_waste(H):
        temp = copy(H)
        start = time.time()
        temp.sort()
        temp.append(-1)
        temp.sort()
        print(temp[0])
        print("排序花费时长：{}".format(time.time() - start))

    H = [random.randint(1, 1000) for _ in range(10000000)]
    # Use heapify to rearrange the elements
    # print(H[:10])
    stack_waste(H)
    # print(H[:10])
    sort_waste(H)

class Stack(object):
    def __init__(self,items):
        self.items = items


    def minimum_stack(self):
        """创建最小堆"""
        pass

    def maximum_stack(self):
        """创建最大堆"""
        pass

if __name__ == '__main__':
    # stack_vs_heap()
    t = [4,3,2,-1]
    heapq.heapify(t)
    print(t)
    print(heapq.heappop(t))
    print(t)


