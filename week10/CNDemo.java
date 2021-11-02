package computerNetwork;

import java.util.Scanner;

import computerNetwork.CNDemo;

/**
 * Tests the CNBinarySearchTee and CNNode Classes
 * 
 * @author Jonathan Bodily
 *
 */
public class CNDemo {

	/**
	 * The main method
	 * provides a user menu on the console and calls methods in response to user input
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		CNBinarySearchTree bst = new CNBinarySearchTree();
		
		Scanner input = new Scanner(System.in);
		int choice;
		@SuppressWarnings("unused")
		String notValid;
		boolean exit = false;
		CNNode searchNode;
		
		do {
			System.out.println();
			System.out.println();
			System.out.println("-------------------------");
			System.out.println("1 Build Users Tree");
			System.out.println("2 Find by IP Adress");
			System.out.println("3 Find by Username");
			System.out.println("4 Report Number of Nodes");
			System.out.println("5 Print Entire Tree");
			System.out.println("6 Exit");
			System.out.println("-------------------------");
			System.out.println("Enter 1, 2, 3, 4, 5, or 6:");
			
			while (!input.hasNextInt()) {
				notValid = input.next();
				System.out.println("Please enter a valid integer");
			}
			
			choice = input.nextInt();
			input.nextLine();
			
			System.out.println();
			
			switch (choice) {
			case 1: 
				constructNetwork(bst);
				System.out.println("Network was built");
				break;
			case 2: 
				System.out.println("Please enter the last 3 digits of the IP Adress");
				while (!input.hasNextInt()) {
					notValid = input.next();
					System.out.println("Please enter a valid integer");
				}
				int enterIP = input.nextInt();
				input.nextLine();
				searchNode = bst.search(bst.root, enterIP);
				if (searchNode == null) {
					System.out.printf("IP 10.0.0.%d not found\n", enterIP);
				} else {
					System.out.printf("Found: 10.0.0.%d %s\n", searchNode.ip, searchNode.user);
				}
				break;
			case 3:
				System.out.println("Please enter the name with first initial and last name as one word   Example: jhernandez");
				while (!input.hasNext()) {
					
				}
				String enterUser = input.next().toLowerCase();
				input.nextLine();
				bst.search(bst.root, enterUser);
				break;
			case 4: 
				System.out.println("There are " + bst.numNodes(bst.root) + " nodes");
				break;
			case 5: 
				bst.inOrderTraverse(bst.root);
				break;
			case 6: 
				exit = true;
				System.out.println("Goodbye");
				break;
			default: 
				System.out.println("Invalid Choice: Please enter an integer between 1 and 6");
			}
			
		} while (!exit);
		
		input.close();
	}

	/**
	 * Reads users.csv and populates a CNBinarySearchTree with the values therein
	 * 
	 * @param bst	A CNBinarySearchTree
	 */
	private static void constructNetwork(CNBinarySearchTree bst) {
		
		Scanner reader = new Scanner(CNDemo.class.getResourceAsStream("users.csv"));
		reader.useDelimiter(",");
		
		while (reader.hasNext()) {
			bst.insert(reader.nextInt(), reader.nextLine().substring(1));
		}

		reader.close();
	}

}
