package dataStructure.linkedList.demo;

import dataStructure.linkedList.SinglyListNode;
import dataStructure.linkedList.SinglyNodeUtils;

/**
 * 判断链表是否带环
 *
 * @author liukang_lc on 2019/2/27
 */
public class LinkedListWithCircle {

    private static SinglyListNode listNode;

    public static void main(String[] arg0){
        listNode= SinglyNodeUtils.initLinkedList(listNode);
        SinglyNodeUtils.printNode(listNode);

        //在链表基础上添加环
        SinglyListNode circleStart=listNode.getNext().getNext().getNext();
        System.out.println("circleStart:"+circleStart.getVal());
        SinglyListNode lastNode=listNode;
        while(lastNode.getNext()!=null){
            lastNode=lastNode.getNext();
        }
        lastNode.setNext(circleStart);

        System.out.println("是否有环："+getCircle(listNode));
    }

    /**
     * 判断链表是否有环
     * 思路：
     * 设置一快一慢两个指针，快指针一次走两步，慢指针一次走一步
     * 如果两个指针相遇，则说明有环
     * 如果快指针走到了null，说明没有环
     * @param listNode
     * @return
     */
    private static boolean getCircle(SinglyListNode listNode){
        SinglyListNode fastNode=listNode;
        SinglyListNode slowNode=listNode;
        while(fastNode.getNext()!=null && fastNode.getNext().getNext()!=null){
            fastNode=fastNode.getNext().getNext();
            slowNode=slowNode.getNext();

            if(fastNode==slowNode){
                return true;
            }
        }
        return false;
    }
}
