package com.ljf.dataStructure.linkedList;
/**
 * @author ：ljf
 * @date ：Created in 2020/1/9 8:31
 * @modified By：
 * @version: $
 */

class ListNode {
    int val;
    ListNode next;
    ListNode pre;

    public ListNode(int val) {
        this.val = val;
    }
}

public class DoublyLinkedList {
    /**
     * 实现链表的创建
     * 实现链表的插入，删除(特定值，引用节点)
     * !! 链表实现LRU算法
     */
    ListNode head = new ListNode(-1);
    ListNode end = head;


    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addNode(1, true);
        list.addNode(2, true);
        list.addNode(3, true);
        list.addNode(4, true);
        list.addNode(5, true);
        list.removeNode(1);
        list.showList();
    }

    private boolean removeNode(int val) {
        //删除节点
        boolean isSuccessfully = false;
        ListNode temp = head.next;
        while (temp != null) {
            if (temp.val == val) {
                if (temp.next != null)
                    temp.next.pre = temp.pre;//判断后继节点是否存在
                temp.pre.next = temp.next;//当前节点的上一个节点连接到下一个节点
                isSuccessfully = true;
                break;
            }
            temp = temp.next;
        }
        return isSuccessfully;
    }

    private void addNode(int val) {

        ListNode newNode = new ListNode(val);
        end.next = newNode;
        newNode.pre = end;
        end = end.next; //尾结点后移


    }

    private void addNode(int val, boolean isHeadInsert) {
        if (isHeadInsert) {
            //头插法需要判断头结点后面是否有节点，否则会出现空指针异常
            ListNode newNode = new ListNode(val);
            if (head.next != null) {
                //如果是第一次插入，不用连接head.next节点。因为他默认为null
                newNode.next = head.next;
                head.next.pre = newNode;
            }
            newNode.pre = head;
            head.next = newNode;
        } else {
            ListNode newNode = new ListNode(val);
            end.next = newNode;
            newNode.pre = end;
            end = end.next; //尾结点后移
        }

    }


    /**
     * 展示双向链表
     */
    private void showList() {
        ListNode temp = head.next;
        while (temp != null) {
            System.out.print(temp.val + "\t");
            temp = temp.next;//临时节点后移
        }
        System.out.println();
    }

}
