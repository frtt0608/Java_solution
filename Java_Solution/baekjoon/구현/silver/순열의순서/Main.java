import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N;
    static Long K, cnt=(long)0;
    static Long fact[];
    static boolean visited[];
    static String input[];
    static LinkedList<Integer> numList;

    // K번째 순열 구하기
    static void permOne() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(visited[j]) continue;

                if(fact[N-i] < K) {
                    // fact가 더 작으면 K를 빼주고,
                    K -= fact[N-i];
                } else {
                    // fact가 더 크면 numList에 추가
                    numList.add(j);
                    visited[j] = true;
                    break;
                }
            }
        }
    }

    static void permTwo() {
        int num = 0;
        for(int i=1; i<=N; i++) {
            num = Integer.parseInt(input[i]);
            for(int j=1; j<=N; j++) {
                if(visited[j]) continue;
                if(num==j) {
                    visited[num] = true;
                    break;
                }
                cnt += fact[N-i];
            }
        }
        cnt += 1; // 마지막 항목
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fact = new Long[N+1];
        fact[0] = (long)1;
        fact[1] = (long)1;
        for(int i=2; i<=N; i++) {
            fact[i] = fact[i-1]*i;
        }
        input = br.readLine().split(" ");
        numList = new LinkedList<>();
        visited = new boolean[N+1];

        if(input[0].equals("1")) {
            K = Long.parseLong(input[1]);
            permOne();
            for(Integer num: numList) {
                System.out.print(num + " ");
            }
        } else {
            permTwo();
            System.out.println(cnt);
        }
    }
}