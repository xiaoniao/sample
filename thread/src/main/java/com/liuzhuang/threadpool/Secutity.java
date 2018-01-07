package com.liuzhuang.threadpool;

import java.security.Permission;

public class Secutity {

	public static void main(String[] args) {
		Permission permission = new Permission("") {

			@Override
			public boolean implies(Permission permission) {
				return false;
			}

			@Override
			public int hashCode() {
				return 0;
			}

			@Override
			public String getActions() {
				return null;
			}

			@Override
			public boolean equals(Object obj) {
				return false;
			}
		};
		String name = permission.getName();
		System.out.println(name);
		
		permission.getActions();
		permission.checkGuard("");
		permission.implies(null);
		permission.newPermissionCollection();
	}
}
