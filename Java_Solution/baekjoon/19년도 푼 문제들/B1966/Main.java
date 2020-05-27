// 프린터 큐
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] printer;
    static Deque<Integer[]> qu;

    static int search() {
        int max = 0;
        for(Integer[] q:qu) {
            if(max < q[1]) {
                max=q[1];
            }
        }
        return max;
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N=in.nextInt();
            M=in.nextInt();
            int cnt=1;
            printer = new int[N];
            qu = new LinkedList<>();
            for(int i=0; i<N; i++) {
                int n = in.nextInt();
                qu.add(new Integer[] {i,n});
                printer[i] = n;
            }

            int max = search();
            while(!qu.isEmpty()) {
                // System.out.println(max);
                Integer[] node = qu.poll();
                int index = node[0];
                int w = node[1];
                if(index==M && max==w) {
                    break;
                } else if(w < max) {
                    qu.addLast(node);
                } else if(w==max) {
                    max = search();
                    cnt+=1;
                }

            }

            System.out.println(cnt);
        }
    }
}