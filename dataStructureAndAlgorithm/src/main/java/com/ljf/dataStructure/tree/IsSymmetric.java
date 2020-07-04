package com.ljf.dataStructure.tree;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/3 8:44
 * @modified By：
 * @version: $
 */
public class IsSymmetric {

  public boolean isMirror(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) {
      return true;
    }
    if (t1 == null || t2 == null) {
      return false;
    }

    return (t1.val == t2.val) && (isMirror(t1.left, t2.right)
        && isMirror(t1.right, t2.left));
  }

  public boolean isSymmetric(TreeNode root) {
    if(root == null) return true;
    return isMirror(root.left,root.right);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    System.out.println(new IsSymmetric().isSymmetric(root));
  }
}
