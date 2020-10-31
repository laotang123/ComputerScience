# coding: utf-8
"""
create by ljf on 2018/11/13 16:10
"""
from PIL import Image
import numpy as np
import matplotlib.pyplot as plt
"""
使用卷积核的形式将(3,5,5)卷积为(3,4,4)。
使用到三组卷积(3,2,2),步长为1
"""

img = Image.open("artificial.jpg")
# print(img.size)
# print(img.mode)
# print(img.format)
# img.show()

# 转换为黑白图像
# img1 = img.convert("L")
# img1.show()
# print(np.array(img1).shape)

# 图像的剪切
box = (200,0,600,500)

# img1 = img1.crop(box)
# img1.show()
# 图像的压缩 (32*32)
img2 = img.resize((32,32))
img2 = np.array(img2).reshape(3,32,32)
# print(img2.shape)
# img3 = Image.fromarray(img2,"RGB")
# img3.show()

# print(np.array(img2)/255)


def convolution_3D(image,filter,stride,bias):
    """
    定义卷积函数，参数有图像矩阵，卷积核矩阵，步长,偏置项
    :param image:
    :param filter:
    :param stride:
    :return:
    """
    # 1.将图像中的区域滑动显示
    img_width = image.shape[1]
    filter_width = filter.shape[1]
    # 图像卷积后的图像宽度(图宽-卷积宽+步长+padding)/步长
    con1_width = (img_width - filter_width + stride) // stride

    conv1 = np.zeros(shape=[con1_width, con1_width])
    # print(conv1.shape)
    # 第k个维度描述图像，第k个二维的灰度图像
    # for k in range(3):
    for i in range(con1_width):
        for j in range(con1_width):
            # print("第{}个滑动区域：".format((i+1)*(j+1)))
            kernel_sum=0
            for k in range(3):
                # 每一组卷积核分别对应每一个维度的RGB矩阵图像
                filter_img = image[k, i:i + filter_width, j:j + filter_width]
                kernel_sum+=np.sum(filter_img * filter[k, :, :])
            conv1[i, j] = kernel_sum+bias


    # 2.显示卷积后的值
    # print(conv1.shape)
    return conv1
np.random.seed(18)
img_gray = np.random.randint(1,9,(3,5,5)).astype(np.float32)
# filter = np.array([[1,0],[1,0],[0,-1]])
filter1 = np.random.randint(-1,2,(3,2,2))
filter2 = np.random.randint(-1,2,(3,2,2))
filter3 = np.random.randint(-1,2,(3,2,2))
# filter = np.random.randint(-1,2,(5,5)).astype(np.float32)
bias = 1.0
stride = 1
image = img_gray
print("image：\n",image)
print("kernel:\n",np.array([filter1,filter2,filter3]))
conv1 = convolution_3D(image,filter1,stride,bias)
conv2 = convolution_3D(image,filter2,stride,bias)
conv3 = convolution_3D(image,filter3,stride,bias)
conv = np.array([conv1,conv2,conv3])
print(conv.shape)



