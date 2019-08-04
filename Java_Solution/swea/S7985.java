import java.io.*;
import java.util.*;

public class S7985 {
    static int[] tree;
    
    static public void inorder(int[] table, int L) {
        if(L==1) {
            // System.out.println(table[0]);
            
        } else {
            // System.out.println(table[L/2]);
            int[] front = new int[L/2];
            for(int i=0; i<L/2; i++) {
                front[i] = table[i];
            }
            
            int[] back = new int[L/2];
            for(int j=L/2+1; j<L; j++) {
                back[j-L/2-1] = table[j];
            }

            System.out.print(front[front.length/2]);
            
            inorder(front, L/2);
            System.out.print(back[back.length/2]);
            inorder(back, L/2);
        }
    }
  
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for(int tc=1; tc<=T; tc++) {
            int K = (int)Math.pow(2, in.nextInt());

            tree = new int[K-1];
            for(int param=0; param < K-1; param++) {
                tree[param] = in.nextInt();
            }
            System.out.println(Arrays.toString(tree));

            System.out.println(tree[tree.length/2]);
            inorder(tree, tree.length);
        }
    }
}