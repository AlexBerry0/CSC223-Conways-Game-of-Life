import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner keyboard = new Scanner(System.in);

//        Lets the user specify how many columns and rows they would like
        System.out.println("How many rows and columns would you like?");
        float rowsandcolumns = (float) 0;
        while (rowsandcolumns <= 5 || rowsandcolumns >= 50) {
            rowsandcolumns = (float) keyboard.nextFloat();
//          If the number is less than or equal to five it will ask them again as it is too small
            if (rowsandcolumns <=5 ) {
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
        for (int i = 0; i < gridarray.length; i++) {
            for (int j = 0; j < gridarray[i].length; j++) {
                if (Math.random() < 0.8) {
                    gridarray[i][j] = 0;
                } else {
                    gridarray[i][j] = 1;
                }
            }
        }
//        Prints Generation 0, to make this easier to understand by humans a 0 is converted into a - and a 1 is converted into a X
        System.out.println("Generation 0:");
        for (int i = 0; i < gridarray.length; i++) {
            for (int j = 0; j < gridarray[i].length; j++) {
                if (gridarray[i][j] == 0) {
                    System.out.print(" "+'-');
                } else {
                    System.out.print(" "+'X');
                }
            }
            System.out.println();
        }




    };
}