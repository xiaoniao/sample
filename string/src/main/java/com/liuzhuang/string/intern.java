package com.liuzhuang.string;

public class Intern {

	public static void main(String[] args) {

//		String str1 = "a";
//		String str2 = "b";
//		String str3 = "ab";
//		String str4 = str1 + str2; // stringbuffer拼接
//		String str5 = new String("ab");
//		System.out.println(str5.equals(str3)); // true 比较字符串里的char
//		System.out.println(str5 == str3); // false
//		System.out.println(str5.intern() == str3); // true
//		System.out.println(str5.intern() == str4); // false
//		System.out.println(str3 == str4);
//
//		System.out.println("------------------------------");
		
		String a = new String("ab");
		String b = new String("ab");
		String c = "ab";
		String d = "a" + "b";
		String e = "b";
		String f = "a" + e;
		System.out.println(b.intern() == a);
		System.out.println(b.intern() == c);
		System.out.println(b.intern() == d);
		System.out.println(b.intern() == f);
		System.out.println(b.intern() == a.intern());

		System.out.println("------------------------------");
		
		/**
		 * 全为 true
		 */
		/*String a = "abc";
		String b = "abc";
		String c = "a" + "b" + "c";
		String d = "a" + "bc";
		String e = "ab" + "c";
		System.out.println(a == b);
		System.out.println(a == c);
		System.out.println(a == d);
		System.out.println(a == e);
		System.out.println(c == d);
		System.out.println(c == e);*/
	}
}
