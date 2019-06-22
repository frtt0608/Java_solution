import java.io.*;
import java.util.*;

public class S7510 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            int cnt = 0;
            int N = in.nextInt();
            for(int i=1; i<=N+1; i++) {
                int res = 0;
                int temp = i;
                while(true) {
                    res += temp;
                    temp += 1;
                    if(res>N) {
                        break;
                    }
                    else if(res==N) {
                        cnt+=1;
                        break;
                    }
    
                }
            }
            System.out.println("#" + tc + " " + cnt);
        }
    }
}