package com.bitxiaoxia.leetcode.daily;

import com.bitxiaoxia.leetcode.utils.PrintUtils;

import java.util.Arrays;

/**
 * Created by Bitxiaoxia on 2020/10/24.
 */
public class S1024_VideoStitching {
	public static void main(String[] args) {
//		PrintUtils.changeToArr("[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]");
		int[][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
		S1024_VideoStitching solution = new S1024_VideoStitching();
		System.out.println(solution.videoStitching(clips,10));
	}

	public int videoStitching(int[][] clips, int T) {
		//排序+贪心.
		Arrays.sort(clips,(a1,a2)->{
			if(a1[0]==a2[0]){
				return a1[1] - a2[1];
			}else{
				return  a1[0] - a2[0];
			}});

		int startPos = 0;
		int end = 0;
		int cnt = 0;
		while(true){
			int[] greedArr = greed(clips,startPos,end);
			if (greedArr[1] == -1){
				return  -1;
			}else {
				startPos = greedArr[0];
				end = greedArr[1];
				cnt++;
			}
			if (end >= T){
				return cnt;
			}
			if (startPos == clips.length){
				return -1;
			}
		}
	}

	public int[] greed(int[][] clips,int startPos,int endNum){
		int max = -1;
		int endPos = startPos;
		for(;endPos<clips.length && clips[endPos][0] <= endNum;endPos++){
			if (clips[endPos][1] > max){
				max = clips[endPos][1];
			}
		}
		if (max < endNum){
			max = -1;
		}
		return new int[]{endPos,max};
	}
}
