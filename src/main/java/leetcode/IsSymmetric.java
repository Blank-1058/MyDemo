package leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 说明:
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * @author liukang_lc on 2019/4/9
 */
public class IsSymmetric {
    public static void main(String[] arg0){
        int[] nums=new int[]{1,2,2,3,4,4,3};
        TreeNode root=TreeNode.createTree(nums);
        System.out.println(isSymmetricIteration(root));
    }

    /**
     * 迭代方式
     * @param root
     * @return
     */
    public static boolean isSymmetricIteration(TreeNode root) {
        if(root==null){
            return true;
        }
        if(root.left==null && root.right==null){
            return true;
        }else if(root.left==null && root.right!=null){
            return false;
        }else if(root.left!=null && root.right==null){
            return false;
        }
        Queue<TreeNode> nodeLeft=new LinkedList<>();
        Queue<TreeNode> nodeRight=new LinkedList<>();
        if(root.left.val==root.right.val){
            nodeLeft.offer(root.left);
            nodeRight.offer(root.right);
        }else{
            return false;
        }
        while(nodeLeft.size()!=0 && nodeRight.size()!=0){
            TreeNode left=nodeLeft.poll();
            TreeNode right=nodeRight.poll();
            //比较左子树的左孩子节点与右子树的右孩子节点是否相等
            if(left.left!=null && right.right!=null){
                if(left.left.val==right.right.val){
                    nodeLeft.offer(left.left);
                    nodeRight.offer(right.right);
                }else{
                    return false;
                }
            }else if(left.left==null && right.right!=null){
                return false;
            }else if(left.left!=null && right.right==null){
                return false;
            }
            //比较左子树的右孩子节点与右子树的左孩子节点是否相等
            if(left.right!=null && right.left!=null){
                if(left.right.val==right.left.val){
                    nodeLeft.offer(left.right);
                    nodeRight.offer(right.left);
                }else{
                    return false;
                }
            }else if(left.right==null && right.left!=null){
                return false;
            }else if(left.right!=null && right.left==null){
                return false;
            }
        }
        if(nodeLeft==null && nodeRight!=null){
            return false;
        }
        if(nodeLeft!=null && nodeRight==null){
            return false;
        }
        return true;
    }

    /**
     * 递归方式
     * @param root
     * @return
     */
    public boolean isSymmetricRecursion(TreeNode root) {
        if(root==null){
            return true;
        }
        if(root.left==null && root.right==null){
            return true;
        }else if(root.left==null && root.right!=null){
            return false;
        }else if(root.left!=null && root.right==null){
            return false;
        }else{
            return isSymmetric(root.left,root.right);
        }
    }

    private boolean isSymmetric(TreeNode nodeLeft,TreeNode nodeRight){
        if(nodeLeft!=null && nodeRight!=null){
            //判断左子树的左子树和右子树的右子树是否对称
            if(!isSymmetric(nodeLeft.left,nodeRight.right)){
                return false;
            }
            //判断左子树的右子树和右子树的左子树是否对称
            if(!isSymmetric(nodeLeft.right,nodeRight.left)){
                return false;
            }
            //判断左子树和右子树的节点是否相等
            if(nodeLeft.val!=nodeRight.val){
                return false;
            }
            return true;
        }else if(nodeLeft==null && nodeRight==null){
            return true;
        }else{
            return false;
        }
    }
}
