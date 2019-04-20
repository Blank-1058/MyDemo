package leetcode;

/**
 * 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * 示例 :
 * 给定二叉树
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * @author liukang_lc on 2019/4/20
 */
public class DiameterOfBinaryTree {

    /**
     * 直径=左子树的最大深度+右子树的最大深度
     * 针对每个节点计算该节点的直径，取出最大值
     * @param root
     * @return
     */
    int max=0;
    public int diameterOfBinaryTree(TreeNode root) {
        getMaxDepth(root);
        return max;
    }

    private int getMaxDepth(TreeNode node){
        if(node==null){
            return 0;
        }
        int left=getMaxDepth(node.left);
        int right=getMaxDepth(node.right);
        max=Math.max(max,left+right);
        return Math.max(left,right)+1;
    }
}
