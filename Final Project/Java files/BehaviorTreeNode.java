package virtualPet;

/**
 * Node for BehaviorTree. Contains references for up to three other nodes.
 * 
 * @author Jonathan Bodily
 *
 */
public class BehaviorTreeNode {

	Behavior behavior;
	BehaviorTreeNode left;
	BehaviorTreeNode middle;
	BehaviorTreeNode right;
	
	/**
	 * Constructs new BehaviorTreeNode.
	 * 
	 * @param behavior	A Behavior object.
	 */
	public BehaviorTreeNode(Behavior behavior) {
		
		this.behavior = behavior;
	}
}
