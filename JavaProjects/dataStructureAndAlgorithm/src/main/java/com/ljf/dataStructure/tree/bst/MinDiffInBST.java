package com.ljf.dataStructure.tree.bst;


import com.ljf.dataStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/7 7:25
 * @modified By：
 * @version: $
 */
public class MinDiffInBST {

  /**
   * 返回二叉搜索树的中序遍历数组
   */
  public List<Integer> inOrder(TreeNode root) {
    List<Integer> resList = new ArrayList<>();
    if (root == null) {
      return resList;
    }

    //构建栈
    Stack<TreeNode> stack = new Stack<>();
    TreeNode temp = root;

    //遍历树
    while (!stack.isEmpty() || temp != null) {
      if (temp != null) {
        stack.push(temp);
        temp = temp.left;//向左遍历
      } else {
        temp = stack.pop();
        resList.add(temp.val);
        temp = temp.right;
      }
    }

    return resList;
  }
  //返回二叉搜索树中任意两节点差值中的最小值

  /**
   * 中序遍历返回树的有序数组， 遍历有序数组找出最小值 4 /   \ 2      6 / \ 1   3
   */
  public int minDiffInBST(TreeNode root) {
    //获取树的有序数组
    List<Integer> orderList = inOrder(root);

    int res = Integer.MAX_VALUE;
    for (int i = 0; i < orderList.size() - 1; i++) {
      if (orderList.get(i + 1) - orderList.get(i) < res) {
        res = orderList.get(i + 1) - orderList.get(i);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right = new TreeNode(6);

    MinDiffInBST solution = new MinDiffInBST();
    System.out.println(solution.minDiffInBST(root));
  }

}
