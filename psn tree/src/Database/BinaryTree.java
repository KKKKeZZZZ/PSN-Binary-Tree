package Database;

import java.util.ArrayList;
import Database.Trophy.Rank;

/**
 * Uses a binary search tree to store a database of PlayStation users. Nodes are
 * ordered by user unique key (see the User class for more detail). Created for
 * Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @version 1.0
 */
public class BinaryTree {
	public User target;
	public User root;
	int height;
	int bigger = 0, smaller = 0;

	/**
	 * Making new friends is great. This method should add your new bestie to your
	 * database (tree). Remember that they should be added according to their key.
	 * 
	 * @param friend
	 *            The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException
	 *             if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();

		}
		User test = root;
		if (root == null) { //for empty tree
			root = friend;
			return true;
		}
		while (test.getRight() != null && test.getLeft() != null) { //while loop to search the position to add
																	//the node 
			if (friend.getKey() > test.getKey()) {
				if (test.getRight() != null) {
					test = test.getRight();
				}

			} else if (friend.getKey() < test.getKey()) {
				if (test.getLeft() != null) {
					test = test.getLeft();
				}

			} else if (friend.getKey() == test.getKey()) {// if the node is exist ,return false
				return false;
			}
		}
		if (friend.getKey() > test.getKey()) {	 // add to the right if it is bigger
			test.setRight(friend);
			return true;
		} else if (friend.getKey() < test.getKey()) {
			test.setLeft(friend);
			return true;
		}
		return false;
	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best to remove that
	 * "friend" altogether. This method should remove all trace of that "friend" in
	 * the database (tree).
	 * 
	 * @param friend
	 *            the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException
	 *             if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {

		if (friend == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			return false;
		}
		User target = this.findNode(root, friend.getKey());
		ArrayList<User> user = preTraverse();  // the ordinary tree array
		ArrayList<User> result = new ArrayList<User>();	
		if (target != null) {
			for (int i = 0; i < user.size(); i++) { // the new tree array without the user we wanna delete
				if (!user.get(i).equals(friend)) {
					result.add(user.get(i));
				}
			}
			root = null;
			for (int t = 0; t < result.size(); t++) { //bulid a new avl tree with the node we have
				root = this.insert(root,result.get(t));
			}
			return true;
		}
		return false;
	}

	/**
	 * In your quest to be the very best you need to know how many of your friends
	 * are ranked higher than you. This method should return the number of higher
	 * ranked users that the provided reference user, or zero if there are none
	 * (woot!).
	 * 
	 * @param reference
	 *            The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException
	 *             if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		User test1 = root;
		target = this.findNode(test1, reference.getKey()); // find the position of the target
		if (target == null || root == null) {
			return -1;
		}
		ArrayList<User> user = preTraverse(); // the array to store all node in the tree
		bigger = 0;
		for (int i = 0; i < user.size(); i++) { // to count the user who has a bigger key
			User test = user.get(i);
			if (test.getKey() > reference.getKey()) {
				if (test.getLevel() > reference.getLevel()) {// check if the reference has the high level
					bigger += 1;
				}
			}
			if (test.getKey() == reference.getKey()) {
			}
		}
		return bigger;
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're
	 * actually better than those over whom you're lording your achievements. This
	 * method should find all those friends who have a lower level than you, or zero
	 * if there are none (you suck).
	 * 
	 * @param reference
	 *            The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException
	 *             if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		;
		User test1 = root;
		target = this.findNode(test1, reference.getKey());
		if (target == null || root == null) {
			return -1;
		}
		ArrayList<User> user = preTraverse();
		smaller = 0;
		for (int i = 0; i < user.size(); i++) {
			User test = user.get(i);
			if (test.getKey() == reference.getKey()) {

			}
			if (test.getKey() < reference.getKey()) {// count the user who has the smaller key
				if (test.getLevel() < reference.getLevel()) {
					smaller += 1;
				}
			}
		}
		return smaller;

	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum
	 * trophies.
	 * 
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {
		ArrayList<User> user = preTraverse();
		// System.out.println(user);
		User test;
		int platNum = 0;
		int dopePlat = 0;
		User dopeMan = null;
		for (int u = 0; u < user.size(); u++) {// select the user in the tree
			test = user.get(u);
			platNum = 0;
			for (int i = 0; i < test.getTrophies().size(); i++) {
				if (test.getTrophies().get(i).getRank() == Rank.PLATINUM) {// count the platinum trophy they have
					platNum += 1;
				}
			}
			if (platNum > dopePlat) { // to store the user who has the most platinums
				dopePlat = platNum;
				dopeMan = test;
			}
		}
		return dopeMan;
	}

	/**
	 * You or one of your friends bought a new game! This method should add it to
	 * their GameList.
	 * 
	 * @param username
	 *            The user who has bought the game
	 * @param game
	 *            The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		if (username == null || game == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<User> user = preTraverse();
		User test;
		for (int u = 0; u < user.size(); u++) {
			boolean exist = false;
			test = user.get(u);
			if (test.getUsername() == username) { // select the right user
				Game start = test.getGames().head;
				while (start.getNext() != null) { //check if the game is already exist
					if (start.equals(game)) {
						exist = true;
					}
					start = start.getNext();
				}
				if (!exist) {// if the game is not in the list ,add it
					start.setNext(game);
				}
			}
		}
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it
	 * to their trophies.
	 * 
	 * @param username
	 *            The user who has earned a new trophy
	 * @param trophy
	 *            The trophy to be added
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		if (username == null || trophy == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<User> user = preTraverse();
		User test;
		for (int u = 0; u < user.size(); u++) {
			boolean exist = false;
			test = user.get(u);
			if (test.getUsername() == username) {
				for (int i = 0; i < test.getTrophies().size(); i++) {
					if (trophy.equals(test.getTrophies().get(i))) {
						exist = true;
					}
				}
			}
			if (!exist) {
				test.getTrophies().add(trophy);
			}
		}
	}

	/**
	 * You or one of your friends has gained one level! This is great news, except
	 * that it may have ruined your tree structure! A node move may be in order.
	 * 
	 * @param username
	 *            The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		if (username == null) { // the defriend method alway go wrong!
			throw new IllegalArgumentException();
		}
	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will result
	 * in an unbalanced and inefficient tree. One approach to fix this is to
	 * implement an add method that uses AVL balancing. This method should work in
	 * the same way as beFriend, but maintain a balanced tree according to AVL
	 * rules.
	 * 
	 * @param friend
	 *            The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException
	 *             if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		else {
			
			root = this.insert(root, friend); //add the friend in the tree
			
			return true;
		}
		
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret
	 * scrap-book that you keep hidden underneath your pillow. This method should
	 * print out the details of each user, traversing the tree in order.
	 * 
	 * @return A string version of the tree
	 */
	public String toString() {
		String text = "";
		ArrayList<User> user = inTraverse(); // the example is the list that store the user with in traverse way
		for (int i = 0; i < user.size(); i++) {
			if (i != user.size() - 1) {
				text += user.get(i) + "\n";
			} else {
				text += user.get(i);
			}

		}
		return text;
	}

	public User findNode(User root, double key) { // the same as how we find the position in befriend;
		root = this.root;
		User test = root;
		if (root == null) {
			return null;
		}
		while (test != null) {
			if (key > test.getKey()) {
				test = test.getRight();
			} else if (key < test.getKey()) {
				test = test.getLeft();
			} else if (key == test.getKey()) { // but in this time we need the exist user
				// System.out.println("!Alread exist!");
				return test;
			}
		}
		return null;
	}

	public ArrayList<User> preTraverse() {// traverse the tree from left to root to right
		return preOrderTraverse(root);
	}

	private ArrayList<User> preOrderTraverse(User node) {
		ArrayList<User> list = new ArrayList<User>();
		list.add(node);
		if (node.getLeft() != null) {
			list.addAll(preOrderTraverse(node.getLeft()));// search the node in the left till the left is null;
		}
		if (node.getRight() != null) {
			list.addAll(preOrderTraverse(node.getRight()));// search the node in the right till the right is null;
		}
		return list;
	}

	public ArrayList<User> inTraverse() {// traverse the tree from root to left  to right
		return inOrderTraverse(root);
	}

	private ArrayList<User> inOrderTraverse(User node) {
		ArrayList<User> list = new ArrayList<User>();
		if (node.getLeft() != null) {
			list.addAll(inOrderTraverse(node.getLeft()));
		}
		list.add(node);
		if (node.getRight() != null) {
			list.addAll(inOrderTraverse(node.getRight()));
		}
		return list;
	}

	private int height(User tree) { // get the height of the node in the tree
		if (tree != null) {
			return tree.height;
		}
		return 0;
	}

	public int height() {
		return height(root);
	}

	private User lLRotation(User user2) { // if the left is greater than right
		User user1;

		user1 = user2.getLeft(); 
		user2.setLeft(user1.getRight());// user1's right child become user2's left child
		user1.setRight(user2);// user1 become root

		user2.height = max(height(user2.getLeft()), height(user2.getRight())) + 1;
		user1.height = max(height(user1.getLeft()), user2.height) + 1;

		return user1;
	}

	private User rRRotation(User user1) {
		User user2;

		user2 = user1.getRight();
		user1.setRight(user2.getLeft());//user2's left child become user1's right child
		user2.setLeft(user1);// user2 become root

		user1.height = max(height(user1.getLeft()), height(user1.getRight())) + 1;
		user2.height = max(height(user2.getRight()), user1.height) + 1;

		return user2;
	}
	// these two means the tree need two times rotation to get balance
	private User lRRotation(User user3) {
		user3.setLeft(rRRotation(user3.getLeft()));// user3's left run rRRotation,
													//then user3 run lLRotation
		return lLRotation(user3);
	}

	private User rLRotation(User user1) {
		user1.setRight(lLRotation(user1.getRight()));//user1's right run lLRotation,
													//then user 1 run rRRotation	
		return rRRotation(user1);
	}

	private int max(int a, int b) {
		return a > b ? a : b;
	}
//	public User insert(User tree, User set) {
//		tree = set;
//		return tree;
//	}
	public User insert(User tree, User set) {
		if (tree != null) {
			double compare = set.getKey() - tree.getKey();
			if (compare < 0) { // should set to left
				tree.setLeft(insert(tree.getLeft(), set));
				// if the tree lose balance when add the node ,re balance it
				if (height(tree.getLeft()) - height(tree.getRight()) == 2) {
					if (set.getKey() < tree.getLeft().getKey()) {
						tree = lLRotation(tree);
					} else {
						tree = lRRotation(tree);
					}
				}
			}
			else if (compare > 0) { 
				tree.setRight(insert(tree.getRight(), set));
				if (height(tree.getRight()) - height(tree.getLeft()) == 2) {
					if (set.getKey() > tree.getRight().getKey()) {
						tree = rRRotation(tree);
					}
						
					else {
						tree = rLRotation(tree);
					}			
				}
			}	        
	    }
		else {
			// if the tree is empty
			tree = set;
	        if (tree==null) {
	            return null;
	        }
		}
		tree.height = max(height(tree.getLeft()), height(tree.getRight())) + 1;

		return tree;
	}
	public void insert(User set) {
	    root = insert(this.root, set);
	}
}