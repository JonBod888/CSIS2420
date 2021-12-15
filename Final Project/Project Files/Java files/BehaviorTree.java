package virtualPet;

/**
 * Tree used to determine optimal Behavior based on Behavior score. Since there multiple layers, results are slightly randomized.
 * This class is admittedly rather rigid at the moment, work should be done to remedy this in the future.
 * 
 * @author Jonathan Bodily
 *
 */
public class BehaviorTree {

	BehaviorTreeNode root;
	
	/**
	 * Inserts a new BehaviorTreeNode root.
	 * 
	 * @param behavior	A Behavior object.
	 */
	public void insert(Behavior behavior) {
		
		root = new BehaviorTreeNode(behavior);
		return;
	}
	
	/**
	 * Inserts three new BehaviorTreeNodes at a time. Will insert the nodes first in the first layer, then in the left layer 2, then the middle
	 * layer 2, and lastly the right layer 2.
	 * 
	 * @param behavior1	First Behavior object.
	 * @param behavior2	Second Behavior object.
	 * @param behavior3	Third Behavior object.
	 */
	public void insert(Behavior behavior1, Behavior behavior2, Behavior behavior3) {
		
		if (root.left == null) {
			
			root.left = new BehaviorTreeNode(behavior1);
			root.middle = new BehaviorTreeNode(behavior2);
			root.right = new BehaviorTreeNode(behavior3);
			
		} else {
			
			BehaviorTreeNode current = root;
			
			if (current.left.left == null) {
				
				current = current.left;
				current.left = new BehaviorTreeNode(behavior1);
				current.middle = new BehaviorTreeNode(behavior2);
				current.right = new BehaviorTreeNode(behavior3);
				
			} else if (current.middle.left == null) {
				
				current = current.middle;
				current.left = new BehaviorTreeNode(behavior1);
				current.middle = new BehaviorTreeNode(behavior2);
				current.right = new BehaviorTreeNode(behavior3);
				
			} else {
				
				current = current.right;
				current.left = new BehaviorTreeNode(behavior1);
				current.middle = new BehaviorTreeNode(behavior2);
				current.right = new BehaviorTreeNode(behavior3);
				
			}
		}
	}
	
	/**
	 * Finds the total score for each branch of the tree and returns the corresponding BehaviorTreeNode.
	 * 
	 * @return	Returns the BehaviorTreeNode from the first layer that corresponds to the highest score.
	 */
	public BehaviorTreeNode findBestAction() {
		
		BehaviorTreeNode current;
		
		int leftScore = 0;
		int middleScore = 0;
		int rightScore = 0;
		
		current = root.left;
		leftScore = current.behavior.score + current.left.behavior.score + current.middle.behavior.score + current.right.behavior.score;
		current = root.middle;
		middleScore = current.behavior.score + current.left.behavior.score + current.middle.behavior.score + current.right.behavior.score;
		current = root.right;
		rightScore = current.behavior.score + current.left.behavior.score + current.middle.behavior.score + current.right.behavior.score;
		
		if (leftScore > middleScore && leftScore > rightScore) {
			return root.left;
		} else if (middleScore > rightScore) {
			return root.middle;
		} else {
			return root.right;
		}
	}
	
	/**
	 * Replaces the root with a new BehaviorTreeNode and deletes the old tree.
	 * 
	 * @param newRoot	The BehaviorTreeNode to become the new root.
	 */
	public void replaceRoot(BehaviorTreeNode newRoot) {
		
		newRoot.left = null;
		newRoot.middle = null;
		newRoot.right = null;
		root = null;
		root = newRoot;
	}
	
}
