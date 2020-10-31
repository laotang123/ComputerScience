package com.ljf.dataStructure.tree.bst;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/9 10:55
 * @modified By：
 * @version: $
 */
// Java program to demonstrate insert operation in binary search tree
public class BinarySearchTree {

  /* Class containing left and right child of current node and key value*/
  class Node {

    int key;
    Node left, right;

    public Node(int item) {
      key = item;
      left = right = null;
    }
  }

  // Root of BST
  Node root;

  // Constructor
  BinarySearchTree() {
    root = null;
  }

  // This method mainly calls deleteRec()
  void deleteKey(int key) {
    root = deleteRec(root, key);
  }

  /* A recursive function to insert a new key in BST */
  Node deleteRec(Node root, int key) {
    /* Base Case: If the tree is empty */
    if (root == null) {
      return root;
    }

    /* Otherwise, recur down the tree */
    if (key < root.key) {
      root.left = deleteRec(root.left, key);
    } else if (key > root.key) {
      root.right = deleteRec(root.right, key);
    }

    // if key is same as root's key, then This is the node
    // to be deleted
    else {
      // node with only one child or no child
      // 这里包含的情况是含有一个子节点和没有子节点的情况
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      }

      // node with two children: Get the inorder successor (smallest
      // in the right subtree)
      // 当前节点包含两个子树，将右子树中的最小值赋值给当前节点
      root.key = minValue(root.right);

      // Delete the inorder successor
      // 同时将该节点删除，
      // 改进：可以将查找和删除合并为一步
//      root.right = deleteRec(root.right, root.key);
    }

    return root;
  }

  private int minValue(Node root) {
    //root是变量,root.left是引用值
    int resValue = root.key;
    while (true){
      if(root.left.left == null){
        resValue = root.left.key;
        //删除节点
        root.left = null;
        break;
      }
      resValue = root.left.key;
      root = root.left;
    }

    return resValue;
  }

  // This method mainly calls insertRec()
  void insert(int key) {
    root = insertRec(root, key);
  }

  /* A recursive function to insert a new key in BST */
  Node insertRec(Node root, int key) {

    /* If the tree is empty, return a new node */
    if (root == null) {
      root = new Node(key);
      return root;
    }

    /* Otherwise, recur down the tree */
    if (key < root.key) {
      root.left = insertRec(root.left, key);
    } else {
      root.right = insertRec(root.right, key);
    }

    /* return the (unchanged) node pointer */
    return root;
  }

  // This method mainly calls InorderRec()
  void inorder() {
    inorderRec(root);
  }

  // A utility function to do inorder traversal of BST
  void inorderRec(Node root) {
    if (root != null) {
      inorderRec(root.left);
      System.out.print(root.key + " ");
      inorderRec(root.right);
    }
  }



  // Driver Program to test above functions
  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree();

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

    System.out.println("\nDelete 20");
    tree.deleteKey(20);
    System.out.println("Inorder traversal of the modified tree");
    tree.inorder();

    System.out.println("\nDelete 30");
    tree.deleteKey(30);
    System.out.println("Inorder traversal of the modified tree");
    tree.inorder();

    System.out.println("\nDelete 50");
    tree.deleteKey(50);
    System.out.println("Inorder traversal of the modified tree");
    tree.inorder();
  }
}

