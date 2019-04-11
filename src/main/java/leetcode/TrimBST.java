package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 修剪二叉搜索树
 * (二叉搜索树：左子树所有的节点均小于根节点，右子树所有的节点均大于根节点)
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 示例 1:
 * 输入:
 *     1
 *    / \
 *   0   2
 *
 *   L = 1
 *   R = 2
 * 输出:
 *     1
 *       \
 *        2
 *
 * 示例 2:
 * 输入:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *   L = 1
 *   R = 3
 * 输出:
 *       3
 *      /
 *    2
 *   /
 *  1
 * @author liukang_lc on 2019/4/11
 */
public class TrimBST {
    public static void main(String[] arg0){
        TreeNode root=TreeNode.createTree(new int[]{3,1,4,Integer.MIN_VALUE,2});
        TreeNode result=trimBST(root,1,2);
    }

    public static TreeNode trimBST(TreeNode root, int L, int R) {
        Queue<TreeNode> queue=new LinkedList<>();
        root=findNode(root,L,R);
        if(root==null){
            return null;
        }
        queue.offer(root);
        while(queue.size()!=0){
            TreeNode node=queue.poll();
            TreeNode tmp=findNode(node.left,L,R);
            node.left=tmp;
            if(tmp!=null){
                queue.offer(tmp);
            }
            tmp=findNode(node.right,L,R);
            node.right=tmp;
            if(tmp!=null){
                queue.offer(tmp);
            }
        }
        return root;
    }

    /**
     * 找出符合条件的子树节点
     * @param node
     * @param L
     * @param R
     * @return
     */
    private static TreeNode findNode(TreeNode node,int L,int R){
        if(node==null){
            return null;
        }
        if(node.val<L){
            if(node.right!=null && node.right.val>=L && node.right.val<=R){
                node=node.right;
            }else{
                return findNode(node.right,L,R);
            }
        }else if(node.val>R){
            if(node.left!=null && node.left.val>=L && node.left.val<=R){
                node=node.left;
            }else{
                return findNode(node.left,L,R);
            }
        }
        return node;
    }
}
