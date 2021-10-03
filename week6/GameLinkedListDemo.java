package gamePackage;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class GameLinkedListDemo {

	public static void main(String[] args) throws FileNotFoundException {

		GameLinkedList gll = new GameLinkedList();

		constructLinkedList(gll);

		Scanner input = new Scanner(System.in);
		boolean exit = false;
		String choice;
		Random random = new Random();

		GameNode node;
		int playerId = 0;
		String firstName;
		String lastName;
		String playerName;
		String playerType;

		do {

			menu();
			choice = input.nextLine();
			choice = choice.toLowerCase();
			switch (choice) {
			case "add":
				System.out.println("Please enter your first name.");
				firstName = input.nextLine();
				System.out.println("Please enter your last name.");
				lastName = input.nextLine();
				System.out.println("Please enter your in-game name.");
				playerName = input.nextLine();
				playerType = "";
				boolean valid = false;
				while (!valid) {
					System.out.println("Please enter your player type out those available.");
					System.out
							.print("Warlock, Thief, Sorcerer, Warrior, Monk, Witch, Ranger, Druid, Paladin, Cleric\n");
					playerType = input.nextLine();

					if (playerType.equalsIgnoreCase("warlock") || playerType.equalsIgnoreCase("Thief")
							|| playerType.equalsIgnoreCase("sorcerer") || playerType.equalsIgnoreCase("warrior")
							|| playerType.equalsIgnoreCase("monk") || playerType.equalsIgnoreCase("witch")
							|| playerType.equalsIgnoreCase("ranger") || playerType.equalsIgnoreCase("druid")
							|| playerType.equalsIgnoreCase("paladin") || playerType.equalsIgnoreCase("cleric")) {
						valid = true;
					} else {
						System.out.print("Invalid type\n\n");
					}
				}
				// creates unique id
				boolean uniqueId = false;
				while (!uniqueId) {
					playerId = (random.nextInt(8999) + 1000);

					if (gll.search(playerId) == null) {
						uniqueId = true;
					}
				}
				gll.append(playerId, firstName, lastName, playerName, PlayerType.valueOf(playerType.toUpperCase()), 0, 0);
				System.out.println("Added...");
				break;
			case "delete":
				System.out.println("Please enter the player ID of the you wish to delete.");
				playerId = Integer.parseInt(input.nextLine());
				gll.delete(playerId);
				System.out.println("Deleted...");
				break;
			case "total":
				System.out.println("Total number of players: " + gll.length());
				break;
			case "print":
				gll.printPlayerList();
				break;
			case "search id":
				System.out.println("Please enter the player ID to be searched.");
				playerId = Integer.parseInt(input.nextLine());
				node = gll.search(playerId);

				if (node == null) {
					System.out.println("Player was not found.");
				} else {
					System.out.println("Player ID: " + node.playerId + "  Name: " + node.firstName + " " + node.lastName
							+ "  Player Name: " + node.playerName + "  Player Type: " + node.playerType
							+ "  LifePoints: " + node.lifePoints + "  Score: " + node.totalScore);
				}
				break;
			case "search real name":
				System.out.println("Please enter the first name.");
				firstName = input.nextLine();
				System.out.println("Please enter the last name.");
				lastName = input.nextLine();
				node = gll.search(firstName, lastName);

				if (node == null) {
					System.out.println("Player was not found.");
				} else {
					System.out.println("Player ID: " + node.playerId + "  Name: " + node.firstName + " " + node.lastName
							+ "  Player Name: " + node.playerName + "  Player Type: " + node.playerType
							+ "  LifePoints: " + node.lifePoints + "  Score: " + node.totalScore);
				}
				break;
			case "search game name":
				System.out.println("Please enter the player's in-game name.");
				playerName = input.nextLine();
				node = gll.search(playerName);

				if (node == null) {
					System.out.println("Player was not found.");
				} else {
					System.out.println("Player ID: " + node.playerId + "  Name: " + node.firstName + " " + node.lastName
							+ "  Player Name: " + node.playerName + "  Player Type: " + node.playerType
							+ "  LifePoints: " + node.lifePoints + "  Score: " + node.totalScore);
				}
				break;
			case "highscore":
				System.out.println(gll.highScore());
				break;
			case "lowscore":
				System.out.println(gll.lowScore());
				break;
			case "exit":
				exit = true;
				break;
			default:
				System.out.println("Invalid choice");
			}

			System.out.print("\n\n");

		} while (!exit);

		System.out.println("Farewell...");
		input.close();

	}

	private static void constructLinkedList(GameLinkedList gll) throws NumberFormatException {

		Scanner reader = new Scanner(GameLinkedListDemo.class.getResourceAsStream("Players.csv"));
		reader.useDelimiter(",");

		int i = 1;

		while (reader.hasNext()) {
			if (i == 1) {

				gll.append(Integer.parseInt(reader.next().substring(3)), reader.next(), reader.next(), reader.next(),
						PlayerType.valueOf(reader.next().toUpperCase()), reader.nextDouble(),
						Integer.parseInt(reader.nextLine().substring(1)));
				i--;
			}
			gll.append(reader.nextInt(), reader.next(), reader.next(), reader.next(),
					PlayerType.valueOf(reader.next().toUpperCase()), reader.nextDouble(),
					Integer.parseInt(reader.nextLine().substring(1)));
		}

		reader.close();
	}

	private static void menu() {

		System.out.println();
		System.out.println();
		System.out.println("Please enter one of the following:");
		System.out.println("--------------------------------------");
		System.out.printf("%-20sAdds a new player.\n", "Add:");
		System.out.printf("%-20sDeletes a player.\n", "Delete:");
		System.out.printf("%-20sShows the total number of players.\n", "Total:");
		System.out.printf("%-20sPrints a list of all players.\n", "Print: ");
		System.out.printf("%-20sSearches for a player based on Player ID.\n", "Search ID:");
		System.out.printf("%-20sSearches for a player based on real name.\n", "Search Real Name:");
		System.out.printf("%-20sSearches for a player based on in-game name.\n", "Search Game Name:");
		System.out.printf("%-20sSearches for the player with the highest score.\n", "Highscore:");
		System.out.printf("%-20sSearches for the player with the lowest score.\n", "Lowscore:");
		System.out.printf("%-20sExits the program.\n", "Exit:");
	}

}
