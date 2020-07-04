package com.ljf.algorithm.Hot30;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/24 22:07
 * @description： 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * * <p>
 * * 你可以假设数组中无重复元素。
 * * <p>
 * * 示例 1:
 * * <p>
 * * 输入: [1,3,5,6], 5
 * * 输出: 2
 * * 示例 2:
 * * <p>
 * * 输入: [1,3,5,6], 2
 * * 输出: 1
 * * 示例 3:
 * * <p>
 * * 输入: [1,3,5,6], 7
 * * 输出: 4
 * * 示例 4:
 * * <p>
 * * 输入: [1,3,5,6], 0
 * * 输出: 0
 * * 链接：https://leetcode-cn.com/problems/search-insert-position
 * * @modified By：
 * * @version: 1.0
 * @modified By：
 * @version: 1.0
 */
public class SearchInsert {

    /**
     * 二分查找：
     * left为其实下标，right为终点下标
     * mid = left+(right+left)/2
     * 终止循环条件left>=right
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int mid = left + (right - left) / 2;

        while (left <= right) {//TODO: 终止条件<=
            //找到数组中存在目标值
            if (nums[mid] == target) {
                break;
            }
            //没有找到
            //左侧找
            if (nums[mid] > target) {
                right = mid - 1;//TODO: 记得-1
            } else {//右侧找
                left = mid + 1; //TODO: 记得+1
            }

            //更新中点値
            mid = left + (right - left) / 2;
        }

        return mid;
    }

    public static void main(String[] args) {
        SearchInsert insert = new SearchInsert();
        int[] nums = {1, 3, 5, 6};
        int target = 6;
        System.out.println(insert.searchInsert(nums, target));
    }


}
