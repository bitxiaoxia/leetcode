package com.bitxiaoxia.leetcode.utils;

import java.util.List;

/**
 * @author JasonZhang
 * @date 2020/4/27 17:24
 */
public class PrintUtils {

	public static void changeToArr(String s) {
		s = s.replace("[", "{");
		s = s.replace("]", "}");
		System.out.println(s);
	}

	public  static<T> void print(List<T> list){
		if (list==null||list.size()==0){
			System.out.println("empty list");
		}
		StringBuilder builder = new StringBuilder();
		for (T x:list){
			builder.append(x).append(" ");
		}
		System.out.println(builder.toString());
	}

	public  static<T> String getPrintStr(List<T> list){
		if (list==null||list.size()==0){
			return "empty list";
		}

		StringBuilder builder = new StringBuilder();
		for (T x:list){
			builder.append(x).append(" ");
		}
		return builder.substring(0,builder.length()-1);
	}
}
