package datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树工具类
 *
 * @author liukang_lc on 2019/3/27
 */
public class TreeNodeUtil {

    /**
     * 根据数组生成一个二叉树
     * 依照数组顺序按层排列
     * @param nums
     * @return
     */
    public static BinaryTreeNode createTree(int[] nums){
        Queue<BinaryTreeNode> queue=new LinkedList<>();
        BinaryTreeNode root=new BinaryTreeNode(nums[0]);
        queue.offer(root);
        int i=1;
        while(!queue.isEmpty()){
            BinaryTreeNode parent=queue.poll();
            BinaryTreeNode left=new BinaryTreeNode(nums[i++]);
            parent.setLeftNode(left);
            queue.offer(left);
            if(i>=nums.length){
                break;
            }
            BinaryTreeNode right=new BinaryTreeNode(nums[i++]);
            parent.setRightNode(right);
            queue.offer(right);
            if(i>=nums.length){
                break;
            }
        }
        return root;
    }
}
