package com.juvenxu.mvnbook.helloworld2;

import java.util.LinkedList;

public class Linked {

	public static class Node {
		public Node next;
		private String name;

		public Node() {
			super();
		}

		public Node(String name) {
			super();
			this.name = name;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void display() {
			System.out.print(name + " ");
		}
	}

	public static class LinkedNode {
		private Node first;
		private int pos = 0;

		public LinkedNode() {
			this.first = null;
		}

		public void addFirstNode(String name) {
			Node node = new Node(name);
			node.next = first;
			first = node;
		}

		public Node deleteFirstNode() {
			Node tempNode = first;
			first = tempNode.next;
			return tempNode;
		}

		// 1 2 3
		// 1 2 4 3
		public Node add(int index, String name) {
			Node node = new Node(name);
			Node current = first;
			Node previous = first;
			// index 3 pos 0 1 2
			while (pos != index) {
				previous = current;
				current = current.next;
				pos++;
			}
			node.next = current;
			previous.next = node;
			pos = 0;
			return node;
		}

		// 0 1 2 3
		public Node deleteByPos(int index) {
			Node current = first;
			Node previous = first;
			while (pos != index) {
				previous = current;
				current = current.next;
				pos++;
			}
			previous.next = current.next;
			pos = 0;
			return current;
		}

		// 根据节点的data删除节点(仅仅删除第一个)
		public Node deleteByData(String name) {
			Node current = first;
			Node previous = first; // 记住上一个节点
			while (current.name != name) {
				if (current.next == null) {
					return null;
				}
				previous = current;
				current = current.next;
			}
			if (current == first) {
				first = first.next;
			} else {
				previous.next = current.next;
			}
			return current;
		}

		// 显示出所有的节点信息
		public void displayAllNodes() {
			Node current = first;
			while (current != null) {
				current.display();
				current = current.next;
			}
			System.out.println();
		}

		// 根据位置查找节点信息
		public Node findByPos(int index) {
			Node current = first;
			if (pos != index) {
				current = current.next;
				pos++;
			}
			return current;
		}

		// 根据数据查找节点信息
		public Node findByData(String name) {
			Node current = first;
			while (current.name != name) {
				if (current.next == null)
					return null;
				current = current.next;
			}
			return current;
		}
	}

	public static void main(String[] args) {
		// Node node = new Node();
		// node.setName("jack");
		//
		// List<String> list = new ArrayList<String>();
		//
		 LinkedList<String> linkedList = new LinkedList<String>();
		// linkedList.add("a");
		// linkedList.addFirst("b");
		// linkedList.addLast("c");
		// linkedList.add(3, "");
		//
		// linkedList.getFirst();
		// linkedList.getLast();
		// linkedList.get(1);
		//
		// linkedList.size();

		LinkedNode linkList = new LinkedNode();
		linkList.addFirstNode("c");
		linkList.addFirstNode("b");
		linkList.addFirstNode("a");
		linkList.displayAllNodes();

		linkList.add(1, "e");
		linkList.add(2, "ee");
		linkList.add(3, "eee");
		linkList.displayAllNodes();

		Node node = linkList.deleteFirstNode();
		System.out.println("delete first : " + node.name);
		linkList.displayAllNodes();
		
		Node deleteBydata = linkList.deleteByData("b");
		System.out.println("delete data: " + deleteBydata.name);
		linkList.displayAllNodes();
		
		Node deleteBypos = linkList.deleteByPos(0);
		System.out.println("delete pos : " + deleteBypos.name);
		linkList.displayAllNodes();
		
		Node firstNode = linkList.findByPos(0);
		System.out.println("first node : " + firstNode.name);
		
		Node node2 = linkList.findByData("c");
		System.out.println("node2: " + node2.name);
	}
}
