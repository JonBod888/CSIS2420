package binaryTree;

import java.util.Random;

public class BSTDemo 
{
	public static void main(String[] args) 
	{
		
        int max = 999999;
        int numNodes = 10000000;
        Random rand = new Random();
        
		BinarySearchTree bst = new BinarySearchTree();
		
		for (int i = 0; i < numNodes; i++) {
			bst.insert(rand.nextInt(max));
		}
		
		System.out.println("inOrder Traverse\t");
		bst.inOrderTraverse(bst.root);
	}
}
