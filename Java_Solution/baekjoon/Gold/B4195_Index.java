import java.io.*;
import java.util.*;

public class B4195_Index {
    static int F, M;
    static int[] networkCount, parent;

    public static void union(int fr1, int fr2) {
        fr1 = find(fr1);
        fr2 = find(fr2);

        // 트리의 루트노드 변경
        if(fr1 != fr2) {
            parent[fr2] = fr1;
            networkCount[fr1] += networkCount[fr2];
        }
    }

    public static int find(int fr) {
        if(parent[fr] == fr) {
            return fr;
        } else {
            return parent[fr] = find(parent[fr]);
        }
    }   

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        Map<String, Integer> friends;

        for(int tc=1; tc<=T; tc++) {
            F = Integer.parseInt(br.readLine());
            friends = new HashMap<>();
            networkCount = new int[F*2 + 1];
            parent = new int[F*2 + 1];

            int index = 0;
            for(int i=0; i<F; i++) {
                String[] input = br.readLine().split(" ");
                for(String friend: input) {
                    if(!friends.containsKey(friend)) {
                        networkCount[index] = 1;
                        parent[index] = index;
                        friends.put(friend, index++);
                    }
                }

                int friendIndex1 = friends.get(input[0]);
                int friendIndex2 = friends.get(input[1]);
                union(friendIndex1, friendIndex2);

                int rootFriendIndex = find(friendIndex2);
                sb.append(networkCount[rootFriendIndex]+"\n");
            }
        }
        System.out.println(sb.toString());
    }
}