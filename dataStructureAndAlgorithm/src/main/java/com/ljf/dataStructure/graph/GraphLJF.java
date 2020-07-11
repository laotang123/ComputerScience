package com.ljf.dataStructure.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/18 9:31
 * @modified By：
 * @version: 1.0
 */
public class GraphLJF {

  //属性，顶点数和邻接表
  private int vertexes;
  private LinkedList<Integer>[] adjList;

  GraphLJF(int vertexes) {
    this.vertexes = vertexes;
    adjList = new LinkedList[vertexes];

    for (int i = 0; i < vertexes; i++) {
      adjList[i] = new LinkedList<>();
    }
  }

  /**
   * 添加边
   */
  void addEdge(int from, int to) {
    adjList[from].add(to);
  }

  void DFSUnit(boolean[] visited, int v) {
    //当前节点访问标志设置true
    visited[v] = true;

    System.out.print(v + "\t");
    //深度遍历当前节点的邻接点
    for (Integer adjv : adjList[v]) {
      if (!visited[adjv]) {
        DFSUnit(visited, adjv);
      }
    }
  }

  /**
   * 图的深度优先遍历
   */
  void DFS() {
    //记忆化节点列表
    boolean[] visited = new boolean[vertexes];

    for (int i = 0; i < vertexes; i++) {
      if (!visited[i]) {
        DFSUnit(visited, i);
      }
    }

    System.out.println();
  }

  /**
   * 图的广度优先遍历
   */
  void BFS() {
    //定义数据结构和标识符
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[vertexes];

    //默认从第一个出度不为0的节点开始遍历
    for (int i = 0; i < vertexes; i++) {
      if (adjList[i].size() > 0) {
        queue.offer(i);
        visited[i] = true;
      }
    }

    //TODO: 通过visited设置为true的数量是否等于顶点总数量进行提前终止程序操作
    //使用队列进行先进先出遍历
    while (!queue.isEmpty()) {
      Integer v = queue.poll();
      System.out.print(v + "\t");

      for (int item : adjList[v]) {
        if (!visited[item]) {//避免节点重复访问
          queue.offer(item);
          visited[item] = true;
        }

      }

    }
  }

  void topologicalSortUnit(int v, Stack<Integer> stack, boolean[] visited) {
    //深度遍历到最深处，然后压入栈中。出栈顺序表示拓扑顺序
    visited[v] = true;

    LinkedList<Integer> list = adjList[v];

    for (int item : list) {
      if (visited[item] == false) {
        topologicalSortUnit(item, stack, visited);
      }
    }

    //递归到最深处开始入栈
    stack.push(v);
  }

  /**
   * 输出图的拓扑排序结果
   * 拓扑排序：需要将入度边所连接的所有节点先访问，如果将一条有向边A->B表示先学A在学B，多条指向节点B表示学完B的所有基础学科才能学A
   * 深度优先遍历，访问B之前需要将接入B的所有节点压入栈中
   */
  void topologicalSort() {
    //创建数据结构和标识符
    boolean[] visited = new boolean[vertexes];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < vertexes; i++) {
      if (!visited[i]) {
        topologicalSortUnit(i, stack, visited);
      }
    }

    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + "\t");
    }
  }

  public static void main(String[] args) {
//    // Create a graph given in the above diagram
//    GraphLJF g = new GraphLJF(7);
//
//    g.addEdge(0, 1);
//    g.addEdge(0, 2);
//    g.addEdge(0, 3);
//    g.addEdge(1, 4);
//    g.addEdge(2, 5);
//    g.addEdge(3, 6);
    GraphLJF g = new GraphLJF(6);

    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);

    System.out.println("Following is a Topological " +
        "sort of the given graph");
    g.topologicalSort();

    System.out.println("深度优先遍历结果：");
    g.DFS();
    System.out.println("广度优先遍历结果：");
    g.BFS();
  }
}
