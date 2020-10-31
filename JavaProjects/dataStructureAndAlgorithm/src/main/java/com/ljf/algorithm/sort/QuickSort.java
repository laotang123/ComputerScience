package com.ljf.algorithm.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/29 13:42
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class QuickSort {
    // start和end为前闭后闭
    private void nonRec_quickSort(int[] nums, int start, int end) {
        // 用栈模拟
        Stack<Integer> stack = new Stack<>();
        if (start < end) {
            stack.push(end);
            stack.push(start);
            while (!stack.isEmpty()) {
                int l = stack.pop();
                int r = stack.pop();
                int index = partition(nums, l, r);

                //左分区的排序
                if (l < index - 1) {
                    stack.push(index - 1);
                    stack.push(l);
                }
                //右分区的排序
                if (r > index + 1) {
                    stack.push(r);
                    stack.push(index + 1);
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            //从右边开始找，找到一个小于pivot
            while (start < end && nums[end] >= pivot)
                end--;
            nums[start] = nums[end];
            //从左边开始找，找到一个大于pivot，两个条件都满足的情况下；做交换
            while (start < end && nums[start] <= pivot)
                start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    /**
     * 非递归实现快速排序
     */

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {8, 9, 2, 3, 7, 4, 6};
        quickSort.nonRec_quickSort(nums, 0, nums.length - 1);
    }

}
