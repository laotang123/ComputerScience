package com.ljf.dataStructure.tree.trie;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/24 9:40
 * @modified By：
 * @version: 1.0
 */
//TODO hashMap与links数组的对比
// TODO 实现startswith，不仅返回词典中是否含有该前缀的单词，并且将含有该前缀的单词list返回
class TrieNodeLJF {

  /**
   * 使用links数组存储该层字符,使用links的数组下标表示到a的偏移量
   * end标识符表示该字符是否为单词的结束
   * 方法：
   *    put，get
   *    containKey
   *    setEnd，isEnd
   */
  private final int R = 26;
  private TrieNodeLJF[] links;
  private boolean isEnd;

  public TrieNodeLJF() {
    links = new TrieNodeLJF[R];
  }

  public void put(char ch, TrieNodeLJF node) {
    links[ch - 'a'] = node;
  }

  public TrieNodeLJF get(char ch) {
    return links[ch - 'a'];
  }

  //当前层是否包含某字符
  public boolean containKey(char ch) {
    return links[ch - 'a'] != null;
  }

  //当前字符是否为单词的末尾字符
  public boolean isEnd() {
    return isEnd;
  }

  public void setEnd() {
    isEnd = true;
  }

  public TrieNodeLJF[] getLinks() {
    return links;
  }
}

public class TrieLJF {

  private TrieNodeLJF root;

  public TrieLJF() {
    root = new TrieNodeLJF();
  }

  /**
   * 1.分析当前业务所需要的类，Trie类，TrieNode类
   *    原则：由功能推导类，类不需要特别细化，适当的功能数量构成的类能够使结构更清晰
   *    实现功能：
   *      插入
   *      查找，前缀查找
   *      删除
   * 2.类之间的关系，TrieNode类是Trie类的一部分
   */

  public void insert(String word) {
    if (word.length() <= 0 || word == null) {
      return;
    }
    TrieNodeLJF node = root;
    for (char c : word.toCharArray()) {
      if (!node.containKey(c)) {
        node.put(c, new TrieNodeLJF());
      }
      node = node.get(c);
    }
    node.setEnd();//设置当前字符为单词结束符
  }

  public boolean search(String word) {
    //分两种情况，一种为字典树中没有超过word单词的长度。故查找可能会提前结束
    //2.word单词在字典树中找到，判断isEnd
    if (word.length() <= 0 || word == null) {
      return true;
    }

    TrieNodeLJF node = root;
    for (char ch : word.toCharArray()) {
      node = node.get(ch);
      if (node == null) {
        return false;
      }
    }

    return node.isEnd();
  }

  public void dfs(List<String> resList, TrieNodeLJF node, String word, StringBuilder builder) {
    //从当前字符层深度遍历
    TrieNodeLJF[] links = node.getLinks();

    for (int i = 0; i < links.length; i++) {
      //判空
      TrieNodeLJF nodeLink = links[i];
      if (nodeLink != null) {
        builder.append((char) ('a' + i));
        if (nodeLink.isEnd()) {//单词结束，添加
          resList.add(word + builder.toString());
        }
        dfs(resList, links[i], word, builder);
      }
    }

    //删除上一个单词在stringbuilder中的残留字符
    if (builder.length() > 0) {
      builder.deleteCharAt(builder.length() - 1);
    }
  }

  public List<String> startsWith(String word) {
    List<String> resList = new ArrayList<>();
    if (word.length() <= 0 || word == null) {
      return resList;
    }

    TrieNodeLJF node = root;
    for (char ch : word.toCharArray()) {
      node = node.get(ch);
      if (node == null) {
        return resList;
      }
    }

    //添加前缀单词
    if (node.isEnd()) {
      resList.add(word);
    }
    //dfs实现单词的查找
    dfs(resList, node, word, new StringBuilder());
    return resList;
  }

  public static void main(String[] args) {
//    TrieNodeLJF root = new TrieNodeLJF();
    TrieLJF trie = new TrieLJF();
    String s;
    int num = 1000000;
//    trie.insert("abcd");
    long start = System.currentTimeMillis();
    for (int i = 0; i < num; i++) {
      s = RandomStringUtils.randomAlphabetic(10).toLowerCase();
      trie.insert(s);
    }
    long end = System.currentTimeMillis();
    System.out.println(trie.startsWith("abc").size());
    System.out.println("cost time : " + (end - start));
  }
}
