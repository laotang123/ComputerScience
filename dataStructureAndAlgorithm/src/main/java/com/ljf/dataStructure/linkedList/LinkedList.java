package com.ljf.dataStructure.linkedList;//package com.aopti.algorithmLearning.dataStructure.linkedList.;
//
///**
// * @author ：ljf
// * @date ：Created in 2020/1/8 9:16
// * @modified By：
// * @version: $
// */
//class ListNode {
//    int val;
//    ListNode next;
//
//    public ListNode(int val) {
//        this.val = val;
//    }
//}
//
//public class LinkedList {
//    /**
//     * 完成单链表的创建(哨兵)
//     * 属性：
//     * 链表长度
//     * 头结点
//     * 方法
//     * 1.添加和删除节点
//     * 2.链表的翻转：头插法翻转，成对对调
//     * 3.查找第k个节点和中间节点
//     */
//    int length = 0;
//    ListNode head = new ListNode(-1);
//    ListNode end = head;
//
//    /**
//     * 添加节点，尾插法
//     *
//     * @param val
//     */
//    private void addNode(int val) {
//        end.next = new ListNode(val);
//        end = end.next;
//
//        length++;//长度累加
//    }
//
//
//    private void showList() {
//        ListNode temp = head.next;
//        System.out.println(length);
//        while (temp != null) {
//            System.out.print(temp.val + "\t");
//            temp = temp.next;
//        }
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        LinkedList list = new LinkedList();
//        list.addNode(1);
//        list.addNode(2);
//        list.addNode(3);
//        list.addNode(4);
//        list.addNode(5);
//        list.addNode(6);
////        list.removeNode(4);
//        list.reverseList();
////        list.removeMidNode();
//
//        list.showList();
//
//    }
//
//    /**
//     * 删除中间节点，如果链表长度为奇数，删除中间节点
//     * 如果长度为偶数，删除中间第二个节点
//     * remove(mid)
//     */
//    private void removeMidNode() {
//        ListNode temp = head;
//        for (int _ = 0; _ < length / 2; _++) {
//            temp = temp.next;//后移n/2个节点
//        }
//
//        temp.next = temp.next.next;
//        length--;//长度自减
//
//    }
//
//
//    private void reverseList() {
//
//        ListNode pre = head.next;
//        ListNode cur = pre.next;
//        pre.next = null;//防止前两个节点形成环
//
//        while (cur != null) {
//            //next节点保留cur后面的节点，防止丢失
//            ListNode next = cur.next;
//            cur.next = pre;
//
//            pre = cur;
//            cur = next;
//        }
//        head.next = pre;//此时pre指向原链表的尾结点
//    }
//
//    /**
//     * 删除节点
//     *
//     * @param removeVal：待删除的值
//     */
//    private void removeNode(int removeVal) {
//        ListNode temp = head;
//        while (temp.next != null) {
//            if (temp.next.val == removeVal) {
//                temp.next = temp.next.next;
//                length--;//长度自减
//                break;
//            }
//            temp = temp.next;
//        }
//    }
//
//
//}
