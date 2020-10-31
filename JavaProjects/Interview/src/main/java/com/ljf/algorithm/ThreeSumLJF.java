package com.ljf.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：ljf
 * @date ：2020/7/13 8:53
 * @description：双指针结合两数之和实现三数之和问题
 * @modified By：
 * @version: $ 1.0
 */
public class ThreeSumLJF {
    private List<List<Integer>> res;
    /**
     * 求解三数之和为0的问题；要求返回结果不重复。
     * 1.先排序，保证a<=b<=c
     * 2.固定a的位置，保证b<=c转换为一个双指针问题同时也是一个两数之和的问题
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums){
        res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //如果a值>0，最小值>0，结束循环
            if(nums[i] > 0) break;
            if(i == 0 || nums[i] != nums[i-1]) twoSum(nums,i);//一层去重
        }
        return res;
    }

    /**
     * 固定a求解，0-a=b+c
     *
     * @param nums
     * @param index：a值
     */
    public void twoSum(int[] nums, int index) {
        int i = index + 1;
        int j = nums.length - 1;//c从最右端开始

        int item = nums[index];
        int target = -item;

        int sum;
        while (i < j) {
            sum = nums[i] + nums[j];

            if (sum == target) {
                res.add(Arrays.asList(item,nums[i],nums[j]));
                //去重，类似111333这样的样例
                while (i + 1 < j && nums[i + 1] == nums[i]) i++;
                while (j - 1 > i && nums[j - 1] == nums[j]) j--;
            }
            if (sum < target) i++;
            else j--;
        }
    }

    public static void main(String[] args) {
        ThreeSumLJF solution = new ThreeSumLJF();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = solution.threeSum(nums);
        System.out.println(res);
    }
}
