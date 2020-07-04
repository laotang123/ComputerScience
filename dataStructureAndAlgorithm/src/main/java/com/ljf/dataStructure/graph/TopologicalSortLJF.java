package com.ljf.dataStructure.graph;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/13 8:20
 * @modified By：
 * @version: 1.0
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 通过邻接表构建图的表示 1.addEdge方法插入边 2.topologicalSort方法获取拓扑排序数组
 */
class Graph {

  //顶点数量和邻接矩阵表
  private int vertexes;
  private LinkedList<Integer> adj[]; //连接数组，每一个元素对应一个邻接表

  Graph(int vertexes) {
    this.vertexes = vertexes;
    this.adj = new LinkedList[vertexes];

    //初始化每一个邻接表
    for (int i = 0; i < vertexes; i++) {
      adj[i] = new LinkedList<>();
    }
  }

  /**
   * 增加边
   */
  void addEdge(int v, int w) {
    adj[v].add(w);
  }

  void topologicalSortUnit(int v, Stack<Integer> stack, boolean[] visited) {
    //该顶点的访问标志为true
    visited[v] = true;

    LinkedList<Integer> adjList = adj[v];
    //确保当前节点的邻接点都已经访问过并且压入栈中

    Iterator<Integer> it = adjList.iterator();
    Integer i;
    while (it.hasNext()) {
       i = it.next();
      //递归层层深入
      if (visited[i] == false) {
        topologicalSortUnit(i, stack, visited);
      }
    }

    //将当前节点压入栈中
    stack.push(new Integer(v));
  }

  /**
   * 获取拓扑排序 1.遍历邻接数组 1.1 如果对应邻接表中所有的顶点都已经被访问过，则将当前顶点压入栈中 1.2 否则深度遍历邻接表(递归)中的每一个元素，保证入栈
   * 2.弹栈打印出元素即为拓扑排序
   */
  void topologicalSort() {
    Stack<Integer> stack = new Stack<>();

    //数组标志节点是否访问过
    boolean[] visited = new boolean[vertexes];

    for (boolean b : visited) {
      System.out.print(b + "\t");
    }
    System.out.println();
    for (int i = 0; i < vertexes; i++) {
      if(visited[i] == false){
        topologicalSortUnit(i, stack, visited);
      }
    }

    //打印弹栈结果
    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + "\t");
    }
  }
}

public class TopologicalSortLJF {

  public static void main(String[] args) {
    // Create a graph given in the above diagram
    Graph g = new Graph(6);

    g.addEdge(5, 2);
    g.addEdge(5, 0);
    g.addEdge(4, 0);
    g.addEdge(4, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);

    System.out.println("Following is a Topological " +
        "sort of the given graph");
    g.topologicalSort();
  }
}
