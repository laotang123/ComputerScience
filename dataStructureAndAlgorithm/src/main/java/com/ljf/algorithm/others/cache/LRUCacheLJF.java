package com.ljf.algorithm.others.cache;


import java.util.Hashtable;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/10 9:18
 * @modified By：
 * @version: $
 */
public class LRUCacheLJF {
    /**
     * 数据结构：
     * 双链表的节点，java自带hashTable
     * HashTable(解决查找节点的O(n)复杂度降低到O(1))和双链表(伪头部和伪尾部，单链表尝试)实现LRU调度算法
     * 核心方法：
     * 1.get方法获取缓存数据，
     * 1.1存在秘钥对应的数据，直接返回数据，同时将该缓存数据移动到链表头部
     * 先删除，后头插法
     * 1.2不存在对应数据，返回-1
     * 2.put方法更新或插入缓存数据
     * 2.1通过get方法，如果获取到数据，则进行更新
     * 2.2如果没有得到数据，则进行头插法插入数据，
     * 插入的同时判断缓存是否满，如果满需要删除尾结点数据
     * TODO：如果是双链表在移动节点和删除节点时，会将复杂度从O(n)降低到O(1)
     * TODO: HashTable将查找节点的时间复杂度从O(n)降低到O(1)
     * TODO：头尾节点为哨兵节点，没有存数据
     */

    //HashTable数据结构
    private Hashtable<Integer, DListNode> cache = new Hashtable<>();
    private int size;
    private int capacity;
    private DListNode head;
    private DListNode tail;

    public LRUCacheLJF(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DListNode();
        tail = new DListNode();

        head.next = tail;
        tail.prev = head;

    }

    //双向链表Node数据结构
    class DListNode {
        int key;
        int value;

        DListNode prev;
        DListNode next;
    }


    public static void main(String[] args) {
        LRUCacheLJF lru = new LRUCacheLJF(2);
        lru.put(1, 5);
        lru.put(2, 6);
        lru.put(3, 7);

        System.out.println(lru.get(1));
        System.out.println(lru.get(2));

    }

    /**
     * 2.put方法更新或插入缓存数据
     * 2.1通过get方法，如果获取到数据，则进行更新，将该节点移动到头部
     * 2.2如果没有得到数据，则进行头插法插入数据，
     * 插入的同时判断缓存是否满，如果满需要删除尾结点数据
     *
     * @param key
     * @param value
     */
    private void put(int key, int value) {
        DListNode node = cache.get(key);
        if (node == null) {
            if (size >= capacity) {
                DListNode end = popTail();//去除双链表连接
                cache.remove(end.key);
                size--;
            }
            DListNode newNode = new DListNode();
            newNode.value = value;
            newNode.key = key;
            addNode(newNode);
            cache.put(key, newNode);
            size++;
        } else {
            //更新数据
            node.value = value;
            moveToHead(node);
        }
    }

    //删除尾结点
    private DListNode popTail() {
        DListNode res = tail.prev;
        removeNode(res);//自动会将伪尾结点连接
        return res;
    }

    /**
     * 1.get方法获取缓存数据，
     * 1.1存在秘钥对应的数据，直接返回数据，同时将该缓存数据移动到链表头部
     * 先删除，后头插法
     * 1.2不存在对应数据，返回-1
     *
     * @param key
     * @return
     */
    private int get(int key) {
        DListNode node = cache.get(key);

        if (node == null) return -1;

        moveToHead(node);//该节点移动到头部

        return node.value;
    }

    /**
     * 先删除节点，再头插法添加节点
     */
    private void moveToHead(DListNode node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * 头插法插入节点
     */
    private void addNode(DListNode node) {
        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    //将该节点删除
    private void removeNode(DListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
