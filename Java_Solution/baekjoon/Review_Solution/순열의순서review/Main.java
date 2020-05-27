import java.util.*;
import java.io.*;

/**
 * Mani
 */
public class Main {
    static int N;
    static String input[];
    static long fact[], K, cnt;
    static boolean visited[];

    static void solveOne() {
        LinkedList<Integer> numList = new LinkedList<>();

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(visited[j]) continue;

                if(fact[N-i] < K) {
                    K -= fact[N-i];
                } else {
                    visited[j] = true;
                    numList.add(j);
                    break;
                }
            }
        }

        for(Integer num: numList) {
            System.out.print(num + " ");
        }
    }

    static void solveTwo() {
        int num = 0;
        for(int i=1; i<N+1; i++) {
            num = Integer.parseInt(input[i]);
            for(int j=1; j<N+1; j++) {
                if(visited[j]) continue;

                if(num==j) {
                    visited[j] = true;
                    break;
                }
                cnt += fact[N-i];
            }
        }

        cnt += 1;
        System.out.println(cnt);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        visited = new boolean[N+1];
        fact = new long[N+1];
        fact[0] = 1;

        for(int i=1; i<=N; i++) {
            fact[i] = fact[i-1]*i;
        }   

        if(input[0].equals("1")) {
            K = Long.parseLong(input[1]);
            solveOne();
        } else {
            solveTwo();
        }
    }
}