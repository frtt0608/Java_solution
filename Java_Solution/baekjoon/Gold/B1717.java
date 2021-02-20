import java.io.*;
import java.util.*;


public class B1717 {
    static int n, m;
    static int[] tree;

    public static void union(int ele1, int ele2) {
        ele1 = find(ele1);
        ele2 = find(ele2);

        // 트리의 루트노드 변경
        if(ele1 != ele2) {
            tree[ele2] = ele1;
        }
    }

    public static int find(int ele) {
        if(tree[ele] == ele) {
            return ele;
        } else {
            // Tree 경로 압축, O(logN)
            return tree[ele] = find(tree[ele]);
        }
    }   

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        tree = new int[n+1];
        for(int i=1; i<n+1; i++) {
            tree[i] = i;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int ele1 = Integer.parseInt(st.nextToken());
            int ele2 = Integer.parseInt(st.nextToken());

            // if check == 0 이면 union
            // 1이면 find

            if(check == 0) {
                union(ele1, ele2);
            } else {
                if(find(ele1) == find(ele2)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        
        System.out.println(sb.toString());
    }
}