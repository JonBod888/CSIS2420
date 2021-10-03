package gamePackage;

public class GameLinkedList {

	GameNode head;

	/**
	 * Adds a node to the end of the linked list
	 * 
	 * @param playerId   integer identifier
	 * @param firstName  player's first name
	 * @param lastName   player's last name
	 * @param playerName in-game name
	 * @param playerType class of character
	 * @param lifePoints double value
	 * @param totalScore integer score
	 */
	public void append(int playerId, String firstName, String lastName, String playerName, PlayerType playerType,
			double lifePoints, int totalScore) {

		if (head == null) {
			head = new GameNode(playerId, firstName, lastName, playerName, playerType, lifePoints, totalScore);
			return;
		}

		GameNode current = head;

		while (current.next != null) {
			current = current.next;
		}
		current.next = new GameNode(playerId, firstName, lastName, playerName, playerType, lifePoints, totalScore);

	}

	/**
	 * Deletes a node based on playerId
	 * 
	 * @param playerId
	 */
	public void delete(int playerId) {

		if (head.playerId == playerId) {
			head = head.next;
			return;
		}

		GameNode current = head;

		while (current.next != null) {
			if (current.next.playerId == playerId) {
				break;
			}
			current = current.next;
		}

		if (current.next == null) {
			return;
		}
		current.next = current.next.next;

	}

	/**
	 * Returns the length of the linked list as an integer
	 * 
	 * @return
	 */
	public int length() {

		if (head == null) {
			return 0;
		}

		int length = 1;

		GameNode current = head;

		while (current.next != null) {
			current = current.next;
			length++;
		}

		return length;
	}

	/**
	 * Adds a node to the beginning of the linked list
	 * 
	 * @param playerId
	 * @param firstName
	 * @param lastName
	 * @param playerName
	 * @param playerType
	 * @param lifePoints
	 * @param totalScore
	 */
	public void prepend(int playerId, String firstName, String lastName, String playerName, PlayerType playerType,
			double lifePoints, int totalScore) {

		if (head == null) {
			head = new GameNode(playerId, firstName, lastName, playerName, playerType, lifePoints, totalScore);
			return;
		}

		GameNode newHead = new GameNode(playerId, firstName, lastName, playerName, playerType, lifePoints, totalScore);
		newHead.next = head;
		head = newHead;
	}

	/**
	 * Prints a list of all players and their attributes
	 */
	public void printPlayerList() {

		if (head == null) {
			System.out.println("List is Empty");
			return;
		}

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("P L A Y E R   L I S T");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "PlayerID", "FirstName", "LastName", "PlayerName",
				"PlayerType", "LifePoints", "TotalScore");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");

		GameNode current = head;

		while (current.next != null) {

			System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", current.playerId, current.firstName,
					current.lastName, current.playerName, current.playerType, current.lifePoints, current.totalScore);
			current = current.next;
		}
		System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", current.playerId, current.firstName,
				current.lastName, current.playerName, current.playerType, current.lifePoints, current.totalScore);
	}

	/**
	 * Searches for a player based on playerId and returns the node reference
	 * 
	 * @param playerId
	 * @return
	 */
	public GameNode search(int playerId) {

		if (head.playerId == playerId) {
			return head;
		}

		GameNode current = head;

		while (current.next != null) {
			current = current.next;

			if (current.playerId == playerId) {
				return current;
			}
		}

		return null;
	}

	/**
	 * Searches for a player based on firstName and lastName and returns the node
	 * reference
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public GameNode search(String firstName, String lastName) {

		if (head.firstName.equalsIgnoreCase(firstName) && head.lastName.equalsIgnoreCase(lastName)) {
			return head;
		}

		GameNode current = head;

		while (current.next != null) {
			current = current.next;

			if (current.firstName.equalsIgnoreCase(firstName) && current.lastName.equalsIgnoreCase(lastName)) {
				return current;
			}
		}

		return null;

	}

	/**
	 * Searches for a player based on playerName(in-game name) and returns the node
	 * reference
	 * 
	 * @param playerName
	 * @return
	 */
	public GameNode search(String playerName) {

		if (head.playerName.equalsIgnoreCase(playerName)) {
			return head;
		}

		GameNode current = head;

		while (current.next != null) {
			current = current.next;

			if (current.playerName.equalsIgnoreCase(playerName)) {
				return current;
			}
		}

		return null;
	}

	/**
	 * Searches for all players of a specified type and prints a list including all
	 * attributes
	 * 
	 * @param playerType
	 */
	public void search(PlayerType playerType) {

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		switch (playerType) {
		case WARLOCK:
			System.out.print("W A R L O C K");
			break;
		case THIEF:
			System.out.print("T H I E F");
			break;
		case SORCERER:
			System.out.print("S O R C E R E R");
			break;
		case WARRIOR:
			System.out.print("W A R R I O R");
			break;
		case MONK:
			System.out.print("M O N K");
			break;
		case WITCH:
			System.out.print("W I T C H");
			break;
		case RANGER:
			System.out.print("R A N G E R");
			break;
		case DRUID:
			System.out.print("D R U I D");
			break;
		case PALADIN:
			System.out.print("P A L A D I N");
			break;
		case CLERIC:
			System.out.print("C L E R I C");
		}
		System.out.println("   L I S T");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", "PlayerID", "FirstName", "LastName", "PlayerName",
				"PlayerType", "LifePoints", "TotalScore");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");

		GameNode current = head;

		while (current.next != null) {

			if (current.playerType == playerType) {
				System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", current.playerId, current.firstName,
						current.lastName, current.playerName, current.playerType, current.lifePoints,
						current.totalScore);
			}
			current = current.next;
		}

		if (current.playerType == playerType) {
			System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%n", current.playerId, current.firstName,
					current.lastName, current.playerName, current.playerType, current.lifePoints, current.totalScore);
		}
	}

	public String highScore() {

		GameNode high = head;

		GameNode current = head;

		while (current.next != null) {
			current = current.next;

			if (current.totalScore == high.totalScore) {
				return "There are multiple players with the highest score.";
			}
			
			if (current.totalScore > high.totalScore) {
				high = current;
			}
		}

		return "Player ID: " + high.playerId + "  Name: " + high.firstName + " " + high.lastName + "  Player Name: "
				+ high.playerName + "  Player Type: " + high.playerType + "  LifePoints: " + high.lifePoints
				+ "  Score: " + high.totalScore;
	}

	public String lowScore() {

		GameNode low = head;

		GameNode current = head;

		while (current.next != null) {
			current = current.next;

			if (current.totalScore == low.totalScore) {
				return "There are multiple players with the lowest score.";
			}
			
			if (current.totalScore < low.totalScore) {
				low = current;
			}
		}

		return "Player ID: " + low.playerId + "  Name: " + low.firstName + " " + low.lastName + "  Player Name: "
				+ low.playerName + "  Player Type: " + low.playerType + "  LifePoints: " + low.lifePoints
				+ "  Score: " + low.totalScore;
	}
}
