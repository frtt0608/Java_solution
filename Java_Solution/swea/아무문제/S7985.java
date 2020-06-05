import java.io.*;
import java.util.*;

public class S7985 {
    static int[] tree;
    static ArrayList<Integer> res[];

    public static void center(int d, int s, int e) {
        int c = (s+e)/2;
        res[d].add(tree[c]);

        if(s == e) {
            return;
        }

        center(d+1, s, c-1);
        center(d+1, c+1, e);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for(int tc=1; tc<=T; tc++) {
            int K = (int)Math.pow(2, in.nextInt());

            tree = new int[K-1];
            res = new ArrayList[K/2];
            for(int i=0; i<res.length; i++) {
                res[i] = new ArrayList<Integer>();
            }

            for(int param=0; param < K-1; param++) {
                tree[param] = in.nextInt();
            }
            // System.out.println(Arrays.toString(tree));

            center(0, 0, tree.length-1);  

            System.out.print("#"+ tc + " ");
            for(int i = 0; i < res.length; i++) {
                int L = res[i].size();
                if(L!=0) {
                    for(int j = 0; j < L; j++) {
                        System.out.print(res[i].get(j) + " ");
                    }
                    System.out.println();
                }   
            }
        }
    }
}