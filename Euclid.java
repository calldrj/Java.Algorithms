import java.io.*;
import java.util.*;

public class Euclid {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        System.out.println("A?");
        int integerA = input.nextInt();
        if (integerA <= 0) {
            System.out.println("A must be a positive integer.");
            return;
        }

        System.out.println("B?");
        int integerB = input.nextInt();
        if (integerB <= 0) {
            System.out.println("B must be a positive integer."); 
            return;
        }
        
        System.out.println("The GCD of " + integerA + " and " + integerB + " is " + NGCD(integerA, integerB) + ".");
    }

    public static int NGCD(int numberA, int numberB) {
        int intA = numberA;
        int intB = numberB;
        while (intB > 0) {
            int intT = intA;
                intA = intB;
                intB = intT % intB;
        }
        return intA;
    }
}
