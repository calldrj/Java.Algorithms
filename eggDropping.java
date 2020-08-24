import java.io.*;
import java.util.*;

public class Project3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many eggs?");
        int eggs = input.nextInt();
        System.out.println("How many windows?");
        int windows = input.nextInt();
        System.out.println("The answer is " + leastDrop(eggs, windows) + ".");
    }
    
    // Return the minimum value between 2 integers
    public static int min(int p, int q) { return (p > q) ? q : p; }
    
    // Return the maximum value between 2 integers
    public static int max(int p, int q) { return (p > q) ? p : q; }

    
    // Return the minimum number of egg drops given the (#eggs, #windows) using Dynamic Programming technique
    public static int leastDrop(int eggs, int windows) {
        
        // Create a 2-D array to contain all minimum drops for e = [0,..., eggs] X w = [0,..., windows]
        int [][] numdrops = new int [eggs + 1][windows + 1];
        int e, w, x;
        
        // Fill up the 1st and 2nd rows of the 2-D array using the base cases
        for (w = 0; w <= windows; w++) {
            numdrops[0][w] = 0;
            numdrops[1][w] = w;
        }

        // Fill up the 1st column of the 2-D array using the base cases
        for (e = 0; e <= eggs; e++)
            numdrops[e][0] = 0;

        // Fill up the remaining cells of the 2-D array using bottom-up Dynamic Programming technique
        for (e = 2; e <= eggs; e++)
            for (w = 1; w <= windows; w++) {
                int minimum = Integer.MAX_VALUE;
                for (x = 1; x <= w; x++)
                    minimum = min(minimum, (1 + max(numdrops[e][w-x], numdrops[e - 1][x - 1])));
                numdrops[e][w] = minimum;
            }

        return numdrops[eggs][windows];
    }
}

