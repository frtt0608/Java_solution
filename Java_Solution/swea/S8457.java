// 알 덴테 스파게티
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class S8457 {
    static int N,B,E,x[];
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N=sc.nextInt();
            B=sc.nextInt();
            E=sc.nextInt();
            x=new int[N];
            int cnt=0, temp=0, time=0;
            for(int i=0; i<N; i++) {
                time = sc.nextInt();
                if( B >= time) 
                    temp = time * (B/time);
                else
                    temp = time;
                if(temp>=B-E && temp<=B+E) {
                    cnt += 1;
                } else if(temp+time>=B-E && temp+time<=B+E) {
                    cnt += 1;
                }
            }
            System.out.println("#"+tc+" "+cnt);
        }
    }
}
