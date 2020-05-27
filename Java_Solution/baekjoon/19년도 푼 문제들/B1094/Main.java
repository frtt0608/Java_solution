// 막대기
import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int cnt = 0;
        int[] stick = new int[] {64,32,16,8,4,2,1};
        for(int i:stick) {
            if(n>=i) {
                n-=i;
                cnt += 1;
            }
            if(n==0)
                break;
        }
        System.out.println(cnt);
    }
}