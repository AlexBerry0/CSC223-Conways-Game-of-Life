
/**
 * Title: Conway's Game of life
 * Author: Alex Berry
 * Date: 2-August-2023
 * Version: 3
 * Purpose: Simulate Conway's game of life
 **/


import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static int DEAD = 0;
    public static int ALIVE = 1;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        Scanner keyboard = new Scanner(System.in);
        boolean manualSelection = false;
        //        Declares Variables relating to the big loop, namely a counter for the number of generations and the number of changes that occur in each generation
        int numOfGens = 0;
        int numOfChangesInGen = 0;
//        Also Declares a string that will be used for getting a string input
        String inputString = "";
//      Calls the get user input method with the messages asking how many rows and columns would the user like and how many gens would the user like. It also calls them with a min and max length of possible resulting number
        int rowsAndColumns = getUserInput(keyboard, "How many rows and columns would you like?", 5, 50);
        int numOfUserSpecifiedGenerations = getUserInput(keyboard, "In the case that the program doesn't finish before then how many generations would you like to simulate", 1, 1000);
        int delayBetweenGens = getUserInput(keyboard, "How many milliseconds would you like there to be between generations? (1000 is one second", 1, 10000);

//      This code asks the user if they would like to set the alive and dead starting cells manually
        System.out.println("Would you like to set the alive and dead starting cells manually?");
//        A loop so that the user has to input either yes or no
        while (!inputString.equalsIgnoreCase("yes") && !inputString.equalsIgnoreCase("no")) {
            inputString = keyboard.nextLine();
//          inputString is set to what the user inputs and if its yes then manual selection is set to yes and if its no then to keep it false
            if (!inputString.equalsIgnoreCase("yes") && !inputString.equalsIgnoreCase("no")) {
                System.out.println("That is not a valid answer, a valid answer is \"yes\" or \"no\" ");
            } else if (inputString.equalsIgnoreCase("yes")) {
                manualSelection = true;
            }
        }


        //        Calls a method that will create a new two-dimensional Array that has as many rows and columns as the user requested and contains a bool value for if the user wants to set the values themselves or not
        int[][] gridArray = initializeGrid(rowsAndColumns, manualSelection);

//        Prints Generation 0, by calling the printGrid method
        System.out.println("Generation 0:");
        printGrid(gridArray);

//        Do while loop that does all the logic
        do {
//          increments the number of generations by one
            numOfGens++;
//          Creates new grid array that is the result of calculating the next generation from the main grid array
            int[][] newGridArray = calculateNextGeneration(gridArray);
//          sets the number of changes in the generation to be the number of differences between gridArray and newGridArray
            numOfChangesInGen = countChanges(gridArray, newGridArray);

//          Sets gridArray to be the same as newGridArray
            gridArray = newGridArray;
//          Makes the code delay for the user specified time
            Thread.sleep(delayBetweenGens);
//          Prints gridArray and the Generation number then prints the number of changes in that generations
            System.out.println("Generation: " + numOfGens);
            printGrid(gridArray);
//            System.out.println(numOfChangesInGen);
//          continue doing this while the number of changes in the last generation was 1 or bigger and the number of Gens is less than the number of user requested gens
        } while (numOfChangesInGen >= 1 && numOfGens <= numOfUserSpecifiedGenerations - 1);
    }

    public static int getUserInput(Scanner keyboard, String message, int min, int max) {
//        Create an integer called input value and set it to 0
        int inputValue = 0;
//      create a boolean variable to track weather the inputted value is a number
        boolean isValid;

        do {
//          print out the message parameter the reset isValid to true after every loop
            System.out.println(message);
            isValid = true;
//          In the inputted value is not a number prompt them again and set isValid to false
            if (!keyboard.hasNextInt()) {
                System.out.println("That's not a number! Please input a number:");
                isValid = false;
            } else {
//              Otherwise set inputValue to the result of an integer inputted by the user
                inputValue = keyboard.nextInt();
//              Checks if the inputValue is within the suggested range
                if (inputValue <= min || inputValue >= max) {
//                  if its too small tell the user and prompt them again, also set isValid to false
                    if (inputValue <= min) {
                        System.out.println("That number is too small please try again");
                    }
//                  if its too big tell the user and prompt them again, also set isValid to false
                    else {
                        System.out.println("That number is too big please try again");
                    }
                    isValid = false;
                }
            }
            keyboard.nextLine();
        } while (!isValid); // Repeat the loop if input was invalid
//      When its valid return the input value
        return inputValue;
    }


    public static int[][] initializeGrid(int rowsAndColumns, boolean manualSelection) {
//      create a new 2d array with the size of the variable rowsandColumns
        int[][] gridArray = new int[rowsAndColumns][rowsAndColumns];
//        if manual selection is not true go through each cell and for each one make 1 in 5 "alive" by setting their value to 1 and the other 4 dead by setting their value to 0
        if (!manualSelection) {
            for (int i = 0; i < gridArray.length; i++) {
                for (int j = 0; j < gridArray[i].length; j++) {
                    if (Math.random() < 0.8) {
                        gridArray[i][j] = DEAD;
                    } else {
                        gridArray[i][j] = ALIVE;
                    }
                }
            }
        }
//      else, therefor if manual selection is true then do this
        else {
//          create a new scanner for the keyboard and a string that temporary values of a string can be stores in
            Scanner keyboard = new Scanner(System.in);
            String inputString = "";
//          for each of the cells
            for (int i = 0; i < gridArray.length; i++) {
                for (int j = 0; j < gridArray[i].length; j++) {
//                    clear the input string then print out asking if the user would like each cell to be alive, they can then answer in a string
                    inputString = "";
                    System.out.print("Would you like cell " + (i + 1) + ", " + (j + 1) + " to be alive?\n");
                    while (!inputString.equalsIgnoreCase("yes") && !inputString.equalsIgnoreCase("no")) {
                        inputString = keyboard.nextLine();
//                      if they answer yes then the cell becomes alive, if they answer no then the cell becomes dead and if they answer something else they get prompted again
                        if (!inputString.equalsIgnoreCase("yes") && !inputString.equalsIgnoreCase("no")) {
                            System.out.println("That is not a valid answer, a valid answer is \"yes\" or \"no\" \n");
                        } else if (inputString.equalsIgnoreCase("yes")) {
                            gridArray[i][j] = ALIVE;
                        } else {
                            gridArray[i][j] = DEAD;
                        }
                    }
                }
            }
        }
//      Then return the gridArray
        return gridArray;
    }

    public static int[][] calculateNextGeneration(int[][] gridArray) {
        int[][] newGridArray = new int[gridArray.length][gridArray[0].length];

        // For every cell the program checks nine cells, the 3 above, 3 on the line, and 3 below, if it finds an alive neighbour then dd to the perSquareCounterOfNeighbours, if the cell itself is alive it will count itself but this will be dealt with later
        for (int i = 0; i < gridArray.length; i++) {
            for (int j = 0; j < gridArray[i].length; j++) {
                int perSquareCounterOfNeighbours = 0;
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int newI = i + di;
                        int newJ = j + dj;

                        if (newI >= 0 && newI < gridArray.length && newJ >= 0 && newJ < gridArray[i].length) {
                            if (gridArray[newI][newJ] == ALIVE) {
                                perSquareCounterOfNeighbours++;
                            }
                        }
                    }
                }

                if (gridArray[i][j] == 1) {
//                      if the cell in question is alive, and it has 2 or fewer neighbours (1 more than what is set in the Conways's game rules because it counts itself), then set that cell to dead in the second 2d array
                    if (perSquareCounterOfNeighbours <= 2) {
                        newGridArray[i][j] = DEAD;
                    }
//                            if the cell in question is alive, and it has 4 or fewer neighbours (1 more than what is set in the Conways's game rules because it counts itself), then set that cell to alive in the second 2d array
                    else if (perSquareCounterOfNeighbours <= 4) {
                        newGridArray[i][j] = ALIVE;
//                            Otherwise set it to dead in the other 2d array
                    } else {
                        newGridArray[i][j] = DEAD;
                    }
                } else {
//                                If the cell in question is dead, and it has exactly 3 neighbours set it to alive in the new 2d array (it counting itself is not an issue here)
                    if (perSquareCounterOfNeighbours == 3) {
                        newGridArray[i][j] = ALIVE;
                    } else {
//                                Otherwise set it to be dead in the new 2d array
                        newGridArray[i][j] = DEAD;
                    }
                }
            }
        }

        return newGridArray;
    }

    public static int countChanges(int[][] oldGrid, int[][] newGrid) {
//        method that counts the number of changes by going through each cell and if is different to the one in the new array then adding to the list of changes by one
        int changes = 0;
        for (int i = 0; i < oldGrid.length; i++) {
            for (int j = 0; j < oldGrid[i].length; j++) {
                if (oldGrid[i][j] != newGrid[i][j]) {
                    changes++;
                }
            }
        }
//        returns the number of changes
        return changes;
    }


    public static void printGrid(int[][] grid) {
//      A method that goes through every cell and prints every 0 as a - and every one as an X and separates them by a space, then after a row prints a new line
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == DEAD) {
                    System.out.print(" " + '-');
                } else {
                    System.out.print(" " + 'X');
                }
            }
            System.out.println();
        }
    }


}