import java.io.File;
import java.util.*;

public class readDataFile {
    public static void main(String[] args) {
        int [][] rank = readRank("input1.txt");
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < rank[i].length; j++) {
                System.out.print(rank[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static int [][] readRank(String dataFile) {
        try {
            File rankFile = new File(dataFile);
            Scanner s = new Scanner(rankFile);
            int mLen = s.nextInt() + 1;

            int [][] rank = new int[mLen][mLen];
            for (int i = 0; i < mLen; i++) {
                for (int j = 0; j < mLen; j++) {
                    rank[i][j] = 0;
                }
            }
            for (int i = 1; i < mLen; i++) {
                int r = 1;
                for (int j = 1; j < mLen; j++) {
                    if (j != i) {
                        rank[i][s.nextInt()] = r;
                        r++;
                    }
                }
            }
            return rank;
        }
        catch (Exception e){
            return null;
        }
    }
}
