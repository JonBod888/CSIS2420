package week5package;

/*
 * LinkedListDemo1.java - Program to demonstrate use of the
 *                        LinkedList and Node classes.
 * @author: @professorgordon
 * @url: http://johngordon.io/javaadvancedlinkedlists1.php
 * @license: Creative Commons. No Warranty. No Liability.
 * @disclaimer: This code file is intended strictly for
 *              academic purposes. It is NOT intended for
 *              use in production systems.
 */

public class LinkedListDemo1 {

	public static void main(String[] args)

	{
		LinkedList ll = new LinkedList();
		ll.append(2);
		ll.append(4);
		ll.append(6);
		ll.append(8);
		ll.append(10);
		ll.printList();
		
		ll.prepend(88);
		ll.prepend(99);
		ll.printList();
		
		ll.delete(4);
		ll.printList();
		ll.delete(4);
		
		ll.search(8);
		ll.search(4);
		
		ll.length();
	}

}