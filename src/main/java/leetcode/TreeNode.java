package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 *
 * @author liukang_lc on 2019/4/9
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val=x;
    }

    public static TreeNode createTree(int[] nums) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            int value=nums[i++];
            if(value==Integer.MIN_VALUE){
                parent.left=null;
            }else{
                TreeNode left = new TreeNode(value);
                parent.left = left;
                queue.offer(left);
            }
            if (i >= nums.length) {
                break;
            }

            value=nums[i++];
            if(value==Integer.MIN_VALUE){
                parent.right=null;
            }else{
                TreeNode right = new TreeNode(value);
                parent.right = right;
                queue.offer(right);
            }
            if (i >= nums.length) {
                break;
            }
        }
        return root;
    }
}
