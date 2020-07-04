package com.ljf.dataStructure.tree.trie;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ：ljf
 * @date ：Created in 2019/11/4 14:16
 * @description：
 * @modified By：
 * @version: $
 */
class WordDictionary {
    Map<Integer,Set<String>> map = new HashMap<>();//根据字符串长度分开存放
    public WordDictionary() {

    }
    public void addWord(String word) {
        int length = word.length();
        if(map.get(length)!=null){
            map.get(length).add(word);
        }else{
            Set<String> set = new HashSet<>();
            set.add(word);
            map.put(length, set);
        }
    }
    public boolean search(String word) {
        Set<String> set = map.get(word.length());
        if(set==null){  //不存在该长度的字符串，直接返回false；
            return false;
        }
        if(set.contains(word)) return true;
        char[] chars = word.toCharArray();
        P:for(String s : set){
            if(word.length()!=s.length()){
                continue;
            }
            char[] cs = s.toCharArray();
            for(int i = 0; i< cs.length; i++){//逐个字符对比
                if(chars[i] != '.' && chars[i] != cs[i]){
                    continue P;
                }
            }
            set.add(word);
            return true;
        }
        return false;
    }

    //程序入口
    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("bad");
        dictionary.addWord("mad");
        dictionary.addWord("pbg");
        System.out.println(dictionary.map.keySet());
        System.out.println(dictionary.search("bad"));
        System.out.println(dictionary.search("..d"));
        System.out.println(dictionary.search("pppp"));
    }
}
