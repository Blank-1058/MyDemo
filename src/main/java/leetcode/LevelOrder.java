package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * @author liukang_lc on 2019/5/6
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        //存储每层的节点值
        List<Integer> level=null;
        //当前层节点的个数
        int levelNodeNum=1;
        //下一层节点的个数
        int nextLevelNodeNum=0;
        while(!queue.isEmpty()){
            TreeNode tmp=queue.poll();
            if(level==null){
                level=new ArrayList<>();
            }
            levelNodeNum--;
            level.add(tmp.val);
            //将当前节点的左右子树节点存入队列
            if(tmp.left!=null){
                queue.offer(tmp.left);
                nextLevelNodeNum++;
            }
            if(tmp.right!=null){
                queue.offer(tmp.right);
                nextLevelNodeNum++;
            }
            if(levelNodeNum==0){
                //表示当前层节点已经全部存入列表
                levelNodeNum=nextLevelNodeNum;
                nextLevelNodeNum=0;
                result.add(level);
                level=null;
            }
        }
        return result;
    }
}
