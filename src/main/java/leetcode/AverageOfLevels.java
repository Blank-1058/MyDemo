package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 * 示例 1:
 * 输入:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 * 注意：
 * 节点值的范围在32位有符号整数范围内。
 */
public class AverageOfLevels {



    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        if(root.left==null && root.right==null){
            result.add(Double.valueOf(root.val));
            return result;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        //记录当前层的节点数
        int nodeNum=1;
        //记录当前层在队列中剩余的节点数
        int nodeNumRest=nodeNum;
        //缓存下一层的节点数
        int tmpNum=0;
        //计算当前层的和
        long nodeSum=0;
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            nodeSum+=node.val;
            nodeNumRest--;
            if(node.left!=null){
                queue.offer(node.left);
                tmpNum++;
            }
            if(node.right!=null){
                queue.offer(node.right);
                tmpNum++;
            }
            if(nodeNumRest==0){
                //表示当前层所有的节点已经从队列中取出
                double avg=(double)nodeSum/nodeNum;
                result.add(avg);
                //初始化下一层的记录数值
                nodeNum=tmpNum;
                nodeNumRest=nodeNum;
                nodeSum=0;
                tmpNum=0;
            }
        }
        return result;
    }
}
