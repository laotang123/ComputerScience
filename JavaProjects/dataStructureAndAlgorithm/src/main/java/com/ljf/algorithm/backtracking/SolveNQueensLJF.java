package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/6 16:19
 * @modified By：
 * @version: 1.0
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
一句话解释n皇后问题：两个皇后不能再同一行，同一列，同一对角线
 */
public class SolveNQueensLJF {

  /*
  回溯算法思路：
    遍历棋盘：
      目标结果查找成功，最后一行落子皇后成功。
      落子：将该行落子皇后影响的坐标全部设置为1
      失败：该行无坐标可落子皇后
      回溯(悔棋)：将该行落子皇后影响的坐标全部设置为0，恢复原貌

      回溯按照行进行遍历，保证了每一行只有一个皇后。其次保证每一列，主对角线和次对角线都只有一个皇后
      主对角线：行索引+列索引=常量 共2n-1条
      次对角线：行索引-列索引=常量 共2n-1条
      注：次对角线如果使用ri-ci表示索引会出现负值，所以向正半轴平移n-1的位置，索引从0开始
        ri-ci+n-1
   */
  int rows[];//0/1代表当前索引的列是否在攻击范围
  int queens[]; //存储列号，代表皇后所在位置queue[0] = 1,皇后在0行1列
  //主对角线
  int dales[];

  //次对角线
  int hills[];

  //皇后数量
  int n;
  //创建返回结果
  List<List<String>> resList = new ArrayList<>();

  public List<List<String>> solveNQueens(int n) {
    //非法数据
    if (n <= 0) {
      return null;
    }

    //属性初始化
    this.n = n;
    rows = new int[n];
    queens = new int[n];
    dales = new int[2 * n - 1];
    hills = new int[2 * n - 1];

    //从第0行开始尝试放皇后
    backTrack(0);
    return resList;
  }

  private void backTrack(int ri) {
    //当前行位置，尝试每一列
    for (int ci = 0; ci < n; ci++) {
      //判断当前位置是否处于攻击位置
      if (notUnderAttack(ri, ci)) {
        //放置皇后
        placeQueue(ri, ci);

        //找到一个可行解
        if (ri + 1 == n) {
          addSolution();
        }//继续下一行
        else {
          backTrack(ri + 1);
        }
        //回溯
        removeQueue(ri, ci);
      }

    }
  }

  /*
  将该位置的皇后删除掉，已经对应的攻击范围
   */
  private void removeQueue(int ri, int ci) {
    queens[ri] = 0;
    rows[ci] = 0;
    hills[ri - ci + n - 1] = 0;
    dales[ri + ci] = 0;
  }

  /*
  根据queens位置添加可行解
   */
  private void addSolution() {
    //创建solution
    ArrayList<String> solution = new ArrayList<>();
    for (int ri = 0; ri < n; ri++) {
      int col = queens[ri];
      //每一列使用一个字符串表示
      StringBuilder sb = new StringBuilder();
      for (int ci = 0; ci < col; ci++) {
        sb.append('.');
      }
      sb.append('Q');
      for (int ci = col + 1; ci < n; ci++) {
        sb.append('.');
      }
      solution.add(sb.toString());
    }
    resList.add(solution);
  }

  private void placeQueue(int ri, int ci) {
    //设置皇后位置和影响的攻击范围
    queens[ri] = ci;
    rows[ci] = 1;//表示该列处于占有状态，别的皇后不能放置该列
    hills[ri - ci + n - 1] = 1;
    dales[ri + ci] = 1;
  }

  private boolean notUnderAttack(int ri, int ci) {
    int res = rows[ci] + hills[ri - ci + n - 1] + dales[ri + ci];
    //只要大于0证明处于攻击范围
    return res > 0 ? false : true;
  }


  public static void main(String[] args) {
    SolveNQueensLJF solveNQueensLJF = new SolveNQueensLJF();
    List<List<String>> nQueens = solveNQueensLJF.solveNQueens(8);

    System.out.println(nQueens.size());
    nQueens.forEach(System.out::println);


  }
}
