import java.io.*;

/**
 * Main
 */
public class Main {
    static int k;
    static boolean visited[];
    static char oper[];
    static long minVal = Long.MAX_VALUE, maxVal = Long.MIN_VALUE;

    static void backTracking(int val, int idx, String res) {
        if(idx==k) {
            long resVal = Long.parseLong(res);
            minVal = Math.min(minVal, resVal);
            maxVal = Math.max(maxVal, resVal);
            return;
        }

        for(int i=0; i<10; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            if(oper[idx] == '>') {
                if(val > i) {
                    backTracking(i, idx+1, res+i);
                }
            } else {
                if(val < i) {
                    backTracking(i, idx+1, res+i);
                }
            }
            visited[i] = false;
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        oper = br.readLine().replaceAll(" ", "").toCharArray();
        visited = new boolean[10];
        String arg = "";
        String minRes = "0";
        int minCheck = (int)Math.pow(10, k);
        

        for(int i=0; i<10; i++) {
            visited[i] = true;
            arg = Integer.toString(i);
            backTracking(i, 0, arg);
            visited[i] = false;
        }

        if(minVal/minCheck == 0) {
            minRes += minVal;
        } else {
            minRes = Long.toString(minVal);
        }
        
        System.out.println(Long.toString(maxVal));
        System.out.println(minRes);
    }
}