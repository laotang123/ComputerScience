package com.ljf.dataStructure.graph;


import com.ljf.dataStructure.tree.TreeNode;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/11 10:01
 * @modified By：
 * @version: 1.0
 */
public class MaxDepth {

  public int unit(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int m = unit(node.left) + 1;
    int n = unit(node.right) + 1;
    return m > n ? m : n;
  }

  public int maxDepth(TreeNode root) {
    return unit(root);
  }
}
