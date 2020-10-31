package com.ljf.dataStructure.heap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/15 10:04
 * @modified By：
 * @version: 1.0
 */
public class EventualSafeNodes {

  public List<Integer> eventualSafeNodes(int[][] G) {
    /**
     * 定义：出度为0的节点为安全节点，如果该节点去掉与安全节点连接的边出度为0，该节点也是安全节点
     */

    //定义常量
    int N = G.length;
    boolean[] isSafe = new boolean[N];

    //定义所需数据结构
    Queue<Integer> queue = new LinkedList<>();
    List<Set<Integer>> graph = new ArrayList<>();
    List<Set<Integer>> rgraph = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      graph.add(new HashSet<>());
      rgraph.add(new HashSet<>());
    }

    //队列保存出度为0的节点
    for (int i = 0; i < N; i++) {
      if (G[i].length != 0) {
        for (int i1 : G[i]) {
          graph.get(i).add(i1);
          rgraph.get(i1).add(i);
        }
      } else {
        queue.offer(i);
      }
    }

    //切除与安全节点连接的边
    while (!queue.isEmpty()) {
      int j = queue.poll();
      isSafe[j] = true;

      for (int i : rgraph.get(j)) {
        graph.get(i).remove(j);
        if (graph.get(i).isEmpty()) {
          queue.offer(i);
        }
      }
    }

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      if (isSafe[i]) {
        ans.add(i);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[][] G = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
    EventualSafeNodes safeNodes = new EventualSafeNodes();
    System.out.println(safeNodes.eventualSafeNodes(G));
  }
}
