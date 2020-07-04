# -*- coding: utf-8 -*-
# @Time    : 2019/10/10 8:18
# @Author  : liujunfeng

from queue import Queue
import heapq


class TreeNode(object):
    def __init__(self, x):
        """
        树节点的定义
        :param x:
        """
        self.val = x
        self.left = None
        self.right = None


class Tree(object):
    """
    TODO 树
    # 普通树

    # 二叉树
    ## 存储：顺序存储，链式存储
    ## 创建：询问法，补空法(先序遍历创建)
    ## 应用：树的遍历，计算树的深度和叶子数，遍历序列还原树和哈夫曼树

    # 完全二叉树


    TODO 森林
    """

    def __init__(self):
        pass
        # self.root = root

    def create_tree(self, pre_seq):
        """
        根据先中后序遍历生成树
        :return:返回树的根节点

        """
        index = 0

        def RecurUnit(i, node):
            """创建树的递归单元"""
            nonlocal index
            index += 1
            if i >= len(pre_seq):
                return
            if pre_seq[i] == "#":  # 构建空树
                return
            else:
                node.val = pre_seq[i]
                node.left = TreeNode(pre_seq[index])  # 左子树
                RecurUnit(index, node.left)
                node.right = TreeNode(pre_seq[index])
                RecurUnit(index, node.right)  # 递归构建右子树

        root = TreeNode(None)
        RecurUnit(index, root)
        return root

    # 先序遍历
    def pre_order(self, node):
        # result = []
        if node != None:
            print(node.val, end=" ")
            self.pre_order(node.left)
            self.pre_order(node.right)

    # 中序遍历
    def mid_order(self, node):
        if node != None:
            self.pre_order(node.left)
            print(node.val, end=" ")
            self.pre_order(node.right)

    # 后序遍历
    def post_order(self, node):
        if node != None:
            self.pre_order(node.left)
            self.pre_order(node.right)
            print(node.val, end=" ")

    # 层次遍历
    def level(self, root):
        """
        队列，先进先出
        :return:
        """

        def Unit():
            if not queue.empty():
                node = queue.get()  # 弹栈

                if node != None:  # 是否为空树
                    result.append(node.val)

                    queue.put(node.left)
                    queue.put(node.right)

                    Unit()

        queue = Queue()
        result = []

        queue.put(root)  # 压栈
        # Unit() # 递归版本

        while not queue.empty():
            node = queue.get()

            if node != None:  # 树非空
                result.append(node.val)

                queue.put(node.left)
                queue.put(node.right)
        print(result)

    # 计算二叉树的深度
    def depth(self, node):
        """递归计算二叉树深度"""
        if node == None:  # 空树深度为0
            return 0
        else:
            m = self.depth(node.left)
            n = self.depth(node.right)

            # 返回左右子树深度最大值+1
            if m > n:
                return m + 1
            else:
                return n + 1

    # 先中序还原二叉树
    def pre_mid_restore_tree(self):
        """秘籍：先序找根，中序分左右"""
        pass

    # 后中序还原二叉树
    def post_mid_restore_tree(self):
        """秘籍：后续找根，中序分左右"""
        pass

    def huffman_tree(self):
        """构建哈夫曼树"""
        """ 
        version1：T集合会出现一个key对应多个value，解决方案 int:list
        version2：优先队列取代list，int：queue
        """
        # 1.初始化T集合，集合元素为TreeNode类型的单节点
        T = {5: [TreeNode(5)], 32: [TreeNode(32)], 18: [TreeNode(18)], 7: [TreeNode(7)], 25: [TreeNode(25)], 13: [TreeNode(13)]}

        # 2.合并操作，选出T集合中前2个最小值合并为一个节点(排序取出，最小堆实现)
        # 创建堆
        heap = list(T.keys())
        heapq.heapify(heap)
        while True:
            top2 = [heapq.heappop(heap) for _ in range(2)]

            print(heap)
            vertex_val = sum(top2)
            print(vertex_val)
            node = TreeNode(vertex_val)
            node.left = T[top2[0]].pop(0)
            node.right = T[top2[1]].pop(0)

            # 删除T中合并节点
            if len(T[top2[0]]) == 0: del T[top2[0]]
            if len(T[top2[1]]) == 0: del T[top2[1]]

            # 合并的父节点添加到节点字典和堆中
            heapq.heappush(heap, node.val)
            if(T.get(vertex_val,0) == 0):
                T[vertex_val] = [node]
            else:
                T[vertex_val].append(node)

            # 2.1判断：如果只剩下一个树，则结束合并
            if len(heap) == 1:
                break

        print(T)
        # 3.左分支为0，右分支为1


if __name__ == '__main__':
    # 创建树
    root = TreeNode("A")
    root.left = TreeNode("B")
    root.right = TreeNode("C")
    root.left.left = TreeNode("D")
    root.left.right = TreeNode("E")
    root.right.left = TreeNode("F")
    root.right.left.right = TreeNode("G")
    tree = Tree()
    print("先序遍历：", end="")
    tree.pre_order(root)
    print()
    # print("中序遍历：", end="")
    # tree.mid_order(root)
    # print()
    # print("后序遍历：", end="")
    # tree.post_order(root)
    # print()
    # print("层次遍历：", end="")
    # tree.level(root)
    # print()

    # 先序遍历序列
    # pre_seq = "ABD##E##CF#G###"
    # root = tree.create_tree(pre_seq)
    # tree.pre_order(root)

    # 二叉树深度
    # print(tree.depth(root))
    tree.huffman_tree()


