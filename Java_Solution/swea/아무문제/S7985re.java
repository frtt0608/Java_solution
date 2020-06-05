import java.io.*;
import java.util.*;

public class S7985re {
    static int[] tree;
    static List<Integer> table[];

    static void halfList(int d, int s, int e) {
        int c = (s+e)/2;
        table[d].add(tree[c]);

        if(s==e)
            return;

        halfList(d+1, s, c-1);
        halfList(d+1, c+1, e);
    }
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        for(int tc=1; tc<=T; tc++) {
            int L = (int)Math.pow(2, in.nextInt());
            
            tree = new int[L];
            table = new ArrayList[L];

            for(int i=0; i<L; i++) {
                table[i] = new ArrayList<Integer>();
            }

            for(int i=1; i<L; i++) {
                tree[i] = in.nextInt();
            }
            System.out.println(Arrays.toString(tree));

            halfList(1, 1, L-1);

            System.out.print("#" + tc + " ");
            for(int i=1; i <= table.length/2; i++) {
                for(int j=0; j<table[i].size(); j++) {
                    System.out.print(table[i].get(j) + " ");
                }
                System.out.println();
            }
        }
    }
}
