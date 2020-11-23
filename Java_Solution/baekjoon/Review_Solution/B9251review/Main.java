import java.io.*;

public class Main {
    static int maxCount;

    public static void findLongestCommonSubsequence(String inputStr1, String inputStr2) {
        int lenStr1 = inputStr1.length();
        int lenStr2 = inputStr2.length();
        int[][] dpLCS = new int[lenStr1+1][lenStr2+1];

        for(int i=1; i<lenStr1+1; i++) {
            for(int j=1; j<lenStr2+1; j++) {
                if(inputStr1.charAt(i-1) == inputStr2.charAt(j-1)) {
                    dpLCS[i][j] = dpLCS[i-1][j-1] + 1;
                } else {
                    dpLCS[i][j] = Math.max(dpLCS[i-1][j], dpLCS[i][j-1]);
                }
            }
        }

        maxCount = dpLCS[lenStr1][lenStr2];
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr1 = br.readLine();
        String inputStr2 = br.readLine();
        maxCount = 0;

        findLongestCommonSubsequence(inputStr1, inputStr2);

        System.out.println(maxCount);
    }
}