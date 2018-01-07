package com.liuzhuang.projectx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//实现电梯
public class ElevatorSampel {

	/**
	 * 电梯内部
	 * 	第一种情况，电梯在一楼，此时一个人按了5楼，电梯开始往上走，在电梯走的过程中，又有一个人按了4楼。
	 */
	
	static class Elevator {
		volatile int state;// 0静止 1上 2下
		List<Integer> floors = new ArrayList<Integer>();// 楼层
		
		Integer destFloor; //下一次目标楼层
		Integer currentFloor; //当前楼层
		
		public Elevator() {
			new Thread(() -> {

				Integer lastFloor = 1;// 上次楼层

				while (true) {
					
					floors.get(0);
					
					try {
						this.state = 0;
						System.out.println("准备上" + destFloor + "楼");
						if (destFloor - lastFloor > 0) {
							state = 1;//上
						} else {
							state = 2;//下
						}
						
						while(destFloor != currentFloor) {
							Thread.sleep(1000);
							currentFloor++;
						}
						
						System.out.println("到达" + destFloor + "楼\n");
						lastFloor = destFloor;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		/**
		 * 加入楼梯队列
		 */
		public void addFloor(Integer floor) {
			if (floors.contains(floor)) {
				return;
			}
			floors.add(floor);
			Collections.sort(floors);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Elevator a = new Elevator();
		
		Thread.sleep(200);
		a.addFloor(5);
		Thread.sleep(200);
		a.addFloor(4);
		Thread.sleep(200);
		a.addFloor(3);
		Thread.sleep(200);
		a.addFloor(7);
		Thread.sleep(200);
		a.addFloor(9);
		Thread.sleep(200);
	}
}
