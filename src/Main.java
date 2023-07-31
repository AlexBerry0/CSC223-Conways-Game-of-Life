import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner keyboard = new Scanner(System.in);
        boolean manual = false;

//        Lets the user specify how many columns and rows they would like
        System.out.println("How many rows and columns would you like?");
        float rowsandcolumns = (float) 0;
        while (rowsandcolumns <= 5 || rowsandcolumns >= 50) {
            rowsandcolumns = (float) keyboard.nextFloat();
//          If the number is less than or equal to five it will ask them again as it is too small
            if (rowsandcolumns <= 5) {
                System.out.println("That number is too small please try again");
            }
//          If the number is greater than or equal to 50 it will ask them again as it is too big


            else if (rowsandcolumns >= 50) {
                System.out.println("That number is too big please try again");
            }
        }
//        Creates a new two-dimensional Array that has as many rows and columns as the user requested
        int[][] gridarray = new int[(int) rowsandcolumns][(int) rowsandcolumns];

//        Randomly makes 1 in 5 of those Columns "alive" by setting their value to 1
        if (manual == false) {
            for (int i = 0; i < gridarray.length; i++) {
                for (int j = 0; j < gridarray[i].length; j++) {
                    if (Math.random() < 0.8) {
                        gridarray[i][j] = 0;
                    } else {
                        gridarray[i][j] = 1;
                    }
                }
            }
        }
//        Prints Generation 0, to make this easier to understand by humans a 0 is converted into a - and a 1 is converted into a X
        System.out.println("Generation 0:");
        for (int i = 0; i < gridarray.length; i++) {
            for (int j = 0; j < gridarray[i].length; j++) {
                if (gridarray[i][j] == 0) {
                    System.out.print(" " + '-');
                } else {
                    System.out.print(" " + 'X');
                }
            }
            System.out.println();
        }

//        Declares Variables relating to the big loop, namely a counter for the number of generations and the number of changes that occur in each generation
        int numOfGens = 0;
        int numOfChangesInGen = 0;





//      Massive do while loop for running the program to calculate each next generation
        do {
//            adds one to the number of geneations and sets the number of changes in each generation to 0
            numOfGens++;
            numOfChangesInGen = 0;
//            Creates a new 2d array that is used to store what values each of the cells should be updated to at the end of the calculations
            int[][] gridarraytwoelectricboolgaloo = new int[(int) rowsandcolumns][(int) rowsandcolumns];
//            Log the generation number
            System.out.print("Generation: "+ numOfGens + "\n");

//           For loop that goes through each cell
            for (int i = 0; i < gridarray.length; i++) {
                for (int j = 0; j < gridarray[i].length; j++) {

//                  creates a variable that is used to store the number of neighbors
                    int perSquareCounterOfNeighbours = 0;

//                  For every cell the program checks nine cells, the 3 above, 3 on the line, and 3 below, this means the cell itself is counted but this is delt with later
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int newI = i + di;
                            int newJ = j + dj;

                            if (newI >= 0 && newI < gridarray.length && newJ >= 0 && newJ < gridarray[i].length) {
                                if (gridarray[newI][newJ] == 1) {
//                              For every alive cell it increments the neighbour counter by one

                                    perSquareCounterOfNeighbours++;
                                }
                            }
                        }
                    }
                    if (gridarray[i][j] == 1) {
//                      if the cell in question is alive, and it has 2 or fewer neighbours (1 more than what is set in the Conways's game rules because it counts itself), then set that cell to dead in the second 2d array
                        if (perSquareCounterOfNeighbours <= 2) {
                            gridarraytwoelectricboolgaloo[i][j] = 0;
//                          increment the number of changes in this generation by one
                            numOfChangesInGen++;
                        } else if (perSquareCounterOfNeighbours <= 4) {
//                            if the cell in question is alive, and it has 4 or fewer neighbours (1 more than what is set in the Conways's game rules because it counts itself), then set that cell to alive in the second 2d array
                            gridarraytwoelectricboolgaloo[i][j] = 1;
                        } else {
//                            Otherwise set it to dead in the other 2d array
                            gridarraytwoelectricboolgaloo[i][j] = 0;
//                          increment the number of changes in this generation by one
                            numOfChangesInGen++;
                        }
                    }
                        if (gridarray[i][j] == 0) {
                            if (perSquareCounterOfNeighbours == 3) {
//                                If the cell in question is dead, and it has exactly 3 neighbours set it to alive in the new 2d array (it counting itself is not an issue here)
                                gridarraytwoelectricboolgaloo[i][j] = 1;
//                          increment the number of changes in this generation by one
                                numOfChangesInGen++;
                            }
                            else {
//                                Otherwise set it to be dead in the new 2d array
                                gridarraytwoelectricboolgaloo[i][j] = 0;
                            }
                    }
                }


            }
//            For loop that sets the grid array to be the same as the new 2d grid array
            for (int i = 0; i < gridarraytwoelectricboolgaloo.length; i++) {
                for (int j = 0; j < gridarraytwoelectricboolgaloo[i].length; j++) {
                    if (gridarraytwoelectricboolgaloo[i][j] == 0) {
                      gridarray[i][j] = 0;
                    } else {
                        gridarray[i][j] = 1;
                    }
                }
            }



//          Print out the grid replacing all 0s with - and all 1s with X
            for (int i = 0; i < gridarray.length; i++) {
                for (int j = 0; j < gridarray[i].length; j++) {
                    if (gridarray[i][j] == 0) {
                        System.out.print(" " + '-');
                    } else {
                        System.out.print(" " + 'X');
                    }
                }
                System.out.println();
            }
//      Log the number of changes in this generation
        System.out.println(numOfChangesInGen);
        }
//        Continue to do this while in each generation there is at least one new change or the generation count gets to be over 1000 (mostly for if there is a bug, or a looping combination)
        while (numOfChangesInGen >= 1 && numOfGens <= 1000);
    }

}