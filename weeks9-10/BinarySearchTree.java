package binaryTree;

public class BinarySearchTree {
	public BSTNode root;

	public void insert(int data) {
		BSTNode newNode = new BSTNode(data);
		if (root == null) {
			root = newNode;
			return;
		} else {
			BSTNode current = root, parent = null;
			while (true) {
				parent = current;
				if (data < current.data) {
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

	public void preOrderTraverse(BSTNode n) {
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		} else {
			System.out.print(n.data + " ");
			if (n.left != null) {
				preOrderTraverse(n.left);
			}
			if (n.right != null) {
				preOrderTraverse(n.right);
			}
		}
	}

	public void inOrderTraverse(BSTNode n) {

		int nanoDivide = 0;
		long start = 0;
		long end = 0;
		long duration = 0;
		double seconds = 0;
		int traversed = 0;

		if (n == root) {
			nanoDivide = 1000000000;
			start = System.nanoTime();
			traversed = 1;
		}

		if (root == null) {
			System.out.println("Tree is empty");
			return;
		} else {
			if (n.left != null) {
				inOrderTraverse(n.left);
			}
			System.out.print(n.data + " ");
			if (n.right != null) {
				inOrderTraverse(n.right);
			}
		}

		if (traversed > 0) {
			end = System.nanoTime();
			duration = end - start;
			seconds = (double) duration / nanoDivide;
			System.out.println();
			System.out.println();
			System.out.printf("Duration: %,d nanoseconds [%.10f seconds]%n", duration, seconds);
		}
	}

	public void postOrderTraverse(BSTNode n) {
		if (root == null) {
			System.out.println("Tree is empty");
			return;
		} else {
			if (n.left != null) {
				postOrderTraverse(n.left);
			}
			if (n.right != null) {
				postOrderTraverse(n.right);
			}
			System.out.print(n.data + " ");
		}
	}

	public BSTNode search(BSTNode n, int data) {

		if (n == null) {
			return null;
		}

		if (n.data == data) {
			return n;
		} else {

			if (data < n.data) {
				return search(n.left, data);
			}

			if (data > n.data) {
				return search(n.right, data);
			}
		}

		return null;
	}

	public int depth(BSTNode n) {

		if (n == null) {
			return 0;
		}

		int left = 0;
		int right = 0;

		if (n.left != null) {
			left = 1 + depth(n.left);
		}

		if (n.right != null) {
			right = 1 + depth(n.right);
		}

		if (left > right) {
			return left;
		} else {
			return right;
		}
	}

	public void delete(BSTNode n, int data) {

		if (n == null) {
			return;
		}
		if (data == root.data) {
			int i = data + 1;
			BSTNode inorder = null;
			BSTNode parent = null;
			while (inorder == null) {
				inorder = search(root, i);
				i++;
			}

			i = inorder.data + 1;

			while (parent == null) {
				parent = search(root, i);
				i++;
			}

			inorder.left = root.left;

			if (root.right != inorder) {
				inorder.right = root.right;
			}
			root = inorder;
			parent.left = null;
		}

		if (n.left != null && n.left.data == data) {
			if (n.left.left == null && n.left.right == null) {
				n.left = null;
			} else if (n.left.left != null && n.left.right != null) {
				int i = data + 1;
				BSTNode inorder = null;
				while (inorder == null) {
					inorder = search(root, i);
					i++;
				}
				if (n.left.left != inorder) {
					inorder.left = n.left.left;
				}
				if (n.left.right != inorder) {
					inorder.right = n.left.right;
				}
				n.left = inorder;
			} else {
				if (n.left.left != null) {
					n.left = n.left.left;
					n.left.left = null;
				} else {
					n.left = n.left.right;
					n.left.right = null;
				}
			}
		} else if (n.right != null && n.right.data == data) {
			if (n.right.left == null && n.right.right == null) {
				n.right = null;
			} else if (n.right.left != null && n.right.right != null) {
				int i = data + 1;
				BSTNode inorder = null;
				while (inorder == null) {
					inorder = search(root, i);
					i++;
				}
				if (n.right.left != inorder) {
					inorder.left = n.right.left;
				}
				if (n.right.right != inorder) {
					inorder.right = n.right.right;
				}
				n.right = inorder;

			} else {
				if (n.right.left != null) {
					n.right = n.right.left;
					n.right.left = null;
				} else {
					n.right = n.right.right;
					n.right.right = null;
				}
			}
		} else {

			if (data < n.data) {
				delete(n.left, data);
			}

			if (data > n.data) {
				delete(n.right, data);
			}
		}

	}
}