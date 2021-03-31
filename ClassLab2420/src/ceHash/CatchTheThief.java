package ceHash;

import java.util.Scanner;

public class CatchTheThief {
	private final int WINNING_SCORE = 20;
	private int policeScore;
	private int  userScore;
	private Predictor p;
	
	public void startChase() {
		p = new Predictor();
		while(!done()) {
			Predictor.recordMove(getLatestMove());
			printScore();
		}
		displayResults();
	}
	
	private boolean done() {
		return policeScore == WINNING_SCORE || userScore == WINNING_SCORE;
	}
	
	private Move getLatestMove() {
		Scanner input = new Scanner(System.in);
		System.out.print("Next move L(left) or R(right): ");
		String response = input.nextLine();
		System.out.println(response);
		if(response.toLowerCase().startsWith("l")) {
			updateScore(p.predict(),Move.LEFT);
			return Move.LEFT;
		}else if(response.toLowerCase().startsWith("r")) {
			updateScore(p.predict(),Move.RIGHT);
			return Move.RIGHT;
		}else {
			System.out.println("Not a valid response. Try Again");
			return getLatestMove();
		}
	}
	
	private void updateScore(Move prediction, Move userChoice) {
		if(userChoice.equals(prediction)) {
			System.out.print("The prediction was correct. ");
			policeScore++;
		}else {
			System.out.print("The prediction was incorrect. ");
			userScore++;
		}
	}
	
	private void printScore() {
		System.out.printf("Thief: %d Police: %d \n", userScore, policeScore);
	}
	
	private void displayResults() {
		if(policeScore > userScore)
			System.out.println("The police outsmarted the thief!");
		if(userScore > policeScore)
			System.out.println("The theif outsmarted the police!");
	}

}
