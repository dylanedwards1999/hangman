package hangman;

import java.util.Scanner;

public class HangmanGame {
	Scanner scnr = new Scanner(System.in);
	String word = "hangman";
	int wordLength = word.length();
	int lives = 6;
	char[] guess = new char[wordLength];
	char currentLetter;
	boolean wordSolved = false;

	public static void main(String[] args) {
		HangmanGame game = new HangmanGame();
		game.startGame();
	}

	public void startGame() {
		for (int i = 0; i < wordLength; ++i) {
			guess[i] = '_';
		}
		do {
			printProgress();
			currentLetter = scnr.next().charAt(0);
			System.out.println();
			
			if (checkLetter(currentLetter)) {
				updateProgress(currentLetter);
				wordSolved = checkCompleteness();
			}
			else {
				--lives;
			}
		}
		while (lives > 0 && !wordSolved);
		
		endGame();
	}
	
	public boolean checkLetter(char c) {
		for (int i = 0; i < wordLength; ++i) {
			if (word.charAt(i) == c)
				return true;
		}
		return false;
	}
	
	public void updateProgress(char c) {
		for (int i = 0; i < wordLength; ++i) {
			if (word.charAt(i) == c) 
				guess[i] = c;
		}
	}
	
	public boolean checkCompleteness() {
		for (char c : guess) {
			if (c == '_') 
				return false;
		}
		wordSolved = true;
		return true;
	}
	
	public void endGame() {
		if (wordSolved) {
			System.out.println("Congratulations!");
		}
		else {
			System.out.println("The word was " + word + " - try again");
		}
	}
	
	public void printProgress() {
		for (char c : guess) {
			System.out.print(c + " ");
		}
		System.out.printf("     (lives left: %d)\n", lives);
		System.out.print("Your guess: ");
	}

}
