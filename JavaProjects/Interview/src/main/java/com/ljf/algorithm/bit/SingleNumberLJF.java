package com.ljf.algorithm.bit;

/**
 * @author ：ljf
 * @date ：Created in 2020/10/11 10:55
 * @description： 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * @modified By：
 * @version: 1.0
 */
public class SingleNumberLJF {
    /**
     * 思路：
     * 一次遍历获取最大值，找到最大值二进制的总长度
     * 根据数组的元素，将元素作为索引。索引对应的二进制做非操作
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int binLen = 0;
        for (int num : nums) {
            binLen = Math.max(binLen, num);
        }
//        return nums[0];
        String binaryString = Integer.toBinaryString((int) Math.pow(2, binLen) - 1);

        System.out.println(binaryString);
//        for (int index : nums) {
//            binaryString.replace(binaryString.charAt(index),'a');
//        }
        //FIXME: 数组中的最大数字会对数组索引越界
        return 0;
    }

    public static void main(String[] args) {
        int i = (int) Math.pow(2, 2) - 1;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));

        SingleNumberLJF ljf = new SingleNumberLJF();
        int[] nums = {2,2,1,5,5};
//        ljf.singleNumber(nums);
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).length());
    }
}
