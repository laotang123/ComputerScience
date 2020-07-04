package com.ljf.dataStructure.tree;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/21 13:43
 * @modified By：
 * @version: $
 */

// A binary tree node

class Index {

  int index = 0;
}
public class BinarySearchTree {
  Index index = new Index();

  // A recursive function to construct Full from pre[]. preIndex is used
  // to keep track of index in pre[].
  TreeNode constructTreeUtil(int pre[], Index preIndex,
      int low, int high, int size) {

    // Base case，传入的前序遍历数组为空或者下标索引传入有误
    //则返回空树
    if (preIndex.index >= size || low > high) {
      return null;
    }

    // The first node in preorder traversal is root. So take the node at
    // preIndex from pre[] and make it root, and increment preIndex
    //前序遍历的第一个元素为根节点，同时将索引下标增加1
    TreeNode root = new TreeNode(pre[preIndex.index]);
    preIndex.index = preIndex.index + 1;

    // If the current subarry has only one element, no need to recur
    //如果只有前序遍历中只有一个元素，直接返回root
    if (low == high) {
      return root;
    }

    // Search for the first element greater than root
    //找到剩下子数组中大于root的第一个元素
    int i;
    for (i = low; i <= high; ++i) {
      if (pre[i] > root.val) {
        break;
      }
    }

    // Use the index of element found in preorder to divide
    // preorder array in two parts. Left subtree and right subtree
    //二叉搜索树中序遍历特点：从索引为1的位置查找到第一个大于根节点的值
    //第一个大于根节点的值便是左右子树的分界点。
    root.left = constructTreeUtil(pre, preIndex, preIndex.index,
        i - 1, size);
    root.right = constructTreeUtil(pre, preIndex, i, high, size);

    return root;
  }

  // The main function to construct BST from given preorder traversal.
  // This function mainly uses constructTreeUtil()
  TreeNode constructTree(int pre[], int size) {
    return constructTreeUtil(pre, index, 0, size - 1, size);
  }

  // A utility function to print inorder traversal of a Binary Tree
  void printInorder(TreeNode node) {
    if (node == null) {
      return;
    }
    printInorder(node.left);
    System.out.print(node.val + " ");
    printInorder(node.right);
  }

  // Driver program to test above functions
  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree();
    int pre[] = new int[]{10, 5, 1, 7, 40, 50};
    int size = pre.length;
    TreeNode root = tree.constructTree(pre, size);
    System.out.println("Inorder traversal of the constructed tree is ");
    tree.printInorder(root);
  }
}
// Java program to construct BST from given preorder traversal


// This code has been contributed by Mayank Jaiswal
