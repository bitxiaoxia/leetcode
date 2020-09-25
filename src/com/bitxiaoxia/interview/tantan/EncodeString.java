package com.bitxiaoxia.interview.tantan;

/**
 * 给定一个经过编码的字符串，输出它解码后的字符串。
 * 编码规则为: k(encoded_string)，表示其中括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2(4) 的输入。
 * <p>
 * 输入1：2(abc)3(cd)ef
 * 输出1：abcabccdcdcdef
 * <p>
 * 输入2：2(1(a)2(b)3(c4(d)))5(b2(a)2(b)4(c))
 * 输出2：abbcddddcddddcddddabbcddddcddddcddddbaabbccccbaabbccccbaabbccccbaabbccccbaabbcccc
 * 输出2：abbcddddcddddcddddabbcddddcddddcddddbaabbccccbaabbccccbaabbccccbaabbccccbaabbcccc
 */
public class EncodeString {
	private String decode(String str) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); ) {
			char c = str.charAt(i);
			if (c >= '1' && c <= '9') {
				//起始位
				int repeatTimes = c - '0';
				// ( 入栈
				int quote = 1;
				int j = i + 2;
				for (; quote > 0 && j < str.length(); j++) {
					if (str.charAt(j) == '(') {
						quote++;
					}
					if (str.charAt(j) == ')') {
						quote--;
					}
				}
				for (int k = 0; k < repeatTimes; k++) {
					builder.append(decode(str.substring(i + 2, j - 1)));
				}
				i = j;
			} else {
				builder.append(c);
				i++;
			}
		}

		return builder.toString();
	}

	private String decodeUseStack(String str) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); ) {
			char c = str.charAt(i);
			if (c >= '1' && c <= '9') {
				//起始位
				int repeatTimes = c - '0';
				// ( 入栈
				for (int k = 0; k < repeatTimes; k++) {
					builder.append(decodeUseStack(str.substring(i + 2)));
				}
			} else {
				builder.append(c);
				i++;
			}
		}
		return builder.toString();
	}

	private class MyStack{
		char[] mem;
	}

	public static void main(String[] args) {
		EncodeString model = new EncodeString();
		System.out.println(model.decode("abbcddddcddddcddddabbcddddcddddcddddbaabbccccbaabbccccbaabbccccbaabbccccbaabbcccc"));
	}

}
