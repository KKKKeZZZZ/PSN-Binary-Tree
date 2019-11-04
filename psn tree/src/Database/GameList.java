package Database;

/**
 * Class to represent a single linked-list of Database.Game objects. Created for
 * Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @version 1.0
 */
public class GameList {

	public Game head;
	public Game next;

	public GameList(Game head) {
		this.head = head;

	}

	public String toString() {
		// System.out.println("Hello!");
		String game = "";

		if (head == null) {
			return "Empty game list";
		} else {
			Game test = head;
			while (test != null) {
				if (test.getNext() != null) {
					game += test.toString() + "\n";
				} else {
					game += test.toString();
				}
				test = test.getNext();
			}
			return game;
		}
	}

	public Game getGame(String string) {
		if (string == null) {
			throw new IllegalArgumentException();
		}
		Game test = head;
		while (test != null) {
			if (test.getName() == string) {
				return test;
			}
			test = test.getNext();
		}

		// TODO Auto-generated method stub
		return null;
	}

	public void addGame(Game massEffect) {
		if (massEffect == null) {
			throw new IllegalArgumentException();
		}
		boolean exist = false;
		if (head == null) {
			head = massEffect;
		} else {
			Game test = head;
			if (head.getNext() == null) {//for the empty game list
				if (massEffect.equals(test)) {
					exist = true;
				}
			}
			while (test.getNext() != null) {// search if the game is already exist
				if (massEffect.equals(test)) {
					exist = true;

				}
				test = test.getNext();
			}
			if (exist != true) {// if not ,add it
				test.setNext(massEffect);
			}

			// TODO Auto-generated method stub
		}
	}

	public void removeGame(Game g1) {
		if (g1 == null) {
			throw new IllegalArgumentException();
		}

		if (head.equals(g1)) {// if the game is exist and is the root
			head = head.getNext();
		} else {
			Game test = head;
			while (test != null) {
				if (test.getNext() == null) {
					test = test.getNext();
				} else {
					if (test.getNext().equals(g1)) {//get the parent node of the target0
						test.setNext(test.getNext().getNext());
					}
					test = test.getNext();
				}

			}
		}
		// TODO Auto-generated method stub

	}

	public void removeGame(String name) {// the same with object one
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name == head.getName()) {
			head = head.getNext();
		} else {
			Game test = head;
			while (test != null) {
				if (test.getNext() == null) {
					test = test.getNext();
				} else {
					if (name == test.getNext().getName()) {
						test.setNext(test.getNext().getNext());
					}
					test = test.getNext();
				}

			}
		}
		// TODO Auto-generated method stub

	}

	public Game getNext() {

		return this.next;
	}
}
