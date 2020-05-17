import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int P[] = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(P);
        int map_value = P[0];
        int res = map_value;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, map_value);

        for(int i=1; i<N; i++) {
            map_value += P[i];
            res += map_value;
            map.put(i, map_value);
        }

        System.out.println(res);
    }
}