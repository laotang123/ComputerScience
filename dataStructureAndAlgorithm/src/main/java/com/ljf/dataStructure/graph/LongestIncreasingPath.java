package com.ljf.dataStructure.graph;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/19 9:38
 * @modified By：
 * @version: 1.0
 */
public class LongestIncreasingPath {

  private final int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  private int m, n;

  /**
   * 最长增长路径，深度优先遍历图结构
   * @param matrix
   * @return
   */
  public int longestIncreasingPath(int[][] matrix) {
    //判空
    if (matrix.length == 0) {
      return 0;
    }
    //数据结构
    m = matrix.length;
    n = matrix[0].length;
    int[][] cache = new int[m][n]; //cache[i][j]表示从节点[i][j]出发，最长增长路径

    int ans = 0;
    //递归算法
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        ans = Math.max( ans, dfs(matrix,i, j, cache));
      }
    }
    return ans;
  }

  private int dfs(int[][] matrix, int i, int j, int[][] cache) {
    if (cache[i][j] > 0) {
      return cache[i][j];
    }
    //从当前节点出发向四个方向行走
    for (int[] dir : dirs) {
      int x = i + dir[0];
      int y = j + dir[1];
      //防止坐标越界
      if (x > 0 && y > 0 && x < m && y < n && matrix[x][y] > matrix[i][j]) {
        cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));//从四个方向选出最长的增长路径
      }
    }
    return ++cache[i][j];
  }
}
