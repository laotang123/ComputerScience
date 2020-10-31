package com.ljf.dataStructure.tree.bst;


import com.ljf.dataStructure.tree.TreeNode;

import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/9 11:44
 * @modified By：
 * @version: $
 */
public class BinarySearchTreeLJF {

  /**
   * 完成二叉搜索树的插入和删除操作
   * 1.定义节点结构
   * 2.完成递归插入操作
   *    2.1插入的key如果小于当前节点，则向左查找插入
   *    2.2插入的key如果大于当前节点，则向右查找插入
   *    2.3如果当前节点为null，则找到key的插入位置
   *
   * 3.完成递归删除操作
   *    3.1递归操作和插入类似，主要是删除的三种情况
   *        3.1.1 删除子节点，root.left==null && root.right==null return root.left
   *        3.1.2 删除含有单个子节点的根节点：if root.left == null return root.right else if root.right == null return root.left
   *        3.1.3 删除两个子节点的根节点： 遍历查询到右子树中的最小key的节点，将该节点删除，通过将key返回
   */

  // 定义节点类，已经定义
  TreeNode root;

  /**
   * 二叉搜索树的插入操作，由插入递归单元完成
   */
  public void insert(int insertVal) {
    root = insertUnit(root, insertVal);
  }

  private TreeNode insertUnit(TreeNode root, int insertVal) {
    /**
     *    * 2.完成递归插入操作
     *    *    2.1插入的key如果小于当前节点，则向左查找插入
     *    *    2.2插入的key如果大于当前节点，则向右查找插入
     *    *    2.3如果当前节点为null，则找到key的插入位置
     */
    if (root == null) {
      root = new TreeNode(insertVal);
      return root;
    }

    //小于当前节点
    if (insertVal < root.val) {
      root.left = insertUnit(root.left, insertVal);
    } else { //大于等于当前节点
      root.right = insertUnit(root.right, insertVal);
    }

    return root;
  }

  /**
   * 删除节点值为deleteVal的节点
   * @param deleteVal
   */
  public void delete(int deleteVal){
    root = deleteUnit(root,deleteVal);
  }

  private TreeNode deleteUnit(TreeNode root,int deleteVal) {
    /**
     *    * 3.完成递归删除操作
     *    *    3.1递归操作和插入类似，主要是删除的三种情况
     *    *        3.1.1 删除子节点，root.left==null && root.right==null return root.left
     *    *        3.1.2 删除含有单个子节点的根节点：if root.left == null return root.right else if root.right == null return root.left
     *    *        3.1.3 删除两个子节点的根节点： 遍历查询到右子树中的最小key的节点，将该节点删除，通过将key返回
     */
    if(root == null){
      return root;
    }


    if(deleteVal < root.val){
      root.left = deleteUnit(root.left ,deleteVal);
    }else if(deleteVal > root.val){
      root.right = deleteUnit(root.right,deleteVal);
    } else{
      //当前节点含有单个节点和没有节点的情况
      if(root.left == null){
        return root.right;
      }else if(root.right == null){
        return root.left;
      }else {
        //含有两个子节点时,将右子树中最小的值替换当前值，同时将最小值对应的节点删除
        root.val = minValue(root.right);
      }
    }
    return root;
  }

  private int minValue(TreeNode root) {
    //root是变量,root.left是引用值
    int resValue = root.val;
    while (true){
      if(root.left.left == null){
        resValue = root.left.val;
        //删除节点
        root.left = null;
        break;
      }
      resValue = root.left.val;
      root = root.left;
    }

    return resValue;
  }

  /**
   * 非递归中序遍历
   */
  public void inorder() {
    //空树
    if (root == null) {
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    TreeNode temp = root;

    while (!stack.isEmpty() || temp != null) {
      //向左子树查找，压栈
      if (temp != null) {
        stack.push(temp);
        temp = temp.left;
      }else{//打印弹出的节点值
        temp = stack.pop();
        System.out.print(temp.val + " ");
        temp = temp.right;
      }
    }

    System.out.println();
  }

  public static void main(String[] args) {
    BinarySearchTreeLJF tree = new BinarySearchTreeLJF();

		/* Let us create following BST
			50
		/	 \
		30	 70
		/ \ / \
	20 40 60 80 */
    tree.insert(50);
    tree.insert(30);
    tree.insert(20);
    tree.insert(40);
    tree.insert(70);
    tree.insert(60);
    tree.insert(80);
//    tree.insert(70);

    // print inorder traversal of the BST
    tree.inorder();

    //删除节点20
    tree.delete(20);
    tree.inorder();

    //删除节点50
    tree.delete(50);
    tree.inorder();
  }

}
