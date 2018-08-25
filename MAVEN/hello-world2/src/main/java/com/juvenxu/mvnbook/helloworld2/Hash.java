package com.juvenxu.mvnbook.helloworld2;

import java.util.HashMap;

/**
 * 用来确定字符唯一性，但是
 */
public class Hash {
	
	/**
	 * Object hashcode 注释
	 * 
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap
     * 
     * 
     * The general contract of hashCode is:
     *     
     *  1. Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method must consistently 
     *     return the same integer, provided no information used in equals comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an application to another execution of the same application.
     *  2. If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of
     *     the two objects must produce the same integer result.
     *  3. It is not required that if two objects are unequal according to the Object.equals(java.lang.Object) method, then calling the hashCode method 
     *     on each of the two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     *     
     * As much as is reasonably practical, the hashCode method defined by class Object does return distinct integers for distinct objects.
     *  (This is typically implemented by converting the internal address of the object into an integer, but this implementation 
     *  technique is not required by the
     * Java&trade; programming language.)
     *
     * @return  a hash code value for this object.
     * @see     java.lang.Object#equals(java.lang.Object)
     * @see     java.lang.System#identityHashCode
	 */

	public static int hashCodeN(String hash) {
		char[] value = hash.toCharArray();
		int h = 0;
		if (value.length > 0) {
			char val[] = value;
			for (int i = 0; i < value.length; i++) {
				System.out.print("31 * " + h + " + " + Integer.valueOf(val[i]));
				h = 31 * h + val[i];
				System.out.println(" = " + h);
			}
		}
		return h;
	}

	public static void main(String[] args) {
		String hash = "0000";
		System.out.println(hash.hashCode());
		System.out.println(hashCodeN(hash));;
		
		// hashCode相同的字符串
		System.out.println("buzzards".hashCode());
		System.out.println("righto".hashCode());
		System.out.println("hierarch".hashCode());
		System.out.println("crinolines".hashCode());
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "json");
		map.get("name");
	}
}
