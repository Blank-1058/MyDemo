package leetcode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode preNode=head;
        ListNode currentNode=head.next;
        preNode.next=null;
        while(currentNode!=null){
            ListNode nodeTmp=currentNode.next;
            currentNode.next=preNode;
            preNode=currentNode;
            currentNode=nodeTmp;
        }
        return preNode;

    }
    
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
