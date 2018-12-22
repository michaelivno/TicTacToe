package TICTacToe;

import java.util.Scanner;

public class theGame {

    public static void main(String[] args) {

        Player p1 = new Player("michael", Type.X);
        Player p2 = new Player("nastya", Type.SIRCLE);
        String[][] theField = new String[3][3];
        theGame(p1, p2, theField);

    }

    private static void theGame(Player p1, Player p2, String[][] theField) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Welcome to the TIC TacToe Game!\n" +
                "Enter your choise:\n" +
                "1) Start the game.\n" +
                "2) Print the scorse of the players.\n" +
                "3) Reset Players Scorse.\n" +
                "4) Print The field.\n" +
                "5) Print the Options.");
        while (!exit) {
            switch (scanner.nextInt()) {
                case 1:
                    boolean exitGame = false;
                    String toType;
                    Player player;

                    System.out.println("Who start the game?\n for " + p1.getName() + " Press 1\n for " + p2.getName() + " Press 2");
                    if (scanner.nextInt() == 1) {
                        player = p1;
                    } else {
                        player = p2;
                    }
                    while (!exitGame) {
                        if (player.getType().equals(Type.X)) {
                            toType = "X";
                        } else {
                            toType = "O";
                        }
                        printTheField(theField);
                        System.out.println("Player " + player.getName() + " Is Playing Now");

                        System.out.print("Number of floor: ");
                        int floor = scanner.nextInt();
                        if (floor >= 0 && floor <= 2) {
                            System.out.print("Number of line: ");
                            int line = scanner.nextInt();
                            if (line >= 0 && line <= 2 && theField[floor][line] == null) {
                                theField = putOnField(floor, line, toType, theField);
                            } else {
                                System.out.println("Not ligal line or ther is alredy some choise on this plase, try again!");
                                if (player.equals(p1)) {
                                    player = p2;
                                } else {
                                    player = p1;
                                }
                            }
                        } else {
                            System.out.println("Not ligal floor try again!");
                            if (player.equals(p1)) {
                                player = p2;
                            } else {
                                player = p1;
                            }
                        }
                        switch (checkForWinner(toType, theField)) {
                            case 1:
                                printTheField(theField);
                                player.win();
                                System.out.println("The winner is: " + player.getName() + " is Score is " + player.win());
                                theField = resetThefield(theField);
                                exitGame = true;
                                break;

                            case 2:
                                System.out.println("Its teko do you want to reset the game and play again?\n" +
                                        "press 1 for yes\n" +
                                        "press 2 for no");
                                theField = resetThefield(theField);
                                if (scanner.nextInt() == 2) {
                                    exitGame = true;
                                }
                                break;
                        }
                        if (player.equals(p1)) {
                            player = p2;
                        } else {
                            player = p1;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Player " + p1.getName() + " Have Score of " + p1.getScore() + " Points");
                    System.out.println("Player " + p2.getName() + " Have Score of " + p2.getScore() + " Points");
                    break;

                case 3:
                    p1.resetScore();
                    p2.resetScore();
                    System.out.println("Players score are reset to 0");
                    break;

                case 4:
                    printTheField(theField);
                    break;

                case 5:
                    System.out.println("Welcome to the TIC TacToe Game!\n" +
                            "Enter your choise:\n" +
                            "1) Start the game.\n" +
                            "2) Print the scorse of the players.\n" +
                            "3) Reset Players Scorse.\n" +
                            "4) Print The field.\n" +
                            "5) Print the Options.");
                    break;

            }
        }
    }

    private static String[][] resetThefield(String[][] theField) {
        for (int i = 0; i < theField.length; i++) {
            for (int j = 0; j < theField[i].length; j++) {
                theField[i][j] = null;
            }
        }
        return theField;
    }

    private static int checkForWinner(String toPrint, String[][] theField) {
        int bingoFlor = 0;
        int bingoRSide = 0;
        int bingoLSide = 0;
        int bingoDown = 0;
        int nullPointer = 0;
        for (int i = 0; i < theField.length; i++) {
            bingoFlor = 0;
            bingoDown = 0;
            for (int j = 0; j < theField.length; j++) {
                if (theField[i][j] != null && theField[i][j].equals(toPrint)) {
                    bingoFlor++;
                }
                if (i == 2 && theField[j][j] != null && theField[j][j].equals(toPrint)) {
                    bingoRSide++;
                }
                if (i == 2 && theField[j][theField.length - j - 1] != null && theField[j][theField.length - j - 1].equals(toPrint)) {
                    bingoLSide++;
                }
                if (theField[j][i] != null && theField[j][i].equals(toPrint)) {
                    bingoDown++;
                }
                if (theField[i][j] == null) {
                    nullPointer++;
                }
            }
            if (bingoFlor == 3 || bingoRSide == 3 || bingoLSide == 3 || bingoDown == 3) {
                return 1;
            }
        }
        if (nullPointer == 0) {
            return 2;
        }
        return 0;
    }

    private static String[][] putOnField(int floor, int line, String toType, String[][] theField) {
        theField[floor][line] = toType;
        return theField;
    }

    private static void printTheField(String[][] theField) {
        System.out.println("   0 , 1 , 2 ");
        for (int i = 0; i < theField.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < theField[i].length; j++) {
                if (theField[i][j] == null) {
                    System.out.print(" ");
                    //theField[i][j] = " ";
                } else {
                    System.out.print(theField[i][j]);
                }
                if (j == 0 || j == 1) {
                    System.out.print(" | ");
                } else if (j == theField[i].length - 1) {
                    System.out.println();
                }
            }
            if (i == 0 || i == 1) {
                System.out.println("   - | - | -");
            }
        }
        System.out.println();
    }
}
