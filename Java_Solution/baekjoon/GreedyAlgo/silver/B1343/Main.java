// 폴리오미노

import java.io.*;

public class Main {
    static StringBuffer sb;

    static boolean change(int cnt) {
        if(cnt%2 == 0) {
            while(true) {
                if(cnt >= 4) {
                    sb.append("AAAA");
                    cnt -= 4;
                } else if(cnt == 2) {
                    sb.append("BB");
                    cnt -= 2;
                }

                if(cnt == 0) break;
            }
            return true;
        } else {
            sb = new StringBuffer();
            sb.append(-1);

            return false;
        }
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] board = br.readLine().split("");
        sb = new StringBuffer();
        String res = "";
        int cnt = 0;

        for(int i=0; i<board.length; i++) {
            if(board[i].equals("X")) {
                cnt += 1;
            } else {
                if(change(cnt)) {
                    sb.append(".");
                    cnt = 0;
                } else break;
            }
        }

        if(cnt > 0) {
            change(cnt);
        }

        res = sb.toString();
        System.out.println(res);
    }
}