package com.ljf.dataStructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/21 9:15
 * @modified By：
 * @version: 1.0
 */
public class FindMinHeightTrees {

  /**
   * 最终可能的节点只有一个或两个
   * @param n
   * @param edges
   * @return
   */
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    //判空
    if (n <= 0) {
      return new ArrayList<Integer>();
    }
    //数据结构，数组+linkedlist(邻接表)存储图。队列中存储最外层节点
    //剥离原则，度为1的节点表示最外层
    List<Integer> resList = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();

    //创建相应数据结构，邻接表，度
    LinkedList<Integer>[] adjList = new LinkedList[n];
    int[] degree = new int[n];

    //初始化邻接表，度数组和队列
    for (int i = 0; i < n; i++) {
      adjList[i] = new LinkedList<>();
    }

    for (int[] edge : edges) {
      degree[edge[0]]++;
      degree[edge[1]]++;

      //无向图，冗余存储
      adjList[edge[0]].add(edge[1]);
      adjList[edge[1]].add(edge[0]);
    }

    for (int i = 0; i < n; i++) {
      if (degree[i] == 1) {
        queue.offer(i);
      }
    }

    int rst = n;

    if (rst == 1) {
      resList.add(0);
    }
    //使用队列开始一层层剥离
    while (rst != 1 && rst != 2) {
      int len = queue.size();
      rst = n - len;//剩余节点数量

      for (int i = 0; i < len; i++) {
        int v = queue.poll();
        //将与该节点连接的节点度值都减一
        for (int adjV : adjList[v]) {
          degree[adjV]--;

          //做判断，如果度值等于1，则外最外层节点。加入队列中,同时删除节点
          if (degree[adjV] == 1) {
            queue.offer(adjV);
          }
        }
      }
    }

    //此时队列中剩余的值为最终返回值
    while (!queue.isEmpty()){
      resList.add(queue.poll());
    }
    return resList;
  }

  public static void main(String[] args) {
    int n = 6;
    int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3},{5, 4}};
    FindMinHeightTrees FMT = new FindMinHeightTrees();
    System.out.println(FMT.findMinHeightTrees(n, edges));
  }
}
