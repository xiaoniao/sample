package com.liuzhuang.thread;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * transient 的变量不会序列化到本地
 */
public class Transient {

	public static class Member implements Serializable{
		private static final long serialVersionUID = -2186455880837197941L;
		private String uid;
		private transient String pwd; // transient 短暂的 瞬变

		public Member(String uid, String pwd) {
			this.uid = uid;
			this.pwd = pwd;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}

		@Override
		public String toString() {
			return "Member [uid=" + uid + ", pwd=" + pwd + "]";
		}
	}

	public static void main(String[] args) {
		Member member = new Member("liu", "bbbb");
		System.out.println(member.toString());
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("member.out"));
			o.writeObject(member);
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("member.out"));
			Member logInfo = (Member) in.readObject();
			System.out.println(logInfo.toString());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
