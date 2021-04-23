package com.szymon.thehangman;

import java.util.Arrays;

public class Hangman {

    // how many chances to guess
    private final int LIVES = 5;
    private final int MAX_CHARACTERS = 15;

    // word is lowercase
    private String word;
    private char[] answer;
    private int wordLength;
    private int livesLeft;
    private GameStatus gameStatus;

    private enum GameStatus {
        GAME_STARTED,
        GAME_STOPPED,
        GAME_WON,
        GAME_LOST
    }


    Hangman() {
        this.word = "";
        this.livesLeft = LIVES;
        this.gameStatus = GameStatus.GAME_STOPPED;
    }

    /**
     * Initializes answer array and fills it with '_' in same amount as word length.
     * @param word String
     * @return void
     */
    private void initAnswer(String word) {

        wordLength = word.length();
        answer = new char[wordLength];

        for(int i = 0; i < wordLength; i++) {
            if (word.charAt(i) != ' ') {
                // if it isn't a space
                answer[i] = '_';
            }
        }
    }

    /**
     * Fills answer array with userGuess when index of character in word and array matches
     */
    private void completeTheAnswerWith(final char userGuess) {
        for(int i = 0; i < wordLength; i++) {
            if(userGuess == answer[i]) {
                answer[i] = userGuess;
            }
        }
    }

    /**
     * Checks if user have guessed correctly and makes appropriate action.
     * @param userGuess character
     * @return boolean
     */
    private boolean isGuessCorrect(final char userGuess) {

        // converting userGuess to CharSequence to use it in contains() metehod
        CharSequence guess = String.valueOf(userGuess);
        return word.contains(guess);
    }

    /**
     * Checks if word doesn't exceed character limit.
     * @param word String
     * @return boolean
     */
    private boolean isWordValid(String word) {

        // might implement case when string is empty
        return word.length() <= MAX_CHARACTERS;
    }

    /**
     * Checks if game is won.
     */
    private boolean isWon() {

        // if answer doesn't have '_' in then it is fully filled and game is won
        return !Arrays.toString(answer).contains("_");
    }

    /**
     * Checks if game is lost.
     */
    private boolean isLost() {
        return livesLeft <= 0;
    }

    /**
     * Starts new game
     */
    private void Start() {
        // restart game data
        if (!(gameStatus == GameStatus.GAME_STARTED)) {
            livesLeft = LIVES;
            gameStatus = GameStatus.GAME_STARTED;
            System.out.println("New game has been started!");
        }
    }

    /**
     * Creates a new game with given word.
     * @param word String
     * @return void
     */
    public void newGame(String word) {

        if(isWordValid(word)) {
            this.word = word.toUpperCase();
            initAnswer(word);
            Start();
        } else {
            System.out.println("Error! \"" + word + "\" is not valid!");
        }
    }

    /**
     * Enables player to input his guess.
     * <ul>
     *     <li>
     *         If guess is CORRECT then spaces in answer are filled with matching character.
     *     </li>
     *     <li>
     *         If guess is NOT CORRECT then 1 life is lost.
     *     </li>
     *     <li>
     *         If answer is completed then the game is won and gameStatus is updated.
     *     </li>
     *     <li>
     *         If lives = 0 than game is lost and gameStatus is updated.
     *     </li>
     * </ul>
     */
    public void enterGuess(final char userGuess) {
        if(isGuessCorrect(userGuess)) {
            System.out.println("Guessed correctly!");
            // TODO implement dialogs
            completeTheAnswerWith(userGuess);

            if(isWon()) {
                System.out.println("Congratulations! You won!");
                gameStatus = GameStatus.GAME_WON;
            }

        } else {
            System.out.println("Missed!");
            livesLeft--;

            if(isLost()) {
                System.out.println("Ops! You lost! :(");
                gameStatus = GameStatus.GAME_LOST;
            }
        }
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public char[] getAnswer() {
        return answer;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public int getWordLength() {
        return wordLength;
    }

    public String getWord() {
        return word;
    }



}
