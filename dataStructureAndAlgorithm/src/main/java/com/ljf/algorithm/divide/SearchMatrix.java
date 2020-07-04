package com.ljf.algorithm.divide;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/28 10:24
 * @modified By：
 * @version: 1.0
 */

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 */
public class SearchMatrix {

  //初步想法：从中心向四周发散遍历，dfs
  public boolean searchMatrix(int[][] matrix, int target) {
    //从左下角出发遍历，如果target比当前值小，向上走。如果比当前值大，向右走
    int row = matrix.length - 1;
    int col = 0;

    //判空
    if (row == 0 || matrix == null) {
      return false;
    }
    while (row >= 0 && col < matrix[0].length) {
      if (matrix[row][col] < target) {
        //向上走
        row--;
      } else if (matrix[row][col] > target) {
        //向右走
        col++;
      } else {
        return true;
      }

    }

    return false;
  }

}
