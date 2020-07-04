#!/usr/bin/python3
# -*-coding:utf-8 -*-
# @Time     : Created in 2019/11/1 10:45
# @Author    : ljf
# @modified By:
# @description: 
# @Software   : PyCharm
"""
给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。
"""

class TreeNode(object):
    def __init__(self, x):
        """
        树节点的定义
        :param x:
        """
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def binaryTreePaths(self, root: TreeNode):
        """树的深度优先遍历"""
        node_str_list = []
        root_str = ""
        def DfsUnit(node,node_str):
            """
            递归单元
            """
            if node is not None:
                if node_str == "":
                    node_str = str(node.val)
                else:
                    node_str += "->{}".format(node.val)

                # 如果左孩子为None，则不进行递归，防止重复添加
                # 可以将下面的if语句注释，结果会重复
                if node.left is not None:
                    DfsUnit(node.left, node_str)
                DfsUnit(node.right,node_str)
            else:
                """到达叶子结点"""
                node_str_list.append(node_str)
        DfsUnit(root,root_str)

        print(node_str_list)

if __name__ == '__main__':
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.right = TreeNode(5)
    Solution().binaryTreePaths(root)
