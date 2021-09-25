package week5package;

/*
 * LinkedList.java - Class to manage LinkedLists.
 * @author: @professorgordon
 * @url: http://johngordon.io/javaadvancedlinkedlists1.php
 * @license: Creative Commons. No Warranty. No Liability.
 * @disclaimer: This code file is intended strictly for
 *              academic purposes. It is NOT intended for
 *              use in production systems.
 */

public class LinkedList {

	Node head;

	public void append(int data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = new Node(data);
	}

	public void prepend(int data) {

		if (head == null) {
			head = new Node(data);
			return;
		}
		
		Node newHead = new Node(data);
		newHead.next = head;
		head = newHead;
	}
	
	public void delete(int data) {
		
		Node current = head;
		Node stitch = head;
		
		while (current.data != data) {
			current = current.next;
			
			if (current.next == null) {
				System.out.println("Entered value for deletion was not found.");
				return;
			}
		}
		
		while (stitch.next != current) {
			stitch = stitch.next;
		}
		
		stitch.next = current.next;
		current = null;
		
	}
	
	public void search(int data) {
		
		Node current = head;
		int count = 1;
		
		while (current.data != data) {
			current = current.next;
			count++;
			
			if (current.next == null) {
				System.out.println("Entered search value was not found.");
				return;
			}
		}
		
		System.out.println("The value " + data + " was found in node " + count);
	}
	
	public void length() {
		
		if (head == null) {
			System.out.println("The length of this linked list is 0");
			return;
		}
		
		Node current = head;
		int count = 1;
		
		while (current.next != null) {
			current = current.next;
			count++;
		}
		
		System.out.println("The length of this linked list is " + count);
	}

	public void printList() {
		Node current = head;
		System.out.print("List Contents: ");
		while (current != null) {
			System.out.print(current.data + " -> ");
			current = current.next;
		}
		System.out.println("null");
	}

}