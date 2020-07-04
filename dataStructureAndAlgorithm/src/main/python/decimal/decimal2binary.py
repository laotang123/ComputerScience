# -*- coding: utf-8 -*-
# @Time    : 2019/9/9 9:41
# @Author  : ljf
def decimal2binary(deci,precision):
    binary = []
    id = 0
    while deci != 0.0:
        temp = deci*2
        if temp >= 1.0:
            binary.append(1)
            deci = temp - 1.0
        else:
            binary.append(0)
            deci = temp
        id += 1
        if id >= precision:
            break
    return binary

bianry = decimal2binary(0.3,16)
decimal = 0.0
print(len(bianry))
for i in range(0,len(bianry)):
    decimal += bianry[i]*2**(-(i+1))

print(decimal)