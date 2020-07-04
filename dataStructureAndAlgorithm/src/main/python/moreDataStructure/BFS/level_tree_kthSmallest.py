#!/usr/bin/python3
# -*-coding:utf-8 -*-
# @Time     : Created in 2019/11/6 10:15
# @Author    : ljf
# @modified By:
# @description: 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
#
# 说明：
# 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
#
# 示例 1:
#
# 输入: root = [3,1,4,null,2], k = 1
#    3
#   / \
#  1   4
#   \
#    2
# 输出: 1
# 时间复杂度：依次遍历前 k 个节点，因此时间复杂度为 O(k)
# 空间复杂度：生成器只需要 O(1) 的空间，如果不考虑递归栈所占用的空间，那么复杂度为 O(1)

# @Software   : PyCharm

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
class Solution:
    def mid_order(self, root):
        if not root: return
        yield from self.mid_order(root.left)
        yield root.val
        yield from self.mid_order(root.right)

    def kthSmallest(self, root: TreeNode, k: int) -> int:
        gen = self.mid_order(root)
        for _ in range(k - 1):
            next(gen)
        return next(gen)

if __name__ == '__main__':
    root = TreeNode(5)
    root.left = TreeNode(3)
    root.right = TreeNode(6)
    root.left.left = TreeNode(2)
    root.left.right = TreeNode(4)
    root.left.left.left = TreeNode(1)

    print(Solution().kthSmallest(root,1))
    print(list(Solution().mid_order(root)))


