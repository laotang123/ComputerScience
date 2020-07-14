package com.ljf.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：ljf
 * @date ：2020/7/12 23:03
 * @description：快慢指针解决三数之和问题
 * @modified By：
 * @version: $ 1.0
 */
public class ThreeSum {
    List<List<Integer>> res;
    public List<List<Integer>> threeSum(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break; // min > 0 后续没必要找了
            if (i == 0 || nums[i] != nums[i - 1]) twoSum(nums, i); // 1层去重
        }

        return res;
    }

    public void twoSum(int[] nums, int index) {
        int i = index + 1, j = nums.length - 1, item = nums[index], target = -item;
        long sum;

        while (i < j) {
            sum = (long) nums[i] + (long) nums[j];
            if (sum == target) {
                res.add(Arrays.asList(item, nums[i], nums[j]));
                // 2层去重，111333这种情况，只需要算一次，i,j 移动到 最内部 1，3
                while (i + 1 < j && nums[i+1] == nums[i]) i++;
                while (j - 1 > i && nums[j-1] == nums[j]) j--;
            }

            if (sum > target) j--;
            else i++;
        }
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = solution.threeSum(nums);
        System.out.println(res);
    }
}
