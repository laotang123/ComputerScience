package com.ljf.algorithm;

/**
 * @author ：ljf
 * @date ：2020/7/11 16:33
 * @description：偷盗问题
 * @modified By：
 * @version: $ 1.0
 */
public class RobLJF {
    /**
     * 相邻住户不能偷盗：
     * f(0) = nums[0]
     * f(1) = max(num[1] ,f(0))
     * f(2) = max(nums[2]+f(0),f(1))
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int curMax = 0;
        int preMax = 0;

        int temp;
        for (int num : nums) {
            temp = curMax;
            curMax = Math.max(preMax + num, curMax);
            preMax = temp;
        }
        return curMax;
    }

    /**
     * 环形村庄盗窃
     * 将环形村庄切成直线村庄
     *
     * @param nums
     * @return
     */
    public static int loopRob(int[] nums) {
        //特殊处理
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int res = Math.max(rob(nums, 1, nums.length),
                rob(nums, 0, nums.length - 1));
        return res;
    }

    /**
     * 指定村庄范围的直线盗窃
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int rob(int[] nums, int start, int end) {
        int pre = 0, cur = 0, tmp;
        for (int i = start; i < end; i++) {
            tmp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = tmp;
        }
        return cur;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 7, 9, 3, 1};
        int[] nums = {2,3,2};
//        System.out.println(rob(nums));
        System.out.println(loopRob(nums));
    }
}
