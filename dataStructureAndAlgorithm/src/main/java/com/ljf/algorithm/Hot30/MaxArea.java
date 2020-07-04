package com.ljf.algorithm.Hot30;


/**
 * @author ：ljf
 * @date ：Created in 2020/4/22 13:39
 * @description： 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * @modified By：
 * @version: 1.0
 */
public class MaxArea {

    /**
     * 双指针；有重复计算使用动态规划
     * 关键点：
     * 1.假设一开始，左边界值为x，右边界值为y且x<y
     * 此时无论y向左移动几步都是小于当前围成的面积；
     * 所以以x为左边界的就可以丢弃了。
     * 2.重复第一个步骤
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int res = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            res = Math.max(res, (j - i) * Math.min(height[i], height[j]));

            if (height[i] < height[j]) {//左边小，向右移
                i++;
            } else {//右边小向左移
                j--;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        MaxArea area = new MaxArea();
        System.out.println(area.maxArea(height));
    }
}
