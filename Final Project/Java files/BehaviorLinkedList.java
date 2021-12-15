package virtualPet;

/**
 * Linked List for storing Behavior objects.
 * 
 * @author Jonathan Bodily
 *
 */
public class BehaviorLinkedList {

	BehaviorNode head;
	
	/**
	 * Adds BehaviorNode to the end of the list.
	 * 
	 * @param index		Number used for searching for a particular BehaviorNode.
	 * @param behavior	A Behavior object.
	 */
	public void append(int index, Behavior behavior) {
		
		if (head == null) {
			head = new BehaviorNode(index, behavior);
			return;
		}
		
		BehaviorNode current = head;
		
		while (current.next != null) {
			current = current.next;
			
		}
		
		current.next = new BehaviorNode(index, behavior);
		
	}
	
	/**
	 * Searches for a BehaviorNode based on the index.
	 * 
	 * @param index	Number used for searching for a particular BehaviorNode.
	 * @return		Returns Behavior object.
	 */
	public Behavior search(int index) {
		
		BehaviorNode current = head;
		
		while (current.next != null && current.index != index) {
			
			current = current.next;
			
		}
		
		return current.behavior;
	}
	
}
