import java.io.*;
import java.util.*;

public class B4195_String {
    static int F, M;
    static Map<String, String> friends;
    static Map<String, Integer> networkCnt;

    public static void union(String fr1, String fr2) {
        fr1 = find(fr1);
        fr2 = find(fr2);

        // 트리의 루트노드 변경
        if(!fr1.equals(fr2)) {
            friends.put(fr2, fr1);

            networkCnt.put(fr1, networkCnt.get(fr1)+networkCnt.get(fr2));
        }

        System.out.println(networkCnt.get(fr1));
    }

    public static String find(String fr) {
        if(friends.get(fr).equals(fr)) {
            return fr;
        } else {
            friends.put(fr, find(friends.get(fr)));
            return friends.get(fr);
        }
    }   

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;


        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            F = Integer.parseInt(br.readLine());
            friends = new HashMap<>();
            networkCnt = new HashMap<>();

            for(int i=0; i<F; i++) {
                String[] input = br.readLine().split(" ");
                for(String friend: input) {
                    if(!friends.containsKey(friend)) {
                        friends.put(friend, friend);
                        networkCnt.put(friend, 1);
                    }
                }

                union(input[0], input[1]);
            }
        }
    }
}