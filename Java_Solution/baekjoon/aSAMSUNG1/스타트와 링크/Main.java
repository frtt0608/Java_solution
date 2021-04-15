// N명. 짝수
// N/2 스타트팀 링크팀
// ij : i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치
// 팀사이 능력치 차이가 최소가 되게 하려고 한다.

import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, skill[][], visited[], minSkill_differ;

    static void DFS(int n, int i) {
        if(N/2==n) {
            int sum[] = find_not_visited(visited);
            System.out.println(Arrays.toString(sum));
            minSkill_differ = Math.min(minSkill_differ, (int)Math.abs(sum[0]-sum[1]));
            return;
        }

        for(int j=i; j<N; j++) {
            if(visited[j]==1 || i==j) continue;
            visited[i] = 1;
            visited[j] = 1;
            DFS(n+1, j);
            visited[i] = 0;
            visited[j] = 0;
        }
    }

    static int[] find_not_visited(int v[]) {
        int sum[] = new int[2];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(v[i]==0 && v[j]==0) 
                    sum[0]+=skill[i][j];
                else if(v[i]==1 && v[j]==1)
                    sum[1]+=skill[i][j];
            }
        }
        return sum;
    }
    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        skill = new int[N][N];
        visited = new int[N];
        minSkill_differ = 1000000;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) 
                skill[i][j] = sc.nextInt();
        }
        DFS(1,0);

        System.out.println(minSkill_differ);
    }
}