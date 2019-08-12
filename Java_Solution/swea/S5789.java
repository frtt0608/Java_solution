// 현주의 상자 바꾸기
import java.io.*;
import java.util.*;

public class S5789 {

    static int[] table; 
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            int N = in.nextInt();
            int Q = in.nextInt();
            table = new int[N];

            for(int i=1; i<=Q; i++) {
                int L = in.nextInt()-1;
                int R = in.nextInt()-1;
                for(int j=L; j<=R; j++) {
                    table[j] = i;
                }
            }
            System.out.print("#" + tc + " ");
            for(int cnt=0; cnt < table.length; cnt++) {
                if(cnt != table.length-1)
                    System.out.print(table[cnt] + " ");
                else
                    System.out.println(table[cnt]);
            }
        }
    }
}