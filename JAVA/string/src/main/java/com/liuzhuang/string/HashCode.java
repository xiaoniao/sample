package com.liuzhuang.string;

public class HashCode {


	int hash = 0;
	char value[];
	
	public int myHashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
	
	public static void main(String[] args) {
		
		/*
		1505998204
		1505998205
		1505998206
		1505998207
		1505998208
		1505998209
		1505998210
		1505998211
		1505998212
		1505998213
		 */
		int i = 0;
		while (i < 10) {
			String ip = "127.0.0." + i;
			System.out.println(ip.hashCode());
			i++;
		}
		
		
		char singleWord = '哦';
		System.out.println(singleWord);
		System.out.println(Integer.toBinaryString(singleWord));
		
		String ip = "你好啊";
		char[] charArray = ip.toCharArray();
		for (char c : charArray) {
			System.out.print(c);
		}
		
//		char[] mChar = new char[] {};
//		ip.getChars(0, ip.length(), mChar, 0);
//		for (char c : mChar) {
//			System.out.println(c);
//		}
//		System.out.println(ip.getBytes());
	}
}
