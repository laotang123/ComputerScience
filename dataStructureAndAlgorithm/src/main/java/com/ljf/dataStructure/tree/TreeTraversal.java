package com.ljf.dataStructure.tree;

import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/5 8:03
 * @modified By：
 * @version: $
 */
public class TreeTraversal {
  /**
   * 实现树的递归和非递归三种遍历
   */

  /**
   * 递归前序遍历
   */
  public void recursivePreOrder(TreeNode node) {
    if (node != null) {
      System.out.print(node.val + " ");
      recursivePreOrder(node.left);
      recursivePreOrder(node.right);
    }
  }

  /**
   * 递归中序遍历
   */
  public void recursiveInOrder(TreeNode node) {
    if (node != null) {
      recursiveInOrder(node.left);
      System.out.print(node.val + " ");
      recursiveInOrder(node.right);
    }
  }

  /**
   * 非递归实现前序遍历 左子树一直遍历到根部，右子树只遍历一次
   */
  public void preOrder(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();

    if (root == null) {
      return;
    }
    TreeNode temp = root;

    while (!stack.isEmpty() || temp != null) {
      //遍历左子树
      if (temp != null) {
        System.out.print(temp.val + " ");
        stack.push(temp);
        temp = temp.left;
      } else
      //如果栈非空，则弹栈访问节点的右子树
      {
        temp = stack.pop();
        temp = temp.right;
      }
    }

  }

  /**
   * 非递归实现前序遍历 左子树一直遍历到根部，右子树只遍历一次
   */
  public void inOrder(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();

    if (root == null) {
      return;
    }
    TreeNode temp = root;

    while (!stack.isEmpty() || temp != null) {
      //遍历左子树
      if (temp != null) {
        stack.push(temp);
        temp = temp.left;
      } else {      //如果栈非空，则弹栈访问节点的右子树
        temp = stack.pop();
        System.out.print(temp.val + " ");
        temp = temp.right;
      }
    }

  }

  public static void main(String[] args) {
    TreeTraversal traversal = new TreeTraversal();

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);

    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);

    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);

    traversal.recursivePreOrder(root);
    System.out.println();
    traversal.preOrder(root);
    System.out.println("\n==========中序遍历==========");
    traversal.recursiveInOrder(root);
    System.out.println();
    traversal.inOrder(root);
  }
}
