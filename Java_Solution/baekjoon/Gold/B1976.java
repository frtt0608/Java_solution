import java.io.*;
import java.util.*;

public class B1976 {
    static int N, M;
    static int[] cities;

    public static void initCities() {
        cities = new int[N+1];
        for(int i=1; i<N+1; i++) {
            cities[i] = i;
        }
    }

    public static void union(int start, int destination) {
        start = find(start);
        destination = find(destination);

        // 트리의 루트노드 변경
        if(start != destination) {
            cities[destination] = start;
        }
    }

    public static int find(int city) {
        if(cities[city] == city) {
            return city;
        } else {
            // Tree 경로 압축, O(logN)
            return cities[city] = find(cities[city]);
        }
    }   

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        initCities();
        String result = "YES";

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<N+1; j++) {
                int input = Integer.parseInt(st.nextToken());
                if(input == 1) {
                    union(i, j);
                }
            }
        }

        String[] plan = br.readLine().split(" ");
        int start = Integer.parseInt(plan[0]);
        
        for(int i=1; i<plan.length; i++) {
            int destination = Integer.parseInt(plan[i]);
            if(find(start) != find(destination)) {
                result = "NO";
                break;
            }

            start = destination;
        }

        System.out.println(result);
    }
}