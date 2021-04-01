import java.util.*;
import java.io.*;

public class B11003 {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] numArr = new int[N];
        
        Deque<Integer> deq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
            while(!deq.isEmpty() && numArr[deq.getLast()] > numArr[i]) {
                deq.removeLast();
            }
            deq.offer(i);
            if(deq.getFirst() < i-L+1) deq.removeFirst();
            sb.append(numArr[deq.getFirst()]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}