package com.ljf.dataStructure.tree;

import java.util.Stack;
import javafx.util.Pair;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/3 7:27
 * @modified By：
 * @version: $
 */
//class TreeNode {
//
//  int val;
//  TreeNode left;
//  TreeNode right;
//
//  TreeNode(int x) {
//    val = x;
//  }
//}

public class MinDepth {

  //  public static int minDepth(TreeNode root) {
//    LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
//    if (root == null) {
//      return 0;
//    }
//    else {
//      stack.add(new Pair(root, 1));
//    }
//
//    int min_depth = Integer.MAX_VALUE;
//    while (!stack.isEmpty()) {
//      Pair<TreeNode, Integer> current = stack.pollLast();
//      root = current.getKey();
//      int current_depth = current.getValue();
//      if ((root.left == null) && (root.right == null)) {
//        min_depth = Math.min(min_depth, current_depth);
//      }
//      if (root.left != null) {
//        stack.add(new Pair(root.left, current_depth + 1));
//      }
//      if (root.right != null) {
//        stack.add(new Pair(root.right, current_depth + 1));
//      }
//    }
//    return min_depth;
//  }
  public static int minDepth(TreeNode root) {
    //左右子树都为null的为叶子节点，根节点到叶子节点的深度与最小深度比较
    Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
    if (root == null) {
      return 0;
    }
    int min_depth = Integer.MAX_VALUE;
    stack.add(new Pair<>(root,1));

    while (!stack.isEmpty()){
      Pair<TreeNode, Integer> pair = stack.pop();
      TreeNode curNode = pair.getKey();
      Integer curDepth = pair.getValue();

      if(curNode.left == null && curNode.right == null){
        min_depth = Math.min(min_depth,curDepth);
      }

      if(curNode.left!=null){
        stack.add(new Pair<>(curNode.left,curDepth+1));
      }
      if(curNode.right!=null){
        stack.add(new Pair<>(curNode.right,curDepth+1));
      }
    }

    return min_depth;
  }


  public static void main(String[] args) {
    /**
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    System.out.println(minDepth(root));
  }
}
