package com.ljf.dataStructure.tree.trie;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/22 11:39
 * @modified By：
 * @version: 1.0
 */
class WordNode {

  char c;
  HashMap<Character, WordNode> children = new HashMap();
  int end;

  public WordNode(char c) {
    this.c = c;
  }
}

class Trie2 {

  WordNode root;
  String[] words;

  public Trie2() {
    root = new WordNode('0');
  }

  public void insert(String word, int index) {
    WordNode cur = root;
    for (char c : word.toCharArray()) {
      cur.children.putIfAbsent(c, new WordNode(c));
      cur = cur.children.get(c);
    }
    cur.end = index;
  }

  public String dfs() {
    String ans = "";
    Stack<WordNode> stack = new Stack();
    stack.push(root);
    while (!stack.empty()) {
      WordNode node = stack.pop();
      if (node.end > 0 || node == root) {
        if (node != root) {
          String word = words[node.end - 1];
          if (word.length() > ans.length() ||
              word.length() == ans.length() && word.compareTo(ans) < 0) {//要么比当前结果长，要么相等的情况下序列小于当前结果
            ans = word;
          }
        }
        for (WordNode nei : node.children.values()) {
          stack.push(nei);
        }
      }
    }
    return ans;
  }
}

public class LongestWord {

  public String longestWord(String[] words) {
    Trie2 trie = new Trie2();
    int index = 0;
    for (String word : words) {
      trie.insert(word, ++index); //indexed by 1
    }
    trie.words = words;
    return trie.dfs();
  }

  public static void main(String[] args) {
    LongestWord LW = new LongestWord();
    System.out.println(LW.longestWord(new String[]{ "a", "banana", "app", "appl", "ap", "apply", "apple"}));
  }

}
