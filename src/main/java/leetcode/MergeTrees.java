package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 合并二叉树
 *
 * @author liukang_lc on 2019/4/12
 */
public class MergeTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null && t2==null){
            return null;
        }else if(t1==null && t2!=null){
            return t2;
        }else if(t1!=null && t2==null){
            return t1;
        }
        Queue<TreeNode> queue1=new LinkedList<>();
        Queue<TreeNode> queue2=new LinkedList<>();
        t1.val=t1.val+t2.val;
        queue1.offer(t1);
        queue2.offer(t2);
        while(queue1.size()!=0 || queue2.size()!=0){
            TreeNode node1=queue1.poll();
            TreeNode node2=queue2.poll();
            if(node1!=null && node2!=null){
                //合并左孩子节点
                if(node1.left==null && node2.left!=null){
                    TreeNode tmp=new TreeNode(node2.left.val);
                    node1.left=tmp;
                }else if(node1.left!=null && node2.left!=null){
                    node1.left.val=node1.left.val+node2.left.val;
                }
                queue1.offer(node1.left);
                queue2.offer(node2.left);
                //合并右孩子节点
                if(node1.right==null && node2.right!=null){
                    TreeNode tmp=new TreeNode(node2.right.val);
                    node1.right=tmp;
                }else if(node1.right!=null && node2.right!=null){
                    node1.right.val=node1.right.val+node2.right.val;
                }
                queue1.offer(node1.right);
                queue2.offer(node2.right);
            }else if(node1!=null && node2==null){
                if(node1.left!=null){
                    queue1.offer(node1.left);
                    queue2.offer(null);
                }
                if(node1.right!=null){
                    queue1.offer(node1.right);
                    queue2.offer(null);
                }
            }
        }
        return t1;
    }
}
