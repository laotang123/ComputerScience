package com.ljf.algorithm.Hot30;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/26 13:55
 * @description： 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * @modified By：
 * @version: 1.0
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearence:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;

    }

    public int singleNumberLJF(int[] nums) {
        /**
         * 自动机+位运算；
         * one one two；异或 与 反
         * two two one；异或 与 反
         */
        int ones = 0, twos = 0;

        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 2};
        System.out.println(new SingleNumber().singleNumberLJF(nums));
        System.out.println(15 ^ 1);
        System.out.println(~15);

        System.out.println(Integer.toBinaryString(-16));
        System.out.println(Integer.toBinaryString(15));
    }
}
