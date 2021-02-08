import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int N = Integer.parseInt(st.nextToken());
        int[] numArr = new int[N];
        for(int i=0; i<N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numArr);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(numArr[i]).append('\n');
        }

        System.out.println(sb.toString());
    }
}
