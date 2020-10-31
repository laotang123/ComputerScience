package com.ljf.algorithm.Hot30;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/28 14:39
 * @description： 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * <p>
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * <p>
 * 你的解法应该是 O(logN) 时间复杂度的。
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * @modified By：
 * @version: 1.0
 */
public class FindPeakElement {

    //FIXME：递归实现的算法时间复杂度为O(logn)

    /**
     * 数组的局部是有序的；
     * 至少峰值与前一个元素，或者后一个元素式有序的；
     *
     * @param nums
     * @return
     */
    public int findPeakElementLJF(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] > nums[mid + 1]) {
                //向左找
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1])
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5, 6};
        FindPeakElement element = new FindPeakElement();
        System.out.println(element.findPeakElementLJF(nums));
    }
}
