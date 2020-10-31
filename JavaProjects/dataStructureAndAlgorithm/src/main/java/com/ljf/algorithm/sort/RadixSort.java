package com.ljf.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author ：ljf
 * @date ：Created in 2019/12/16 9:43
 * @modified By：
 * @version: $
 */
public class RadixSort {
    /**
     * 1.创建二维数组[10][arr.length]，一共有十个桶，每个桶为一个一维数组
     * 2.创建一维数组bucketElemCounts[10]记录每个桶中的数据总量
     * 3.计算分配和收集数据的最大次数即原始数组中最大元素的位长
     * 4.根据最大元素的位长循环进行分配和收集
     */
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        //1.计算循环次数和创建数组
        int elemMaxLength = getElemMaxLength(arr);
        int[][] bucket = new int[10][arr.length];
        int[] bucketElemCounts = new int[10]; // 记录每个桶中的数据总量

        System.out.println("数组中最大数值的长度：" + elemMaxLength);

        for (int len = 0, n = 1; len < elemMaxLength; len++, n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                //计算元素的个位所在的桶下标
                int indexOfBucket = arr[i] / n % 10;

                //根据元素所在个位把元素放入桶中
                bucket[indexOfBucket][bucketElemCounts[indexOfBucket]] = arr[i];
                bucketElemCounts[indexOfBucket]++; //桶中数据长度自增
            }

            int index = 0; //收集到数组下标
            //将桶中元素收集到数组
            for (int i = 0; i < bucket.length; i++) {
                //判断桶中数据
                if (bucketElemCounts[i] != 0) {
                    for (int j = 0; j < bucketElemCounts[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                //遍历当前桶数据之后，将该桶数量置0
                bucketElemCounts[i] = 0;
            }

            System.out.println(Arrays.toString(arr));
        }


        /*//2.第一次将数组分配到桶中
        for (int i = 0; i < arr.length; i++) {
            //计算元素的个位所在的桶下标
            int indexOfBucket = arr[i] % 10;

            //根据元素所在个位把元素放入桶中
            bucket[indexOfBucket][bucketElemCounts[indexOfBucket]] = arr[i];
            bucketElemCounts[indexOfBucket]++; //桶中数据长度自增
        }

        int index = 0; //收集到数组下标
        //将桶中元素收集到数组
        for (int i = 0; i < bucket.length; i++) {
            //判断桶中数据
            if (bucketElemCounts[i] != 0) {
                for (int j = 0; j < bucketElemCounts[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
            //遍历当前桶数据之后，将该桶数量置0
            bucketElemCounts[i] = 0;
        }

        System.out.println(Arrays.toString(arr));*/
    }

    private static int getElemMaxLength(int[] arr) {
        int max = 0;
        for (int elem : arr) {
            if (elem > max) {
                max = elem;
            }
        }
        return (max + "").length();
    }
}
