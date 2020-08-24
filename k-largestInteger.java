  import java.io.*;
import java.util.*;

public class Project2 {
    public static void main(String[] args) throws IOException {

        // Read the ith smaller for the progam
        Scanner s = new Scanner(new File("input2.txt"));
        int ith = s.nextInt();

        // Read input file into an array of integer
        int [] arrInt1 = readInt("input2.txt");

        // Find and print out the ith smallest of the input array using sort()
        System.out.println("Sort finds "
                            + sortSelect(arrInt1, 0, arrInt1.length - 1, ith)
                            + ".");

        int [] arrInt2 = new int [arrInt1.length];
        int [] arrInt3 = new int [arrInt1.length];
        int j1, j2 = 0, j3 = 0;
        for (j1 = 0; j1 < arrInt1.length; j1++) {
            arrInt2[j2++] = arrInt1[j1];
            arrInt3[j3++] = arrInt1[j1];
        }

        // Find and print out the ith smallest of the input array using QuickSelect()
        System.out.println("Quick finds "
                            + quickSelect(arrInt2, 0, arrInt2.length - 1, ith)
                            + ".");

        // Find and print out the ith smallest of the input array using AlgorithmsX()
        System.out.println("MOM finds "
                            + algorithmX(arrInt3, ith)
                            + ".");
    }

    // Read input into a preference array
    public static int [] readInt(String dataFile) {
        try {
                File inputFile = new File(dataFile);
                Scanner s = new Scanner(inputFile);
                int arrSize = s.nextInt();
                    arrSize = s.nextInt();
                int [] arrInt = new int[arrSize];
                for (int i = 0; i < arrSize; i++)
                    arrInt[i] = s.nextInt();
                return arrInt;
        }
        catch (Exception e) {
                return null;
        }
    }

    // Select the ith smallest integer in a array using standard sort()
    public static int sortSelect(int arrInt[], int beginIndex, int endIndex, int ith) {
        int i = 0, j = beginIndex;
        int [] arr = new int[endIndex - beginIndex + 1];
        while (i < endIndex - beginIndex + 1)
            arr[i++] = arrInt[j++];
        Arrays.sort(arr);
        return arr[ith];
    }

    // Swap 2 elements in an array of integers
    public static void  swap(int arrInt[], int leftIndex, int rightIndex) {
        int temp = arrInt[leftIndex];
            arrInt[leftIndex] = arrInt[rightIndex];
            arrInt[rightIndex] = temp;
    }

    // Return a random pivot
    public static int partition(int arrInt [], int leftIndex, int rightIndex, int pivotIndex) {

        // Get the pivot value from the array
        int pivot = arrInt[pivotIndex];

        // Swap the pivot element with the most right in the array
        swap(arrInt, pivotIndex, rightIndex);

        int leftMostIndex = leftIndex, i;

        // Partition the array in two halves, left < pivot < right
        for (i = leftIndex; i < rightIndex; i++) {
            if (arrInt[i] <= pivot) {
                swap(arrInt, i, leftMostIndex);
                leftMostIndex++;
            }
        }

        // Place the pivot into correct place in the array
        swap(arrInt, leftMostIndex, rightIndex);

        return leftMostIndex;

    }

    // Quick select the ith smallest element
    public static int quickSelect(int arrInt[], int leftIndex, int rightIndex, int ith) {
        // Return the only interger in the array
        if (leftIndex == rightIndex)
            return arrInt[leftIndex];
        // Find the pivot index
        int pivotIndex = leftIndex +
                (leftIndex + new Random().nextInt(rightIndex - leftIndex + 1)) % (rightIndex - leftIndex + 1);
        pivotIndex = partition(arrInt, leftIndex, rightIndex, pivotIndex);

        // Return ith as pivot if it is in correct position
        if (ith == pivotIndex)
            return arrInt[ith];
        // Find ith in the left partition if ith < pivotIndex
        else if (ith < pivotIndex)
            return quickSelect(arrInt, leftIndex, pivotIndex - 1, ith);
        // Find ith in the right partition if ith > pivotIndex
        else
            return quickSelect(arrInt, pivotIndex + 1, rightIndex, ith);
    }

    // Algorithm X - median of all medians
    public static int algorithmX(int[] arrInt, int ith) {
        // Use simple sort algorithm if the array has less than 51 integers
        int N = arrInt.length;
        if (N < 50)
            return sortSelect(arrInt, 0, arrInt.length - 1, ith);

        // Add medians in subarrays with size of 5
        int [] medians = new int [N/5];
        int m;
        for (m = 0; m < N/5; m++) {
            medians[m] = sortSelect(arrInt, 5 * m, 5 * m + 4, 2);
        }

        // Find MOM
        int pivot = algorithmX(medians, medians.length/2);
        // Find MOM index
        int pivotIndex = -1;
        while (pivot != arrInt[++pivotIndex]);

        // Create larger array w/ integers > MOM and smaller array w/ integers <= MOM
        int [] low  = new int [N/2];
        int [] high = new int [N/2];
        int i, s = 0, l = 0;
        for (i = 0; i < N; i++) {
            if (arrInt[i] < pivot)
                low[s++] = arrInt[i];
            else
                high[l++] = arrInt[i];
        }

        int val;
        if (ith < s)
            val = algorithmX(low, ith);
        else if (ith > s)
            val = algorithmX(high, ith - s);
        else
            val = pivot;

        return val;
    }
}

