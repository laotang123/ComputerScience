package com.ljf.algorithm.others.array;

import java.util.Arrays;
import java.util.Date;

/**
 * @author: ljf
 * @date: 2021/1/28 9:27
 * @description: 公司对新员工进行分组培训，设计一个算法对员工进行随机分组。员工信息保存在一个数组中array[a1,a2...an]。分组数量为k
 * 思路：
 * 1.如何随机：java自带or根据时间戳对数组长度求随机下标索引
 * 2.创建二维数组[k,n]
 * 3.遍历员工信息数组。对k求余数，找到对应组索引
 * <p>
 * 8的补码还是原码：...1000
 * -8的补码：原码: 10...1000->反码(除符号位，取反)：111111...0111->补码(反码+1): 1111...1000
 * @modified By：
 * @version: $ 1.0
 */
interface Chooser {
    //返回求余数下标
    int next(int n);
}

//针对2的幂次方优化版求余数
class PowerOfTwoChooser implements Chooser {
    private final int k;

    PowerOfTwoChooser(int k) {
        this.k = k;
    }

    @Override
    public int next(int n) {
        return n & (k - 1);
    }
}

//通用版，%求余
class GenericChooser implements Chooser {
    private final int k;

    GenericChooser(int k) {
        this.k = k;
    }

    @Override
    public int next(int n) {
        return n % k;
    }
}

public class RandomGroup {
    private static boolean isPowOfTwo(long val) {
        return (val & -val) == val;
    }

    private static int randomIndex(int k) {
        int currentTime = (int) System.currentTimeMillis();
        if (isPowOfTwo(k)) {
            return currentTime & (k - 1);
        } else {
            return currentTime % k;
        }
    }

    private static Chooser newChooser(int k) {
        if (isPowOfTwo(k)) {
            return new PowerOfTwoChooser(k);
        } else {
            return new GenericChooser(k);
        }
    }

    /**
     * 对员工进行随机分组
     * 空间复杂度：近似O(n)，最多有k个int空间的浪费。对比ArrayList，内存空间指定更精准，减少数组扩容
     * 时间复杂度：O(n)
     *
     * @param array：员工数组信息
     * @param k：分组数量
     * @return : 二维数组
     */
    public static int[][] solution(int[] array, int k) {
        //异常情况，保证了正常情况：array含有数据且数量小于等于员工人数
        if (array == null || array.length <= 0 || k <= 0 || k > array.length) {
            return new int[][]{};
        }

        int numOfPerGroup = (int) Math.ceil((double) array.length / k);

        int[][] res = new int[k][numOfPerGroup];

        Chooser chooser1 = newChooser(array.length);
        Chooser chooser2 = newChooser(k);
        int randomIndex = randomIndex(array.length);
//        System.out.println("random index: " + randomIndex);
        int blockIndex = 0;
        for (int i = 0; i < array.length; i++) {
//            int index = ((randomIndex + i) % array.length);
            int index = (chooser1.next(randomIndex + i));//优化版

//            res[i % k][blockIndex] = array[index];
            res[chooser2.next(i)][blockIndex] = array[index];//优化版
//            System.out.println("i: " + (i % k) + " j: " + blockIndex + " value: " + array[index]);//方便理解
            //更新blockIndex (i+1)临界点！
            blockIndex = (i + 1) / k;
        }


        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 5;

        int[][] res = solution(array, k);
        for (int[] arr : res) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
