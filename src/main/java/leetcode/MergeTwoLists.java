package leetcode;

/**
 * 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result=new ListNode(-1);
        ListNode tmp=result;
        while(l1!=null && l2!=null){
            if(l1.val>=l2.val){
                tmp.next=new ListNode(l2.val);
                l2=l2.next;
            }else{
                tmp.next=new ListNode(l1.val);
                l1=l1.next;
            }
            tmp=tmp.next;
        }
        if(l1!=null){
            tmp.next=l1;
        }
        if(l2!=null){
            tmp.next=l2;
        }
        return result.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
