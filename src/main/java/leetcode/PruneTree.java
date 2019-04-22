package leetcode;

/**
 * 二叉树剪枝
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 *
 * 示例1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 *
 * 示例2:
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 *
 * 示例3:
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 *
 * @author liukang_lc on 2019/4/22
 */
public class PruneTree {

    public static void main(String[] arg0){
        TreeNode node=TreeNode.createTree(new int[]{1,Integer.MIN_VALUE,0,0,1});
        node=pruneTree(node);
    }

    public static TreeNode pruneTree(TreeNode root) {
        if(canBeDelete(root)){
            return null;
        }else{
            return root;
        }
    }

    private static boolean canBeDelete(TreeNode node){
        if(node==null){
            return true;
        }
        boolean leftCanBeDelete=canBeDelete(node.left);
        boolean rightCanBeDelete=canBeDelete(node.right);
        if(leftCanBeDelete){
            node.left=null;
        }
        if(rightCanBeDelete){
            node.right=null;
        }
        if(leftCanBeDelete && rightCanBeDelete && node.val==0){
            return true;
        }else{
            return false;
        }
    }
}
