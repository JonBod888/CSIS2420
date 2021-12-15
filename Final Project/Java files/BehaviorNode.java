package virtualPet;

/**
 * Node for BehaviorLinkedList. Contains an index and Behavior object.
 * 
 * @author Jonathan Bodily
 *
 */
public class BehaviorNode {

	int index;
	Behavior behavior;
	BehaviorNode next;
	
	/**
	 * Constructs BehaviorNode.
	 * 
	 * @param index		Number used for searching for a particular BehaviorNode.
	 * @param behavior	A Behavior object.
	 */
	public BehaviorNode(int index, Behavior behavior) {
		
		this.index = index;
		this.behavior = behavior;
	}
}
