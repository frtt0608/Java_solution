import java.io.*;

public class Main {
    static int maxLCS, lenStr1, lenStr2;
    static String result;
    static int[][] dpLCS;

    public static void calculateLCS(String inputStr1, String inputStr2) {
        lenStr1 = inputStr1.length();
        lenStr2 = inputStr2.length();
        dpLCS = new int[lenStr1+1][lenStr2+1];

        for(int i=1; i<lenStr1+1; i++) {
            for(int j=1; j<lenStr2+1; j++) {
                if(inputStr1.charAt(i-1) == inputStr2.charAt(j-1)) {
                    dpLCS[i][j] = dpLCS[i-1][j-1] + 1;
                } else {
                    dpLCS[i][j] = Math.max(dpLCS[i-1][j], dpLCS[i][j-1]);
                }
            }
        }
    }

    public static void findString(String inputStr1, String inputStr2) {
        int idx1 = lenStr1;
        int idx2 = lenStr2;
        StringBuilder sb = new StringBuilder();

        while(idx1 > 0 && idx2 > 0) {
            if(inputStr1.charAt(idx1-1) == inputStr2.charAt(idx2-1)) {
                sb.append(inputStr1.charAt(idx1-1));
                idx1 -= 1;
                idx2 -= 1;
            } else if(dpLCS[idx1][idx2] == dpLCS[idx1-1][idx2])
                idx1 -= 1;
            else if(dpLCS[idx1][idx2] == dpLCS[idx1][idx2-1])
                idx2 -= 1;
        }

        result = sb.reverse().toString();
    }

    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr1 = br.readLine();
        String inputStr2 = br.readLine();

        calculateLCS(inputStr1, inputStr2);
        maxLCS = dpLCS[lenStr1][lenStr2];
        
        if(maxLCS != 0)
            findString(inputStr1, inputStr2);

        System.out.println(maxLCS);
        if(result != null)
            System.out.println(result);
    }
}
