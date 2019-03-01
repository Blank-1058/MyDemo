package dataStructure.linkedList;

/**
 * 链表工具类
 *
 * @author liukang_lc on 2019/2/21
 */
public class SinglyNodeUtils {

    public static SinglyListNode initLinkedList(SinglyListNode listNode){
        if(listNode==null){
            listNode=new SinglyListNode();
        }
        SinglyListNode node1=new SinglyListNode();
        node1.setVal(1);

        SinglyListNode node2=new SinglyListNode();
        node2.setVal(2);
        node1.setNext(node2);

        SinglyListNode node3=new SinglyListNode();
        node3.setVal(3);
        node2.setNext(node3);

        SinglyListNode node4=new SinglyListNode();
        node4.setVal(4);
        node3.setNext(node4);

        SinglyListNode node5=new SinglyListNode();
        node5.setVal(5);
        node4.setNext(node5);

        SinglyListNode node6=new SinglyListNode();
        node6.setVal(6);
        node5.setNext(node6);

        listNode.setVal(0);
        listNode.setNext(node1);

        return listNode;
    }

    public static void printNode(SinglyListNode listNode){
        if(listNode!=null){
            SinglyListNode nodeTmp=listNode;
            System.out.print("node:");
            while(nodeTmp!=null){
                System.out.print(nodeTmp.getVal()+",");
                nodeTmp=nodeTmp.getNext();
            }
        }else{
            System.out.println("null linkedList");
        }

        System.out.println();
    }
}
