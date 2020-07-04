package com.ljf.dataStructure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/6 9:53
 * @modified By：
 * @version: $
 */
public class Codec {

  public TreeNode rdeserialize(List<String> l) {
    if (l.size() <= 0) {
      return null;
    }
    // Recursive deserialization.
    if (l.get(0).equals("null")) {
      l.remove(0);
      return null;
    }

    TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
    l.remove(0);
    root.left = rdeserialize(l);
    root.right = rdeserialize(l);

    return root;
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] data_array = data.split(",");
    List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
    return rdeserialize(data_list);
  }

  public static void main(String[] args) {
    Codec codec = new Codec();
    ArrayList<String> list = new ArrayList<String>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("null");
    list.add("null");
    list.add("4");
    list.add("5");
    TreeNode root = codec.rdeserialize(list);
    TreeTraversal treeTraversal = new TreeTraversal();
    treeTraversal.recursivePreOrder(root);
    System.out.println();
    treeTraversal.recursiveInOrder(root);
  }
}
