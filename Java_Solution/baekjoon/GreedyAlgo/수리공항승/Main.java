import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        boolean[] visited = new boolean[N];
        int[] tape = new int[N];

        for(int i=0; i<N; i++) {
            tape[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tape);
        
        int tapeCnt = 0;
        
        for(int i=0; i<N-1; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            for(int j=i+1; j<N; j++) {
                if(tape[j]-tape[i]+1 <= L) {
                    visited[j] = true;
                } else {
                    break;
                }
            }
            tapeCnt++;
        }
        if(!visited[N-1]) tapeCnt++;

        System.out.println(tapeCnt);
    }
}