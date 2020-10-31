package com.ljf.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ：ljf
 * @date ：2020/7/11 9:21
 * @description：闪银奇异一面全排列，回溯实现
 * @modified By：
 * @version: $ 1.0
 */
public class PermuteLJF {
    public List<List<Integer>> permute(int[] nums) {
        /**
         * 数组nums转为ArrayList
         * 创建回溯函数，接受结果的list
         */
        List<Integer> tmp = new ArrayList<>();

        for (int num : nums) {
            tmp.add(num);
        }

        int end = nums.length;
        List<List<Integer>> resList = new ArrayList<>();

        //回溯算法实现
        backtrack(0, tmp, resList, end);
        return resList;
    }

    private void backtrack(int first, List<Integer> nums, List<List<Integer>> resList, int end) {
        //记录有效结果
        if (first == end) {
            resList.add(new ArrayList<>(nums));
        }

        for (int i = first; i < end; i++) {
            //未排序的数值填充坑位
            Collections.swap(nums, first, i);

            //继续填充
            backtrack(first + 1, nums, resList, end);

            //回溯
            Collections.swap(nums, first, i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3,4};
        PermuteLJF solution = new PermuteLJF();
        List<List<Integer>> res = solution.permute(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
