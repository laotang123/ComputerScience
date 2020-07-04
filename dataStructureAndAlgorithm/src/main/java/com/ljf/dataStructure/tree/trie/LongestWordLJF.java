package com.ljf.dataStructure.tree.trie;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/23 10:21
 * @modified By：
 * @version: 1.0
 */

/**
 * 节点信息包括结束字符
 * 以当前字符结束的单词下标
 * 当字符接着后面的字符
 */
class NodeLJF {

  char c;//当前字符
  HashMap<Character, NodeLJF> children = new HashMap();
  int end;

  public NodeLJF(char c) {
    this.c = c;
  }
}

/**
 * trie树
 * 包含根节点，所有的words信息
 */
class LongWordTrie {

  NodeLJF root;
  String[] words;

  public LongWordTrie() {
    root = new NodeLJF('0');//根节点的c用不到
  }

  //插入单词，string转为char数组。遍历数组结束后将
  public void insert(String word, int index) {
    NodeLJF cur = root;
    for (char c : word.toCharArray()) {
      cur.children.putIfAbsent(c, new NodeLJF(c));
      cur = cur.children.get(c);
    }
    cur.end = index;
  }

  /**
   * 非递归实现trie树的深度遍历(参考非递归前序遍历的实现)
   * @return
   */
  public String dfs() {
    String res = "";
    Stack<NodeLJF> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      NodeLJF node = stack.pop();
      if (node.end > 0 || node == root) {//节点为root和插入的节点两种情况
        if (node != root) {
          String word = words[node.end - 1];
          //如果word比以保存的res长，或者长度相等的情况下字典更小
          if (word.length() > res.length() ||
              word.length() == res.length() && word.compareTo(res) < 0) {
            res = word;
          }
        }

        //遍历children，压入栈中
        for (NodeLJF value : node.children.values()) {
          stack.push(value);
        }
      }
    }

    return res;
  }

}

public class LongestWordLJF {
  public String longestWord(String[] words) {
    LongWordTrie trie = new LongWordTrie();
    int index = 0;
    for (String word : words) {
      trie.insert(word, ++index); //indexed by 1
    }
    trie.words = words;
    return trie.dfs();
  }

  public static void main(String[] args) {
//    LongestWordLJF LW = new LongestWordLJF();
//    System.out.println(LW.longestWord(new String[]{ "a", "banana", "app", "appl", "ap", "apply", "apple"}));
    System.out.println("a".compareTo("b"));
  }
}
