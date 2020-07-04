package com.ljf.dataStructure.tree.trie;//package com.aopti.algorithmLearning.dataStructure.java.tree.trie;
//
///**
// * @author ：ljf
// * @date ：Created in 2019/11/4 13:06
// * @description：字典树
// * @modified By：
// * @version: $
// */
//public class TrieNode {
//
//  //类属性
//  private final int R = 26;
//  private TrieNode[] links;
//  private boolean isEnd;
//
//  //构造方法
//  public TrieNode() {
//    links = new TrieNode[R];
//  }
//
//  //实例方法
//  public boolean containsKey(char ch) {
//    return links[ch - 'a'] != null;
//  }
//
//  public TrieNode get(char ch) {
//    return links[ch - 'a'];
//  }
//
//  public void put(char ch, TrieNode trieNode) {
//    links[ch - 'a'] = trieNode;
//  }
//
//  public void setEnd() {
//    isEnd = true;
//  }
//
//  public boolean isEnd() {
//    return isEnd;
//  }
//
//}
