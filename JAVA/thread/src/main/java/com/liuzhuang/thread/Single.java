package com.liuzhuang.thread;

public class Single {

	private static volatile Single single = null;

	private Single() {

	}

	public Single getInstance() {
		if (single == null) {
			synchronized (Single.class) {
				if (single == null) {
					single = new Single();
				}
			}
		}
		return single;
	}
}
