// 사과 담기 게임

import java.io.*;

public class Main {
    static int N, M, appleCnt;
    
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        appleCnt = Integer.parseInt(br.readLine());

        int pos = 0;
        int sb = 1;
        int eb = M;
        int moveCnt = 0;

        if(M == 1) {
            for(int i=0; i<appleCnt; i++) {
                pos = Integer.parseInt(br.readLine());
                moveCnt += (int)Math.abs(pos-sb);
                sb = pos;
            }
        } else {
            for(int i=0; i<appleCnt; i++) {
                pos = Integer.parseInt(br.readLine());
                
                if(pos >= sb && pos <= eb) {
                    continue;
                } else if(pos > eb) {
                    moveCnt += pos-eb;
                    eb = pos;
                    sb = pos-M+1;
                } else if(pos < sb) {
                    moveCnt += sb-pos;
                    sb = pos;
                    eb = pos+M-1;
                }
            }
        }

        System.out.println(moveCnt);
    }
}