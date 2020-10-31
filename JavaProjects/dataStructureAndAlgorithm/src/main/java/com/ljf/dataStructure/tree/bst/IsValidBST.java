package com.ljf.dataStructure.tree.bst;

import com.ljf.dataStructure.tree.TreeNode;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/7 8:28
 * @modified By：
 * @version: $
 */
public class IsValidBST {

  public boolean isValidBST(TreeNode root) {
    return helper(root, null, null);
  }

  /**
   * 当前节点的上下界动态变化 递归原则，做当前节点的操作。然后左子树和右子树调用当前函数 左子树和右子树和当前树为相同结构
   * 只要有一个子树不符合结构，就返回false
   */
  public boolean helper(TreeNode node, Integer lower, Integer upper) {
    //判空
    if (node == null) {
      return true;
    }
    //多个条件同时成立返回true分解为单个条件的方向为false
    int val = node.val;
    if (lower != null && val <= lower) {
      return false;
    }
    if (upper != null && val >= upper) {
      return false;
    }

    if (!helper(node.left, lower, val)) {
      return false;
    }
    if (!helper(node.right, val, upper)) {
      return false;
    }

    return true;
  }
}
