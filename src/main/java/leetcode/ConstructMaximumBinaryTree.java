package leetcode;

import java.util.Collections;

/**
 * 最大二叉树
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * Example 1:
 * 输入: [3,2,1,6,0,5]
 * 输入: 返回下面这棵树的根节点：
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * 注意:
 *
 * 给定的数组的大小在 [1, 1000] 之间。
 * @author liukang_lc on 2019/4/22
 */
public class ConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int maxIndex=findMax(nums,0,nums.length-1);
        TreeNode root=new TreeNode(nums[maxIndex]);
        root.left=constructMaximumBinaryTree(nums,0,maxIndex-1);
        root.right=constructMaximumBinaryTree(nums,maxIndex+1,nums.length-1);
        return root;
    }

    private TreeNode constructMaximumBinaryTree(int[] nums,int startIndex,int endIndex){
        int maxIndex=findMax(nums,startIndex,endIndex);
        if(maxIndex==-1){
            return null;
        }else{
            TreeNode node=new TreeNode(nums[maxIndex]);
            node.left=constructMaximumBinaryTree(nums,startIndex,maxIndex-1);
            node.right=constructMaximumBinaryTree(nums,maxIndex+1,endIndex);
            return node;
        }
    }

    private int findMax(int[] nums,int startIndex,int endIndex){
        if(startIndex>endIndex){
            return -1;
        }
        int max=nums[startIndex];
        int index=startIndex;
        for(int i=startIndex+1;i<=endIndex;i++){
            if(nums[i]>max){
                max=nums[i];
                index=i;
            }
        }
        return index;
    }
}
