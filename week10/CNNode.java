package computerNetwork;

/**
 * Custom Node class for use with the CNBinarySearchTree class
 * Includes left and right CNNode references, an integer for an IP address, and a String for a username
 * 
 * @author Jonathan Bodily
 *
 */
public class CNNode {
	
	CNNode left;
	CNNode right;
	int ip;
	String user;
	
	/**
	 * Constructs a CNNode
	 * 
	 * @param ip	An integer between 1 and 255 representing the last three digits of an IP address
	 * @param user	A String formated in lower case and no whitespace with the first initial and last name of a user
	 */
	public CNNode (int ip, String user) {
		
		this.ip = ip;
		this.user = user;
	}

}
