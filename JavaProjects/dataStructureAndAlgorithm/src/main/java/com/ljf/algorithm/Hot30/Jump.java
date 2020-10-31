package com.ljf.algorithm.Hot30;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/25 14:16
 * @description： 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * @modified By：
 * @version: 1.0
 */
public class Jump {
    //贪心算法：只在乎每一次跳跃都能到最远的距离
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            //在该次跳跃的可选范围内找到所能跳跃的最大范围
            if (i == end) { //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }

        }
        return steps;
    }

    public int jumpLJF(int[] nums) {
        //本次跳跃点的边界
        int end = 0;
        int maxPosition = 0;//所能到达的最远位置
        int steps = 0; //到达终点需要的最少步数

        //FIXME：临时变量的更新替换循环, 如果换成nums.length造成到达末尾多加1现象
        for (int i = 0; i < nums.length - 1; i++) {

            //从当前位置所能到达的位置，当前的位置+能跳跃的长度
            maxPosition = Math.max(maxPosition, nums[i] + i);

            //到达本次跳跃点的边界，更细边界；步数+1
            if (i == end) {
                end = maxPosition;
                steps++;
            }
            //FIXME：会报错
//            if(maxPosition == nums.length-1){
//                steps++;
//                break;
//            }
        }

        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        Jump solution = new Jump();
        System.out.println(solution.jumpLJF(nums));
    }
}
