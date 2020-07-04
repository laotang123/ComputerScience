#coding: utf-8
"""
思想：使用微分的形式求得当函数值=任意值时，自变量x的值
函数的割线近似表示切线
"""
import numpy as np
import matplotlib.pyplot as plt
# y = lambda x : x**2 # 映射函数f（x）
# # y对x的偏导函数
# y_x = lambda x : 2*x

a =2
lr = 0.0025

# 因变量y的变化值
# dy= lambda x: a-y(x)
#
# # 因变量的变化值dy*偏导值*学习率为x的变化方向
# dx = lambda x:dy(x)*y_x(x)*lr

# 下面两个式子是将x的变化方向整理后的式子，也是上面函数的全部总结
dx = lambda x: (2-x**2)*2*x*lr
dx = lambda x: (2*a*x-2*x**3)*lr
x=20
X =[x]
Y2 = [x**2]
for i in range(10000):
    x+=dx(x)
    X.append(x)
    Y2.append(x**2)
print(x)

# 画图展示
x1 = np.linspace(-20,20,1000)
y1 = x1**2
plt.plot(x1,y1,"r-")
plt.plot(X,Y2 ,"b-")
plt.show()


