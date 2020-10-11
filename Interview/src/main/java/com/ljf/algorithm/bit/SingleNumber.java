package com.ljf.algorithm.bit;

/**
 * @author ：ljf
 * @date ：Created in 2020/10/11 12:17
 * @description：只出现一次的数字；异或计算 a^0=a,a^a=0;  满足结合律和交换律
 * @modified By：
 * @version: 1.0
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 3, 3};
        SingleNumber solution = new SingleNumber();
        System.out.println(solution.singleNumber(nums));
    }
}
