package com.ljf.cambricon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ：ljd
 * @date ：Created in 2020/7/13 20:37
 * @description：任务 面试官提出的问题将出现在这里。
 * <p>
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
public class SectionCompute {
    /**
     * 暴力解题版本
     * N个非零整数，相乘为负为沮丧区间，为正是微笑区间
     * 思路：
     * 1-N个窗口，依次扫描输入数组
     *
     * @param nums
     * @return
     */
    public int[] force(int[] nums) {
        int[] res = {0, 0};
        //异常判断 TODO
        if (nums == null || nums.length == 0) {
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
                if (tmp < 0) res[0] += 1;
                else res[1] += 1;

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


    /**
     * 动态规划关键在推导出递推方程，状态转移之间的逻辑关系；因为区间是连续的，所以n元素的加入只影响[0,n-1]
     * 由于第n个元素的加入，区间[0,n-1]的微笑和沮丧区间没有改变，因为第n个元素的加入，增加了那些微笑和沮丧区间？
     * 如果n元素为正，则[0,n]的微笑区间=[0,n-1]微笑区间*2+1，[0,n]沮丧区间=[0,n-1]沮丧区间*2
     * 如果n元素为负，则[0,n]的微笑区间=[0,n-1]沮丧区间*2，[0,n]沮丧区间=[0,n-1]微笑区间*2+1
     *
     * @param nums
     * @return：[沮丧区间个数，微笑区间个数]
     */
    public int[] dp(int[] nums) {
        int[] res = {0, 0};
        int[] update = {0, 0};//每一次元素的加入增加的区间

        int tmp;//暂存更新值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                tmp = update[0];
                update[0] = update[1]  + 1;
                update[1] = tmp ;
            } else {
                update[0] = update[0] ;
                update[1] = update[1]  + 1;
            }
            //更新结果
            res[0] += update[0];
            res[1] += update[1];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, -1, 1, -1, 1};
        SectionCompute solution = new SectionCompute();
        System.out.println(Arrays.toString(solution.force(nums)));
        System.out.println(Arrays.toString(solution.dp(nums)));
    }
}
