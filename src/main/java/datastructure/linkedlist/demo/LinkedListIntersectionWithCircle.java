package datastructure.linkedlist.demo;

import datastructure.linkedlist.SinglyListNode;
import datastructure.linkedlist.SinglyNodeUtils;

/**
 * 判断两个链表是否有交点（链表可能带环）
 * 分为三种情况：
 * 1、两个链表都不带环（与不带环链表相交方法相同）
 * 2、一个链表带环，另一个不带环（必定不相交）
 * 3、两个链表都带环（分三种情况）
 *    3.1、两个链表不相交
 *    3.2、相交点在环外
 *    3.3、相交点在环内
 * @author liukang_lc on 2019/3/1
 */
public class LinkedListIntersectionWithCircle {
    private static SinglyListNode listNode1;
    private static SinglyListNode listNode2;

    public static void main(String[] arg0) {
        listNode1 = SinglyNodeUtils.initLinkedList(listNode1);
        SinglyNodeUtils.printNode(listNode1);
        listNode2 = SinglyNodeUtils.initLinkedList(listNode2);
        SinglyNodeUtils.printNode(listNode2);

        //添加交点
        listNode2.getNext().getNext().setNext(listNode1.getNext().getNext());
        SinglyNodeUtils.printNode(listNode1);
        SinglyNodeUtils.printNode(listNode2);

    }

}
