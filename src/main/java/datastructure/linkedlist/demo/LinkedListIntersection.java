package datastructure.linkedlist.demo;

import datastructure.linkedlist.SinglyListNode;
import datastructure.linkedlist.SinglyNodeUtils;

/**
 * 两个链表的交点
 * 查找两个不带环链表是否有交点
 *
 * @author liukang_lc on 2019/3/1
 */
public class LinkedListIntersection {
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

        System.out.println("是否有交点："+checkIntersection(listNode1,listNode2));
    }

    /**
     * 判断两个链表是否有交点
     * 思路：两个链表如果有交点，则两个链表的结尾节点必然一致
     * 1、先判断两个链表的长度，假设链表1长度为a,链表2长度为b,且a>=b
     * 2、两个链表分别设置一个指针p1、p2
     * 3、p1指针先走a-b步，然后两个指针同时向前执行，当两个指针相等时，则表示有交点，否则当其中某一个指针为null时，说明没有交点
     *
     * @param listNode1
     * @param listNode2
     * @return
     */
    private static boolean checkIntersection(SinglyListNode listNode1, SinglyListNode listNode2) {
        int length1 = 0;
        SinglyListNode pLong = listNode1;
        while (pLong.getNext() != null) {
            pLong = pLong.getNext();
            length1++;
        }
        int length2 = 0;
        SinglyListNode pShort = listNode2;
        while (pShort.getNext() != null) {
            pShort = pShort.getNext();
            length2++;
        }
        int diff = 0;
        if (length1 < length2) {
            pLong = listNode2;
            pShort = listNode1;
            diff = length2 - length1;
        } else {
            pLong = listNode1;
            pShort = listNode2;
            diff = length1 - length2;
        }
        for (int i = 0; i < diff; i++) {
            pLong = pLong.getNext();
        }
        while (pLong != null && pShort != null) {
            if (pLong == pShort) {
                System.out.println("交点处："+pLong.getVal());
                return true;
            } else {
                pLong = pLong.getNext();
                pShort = pShort.getNext();
            }

        }
        return false;
    }
}
