package com.ljf.dataStructure.tree;

import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/21 8:42
 * @modified By：
 * @version: $
 */
public class NoCurseMidOrder {

  //非递归中序遍历
  public static void midOrder(TreeNode node) {
    if (node == null) {
      return;
    }
    //创建栈
    Stack<TreeNode> nodeStack = new Stack<>();

    TreeNode p = node;
    //压栈或者弹栈，保证栈非空或p非空
    while (!nodeStack.isEmpty() || p != null) {
      if (p != null) {
        nodeStack.push(p);
        p = p.left;
      } else {
        p = nodeStack.peek();
        System.out.print(nodeStack.pop().val + "\t");
        p = p.right;
      }
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    TreeNode node8 = new TreeNode(8);
    TreeNode node9 = new TreeNode(9);

    root.left = node2;
    root.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;
    node4.left = node8;
    node4.right = node9;
    node8.left = new TreeNode(10);

    midOrder(root);

  }
}
