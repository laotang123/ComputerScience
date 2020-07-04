package com.ljf.dataStructure.graph;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/11 9:23
 * @modified By：
 * @version: 1.0
 */
public class NumIsLandsLJF {
  /**
   * 思路：dfs遍历，遇到1，numLands加1，同时将该次dfs访问到的点都设置为0
   */

  /**
   * dfs递归单元
   */
  public void dfs(char[][] grid, int r, int c) {
    //判断当前索引是否越界
    int nr = grid.length;
    int nc = grid[0].length;

    if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
      return;
    }

    //上下左右递归
    grid[r][c] = '0';
    dfs(grid, r, c - 1);
    dfs(grid, r, c + 1);
    dfs(grid, r - 1, c);
    dfs(grid, r + 1, c);
  }

  public int numIslands(char[][] grid) {
    //判断和null值
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int numLands = 0;
    //图的行数和列数
    int nr = grid.length;
    int nc = grid[0].length;

    for (int i = 0; i < nr; i++) {
      for (int j = 0; j < nc; j++) {
        if (grid[i][j] == '1') {
          ++numLands;
          dfs(grid, i, j);
        }
      }
    }

    return numLands;
  }


}
