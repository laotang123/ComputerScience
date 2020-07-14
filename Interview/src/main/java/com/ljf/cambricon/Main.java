package com.ljf.cambricon;

/**
 * @author ：ljd
 * @date ：Created in 2020/7/13 20:37
 * @description：任务
 * 面试官提出的问题将出现在这里。
 *
 * 区间计数
 * 限定语言：C++、C++14、Java、C
 * 有一个序列A，包含N个非0整数。我们定义一个区间[l,r]（l<=r）为微笑区间，当且仅当A[l]*A[l+1]*…*A[r−1]*A[r] 为正数；定义一个区间[l,r] （l<=r）为沮丧区间，当且仅当A[l]*A[l+1]*…*A[r−1]*A[r] 为负数。现在请你计算一下在[0, N-1]可以包含的所有不重复合法区间中，沮丧区间的数目X和微笑区间的数目Y。注意，X+Y应该等于N*(N+1)/2。
 * 输入描述
 * 输入数据包括两行。第一行为一个整数N，第二行包含N个整数，每个数之间用空格隔开，输入数据保证1<=N<=200000，且序列A中的元素可以被int32类型表示。
 * 输出描述
 * 输出数据包括一行，包含两个数，分别是X和Y，分别用空格隔开
 * 示例1
 * 输入
 * 5
 * 1 -1 1 -1 1
 * 输出
 * 8 7
 * @modified By：
 * @version: 1.0
 */
public class Main {
    /**
     * N个非零整数，相乘为负为沮丧区间，为正是微笑区间
     * 思路：
     * 1-N个窗口，依次扫描输入数组
     * @param nums
     * @return
     */
    public int[] func(int[] nums) {
        int[] res = {0, 0};
        //异常判断 TODO
        if(nums == null || nums.length == 0){
            return res;
        }
        int interval = 0; //窗口大小

        int tmp;
        while (interval < nums.length) {
            int i = 0;
            int j = i + interval;
            while (j < nums.length) {
                //计算窗口内乘积
                tmp = compute(nums, i, j);
                if (tmp < 0) nums[0] += 1;
                else nums[1] += 1;

                //窗口滑动
                i += 1;
                j = i + interval;
            }
            //窗口增大
            interval += 1;
        }

        return res;
    }

    /**
     * 计算指定区间内数值乘积
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public int compute(int[] nums, int start, int end) {
        int res = 1;
        for (int i = start; i <= end; i++) {
            res *= nums[i];
        }
        return res;
    }
}
