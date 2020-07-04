package com.ljf.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/3 9:54
 * @modified By：
 * @version: $
 */
public class ZigZagLevelOrder {

  //锯齿形层次遍历
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();
    List<List<Integer>> resList = new ArrayList<List<Integer>>();

    if (root == null) {
      return resList;
    } else {
      stack1.add(root);
    }

    while (true) {
      //当前层的list
      ArrayList<Integer> curList1 = new ArrayList<>();
      while (!stack1.isEmpty()) {
        TreeNode curNode = stack1.pop();
        curList1.add(curNode.val);
        if (curNode.left != null) {
          stack2.add(curNode.left);
        }
        if (curNode.right != null) {
          stack2.add(curNode.right);
        }

      }
      if (!curList1.isEmpty()) {
        resList.add(curList1);
      }

      //跳出循环的条件
      if (stack1.isEmpty() && stack2.isEmpty()) {
        break;
      }
      //当前层的list
      ArrayList<Integer> curList2 = new ArrayList<>();
      while (!stack2.isEmpty()) {
        TreeNode curNode = stack2.pop();
        curList2.add(curNode.val);
        if (curNode.right != null) {
          stack1.add(curNode.right);
        }
        if (curNode.left != null) {
          stack1.add(curNode.left);
        }

      }
      if (!curList2.isEmpty()) {
        resList.add(curList2);
      }

    }

    return resList;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.right.right = new TreeNode(5);
    System.out.println(new ZigZagLevelOrder().zigzagLevelOrder(root));
  }
}
