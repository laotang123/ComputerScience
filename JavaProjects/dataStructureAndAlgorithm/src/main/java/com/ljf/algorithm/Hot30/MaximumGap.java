package com.ljf.algorithm.Hot30;


import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/29 13:54
 * @description： 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * <p>
 * 如果数组元素个数小于 2，则返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 * <p>
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 * <p>
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-gap
 * @modified By：
 * @version: 1.0
 */
public class MaximumGap {
    /**
     * 先排序，在遍历依次与保存的最大值比较
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        int resMax = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            resMax = Math.max(resMax, nums[i + 1] - nums[i]);
        }
        return resMax;
    }

    public static void main(String[] args) {
        MaximumGap gap = new MaximumGap();
        int[] nums = {6, 7, 18, 6, 3, 9, 1};
        System.out.println(gap.maximumGap(nums));
    }
}
