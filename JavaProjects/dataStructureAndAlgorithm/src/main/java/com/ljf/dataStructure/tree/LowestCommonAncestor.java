package com.ljf.dataStructure.tree;


/**
 * @author ：ljf
 * @date ：Created in 2020/1/21 7:48
 * @modified By：
 * @version: $
 */



class Solution {

  private TreeNode res;

  //递归单元
  public boolean recurseUnit(TreeNode curNode, TreeNode p, TreeNode q) {
    //叶子节点
    if (curNode == null) {
      return false;
    }

    //当前节点
    int mid = (curNode == p || curNode == q) ? 1 : 0;

    //左子树
    int left = recurseUnit(curNode.left, p, q) ? 1 : 0;

    //右子树
    int right = recurseUnit(curNode.right, p, q) ? 1 : 0;

    if (mid + left + right >= 2) {
      this.res = curNode;
    }
    return (mid + left + right) > 0;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //构造递归单元，从当前节点考虑问题，不深究
    //最近祖先节点：当前节点，左子树和右子树至少有两个满足p，q节点即可
    recurseUnit(root,p ,q);
    return this.res;
  }
}

public class LowestCommonAncestor {

  public static void main(String[] args) {
    Solution solution = new Solution();
//    solution.lowestCommonAncestor()
  }
}
