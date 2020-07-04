package com.ljf.algorithm.dynamicPrograming;

import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/29 6:35
 * @modified By：
 * @version: $
 */
public class UniquePath {

  /**
   * 动态规划问题求解套路，记住之前问题的答案 1.问题拆解 2.状态定义 3.递推方程 4.实现 到达当前点的路径=相邻上面点的路径+相邻左边点的路径 d[i][j] = d[i-1][j] +
   * d[i][j-1]
   */
  public static int uniquePath(int m, int n) {
    int[][] map = new int[m][n];

    //将地图的左边界和上边界上点的路径值设为1
    for (int i = 0; i < m; i++) {
      map[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
      map[0][j] = 1;
    }

    //递推方程的实现
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        map[i][j] = map[i - 1][j] + map[i][j - 1];
//        System.out.print(map[i][j] + "\t");
      }
//      System.out.println();
    }

    for (int i = 0; i < m; i++) {
      System.out.println(Arrays.toString(map[i]));
    }
    return map[m - 1][n - 1];
  }

  /**
   * 不同路径的升级版1，如果当前点是障碍点将到达该点的路径数设置为0
   */
  public static int uniquePathOne(int m, int n) {
    int[][] map = new int[m][n];
    map[m / 2][n / 2] = -1;

    //地图展示，1表示障碍点
    System.out.println("地图展示");
    for (int i = 0; i < m; i++) {
      System.out.println(Arrays.toString(map[i]));
    }
    //初始化左边界和上边界
    for (int i = 0; i < m; i++) {
      if (map[i][0] == -1) {//边界处只要出现一处障碍点，后面的都不能通过
        map[i][0] = 0;
        break;
      }
      map[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
      if (map[0][j] == -1) {
        map[0][j] = 0;
        break;
      }
      map[0][j] = 1;
    }

    //递推方程
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (map[i][j] == -1) {//如果当前点为障碍点，则将到达该点的路径设置为0
          map[i][j] = 0;
        } else {
          map[i][j] = map[i - 1][j] + map[i][j - 1];
        }
      }
    }

    //路径展示
    System.out.println("路径展示");
    for (int i = 0; i < m; i++) {
      System.out.println(Arrays.toString(map[i]));
    }
    return map[m - 1][n - 1];
  }

  /**
   * 不同路径的升级版2，节点带权值，求权值最小的路径
   */
  public static int minimumPath(int[][] arr) {
    int m = arr.length;
    int n = arr[0].length;
    int[][] paths = new int[m][n];

    //初始化左边界和上边界的路径
    paths[0][0] = arr[0][0];
    for (int i = 1; i < m; i++) {
      paths[i][0] = paths[i - 1][0] + arr[i][0];
    }

    for (int j = 1; j < n; j++) {
      paths[0][j] = paths[0][j - 1] + arr[0][j];
    }

    //递推方法，最小路径 dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + arr[i][j]
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        paths[i][j] = Math.min(paths[i - 1][j], paths[i][j - 1]) + arr[i][j];
      }
    }

    return paths[m - 1][n - 1];
  }

  public static void main(String[] args) {
//    System.out.println(uniquePath(3, 3));
//    System.out.println(uniquePathOne(4, 4));
    int[][] paths = new int[][]{
        {1, 3, 1},
        {1, 5, 1},
        {4, 2, 1}
    };
    System.out.println(minimumPath(paths));
  }
}
