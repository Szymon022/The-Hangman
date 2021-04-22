package com.szymon.thehangman;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Hangman {

    // how many chances to guess
    private final int LIVES = 5;
    private final int MAX_CHARACTERS = 15;

    // word is lowercase
    private String word;
    private char[] answer;
    private int wordLength;
    private int lives_left;
    private Boolean isStarted;
    private Boolean gameWon;
    private Boolean gameLost;


    Hangman() {
        this.word = "";
        this.lives_left = LIVES;
        this.isStarted = false;
        this.gameWon = false;
        this.gameLost = false;
    }

    private void initAnswer(String word) {
        /**
         * Initializes answer array and fills it with '_' in same amount as word length.
         * @param word - String
         * @return void
         */

        wordLength = word.length();
        answer = new char[wordLength];

        for(int i = 0; i < wordLength; i++) {
            if (word.charAt(i) != ' ') {
                // if it isn't a space
                answer[i] = '_';
            }
        }
    }

    private void completeTheAnswerWith(final char userGuess) {
        /**
         * Fills answer array with userGuess when index of character in word and array matches
         */

        for(int i = 0; i < wordLength; i++) {
            if(userGuess == answer[i]) {
                answer[i] = userGuess;
            }
        }
    }

    private boolean isGuessCorrect(final char userGuess) {
        /**
         * Checks if user have guessed correctly and makes appropriate action.
         * @param userGuess - character
         * @returns boolean
         */
        // converting userGuess to CharSequence to use it in contains() metehod
        CharSequence guess = String.valueOf(userGuess);
        return word.contains(guess);
    }

    private boolean isWordValid(String word) {
        /**
         * Checks if word doesn't exceed character limit.
         * @param word - String
         * @return boolean
         */
        // might implement case when string is empty
        return word.length() <= MAX_CHARACTERS;
    }

    private boolean isWon() {
        /**
         * Checks if game is won.
         */
        // if answer doesn't have '_' in then it is fully filled and game is won
        if(!Arrays.toString(answer).contains("_")) {
            isStarted = false;
            return true;
        } else {
            return false;
        }
    }

    private boolean isLost() {
        if(lives_left <= 0) {
            isStarted = false;
            return true;
        } else {
            return false;
        }
    }

    private void Start() {
        /**
         * Starts new game
         */
        // restart game data
        if (!isStarted) {
            lives_left = LIVES;
            isStarted = true;
            gameWon = false;
            gameLost = false;
        }
    }

    public void newGame(String word) {
        /**
         * Creates a new game with given word.
         * @param word - String
         * @returns void
         */
        if(isWordValid(word)) {
            this.word = word.toUpperCase();
            initAnswer(word);
            Start();
        } else {
            System.out.println("Error! \"" + word + "\" is not valid!");
        }
    }

    public void enterGuess(final char userGuess) {
        /**
         * Enables player to input his guess.
         * <ul>
         *     <li>
         *         If guess is CORRECT then spaces in answer are filled with matching character.
         *     </li>
         *     <li>
         *         If guess is NOT CORRECT then 1 life is lost and the gallows build up.
         *     </li>
         *     <li>
         *         If answer is completed then the game is.
         *     </li>
         *     <li>
         *         If lives = 0 than game is lost.
         *     </li>
         * </ul>
         */

        if(isGuessCorrect(userGuess)) {
            System.out.println("Guessed correctly!");
            // TODO implement a better way to inform that game was won or lost with dialogs
            // TODO implement dialogs
            completeTheAnswerWith(userGuess);
            if(isWon()) {
                gameWon = isWon();
            }

        } else {
            System.out.println("Missed!");

            lives_left--;
            if(isLost()) {
                gameLost = isLost();
            }
        }
    }



}
