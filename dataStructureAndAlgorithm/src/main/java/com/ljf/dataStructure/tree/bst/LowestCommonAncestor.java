package com.ljf.dataStructure.tree.bst;


import com.ljf.dataStructure.tree.TreeNode;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/7 9:21
 * @modified By：
 * @version: $
 */
public class LowestCommonAncestor {

  private TreeNode res;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //构造递归单元，从当前节点考虑问题，不深究
    //最近祖先节点：当前节点，左子树和右子树至少有两个满足p，q节点即可
    recurseUnit(root, p, q);
    return this.res;
  }

  private boolean recurseUnit(TreeNode curNode, TreeNode p, TreeNode q) {
    //当前节点的处理逻辑
    //判空
    if (curNode == null) {
      return false;
    }

    //当前节点
    int mid = (curNode == p || curNode == q) ? 1 : 0;

    //左子树
    int left = recurseUnit(curNode.left, p, q) ? 1 : 0;

    //右子树
    int right = recurseUnit(curNode.right, p, q) ? 1 : 0;

    if(mid+right+left>=2){
      this.res = curNode;
    }

    return (mid+left+right)>0;
  }
}
