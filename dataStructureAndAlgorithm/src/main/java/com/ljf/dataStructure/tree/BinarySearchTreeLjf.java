package com.ljf.dataStructure.tree;


/**
 * @author ：ljf
 * @date ：Created in 2019/12/21 13:46
 * @modified By：
 * @version: $
 */
//二叉搜索树的节点，左右节点值默认为null


//前序遍历数组的索引
class ArrayIndex {

  int index = 0;
}

public class BinarySearchTreeLjf {

  /**
   * 根据前序遍历创建二叉搜索树
   */
  ArrayIndex preIndex = new ArrayIndex();

  /**
   * 构建二叉搜索树的递归单元
   */
  public TreeNode constructTreeUnit(int[] preArray, int low, int high,
      int length) {
    //结束递归的条件，数组为空或者preIndex超过数组的最大长度，越界
    if (low > high) {
      return null;
    }

    //将数组的第一个元素创建为root节点
//    TreeNode root = new TreeNode(preArray[preIndex.index]);
//    preIndex.index = preIndex.index + 1;//数组索引自增
    TreeNode root = new TreeNode(preArray[low++]);

    //如果子树中只有一个节点，则直接返回
    if (low > high) {
      return root;
    }

    //找到子树中左子树和右子树的分界点，即剩下数组中第一个大于根节点的值
    int i;
    for (i = low; i <= high; i++) {
      if (preArray[i] > root.val) {
        break;
      }
    }

    //找到左右子树的分界点后分别单独创建左右子树
    root.left = constructTreeUnit(preArray, low, i - 1, length);
    root.right = constructTreeUnit(preArray, i, high, length);
    return root;
  }

  public TreeNode constructTree(int[] preArray) {
    int length = preArray.length;
    return constructTreeUnit(preArray, 0, length - 1, length);
  }

  //二叉搜索树的中序遍历即为有序数组
  public void printInOrder(TreeNode node) {
    //判断是否为空
    if (node == null) {
      return;
    }
    printInOrder(node.left);
    System.out.print(node.val + " ");
    printInOrder(node.right);
  }

  public static void main(String[] args) {
    BinarySearchTreeLjf tree = new BinarySearchTreeLjf();
//    int pre[] = new int[]{10, 5, 1, 7, 40, 50};
    int pre[] = new int[]{8,6,5,7,10,9,11};
    int size = pre.length;
    TreeNode root = tree.constructTree(pre);
    System.out.println("Inorder traversal of the constructed tree is ");
    tree.printInOrder(root);
  }
}
