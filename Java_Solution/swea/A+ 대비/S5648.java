// 원자 소멸 시뮬레이션
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class S5648 {
    static int N, map[][];
    static int dr[]={}, dc[]={};
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            map = new int[N][2];
            for(int i=0; i<N; i++) {
                int x=sc.nextInt();
                int y=sc.nextInt();
                
            }
        }
    }
}
