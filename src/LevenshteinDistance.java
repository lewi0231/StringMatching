import java.util.Arrays;


public class LevenshteinDistance {
    public static void main(String[] args) {
        System.out.println(calculateDistanceRecursively("frog", "string"));
    }

//    This os O(n^2) - calculates the lowest cost (a measure of difference between the two strings)
    public static int calculateDistance(String s, String t){
        s = "#" + s;
        t = "#" + t;
        int[][] dTable = new int[s.length()][t.length()];

        for (int i = 0; i < s.length(); i++){
            dTable[i][0] = i;
        }
        for (int j = 0; j < t.length(); j++){
            dTable[0][j] = j;
        }
        for (int i = 1; i < s.length(); i++){
            for (int j = 1; j < t.length(); j++){
                int cost;
                if (s.charAt(i) == t.charAt(j)){
                    cost = 0;
                } else {
                    cost = 1;
                }
                dTable[i][j] = Math.min(dTable[i - 1][j] + 1, Math.min(
                                        dTable[i][j - 1] + 1,
                                        dTable[i - 1][j - 1] + cost));
            }
        }
        System.out.println(Arrays.deepToString(dTable));
        return dTable[s.length()-1][t.length() - 1];
    }

//    Need to have a think about why this is not working
    public static int calculateDistanceRecursively(String s, String t){
        if (s.length() == 0){
            return 0;
        }
        if (t.length() == 0){
            return 0;
        }

        int cost = s.charAt(s.length() - 1) != s.charAt(t.length() - 1) ? 1 : 0;
        return Math.min(calculateDistanceRecursively(s.substring(0, s.length() - 1), t) + 1,
                Math.min(calculateDistanceRecursively(s, t.substring(0, t.length() - 1) + 1),
                calculateDistanceRecursively(s.substring(0, s.length() - 1),t.substring(0, t.length() - 1) ) + cost));
    }
}
