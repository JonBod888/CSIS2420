package gamePackage;

public class GameNode {

	GameNode next;
	int playerId;
	String firstName;
	String lastName;
	String playerName;
	PlayerType playerType;
	double lifePoints;
	int totalScore;

	public GameNode(int playerId, String firstName, String lastName, String playerName, PlayerType playerType,
			double lifePoints, int totalScore) {

		this.playerId = playerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.playerName = playerName;
		this.playerType = playerType;
		this.lifePoints = lifePoints;
		this.totalScore = totalScore;
	}
}
