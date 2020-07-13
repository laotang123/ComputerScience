package com.ljf.algorithm;

import java.util.HashMap;

/**
 * @author ：ljf
 * @date ：2020/7/12 17:43
 * @description：双向链表+HashMap实现LRU缓存算法 //1.查询缓存：如果命中更新[先删除再添加]到头结点，如果不命中则添加到头结点
 * 双向链表删除节点的时间复杂度是O(1)
 * @modified By：
 * @version: $ 1.0
 */
class LRUNode {
    //添加key是原因是在删除尾结点的时候能够找到对应的key，从而删除掉map中对应的节点
    LRUNode prev;
    LRUNode next;
    int value;
    int key;

    LRUNode(int key, int val) {
        this.value = val;
        this.key = key;
    }

    LRUNode() {}
}

public class LRUCacheLJF {
    private LRUNode head;
    private LRUNode tail;
    private int capacity, size;
    private HashMap<Integer, LRUNode> map;

    public LRUCacheLJF(int capacity) {
        this.capacity = capacity;
        head = new LRUNode();
        tail = new LRUNode();
        map = new HashMap<>(capacity);

        //头尾相连，空链表
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        LRUNode resNode = map.get(key);
        if (resNode == null) return -1;

        removeNode(resNode);
        addNode(resNode);

        return resNode.value;
    }

    /**
     * 添加缓存，先判断容量：
     * 1.如果满了，先删除尾结点，再添加节点
     * 2.如果不满则直接添加节点，更新值
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        LRUNode node = map.get(key);

        if (node == null) {
            if (size >= capacity) {
                LRUNode popNode = popTail();
                map.remove(popNode.key);
                size--;
            }

            LRUNode newNode = new LRUNode(key, value);
            addNode(newNode);
            map.put(key, newNode);
            size++;
        } else {
            //直接更新节点
            map.get(key).value = value;
        }
    }

    /**
     * 头插法添加节点
     *
     * @param node
     */
    private void addNode(LRUNode node) {
        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    /**
     * 删除指定节点
     *
     * @param node
     */
    private void removeNode(LRUNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 缓存容量满时删除尾结点
     */
    private LRUNode popTail() {
        LRUNode popNode = tail.prev;
        removeNode(popNode);

        return popNode;
    }

    public static void main(String[] args) {
        LRUCacheLJF cache = new LRUCacheLJF(2);
        cache.put(1, 1);
        cache.put(2, 2);
//        System.out.println("正确答案1：" + cache.get(1));// 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4


    }
}
