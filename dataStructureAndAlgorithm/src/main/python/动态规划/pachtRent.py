# coding=utf-8
# 初始化最大堆
import numpy as np

# 1.初始化站点之间的租金，更新的租金和两地之间停靠点的index

init_rent = np.array(
    [[0, 2, 6, 9, 15, 20], [0, 0, 3, 5, 11, 18], [0, 0, 0, 3, 6, 12], [0, 0, 0, 0, 5, 8], [0, 0, 0, 0, 0, 6]])
update_rent = init_rent.copy()
stop_arr = np.full((6, 6), -1)  # -1代表两个站点之间是直达的，中间没有停靠。

# 2.按照递归公式进行计算
"""
当j=i时，只有1个站点，m[i][j] = 0;
当j=i+1时，只有2个站点，m[i][j] = 1;
当j>i+1时，有3个以上的站点，m[i][j] = min(m[i][k]+m[k][j],m[i][j]),i<k<j;
"""


def RentYacht(stops):
    """
    返回起始站到终点站的最短距离
    :param stops:
    :return:
    """
    # 1.判断站点数量
    if stops == 1:
        return 0
    elif stops == 2:
        return init_rent[0][1]  # 直接返回两个站点之间的距离
    else:
        for d in range(2, stops):  # 将问题分解为n-2个子问题
            for i in range(stops - d):
                j = i + d
                for k in range(i + 1, j):  # 每个子问题的最优结果
                    temp = update_rent[i][k] + update_rent[k][j]
                    if temp < update_rent[i][j]:
                        update_rent[i][j] = temp
                        stop_arr[i][j] = k


def GetRoute(start=0, end=5):
    """
    获取游艇的路线和最短距离，以0站点到5站点为例
    :param stop_arr:
    :return:
    """
    if stop_arr[start][end] == -1:  # 表示直达
        print("--{}".format(end), end="")
        return
    GetRoute(start, stop_arr[start][end])  # 递归查找
    GetRoute(stop_arr[start][end], end)


if __name__ == "__main__":
    RentYacht(6)
    print("初始化租金矩阵：\n", init_rent)
    print("更新后的租金矩阵：\n", update_rent)
    print("更新后的站点矩阵:\n", stop_arr)
    print("从0站点到5站点，话费的最少租金为：{}".format(update_rent[0][-1]))
    print("最少租金经过的站点：0", end="")
    GetRoute()


