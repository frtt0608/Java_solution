// 보물상자 비밀번호

import java.io.*;
import java.util.*;


public class S5658 {
    static int T, N, K, oneStrlen, maxNum;
    static Set<String> strSet;

    static void parse_data(String numStr) {
        for(int i=0; i<oneStrlen; i++) {
            strSet.add(numStr.substring(i, i+oneStrlen));
        }
    }

    static String rotateString(String numStr) {
        String numStrArr[] = numStr.split("");
        String tempStrArr[] = new String[N];

        for(int i=0; i<N; i++) {
            tempStrArr[i] = numStrArr[(N-1+i)%N];
        }

        return Arrays.toString(tempStrArr).replace(", ", "");
    }

    static long changeStringToint(String data) {
        long num = 0;
        char chr;
        for(int i=oneStrlen-1; i>=0; i--) {
            chr = data.charAt(i);
            if(chr >= 65) chr -= 55;
            else chr -= 48;

            num += chr * Math.pow(16, oneStrlen-1-i);
        }

        return num;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
            String input[] = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);
            String numStr = br.readLine();
            oneStrlen = N/4;

            strSet = new HashSet<>();
            ArrayList<Long> numList = new ArrayList<>();

            int rotateCnt = 0;
            while(rotateCnt < N-1) {
                parse_data(numStr);
                numStr = rotateString(numStr).substring(1, N+1);
                rotateCnt++;
            }
                
            Iterator<String> numSet_iter = strSet.iterator();
            
            while(numSet_iter.hasNext()) {
                numList.add(changeStringToint(numSet_iter.next()));
            }
            numList.sort(Comparator.reverseOrder());

            System.out.println("#"+ tc + " " + numList.get(K-1));
        }
    }
}