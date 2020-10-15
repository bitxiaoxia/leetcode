package com.bitxiaoxia.leetcode.contest.context207;

import java.util.*;

/**
 * Created by Bitxiaoxia on 2020/9/28.
 */
public class Pro {
	public static void main(String[] args) {
//		System.out.println(reorderSpaces(" practice   makes   perfect"));

		Pro pro = new Pro();
//		System.out.println(pro.maxUniqueSplit("aab"));
//		int[][] arr = {{-1,-2,-3},{-2,-3,-3},{-3,-3,-2}};
//		int[][] arr = {{1,-1,2,1,-1,0,0,4,3,2,0,-2,-2},
//			{-2,3,3,-1,-1,0,0,-2,4,-3,3,0,0},
//			{-4,-1,-1,-2,2,-1,-2,-2,0,3,-1,-4,1},
//			{-3,4,-3,0,-3,1,-3,1,4,4,-4,-4,-2},
//			{3,-3,1,0,-1,-4,-4,-4,3,2,2,3,3},
//			{2,-1,-1,-4,-3,-3,4,2,3,4,4,-4,0},
//			{4,-1,2,-3,-1,-1,-3,-4,4,4,4,-3,-1},
//			{-3,-4,4,-2,-1,2,3,-1,2,3,4,4,-4},
//			{-3,-1,-2,1,1,-1,-3,-4,-3,1,-3,3,-4},
//			{2,4,4,4,-3,-3,1,-1,3,4,-1,1,4},
//			{2,-2,0,4,-1,0,-2,4,-4,0,0,2,-3},
//			{1,1,-3,0,-4,-4,-4,-4,0,-1,-4,-1,0},
//			{3,-1,-3,-3,-3,-2,-1,4,-1,-2,4,2,3}};
		int[][] arr = {{2,-3,4,-1,3,-1,4,3,-4,2,0,2,-1,4,3},{2,-2,-3,1,3,0,-4,-2,0,0,-1,-4,1,-3,-2},{-2,0,1,-2,-2,-2,1,0,3,1,-2,2,-3,0,-3},{0,-4,2,0,-1,-2,3,-4,0,4,-2,-4,2,-1,2},{0,-3,3,3,4,-3,-2,4,-1,4,-2,3,3,0,-1},{3,-1,-4,1,-4,-1,2,4,4,2,1,-1,-4,3,-1},{1,-1,2,-1,4,2,-1,0,4,2,4,2,-2,0,-3},{-4,-1,1,3,-1,-2,-1,-2,-2,2,-2,-3,-4,2,0},{-1,-1,-2,0,-4,0,-4,-4,-2,-2,-4,-2,-4,1,2},{1,-1,-1,3,0,3,4,0,3,-1,0,-1,4,-4,2},{-3,2,-2,-1,3,4,-1,-2,2,4,3,4,2,2,4},{1,-2,-4,-4,1,-2,1,-3,4,4,-1,2,-3,-3,3},{-2,2,0,3,-2,0,-1,-4,-4,-2,-4,3,-3,-2,-2},{2,1,1,3,-4,-2,0,2,1,2,2,3,-1,3,3},{-4,-1,0,-3,4,1,1,4,-4,-1,1,4,4,-3,-4}};
		System.out.println(pro.maxProductPath(arr));
	}

	public int connectTwoGroups(List<List<Integer>> cost) {
		//先让点1链接所有的点，然后判断点二是否比当前所有路径短，
		//如果有，更新到每个点的最短路径，
		//如果没有，路径和加上点二的最小值。并记录下最小值对应的点。
		//需要记录下每个最小值是A中哪个点的，这样最后只要把那个点算上就可以了。
		if(cost == null || cost.size()<=0){
			return 0;
		}
		List<Integer> line1 = cost.get(0);
		int[] shortedPath = new int[line1.size()];
		int[] shortedPos = new int[line1.size()];
		boolean[] visitedB = new boolean[line1.size()];
		boolean[] visitedA = new boolean[cost.size()];
		for(int i=0;i<line1.size();i++){
			shortedPath[i] = line1.get(i);
			shortedPos[i] = 0;
		}
		int sum = 0;
		for(int i=1;i<cost.size();i++){
			List<Integer> line = cost.get(i);
			boolean flag = false;
			int min = line.get(0);
			int minPos = 0;
			for(int j=0;j<line.size();j++){
				int temp = line.get(j);
				if (temp < shortedPath[j]){
					shortedPath[j] = temp;
					shortedPos[j] = i;
					flag = true;
				}
				if (temp<min){
					min = temp;
					minPos = j;
				}
			}
			if(!flag){
				sum += min;
				visitedB[minPos] = true;
				visitedA[i] = true;
			}
		}
		//校验还有哪些点没有被访问过。并从最小路径找到对应的点。

		return sum;
	}

	long max = 0;
	public int maxProductPath(int[][] grid) {
		if (grid.length==0){
			return -1;
		}
		this.max = -1;

		dfs(grid,0,0,1);
		if (max>=0){
			return(int) (max%1000000007);
		}else{
			return -1;
		}

	}

	public void dfs(int[][] grid,int line,int column,long res){
//		if (line>=grid.length || column>=grid[0].length){
//			return;
//		}
		if(grid[line][column] == 0){
			if (max < 0){
				max = 0;
			}
			return;
		}
		res *= grid[line][column];
		if(line == grid.length-1 && column == grid[0].length -1){
			//终止条件
			if (res > max){
				max = res;
			}
		}else{
			//向右
			if(column + 1<grid[0].length){
				dfs(grid,line,column+1,res);
			}
			//向下
			if(line+1<grid.length){
				dfs(grid,line+1,column,res);
			}
		}
	}


	public int maxUniqueSplit(String s) {
		if (s==null){
			return 0;
		}
		this.max = 0;
		Set<String> set = new HashSet<>();
		StringBuilder builder = new StringBuilder();
		backTrace(s,0,set,builder);
		return (int)this.max;
	}

	public void backTrace(String s,int pos,Set<String> set,StringBuilder curr){
		if (pos == s.length()){
			if(curr.length() == 0){
				if(max < set.size()){
					max = set.size();
				}
			}
			return;
		}

		//两种情况、当前Set不存在，当前set存在。
		curr.append(s.charAt(pos));
		//只能继续
		backTrace(s,pos+1,set,curr);

		if (!set.contains(curr.toString())){
			String temp = curr.toString();
			set.add(temp);
			curr.delete(0,curr.length());
			backTrace(s,pos+1,set,curr);

			set.remove(temp);
			curr.append(temp);
		}

		curr.deleteCharAt(curr.length()-1);
	}

	public static String reorderSpaces(String text) {
		if(text == null){
			return  null;
		}
		Deque<String> wordsQueue = new ArrayDeque<>();
		int spaceNum = 0;
		StringBuilder builder = new StringBuilder();
		for(char c:text.toCharArray()){
			if(c==' '){
				spaceNum++;
				if(builder.length()!=0) {
					wordsQueue.add(builder.toString());
					builder.delete(0, builder.length());
				}
			}else{
				builder.append(c);
			}
		}
		if(builder.length()!=0) {
			wordsQueue.add(builder.toString());
			builder.delete(0, builder.length());
		}
		if (wordsQueue.size() == 0||spaceNum == 0){
			return  text;
		}
		if (wordsQueue.size() == 1){
			builder.append(wordsQueue.poll());
			for(int i=0;i<spaceNum;i++){
				builder.append(' ');
			}
			return builder.toString();
		}
		int averageSpace = spaceNum / (wordsQueue.size() - 1);
		while(wordsQueue.size()!=0){
			builder.append(wordsQueue.poll());
			if (wordsQueue.size()!=0){
				for(int i=0;i<averageSpace;i++){
					builder.append(' ');
					spaceNum --;
				}
			}else{
				for(int i=0;i<spaceNum;i++){
					builder.append(' ');
				}
			}
		}
		return  builder.toString();
	}
}
