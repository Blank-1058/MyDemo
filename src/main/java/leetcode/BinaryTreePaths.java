package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        if(root.left==null && root.right==null){
            result.add(String.valueOf(root.val));
            return result;
        }

        String s=String.valueOf(root.val);
        getpaths(s,result,root.left);
        getpaths(s,result,root.right);
        return result;

    }

    /**
     * 深度优先搜索
     * @param s
     * @param list
     * @param node
     * @return
     */
    private List<String> getpaths(String s,List<String> list,TreeNode node){
        if(node==null){
            return null;
        }
        s=s+"->"+node.val;
        if(node.left==null && node.right==null){
            list.add(s);
            return list;
        }

        getpaths(s,list,node.left);
        getpaths(s,list,node.right);
        return list;

    }
}
