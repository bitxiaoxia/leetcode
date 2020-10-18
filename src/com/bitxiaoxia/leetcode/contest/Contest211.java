package com.bitxiaoxia.leetcode.contest;

import javafx.scene.layout.Priority;

import java.util.*;

/**
 * Created by Bitxiaoxia on 2020/10/18.
 */
public class Contest211 {
	public static void main(String[] args) {
//		String s = "mgntdygtxrvxjnwksqhxuxtrv";
//		System.out.println(maxLengthBetweenEqualCharacters(s));
		Contest211 contest211 = new Contest211();
//		System.out.println(contest211.findLexSmallestString("43987654", 7, 3));

		int[] scores = {776,566,470,966,279,687,217,676,360,427,650,336,787,239,277,230,648,556,283,116,398,682,362,221,140,446,50,553,280,543,765,556,907,927,351,457,571,274,532,611,553,88,598,739,635,412,893,588,643,376,16,493,239,714,733,15,959,782,623,440,983,742,100,437,683,71,783,381,472,235,422,511,779,715,42,151,320,621,614,434,970};//5431066
		int[] ages = {6,30,82,45,98,99,61,99,96,61,14,50,79,11,30,76,28,28,3,46,53,77,97,12,31,91,88,99,14,85,43,27,9,4,17,49,93,85,96,4,89,6,70,86,37,54,28,80,88,23,60,24,30,78,22,2,59,29,77,1,82,81,35,1,10,6,75,61,69,27,24,85,44,83,51,54,78,58,30,26,44};
		System.out.println(contest211.bestTeamScore(scores,ages));
	}

	public int bestTeamScore(int[] scores, int[] ages) {
		//思路，按照年龄排序
		//先选中最小年纪的队员，再考虑不选中他的情况
		PriorityQueue<Player> playerQueue = new PriorityQueue<>(scores.length,(p1, p2) -> (p1.cmp-p2.cmp));
		for (int i = 0; i < scores.length && i < ages.length; i++) {
			playerQueue.add(new Player(ages[i], scores[i]));
		}
		List<Player> players = new ArrayList<>();
		while(playerQueue.size()>0){
			players.add(playerQueue.poll());
		}
		return getMaxValue(players, 0,0);
	}

	private int getMaxValue(List<Player> players, int pos,int minScore) {
		Player player = players.get(pos);
		if (players.size()-1 == pos){
			if (player.score >= minScore){
				return player.score;
			}else{
				return 0;
			}
		}else{
			if (player.score >= minScore){
				//选中当前队员，需要之后的队员，得分都比自己高。
				int selectedValue = getMaxValue(players, pos+1,player.score) + player.score;
				//不选中当前队员，则只考虑后面的情况。
				int unSelectedValue = getMaxValue(players, pos+1,minScore);
				return selectedValue > unSelectedValue ? selectedValue : unSelectedValue;
			}else{
				//无法选中当前队员
				return getMaxValue(players, pos+1,minScore);
			}
		}
	}

	class Player {
		int age;
		int score;
		int cmp;

		Player(int age, int score) {
			this.age = age;
			this.score = score;
			this.cmp = age*1000000+score;
		}
	}

	public static int maxLengthBetweenEqualCharacters(String s) {
		char[] chars = s.toCharArray();
		int max = -1;
		Map<Character, Integer> posMap = new HashMap<>();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (posMap.containsKey(c)) {
				int oldPos = posMap.get(c);
				int diff = i - oldPos - 1;
				if (diff > max) {
					max = diff;
				}
			} else {
				posMap.put(c, i);
			}
		}
		return max;
	}

	public String findLexSmallestString(String s, int a, int b) {
		//任意数字加上十次，必定得到原数字。
		//对十个数组进行轮转，找到最小值。
		int n = s.length();
		int[][] rollValues = new int[10][n];
		//初始化轮转数组
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			//没写容错
			rollValues[0][i] = chars[i] - '0';
		}

		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < n; j++) {
				rollValues[i][j] = (rollValues[i - 1][j] + a) % 10;
				System.out.print(rollValues[i][j] + " ");
			}
			System.out.println();
		}

		int startPos = 0;
		String minStr = null;
		int minStartPos = 0;
		int minValuesPos = 0;
		do {
			System.out.println(startPos);
			for (int i = 0; i < 10; i++) {
				if (minStr == null) {
					minStr = "1";
					minValuesPos = i;
					minStartPos = startPos;
				} else {
					boolean isMin = compareStr(rollValues[minValuesPos], minStartPos, rollValues[i], startPos);
					if (!isMin) {
						minValuesPos = i;
						minStartPos = startPos;
					}
				}
			}
			startPos += b;
			startPos %= n;
		} while (startPos != 0);
		return getValue(rollValues[minValuesPos], minStartPos);
	}

	private boolean compareStr(int[] v1, int s1, int[] v2, int s2) {
		int n = v1.length;
		boolean v1Flag = true;
		for (int i = 0; i < v1.length; i++) {
			int p1 = (s1 + i) % n;
			int p2 = (s2 + i) % n;
			if (p2 < p1) {
				v1Flag = false;
				break;
			}
		}
		return v1Flag;
	}

	private String getValue(int[] values, int startPos) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			int pos = (startPos + i) % values.length;
			builder.append(values[pos]);
		}
		return builder.toString();
	}
}
