package com.ljf.dataStructure.tree;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/24 10:50
 * @modified By：
 * @version: 1.0
 */
public class TreeNode {


  public int height;
  public int val;
  public TreeNode left, right;

  public TreeNode(int val) {
    val = val;
    left = right = null;
  }

}
