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

    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = solution.threeSum(nums);
        System.out.println(res);
        System.out.println(solution.threeSum2(nums));
    }
}
