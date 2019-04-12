package leetcode;

/**
 * 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false
 * @author liukang_lc on 2019/4/12
 */
public class IsBalanced {

    public static void main(String[] arg0){
        TreeNode node=TreeNode.createTree(new int[]{1,2,2,3,3,Integer.MIN_VALUE,Integer.MIN_VALUE,4,4});
        System.out.println(isBalanced(node));
    }

    public static boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        if(Math.abs(depth(root.left)-depth(root.right))>1){
            return false;
        }else{
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    /**
     * 求树的深度
     * @param root
     * @return
     */
    private static int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        int depth=Math.max(depth(root.left),depth(root.right))+1;
        return depth;
    }
}
