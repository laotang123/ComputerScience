package com.ljf.algorithm;

/**
 * @author ：ljf
 * @date ：2020/7/12 22:36
 * @description：盛水最多的容器
 * @modified By：
 * @version: $ 1.0
 */
public class MaxContainer {
    public int maxArea(int[] height) {
        int res = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            res = Math.max(res, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(new MaxContainer().maxArea(height));
    }
}
