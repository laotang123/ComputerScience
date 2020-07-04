package com.ljf.algorithm.Hot30;

import java.util.*;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/26 13:32
 * @description： 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 *  
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * @modified By：
 * @version: 1.0
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LevelOrder {

    /**
     * 队列实现树的层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        //空树判断
        if (root == null) {
            return resList;
        }
        //创建队列
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        //当前队列中的元素就是树这一层的元素个数
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            //获取当前这一层节点的元素
            while (size > 0) {
                TreeNode curNode = queue.poll();
                list.add(curNode.val);

                //访问当节点的左右非null节点，添加到queue中
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }

                //当前层的节点数量-1
                size--;

            }

            //将本层的节点列表添加到结果列表中
            resList.add(list);
        }
        return resList;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        LevelOrder order = new LevelOrder();
        System.out.println(order.levelOrder(root));
    }
}
