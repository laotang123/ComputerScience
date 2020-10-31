package com.ljf.algorithm.others.cache;

import java.util.LinkedList;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/9 10:12
 * @modified By：
 * @version: $
 */
public class LinkedListLRU {
    /**
     * 链表实现LRU(最近最久未被使用)算法:
     * 1.规定链表长度即缓存大小
     * 2.LRU替换算法
     *  2.1命中：将命中数据替换到链表头部
     *  2.2不命中：
     *      cache满，删除尾结点，头插法
     *      cache不满，直接头插法
     */
    int cacheSize = 4;
    LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) {
        LinkedListLRU lru = new LinkedListLRU();
        System.out.println(lru.searchCache(1));
        System.out.println(lru.searchCache(2));
        System.out.println(lru.searchCache(3));
        System.out.println(lru.searchCache(4));
        System.out.println(lru.searchCache(5));
        System.out.println(lru.searchCache(1));
    }

    private boolean searchCache(int val) {
        boolean isHit; //是否命中

        if (list.contains(val)){
            //先删除，再头插法
            list.remove(list.indexOf(val));
            list.push(val);
            isHit = true;
        }else{
            //cache满
            if(list.size() == cacheSize){
                list.removeLast();//删除尾结点
            }
            list.push(val);
            isHit = false;
        }
        return isHit;
    }
}
