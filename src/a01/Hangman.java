package a01;

import java.util.Scanner;

/*
 * Simple hangman game for CS2450.
 *
 * Test for git 
 * @author Dylan Edwards
 */

public class Hangman {
	Scanner scnr = new Scanner(System.in);
	String word = "hangman";
	int wordLength = word.length();
	int lives = 6;
	char[] guess = new char[wordLength];
	char currentLetter;
	boolean wordSolved = false;

	public static void main(String[] args) {
		Hangman game = new Hangman();
		game.startGame();
	}

	/**
	 * Initializes the progress string and starts the game loop
	 */
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
	
	
	/**
	 * Checks whether the given char is in the target word
	 * 
	 * @param c		the char to be checked
	 * @return		whether the char was found or not
	 */
	public boolean checkLetter(char c) {
		for (int i = 0; i < wordLength; ++i) {
			if (word.charAt(i) == c)
				return true;
		}
		return false;
	}
	
	/**
	 * Adds the newly (correctly) guessed char to the progress string
	 * 
	 * @param c		the char to be added
	 */
	public void updateProgress(char c) {
		for (int i = 0; i < wordLength; ++i) {
			if (word.charAt(i) == c) 
				guess[i] = c;
		}
	}
	
	/**
	 * Checks if there are any remaining '_' in the progress string,
	 * indicating that the word has not been solved
	 * 
	 * @return	whether the word has been solved
	 */
	public boolean checkCompleteness() {
		for (char c : guess) {
			if (c == '_') 
				return false;
		}
		wordSolved = true;
		return true;
	}
	
	/**
	 * Prints final string to player--dependent on if they won or lost 
	 */
	public void endGame() {
		if (wordSolved) {
			System.out.println("Congratulations!");
		}
		else {
			System.out.println("The word was " + word + " - try again");
		}
	}
	
	/**
	 * Prints out the progress string
	 */
	public void printProgress() {
		for (char c : guess) {
			System.out.print(c + " ");
		}
		System.out.printf("     (lives left: %d)\n", lives);
		System.out.print("Your guess: ");
	}

}
