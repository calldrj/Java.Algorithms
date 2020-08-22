import java.io.*;
import java.util.*;

public class StableRoommate {
    public static void main(String[] args) {

        System.out.println("\nPreferrence Matrix:");
        int [][] pref = readPref("input1.txt");
        for (int i = 0; i < pref.length; i++) {
            for (int j = 0; j < pref[i].length - 1; j++)
                System.out.print(pref[i][j] + " ");
            System.out.println();
        }

        System.out.println("\nRank Matrix:");
        int [][] rank = readRank("input1.txt");
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < rank[i].length; j++)
                System.out.print(rank[i][j] + " ");
            System.out.println();
        }

        System.out.println("\nSet of Perfect Matching Pairs:");
        Set<Integer> set = new LinkedHashSet<Integer>();
        for (int i = 1; i < rank[0].length; i++) set.add(i);
        ArrayList<List<List<Integer>>> perfectSet = new ArrayList<List<List<Integer>>>();
        getMatchingSet(set, new ArrayList<List<Integer>>(), perfectSet);

        for (List<List<Integer>> pairs : perfectSet)
            System.out.println(pairs);

        ArrayList<List<List<Integer>>> stabSet = new ArrayList<List<List<Integer>>>();
        for (List<List<Integer>> pairs : perfectSet)
            if (getStableSet(pairs, rank))
                stabSet.add(pairs);

        // Print out number of stable set
        System.out.println("\nResults:");
        if (stabSet.size() > 0) {
            System.out.println("Yes " + stabSet.size());
            System.out.println("\nSet of Stable Matching Pairs:");
            for (List<List<Integer>> pairs : stabSet)
                System.out.println(pairs);
            System.out.println();
        }
        else
            System.out.println("No\n");
    }

    // Read input into a preference array
    public static int [][] readPref(String dataFile) {
        try {
            File prefFile = new File(dataFile);
            Scanner s = new Scanner(prefFile);
            int mLen = s.nextInt();
            int [][] pref = new int[mLen][mLen];
            for (int i = 0; i < mLen; i++)
                for (int j = 0; j < mLen - 1; j++)
                    pref[i][j] = s.nextInt();
            return pref;
        }
        catch (Exception e) {
            return null;
        }
    }

    // Read input into a rank array
    public static int [][] readRank(String dataFile) {
        try {
            File rankFile = new File(dataFile);
            Scanner s = new Scanner(rankFile);
            int mLen = s.nextInt() + 1;

            int [][] rank = new int[mLen][mLen];
            for (int i = 0; i < mLen; i++)
                for (int j = 0; j < mLen; j++)
                    rank[i][j] = 0;

            for (int i = 1; i < mLen; i++) {
                int r = 1;
                for (int j = 1; j < mLen; j++)
                    if (j != i)
                        rank[i][s.nextInt()] = r++;
            }
            return rank;
        }
        catch (Exception e) {
            return null;
        }
    }

    // Generate set of perfect matching pairs
    private static void getMatchingSet(Set<Integer> set,
                                       List<List<Integer>> existedPairs,
                                       List<List<List<Integer>>> perfectSet) {
        try {
            if (set.size() < 2) {
                perfectSet.add(new ArrayList<List<Integer>>(existedPairs));
                return;
            }

            List<Integer> list = new ArrayList<Integer>(set);
            Integer n1 = list.remove(0);

            for (int i = 0; i < list.size(); i++) {
                Integer n2 = list.get(i);
                Set<Integer> nextSet = new LinkedHashSet<Integer>(list);
                nextSet.remove(n2);
                List<Integer> pair = Arrays.asList(n1, n2);
                existedPairs.add(pair);
                getMatchingSet(nextSet, existedPairs, perfectSet);
                existedPairs.remove(pair);
            }
        }
        catch (Exception e) {
            return;
        }
    }

    // Find the stable matching set
    private static boolean getStableSet (List<List<Integer>> perfectPairs, int [][] rank) {
        for (int i = 0; i < perfectPairs.size() - 1; i++) {
            for (int j = i + 1; j < perfectPairs.size(); j++) {
                int     n1 = perfectPairs.get(i).get(0),
                        n2 = perfectPairs.get(i).get(1),
                        n3 = perfectPairs.get(j).get(0),
                        n4 = perfectPairs.get(j).get(1);
                boolean s13 = (rank[n1][n3] < rank[n1][n2]) && (rank[n3][n1] < rank[n3][n4]),
                        s14 = (rank[n1][n4] < rank[n1][n2]) && (rank[n4][n1] < rank[n4][n3]),
                        s23 = (rank[n2][n3] < rank[n2][n1]) && (rank[n3][n2] < rank[n3][n4]),
                        s24 = (rank[n2][n4] < rank[n2][n1]) && (rank[n4][n2] < rank[n4][n3]),
                        s31 = (rank[n3][n1] < rank[n3][n4]) && (rank[n1][n3] < rank[n1][n2]),
                        s32 = (rank[n3][n2] < rank[n3][n4]) && (rank[n2][n3] < rank[n2][n1]),
                        s41 = (rank[n4][n1] < rank[n4][n3]) && (rank[n1][n4] < rank[n1][n2]),
                        s42 = (rank[n4][n2] < rank[n4][n3]) && (rank[n2][n4] < rank[n2][n1]);
                if (s13 || s14 || s23 || s24 || s31 || s32 || s41 || s42)
                    return false;
            }
        }
        return true;
    }
}

