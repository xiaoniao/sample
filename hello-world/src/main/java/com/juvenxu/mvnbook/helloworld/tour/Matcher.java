package com.juvenxu.mvnbook.helloworld.tour;

import java.util.regex.Pattern;

public class Matcher {

	public static void main(String[] args) {
		
		// java中的 \\ 代表 正则表达式中的 \
		
		// -?\\d+ 已减号开头活着 没有减号开头，接下来是1个或多个数字
		System.out.println("-1234".matches("-?\\d+"));
		System.out.println("+1234".matches("-?\\d+"));
		System.out.println("1234".matches("-?\\d+"));
		
		// (-|\\+)?\\d+ 已减号或者加号开头，或者什么都没有，接下来是数字 + 号在正则表达式中有特殊的意义，所以用\\将其转义，相当于\+
		System.out.println("-1234".matches("(-|\\+)?\\d+"));
		System.out.println("+1234".matches("(-|\\+)?\\d+"));
		System.out.println("1234".matches("(-|\\+)?\\d+"));
		
		// w单词字符 W非单词字符
		System.out.println("fuck".matches("f\\w+"));
		System.out.println("f0".matches("f\\w+"));
		System.out.println("f你好".matches("f\\w+"));
		
		System.out.println("----");
		for (String string : new String[] {"Rudolph", "[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*"}) {
			System.out.println("Rudolph".matches(string));
		}
		
		// pattert 有abc 至少出现一次 至少出现两次
		for (String regx : new String[] {"abc+", "(abc)+", "(abc){2,}"}) {
			Pattern pattern = Pattern.compile(regx);
			java.util.regex.Matcher matcher = pattern.matcher("pppabcabcabcdefabc");
			while(matcher.find()) {
				System.out.println(matcher.group() + "    " +  matcher.start() + "-" + matcher.end());
			}
		}
	}
}
