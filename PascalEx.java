import java.io.*;
import java.util.*;

public class PascalEx {
    public static void main(String args[]) {
        // Declare the array and variables
        int [] intlist = new int[99];
        int listlen, k, sum,  result;
        double average;
        
        // Initialize the variables
        result = 0;
        sum = 0;

        // Read the length of the array
        Scanner input = new Scanner(System.in);
        listlen = input.nextInt();
        
        // Proceed if the input for the array length is valid
        if (listlen > 0 && listlen < 100) {
            // Read input integers into the array and compute its sum
            for (int n = 0; n < listlen; n++) {
                intlist[n] = input.nextInt();
                sum = sum + intlist[n];
            }

            // Calculate the average of the inputs
            average = 1.0 * sum / listlen;

            // Count the number of integers in the array that are greater than its average
            for (int n = 0; n < listlen; n++)
                if (intlist[n] > average)
                    result = result + 1;
            
            // Print the number of integers in the array that are greater than its average
            System.out.println("The number of values > average is " + result);
        }

        // If the input for the array length is invalid, print the warning message then quit
        else
            System.out.println("Error – input list length is not legal");
    }
    
}