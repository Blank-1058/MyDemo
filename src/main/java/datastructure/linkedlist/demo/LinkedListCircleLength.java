package datastructure.linkedlist.demo;

import datastructure.linkedlist.SinglyListNode;
import datastructure.linkedlist.SinglyNodeUtils;

/**
 * 单链表环的入口和长度
 *
 * @author liukang_lc on 2019/3/1
 */
public class LinkedListCircleLength {
    private static SinglyListNode listNode;

    public static void main(String[] arg0){
        listNode= SinglyNodeUtils.initLinkedList(listNode);
        SinglyNodeUtils.printNode(listNode);

        //在链表基础上添加环
        SinglyListNode circleStart=listNode.getNext().getNext();
        System.out.println("circleStart:"+circleStart.getVal());
        SinglyListNode lastNode=listNode;
        while(lastNode.getNext()!=null){
            lastNode=lastNode.getNext();
        }
        lastNode.setNext(circleStart);

        //获取链表是否有环
        SinglyListNode meetNode=getCircle(listNode);
        if(meetNode!=null){
            System.out.println("是否有环：是");
            //获取环的入口
            SinglyListNode enterNode=getEnter(listNode,meetNode);
            System.out.println("circleEnter :"+enterNode.getVal());
            //获取环的长度
            int length=getCircleLength(enterNode);
            System.out.println("circleLength :"+length);
        }else{
            System.out.println("是否有环：否");
        }
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
    public static SinglyListNode getCircle(SinglyListNode listNode){
        SinglyListNode fastNode=listNode;
        SinglyListNode slowNode=listNode;
        while(fastNode.getNext()!=null && fastNode.getNext().getNext()!=null){
            fastNode=fastNode.getNext().getNext();
            slowNode=slowNode.getNext();

            if(fastNode==slowNode){
                return fastNode;
            }
        }
        return null;
    }

    /**
     * 获取链表环的入口
     * 思路：
     * 假设链表起始点到环入口节点的距离为l,
     * 快慢两个指针相遇点与环入口的距离为a,
     * 快指针从相遇点到环入口节点走过的距离为c,
     * 则快指针走过的全部距离为：fast=l+a+c+a
     * 慢指针走过的全部距离为：slow=l+a
     * 又因为快指针速度为慢指针的两倍，所以fast=2*slow
     * 得到结论：l=c
     * 所以，在快慢指针相遇处，慢指针继续往前走，同时另一个指针从链表开头开始走，两指针的相遇点就是环的入口处
     * @param listNode 链表
     * @param slowNode 快慢指针的相遇点
     */
    private static SinglyListNode getEnter(SinglyListNode listNode,SinglyListNode slowNode){
        SinglyListNode enterNode=listNode;
        while(enterNode!=slowNode){
            enterNode=enterNode.getNext();
            slowNode=slowNode.getNext();
        }
        return enterNode;
    }

    /**
     * 获取环的长度
     * 思路：
     * 已知环的入口，遍历节点，直到节点回到入口，遍历的长度就是环的长度
     * @param enterNode
     * @return
     */
    private static int getCircleLength(SinglyListNode enterNode){
        int length=1;
        SinglyListNode node=enterNode.getNext();
        while(node!=enterNode){
            node=node.getNext();
            length++;
        }
        return length;
    }
}
