package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * @author liukang_lc on 2019/4/11
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(queue.size()!=0){
            TreeNode node=queue.poll();
            TreeNode tmp=node.left;
            node.left=node.right;
            node.right=tmp;
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return root;
    }
}
