# coding: utf-8
# 信息熵直观图展示

#E-step
# 类别信息为硬币A正面的概率和硬币B正面B出现的概率
def Mstep(thetaA,thetaB):
    # 定义M-step的硬币类别列表
    coinA =[]
    coinB = []
    # 计算样本所属类别的期望
    samplea = ((10*9*8*7*6)/(5*4*3*2*1))*(thetaA**5)*((1-thetaA)**5)
    sampleb = ((10*9*8*7*6)/(5*4*3*2*1))*(thetaB**5)*((1-thetaB)**5)

    # 将第一组数据归一化
    samplea1 = samplea/(samplea+sampleb)
    sampleb1 = sampleb / (samplea + sampleb)

    samplea = 10 * (thetaA ** 9) * (1-thetaA)**1
    sampleb = 10 * (thetaB ** 9) * (1-thetaB)**1
    samplea2 = samplea / (samplea + sampleb)
    sampleb2 = sampleb / (samplea + sampleb)

    samplea = 45 * (thetaA ** 8) * (1-thetaA)**2
    sampleb = 45 * (thetaB ** 8) * (1-thetaB)**2
    samplea3 = samplea / (samplea + sampleb)
    sampleb3 = sampleb / (samplea + sampleb)

    samplea = 210 * (thetaA ** 4) * (1-thetaA) ** 6
    sampleb = 210 * (thetaB ** 4) * (1-thetaB) ** 6
    samplea4 = samplea / (samplea + sampleb)
    sampleb4 = sampleb / (samplea + sampleb)

    samplea = 120 * (thetaA ** 7) * (1 - thetaA) ** 3
    sampleb = 120 * (thetaB ** 7) * (1 - thetaB) ** 3
    samplea5 = samplea / (samplea + sampleb)
    sampleb5 = sampleb / (samplea + sampleb)

    coinA1 = (samplea1*5+samplea2*9+samplea3*8+samplea4*4+samplea5*7)
    coinA0 = (samplea1 * 5 + samplea2 * 1 + samplea3 * 2 + samplea4 * 6 + samplea5 * 3)

    coinB1 = (sampleb1 * 5 + sampleb2 * 9 + sampleb3 * 8 + sampleb4 * 4 + sampleb5 * 7)
    coinB0 = (sampleb1 * 5 + sampleb2 * 1 + sampleb3 * 2 + sampleb4 * 6 + sampleb5 * 3)

    # 返回类别属性的概率值
    coinA.append((coinA1,coinA0))
    coinB.append((coinB1, coinB0))

    return coinA,coinB


# 更新类别信息后的的参数更新
def Estep(coinA,coinB):
    thetaA = coinA[0][0]/(coinA[0][0]+coinA[0][1])
    thetaB = coinB[0][0] / (coinB[0][0] + coinB[0][1])
    return thetaA,thetaB

# EM算法进行迭代
thetaa = 0.6
thetab = 0.5
for i in range(10):
    # E步骤
    coinA,coinB = Mstep(thetaa,thetab)

    # E步骤
    thetaA,thetaB = Estep(coinA,coinB)
    thetaa = thetaA
    thetab = thetaB

print(thetaA)
print(thetaB)