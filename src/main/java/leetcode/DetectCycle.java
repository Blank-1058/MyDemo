package leetcode;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if(head==null){
            System.out.println("no cycle");
            return null;
        }
        //首先查找是否有环
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                break;
            }
        }
        if(fast==null || fast.next==null){
            //当快指针为空时，说明链表没有环
            System.out.println("no cycle");
            return null;
        }
        //设链表开始到环的入口处长度为a,入口处距离两个指针相遇的位置长度分别为b和c
        //快指针走过的距离为a+b+c+b,慢指针走过的距离为a+b，快指针走过的距离为慢指针的两倍，所以可以得出a=c
        //即相遇点到入口处的距离与链表头到入口处的距离相等
        // 所以设置一个指针从头开始，直到与慢指针相遇，则为环的入口处
        ListNode tmpNode=head;
        int pos=0;
        while(tmpNode!=slow){
            tmpNode=tmpNode.next;
            slow=slow.next;
            pos++;
        }
        System.out.println("tail connects to node index "+pos);
        return tmpNode.next;

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
