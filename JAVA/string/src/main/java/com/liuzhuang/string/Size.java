package com.liuzhuang.string;

import java.io.UnsupportedEncodingException;

public class Size {

	public static void main(String[] args) {
		// 查看字符串hashCode生成规则
		testHashCode();
		// 查看byte基本属性
		testByte();
		// 查看string和char关系
		testStringCharLength();
		// 查看string和byte关系 占几个byte
		testStringByteLength();
	}
	
	private static void testHashCode() {
		System.out.println("String - hashCode");
		char[] value = "abcd".toCharArray();
		int h = 0; // 默认hashCode是0
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
            	System.out.println("----h:" + h + " val[" + i + "]:" + Integer.valueOf(val[i]));
                h = 31 * h + val[i];
                System.out.println("====h:" + h + " val[" + i + "]:" + Integer.valueOf(val[i]));
            }
        }
        System.out.println(h);
	}
	
	/**
	 * byte占一个字节大小 8位
	 * byte的最大值
	 * byte的最小值
	 */
	private static void testByte() {
		System.out.println("basic - byte");
		System.out.println("byte bits length: " + Byte.SIZE);
		byte bMax = 127;
		byte bMin = -128;
		System.out.println("byte max value:" + bMax);
		System.out.println("byte min value:" + bMin);
	}
	
	/**
	 * String 和 char 的关系
	 * char代表一个字符，不管是英文中文韩语日语，因为java采用的编码集是utf-16可以包含目前所有的字符
	 */
	private static void testStringCharLength() {
		System.out.println("String - char");
		String[] strings = {"1234", ".,-=", "abcd", "斯蒂芬说", "1a你好", "사랑해요!", "あなたを爱している"};
		for (String string : strings) {
			char[] chars = string.toCharArray();
			System.out.print("String:" + string + "   chars length:" + chars.length + " hex:[");
			for (char c : chars) {
				System.out.print(c);
			}
			System.out.println("]");
		}

		// utf-8 的表示方法，占几个字节 占1、2、3、4个字节
		// utf-16 的标识方法，占几个字节
//		Character.SIZE
	}
	
	// 字符都需要存成二进制来表示，让电脑来识别再转换显示到屏幕上 ，
	// 比如 
	// 1(8)个字节最多存256个字符                                     2百
	// 2(16)个字节最多存65536个字符                              6万
	// 3(24)个字节最多存16777216个字符                      1千6百万
	// 4(32)个字节最多存4294967296个字符                 42亿
	// 5(40)个字节最多存1099511627776个字符          1万亿
	// 6(48)个字节最多存281474976710656个字符     2千8百万亿
	
	// ASCII 一个字节 8bits 表示英语完全够
	// unicode 只定义了字符的格式 二进制位，但并没有规定具体使用几个字节表示。 其实现方式有 utf-8 utf-16 utf-32
	
	// getBytes() 默认UTF-8 如果默认不支持，报错就尝试 ISO 8859 即 ASCII和后续的扩展集.
	
	/**
	 * String 和 byte 的关系
	 * 字符串String的字节数是变长的不是定长，有的字符代表一个字节 有的字符代表三个字节
	 */
	private static void testStringByteLength() {
		System.out.println("String - byte");
		String[] strings = {"1234", ".,-=", "abcd", "斯蒂芬说", "1a你好"};
		
		System.out.println("ISO-8859-1:");
		for (String string : strings) {
			byte[] bytes;
			try {
				bytes = string.getBytes("ISO-8859-1"); // 默认平台编码现在是UTF-8
				System.out.print("String:" + string + "   bytes length:" + bytes.length + " hex:[");
				for (byte b : bytes) {
					System.out.print(Integer.toHexString(b) + " ");
				}
				System.out.println("]");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("UTF-8");
		for (String string : strings) {
			byte[] bytes;
			try {
				bytes = string.getBytes("UTF-8");
				System.out.print("String:" + string + "   bytes length:" + bytes.length + " hex:[");
				for (byte b : bytes) {
					 System.out.print(Integer.toHexString(b) + " ");
				}
				System.out.println("]");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * char和byte的关系
	 * 1个char 代表1个byte 或3个byte
	 */
}
