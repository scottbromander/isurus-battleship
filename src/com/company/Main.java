package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int GAME_WIDTH = 5;
    private static final int GAME_HEIGHT = 5;
    private static final int TARGETS = 5;

    private static char[][] grid = new char[GAME_WIDTH][GAME_HEIGHT];
    private static char[][] targets = new char[GAME_WIDTH][GAME_HEIGHT];

    private static int hitsLeft = TARGETS;
    private static int guesses = 0;

    public static void main(String[] args) {
        for (int i = 0; i < GAME_WIDTH; i++) {
            for (int j = 0; j < GAME_HEIGHT; j++) {
                grid[i][j] = 'O';
            }
        }

        assignHits();

        while(hitsLeft > 0) {
            promptUser();
            printMap();
        }

        System.out.println("YOU WIN!");
        System.out.println("TOTAL GUESSES: " + guesses);
    }

    public static void assignHits() {
        Random random = new Random();
        for (int i = 0; i < TARGETS; i++) {
            int x = random.nextInt(GAME_WIDTH);
            int y = random.nextInt(GAME_HEIGHT);
            targets[x][y] = 'Y';
        }
    }

    public static void printMap() {
        for (int i = 0; i < GAME_WIDTH; i++) {
            for (int j = 0; j < GAME_HEIGHT; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void promptUser() {
        // COLLECT GUESSES FROM THE USER
        int xGuess;
        int yGuess;

        try {
            xGuess = Integer.parseInt(getInput("ENTER AN X VALUE:"));
        } catch (Exception error) {
            System.out.println("THAT IS NOT A NUMBER FOOL!");
            xGuess = -1;
        }

        try {
            yGuess = Integer.parseInt(getInput("ENTER AN Y VALUE:"));
        } catch (Exception error) {
            System.out.println("THAT IS NOT A NUMBER FOOL!");
            yGuess = -1;
        }

        try {
            if (targets[yGuess][xGuess] == 'Y') {
                grid[yGuess][xGuess] = 'H';
                targets[yGuess][xGuess] = 'O';
                System.out.println("HIT!");
                --hitsLeft;
                ++guesses;
            } else if (grid[yGuess][xGuess] == 'M') {
                System.out.println("You already guessed that spot!");
            } else {
                grid[yGuess][xGuess] = 'M';
                System.out.println("MISS!");
                ++guesses;
            }

        } catch (Exception error) {
            System.out.println("You entered a position that does not exist! Try Again!");
        }
    }

    public static String getInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
