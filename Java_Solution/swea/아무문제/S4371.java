// 항구로 들어오는 배, 에라토스테네스의 체
import java.io.*;
import java.util.*;

public class S4371 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            int cnt = 0;
            int N = sc.nextInt();
            int table[] = new int[N];
            Boolean flag[] = new Boolean[N];
            for(int n=0; n<N; n++) {
                table[n] = sc.nextInt();
                flag[n] = false;
            }
            for(int i=1; i<N; i++) {
                if(!flag[i]) {
                    cnt+=1;
                    int temp = table[i]-1;
                    for(int j=i+1; j<N; j++) {
                        if((table[j]-table[i])%temp==0) {
                            flag[j] = true;
                        }
                    }
                }
            }
            System.out.println("#" + tc + " " + cnt);
        }
    }
}