package com.ljf.algorithm.Hot30;

import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/25 17:54
 * @description： 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * @modified By：
 * @version: 1.0
 */
public class RotateImage {
    /**
     * 思路：
     * 图像旋转，每个点都要顺时针移动n-1次到达指定位置
     * 先按行移动，如果到达边界移动次数不够。按列移动
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
