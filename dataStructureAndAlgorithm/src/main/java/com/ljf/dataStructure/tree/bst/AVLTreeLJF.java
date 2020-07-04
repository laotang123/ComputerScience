package com.ljf.dataStructure.tree.bst;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/10 8:14
 * @modified By：
 * @version: $
 */
class AVLNode {

  int val, height;
  AVLNode left;
  AVLNode right;

  public AVLNode(int val) {
    this.val = val;
    this.height = 1;
  }
}
public class AVLTreeLJF {
  /**
   * 1.定义基础的函数：
   *    1.1计算树的高度
   *    1.2计算树的平衡度
   *    1.3计算a，b的最大值
   *    1.4左旋 left rotation
   *    1.5右旋 right rotation
   *
   * 2.完成节点的插入操作，返回值AVLNode
   *    2.1空节点 直接返回
   *    2.2判断插入值向那个子树前进(这里已经完成了基本的插入操作)
   *    2.3更新当前节点的高度
   *    2.4四种旋转操作，left left，left right，right right ，right left
   */

  private AVLNode root;

  /**
   * 1.1计算当前节点高度
   * @param node
   * @return
   */
   private int height(AVLNode node) {
    if (node == null) {
      return 0;
    }
    return node.height;
  }

  /**
   * 1.2计算树的平衡度
   * @param node
   * @return
   */
  private int getBalance(AVLNode node) {
    if (node == null) {
      return 0;
    }
    return height(node.left) - height(node.right);
  }

  /**
   * 计算a，b的最大值
   */
  private int max(int a, int b) {
    return a > b ? a : b;
  }

  /**
   * 1.4左旋 left rotation
   * @param node
   * @return
   */
  private AVLNode leftRotate(AVLNode node) {
    //相关节点的引用，防止丢失
    AVLNode right = node.right;
    AVLNode left = right.left;

    //开始旋转
    node.right = left;
    right.left = node;

    //更新节点高度
    node.height = max(height(node.left), height(node.right)) + 1;
    right.height = max(height(right.left), height(right.right)) + 1;

    //返回旋转后的根节点
    return right;
  }

  /**
   * 1.5右旋 right rotation
   * @param node
   * @return
   */
  private AVLNode rightRotate(AVLNode node){
    //创建引用
    AVLNode left = node.left;
    AVLNode right = left.right;

    //开始旋转
    node.left = right;
    left.right = node;

    //更新x，y节点高度
    node.height = max(height(node.left), height(node.right)) + 1;
    left.height = max(height(left.left), height(left.right)) + 1;

    //返回旋转后的根节点
    return left;


  }

  /**
   * * 2.完成节点的插入操作，返回值AVLNode
   *    *    2.1空节点 创建插入值的节点
   *    *    2.2判断插入值向那个子树前进(这里已经完成了基本的插入操作)
   *    *    2.3更新当前节点的高度
   *    *    2.4四种旋转操作，left left，left right，right right ，right left
   * @param node
   * @param insertVal
   * @return
   */
  private AVLNode insert(AVLNode node, int insertVal) {
    if (node == null) {
      return new AVLNode(insertVal);
    }

    //判断插入值和当前节点的值
    if (insertVal < node.val) {
      node.left = insert(node.left, insertVal);
    } else if (insertVal > node.val) {
      node.right = insert(node.right, insertVal);
    } else{//不接受插入重复值
      return node;
    }

    //更新当前节点的高度，从子节点自下而上更新
    node.height = max(height(node.left),height(node.right)) + 1;

    //获取平衡度
    int balance = getBalance(node);

    /**
     * 左左型，balance>1 且 insertVal 小于左节点的值
     * 调用右旋操作
     */
    if (balance > 1 && insertVal < node.left.val) {
      return rightRotate(node);
    }

    /**
     * 右右型，balance<-1且 insertVal 大于右节点的值
     * 调用左旋操作
     */
    if (balance < -1 && insertVal > node.right.val) {
      return leftRotate(node);
    }

    /**
     * 左右型，balance>1 且insertVal 大于左节点的值
     * 先左旋再右旋
     */
    if (balance > 1 && insertVal > node.left.val) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    /**
     * 右左型，balance<-1且insertVal 小于右节点的值
     * 先右旋再左旋
     */
    if (balance < -1 && insertVal < node.right.val) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }


  void inOrder(AVLNode node){
    if (node != null){
      inOrder(node.left);
      System.out.print(node.val + " ");
      inOrder(node.right);
    }
  }
  public static void main(String[] args) {
    AVLTreeLJF tree = new AVLTreeLJF();

    /* Constructing tree given in the above figure */
    tree.root = tree.insert(tree.root, 10);
    tree.root = tree.insert(tree.root, 20);
    tree.root = tree.insert(tree.root, 30);
    tree.root = tree.insert(tree.root, 40);
    tree.root = tree.insert(tree.root, 50);
    tree.root = tree.insert(tree.root, 25);

		/* The constructed AVL Tree would be
			30
			/ \
		20 40
		/ \	 \
		10 25 50
		*/
    System.out.println("Preorder traversal" +
        " of constructed tree is : ");
//    tree.preOrder(tree.root);
    tree.inOrder(tree.root);
  }
}
