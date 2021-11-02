package computerNetwork;

/**
 * A binary search tree using nodes that contain an IP address between 1 and 255 and a single String username
 * 
 * @author Jonathan Bodily
 *
 */
public class CNBinarySearchTree {
	public CNNode root;

	/**
	 * Adds a new node based on an integer ip
	 * If the tree is empty, the new node will become the root
	 * If the new node is less than the current node, it will go to the left, otherwise, it will go to the right
	 * 
	 * @param ip	A 3-digit integer that determines the position of a node in the tree
	 * @param user	A String formated in lower case and no whitespace with the first initial and last name of a user
	 */
	public void insert(int ip, String user) {
		CNNode newNode = new CNNode(ip, user);
		if (root == null) {
			root = newNode;
			return;
		} else {
			CNNode current = root, parent = null;
			while (true) {
				parent = current;
				if (ip < current.ip) {
					current = current.left;
					if (current == null) {
						parent.left = newNode;
						return;
					}
				} else {
					current = current.right;
					if (current == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Searches the tree for a specified ip address and returns the reference
	 * 
	 * @param n		The starting node, generally the root
	 * @param ip	The 3-digit integer to be searched for 
	 * @return		If ip is found, returns the reference of the node it was found in, otherwise, returns null
	 */
	public CNNode search(CNNode n, int ip) {

		if (n == null) {
			return null;
		}

		if (n.ip == ip) {
			return n;
		} else {

			if (ip < n.ip) {
				return search(n.left, ip);
			}

			if (ip > n.ip) {
				return search(n.right, ip);
			}
		}
		
		return null;
	}
	
	/**
	 * Searches the tree for a specified username and prints the node elements
	 * 
	 * @param n		The starting node, generally the root
	 * @param user	The String username to be searched for
	 */
	public void search(CNNode n, String user) {
		
		if (root == null) {
			System.out.printf("User %s not found", user);
			return;
		}
		
		if ((n.user).contentEquals(user)) {
			System.out.printf("Found: 10.0.0.%d %s\n", n.ip, n.user);
			return;
			
		} else {
			if (n.left != null) {
				search(n.left, user);
			}
			
			if (n.right != null) {
				search(n.right, user); 
			}
		}
		
	}
	
	/**
	 * Traverses the tree and prints elements found in each node in ascending order of the ip addresses
	 * 
	 * @param n		The starting node, generally the root
	 */
	public void inOrderTraverse(CNNode n) {

		if (root == null) {
			System.out.println("Tree is empty");
			return;
		} else {
			if (n.left != null) {
				inOrderTraverse(n.left);
			}
			System.out.println("10.0.0." + n.ip + "\t" + n.user);
			if (n.right != null) {
				inOrderTraverse(n.right);
			}
		}

	}
	
	/**
	 * Traverses the tree and returns the total number of nodes as an integer
	 * 
	 * @param n		The starting node, generally the root
	 * @return		An integer equal to the total number of nodes
	 */
	public int numNodes(CNNode n) {
		
		if (n == null) {
			return 0;
		}

		int left = 0;
		int right = 0;

		if (n.left != null) {
			left = 1 + numNodes(n.left);
		}

		if (n.right != null) {
			right = 1 + numNodes(n.right);
		}
		
		if (n == root) {
			return left + right + 1;
		} else {
			return left + right;
		}
	}
}
