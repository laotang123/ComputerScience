package com.ljf.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：ljf
 * @date ：2020/7/12 12:15
 * @description：图问题中的岛屿问题
 * @modified By：
 * @version: $ 1.0
 */
public class GraphIsland {
    /**
     * dfs查找岛屿
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid == null || grid.length == 0) {
            return res;
        }

        //dfs访问，遇到单元格为1时结果+1
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
//                    DFSUnit(grid, i, j, m, n);
                    BFSUnit(grid, i, j, m, n);
                    res += 1;
                }
            }
        }
        return res;
    }

    /**
     * dfs遍历图
     *
     * @param grid：图二维数组
     * @param i：点横坐标
     * @param j：点纵坐标
     * @param m：横坐标长度
     * @param n：纵坐标长度
     */
    private void DFSUnit(char[][] grid, int i, int j, int m, int n) {
        //递归结束条件
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        //遍历相邻点，dfs
        DFSUnit(grid, i - 1, j, m, n);
        DFSUnit(grid, i + 1, j, m, n);
        DFSUnit(grid, i, j - 1, m, n);
        DFSUnit(grid, i, j + 1, m, n);

    }

    private void BFSUnit(char[][] grid, int i, int j, int m, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};


        //i*n+j唯一标识节点
        queue.offer(i * n + j);

        int item, x, y;
        while (!queue.isEmpty()) {
            item = queue.poll();
            i = item / n;
            j = item % n;
            grid[i][j] = '0';

            for (int k = 0; k < dx.length; k++) {
                x = i + dx[k];
                y = j + dy[k];

                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '0') continue;
                queue.offer(x * n + y);
            }
        }

    }

    public static void main(String[] args) {
        GraphIsland graph = new GraphIsland();
//        char[][] grid = {
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}};
        char[][] grid = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'0', '1', '0'}};

        System.out.println(graph.numIslands(grid));
    }
}
