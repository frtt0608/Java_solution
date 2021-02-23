import java.util.*;
import java.io.*;

public class B4195 {
    static int[] networkCnt, parent;
    static Map<String, Integer> nameMap;

    public static void union(int friend1, int friend2) {
        friend1 = find(friend1);
        friend2 = find(friend2);

        if(friend1 != friend2) {
            parent[friend2] = friend1;
            networkCnt[friend1] += networkCnt[friend2];
        }
    }
    
    public static int find(int idx) {
        if(parent[idx] == idx) return idx;
        else {
            return parent[idx] = find(parent[idx]);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int F = Integer.parseInt(br.readLine());
            networkCnt = new int[F*2 + 1];
            parent = new int[F*2 + 1];
            nameMap = new HashMap<>();

            int nameIndex = 0;
            for(int i=0; i<F; i++) {
                String[] input = br.readLine().split(" ");
                for(String name: input) {
                    if(!nameMap.containsKey(name)) {
                        networkCnt[nameIndex] = 1;
                        parent[nameIndex] = nameIndex;
                        nameMap.put(name, nameIndex);
                        nameIndex += 1;
                    }
                }

                int friend1 = nameMap.get(input[0]);
                int friend2 = nameMap.get(input[1]);
                union(friend1, friend2);
                
                sb.append(networkCnt[parent[friend1]]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}   
