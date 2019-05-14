package leetcode;

/**
 * 在二叉树中分配硬币
 * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * 返回使每个结点上只有一枚硬币所需的移动次数。
 * 示例 1：
 * 输入：[3,0,0]
 * 输出：2
 * 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
 * @author liukang_lc on 2019/5/11
 */
public class DistributeCoins {

    private int count=0;
    public int distributeCoins(TreeNode root) {
        return 0;
    }

    /**
     * 后序遍历
     * @param node
     * @return
     */
    private int postOrder(TreeNode node){
        if(node==null){
            return 0;
        }
        if(node.val==0){
            return 0;
        }
        return 0;
    }
}
