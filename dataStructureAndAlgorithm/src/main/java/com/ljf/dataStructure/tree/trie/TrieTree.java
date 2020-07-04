package com.ljf.dataStructure.tree.trie;


/**
 * @author ：ljf
 * @date ：Created in 2020/4/29 14:22
 * @description：
 * @modified By：
 * @version: 1.0
 */


class TrieNode {

    //类属性
    private final int R = 26;
    private TrieNode[] links;
    private boolean isEnd;

    //构造方法
    public TrieNode() {
        links = new TrieNode[R];
    }

    //实例方法
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode trieNode) {
        links[ch - 'a'] = trieNode;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

}

public class TrieTree {

    public TrieNode root;

    public TrieTree(TrieNode root) {
        this.root = root;
    }

    //程序入口
    public static void main(String[] args) {
        TrieTree trie = new TrieTree(new TrieNode());
        trie.instert("bad");
        trie.instert("mad");
        trie.instert("vvad");
        System.out.println(trie.search("vvad"));
    }

    //插入word
    public void instert(String word) {
        /**
         * 1.遍历word中的字符
         * 2.检查字符是否已经存在当前节点中的子节点中
         * 3.如果存在则进入下一层
         * 4.不存在则创建
         * 5.将最后一个字符的isEnd设置为true
         */
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    //查找word
    private TrieNode searchPrefix(String word) {
        /**
         * 子单元一般定义为私有访问
         * 1.从根节点开始遍历字符
         * 2.如果在下一层中没有找到字符，返回false
         */
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currnetChar = word.charAt(i);
            if (!node.containsKey(currnetChar)) {
                return null;
            }
            node = node.get(currnetChar);
        }
        return node;

    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);

        return node != null && node.isEnd();
    }


}

