package com.github.zipcodewilmington;

//Jared Thacker


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Wordguess {

    private static final String[] words = {"cat", "dog", "bog", "cut"};
    public static Random randomGenerator = new Random(System.currentTimeMillis());
    public static Scanner scanner = new Scanner(System.in);

    public Wordguess() {
    }

    public Wordguess(long seed) {
        randomGenerator = new Random(seed);
    }

    public void runGame() {
        boolean runGame = false;
        do{
            playGame();


            runGame = askToPlayAgain();
        } while(runGame);
    }

    public void playGame() {
        int randomIndex = randomGenerator.nextInt(words.length);
        String randomWord = words[randomIndex];
        int guessNumber = 0;
        char[] randomWordArray = randomWord.toCharArray();
        char[] guesses = this.generateUnderscores(randomWord.length());

        while(guessNumber < randomWord.length()){
             gameState(guesses);
             char guess = askGuess();
             boolean check = checkLetter(randomWordArray, guess);
             if (check){
                 int indexGuess = replaceLetter(randomWordArray, guess);
                 guesses[indexGuess] = guess;
             }
             guessNumber++;
        }
        if (Arrays.equals(guesses, randomWordArray)){
            System.out.println(youWon());
        }else{
            System.out.println(youLost());
        }
    }

    public char[] generateUnderscores(int length) {
        char[] underscoreArray = new char[length];
        Arrays.fill(underscoreArray, '_');
        return underscoreArray;
    }

    public void gameState(char[] guesses){
        System.out.println(guesses);
    }

    public char askGuess(){
        System.out.println("What is your guess? ");
        String guess = scanner.nextLine();
        return guess.charAt(0);

    }

    public boolean checkLetter(char[] randomWordArray, char guess){
        for (char eachLetter : randomWordArray){
            if (guess == eachLetter){
                return true;
            }
        }
        return false;
    }

    public int replaceLetter(char[] randomWordarray, char guess){
        for (int i = 0; i < randomWordarray.length; i++){
            if (guess == randomWordarray[i]){
                return i;
            }
        }
        return -1;
    }


    public boolean askToPlayAgain() {
        System.out.println("Do you want to play again? y/n ");
        String playAgain = scanner.nextLine();

        return playAgain.equalsIgnoreCase("y");
    }

    public String youWon(){
        return "You Won! Congrats \n" ;
    }

    public String youLost(){
        return "You Lost! \n";
    }
}






