import java.io.*;
import java.util.*;

public class B1956 {
    static final int MAX = 4000001;
    static int V, E, minCycle;
    static int[][] city;


    public static void calculateTotalMinRoute() {
        for(int k=1; k<V+1; k++) {
            for(int i=1; i<V+1; i++) {
                for(int j=1; j<V+1; j++) {
                    if(k==i && i==j) continue;

                    city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
                }
            }
        }

        for(int i=1; i<V+1; i++) {
            minCycle = Math.min(minCycle, city[i][i]);
        }

        if(minCycle == MAX) {
            minCycle = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        minCycle = MAX;

        city = new int[V+1][V+1];
        for(int i=1; i<V+1; i++) {
            Arrays.fill(city[i], MAX);
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            city[s][e] = c;
        }

        calculateTotalMinRoute();

        System.out.println(minCycle);
    }
}
