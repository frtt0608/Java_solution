import java.util.*;
import java.io.*;

public class Main {
    static int N, virusCnt;
    // static int[][] network;
    static boolean[] visited;
    static List<Integer>[] network;

    public static void infestComputer(int num) {
        for(int i : network[num]) {
            if(!visited[i]) {
                visited[i] = true;
                virusCnt += 1;
                infestComputer(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        network = new ArrayList[N+1];

        for(int i=1; i<N+1; i++) {
            network[i] = new ArrayList<>();
        }

        int pair = Integer.parseInt(br.readLine());
        virusCnt = 0;

        for(int i=0; i<pair; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            network[p1].add(p2);
            network[p2].add(p1);
        }

        infestComputer(1);

        System.out.println(virusCnt-1);
    }
}