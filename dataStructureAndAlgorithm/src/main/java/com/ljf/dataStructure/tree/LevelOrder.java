package com.ljf.dataStructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/4 9:55
 * @modified By：
 * @version: $
 */
public class LevelOrder {

  //层次遍历
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> resList = new ArrayList<List<Integer>>();
    if (root == null) {
      return resList;
    }
    //创建队列
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> list = new ArrayList<>();
      while (size > 0) {
        TreeNode curNode = queue.poll();
        list.add(curNode.val);

        //添加非空子节点，防止空指针异常
        if (curNode.left != null) {
          queue.offer(curNode.left);
        }
        if (curNode.right != null) {
          queue.offer(curNode.right);
        }

        //队列长度减一
        size--;
      }

      //添加这一层的遍历结果
      resList.add(list);
    }
    return resList;

  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.right.right = new TreeNode(5);
    System.out.println(new LevelOrder().levelOrder(root));
  }
}
