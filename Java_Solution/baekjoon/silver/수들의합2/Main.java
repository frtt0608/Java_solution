import java.util.*;
import java.io.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split(" ");
        int[] numArr = new int[N];

        for(int i=0; i<N; i++) {
            numArr[i] = Integer.parseInt(arr[i]);
        }


        int cnt = 0;
        int startP = 0;
        int endP = 0;
        int num = 0;

        while(true) {
            if(num >= M) {
                num -= numArr[startP++];
            } else {
                if(endP == N) break;
                num += numArr[endP++];
            }

            if(num == M) 
                cnt += 1;
        }

        System.out.println(cnt);
    }
}