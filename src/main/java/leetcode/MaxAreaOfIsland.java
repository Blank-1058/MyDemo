package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 岛屿的最大面积
 *
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 */
public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {

        List<String> islandIndex=new ArrayList<>();
        int width=grid[0].length;
        int hight=grid.length;
        for(int i=0;i<width;i++){
            for(int j=0;j<hight;j++){

                String index=String.valueOf(i)+String.valueOf(j);
                if(islandIndex.contains(index)){
                    continue;
                }
                if(grid[i][j]==1){
                    islandIndex.add(index);

                }
            }
        }

        return 0;
    }

    public void maxAreaOfIsland(int[][] grid,int i,int j,List<String> islandIndex){

    }
}
