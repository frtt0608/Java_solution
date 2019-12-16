import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution {
    static int table[][], L1, L2;
    static String data1, data2;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T=sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            data1 = sc.next();
            data2 = sc.next();
            L1 = data1.length();
            L2 = data2.length();
            table = new int[L2+1][L1+1];

            for(int data2_idx=1; data2_idx<=L2; data2_idx++) {
                char last_temp2 = data2.charAt(data2_idx-1);
                
                for(int data1_idx=1; data1_idx<=L1; data1_idx++) {
                    int left = table[data2_idx][data1_idx-1];
                    int up = table[data2_idx-1][data1_idx];

                    table[data2_idx][data1_idx] = (last_temp2 == data1.charAt(data1_idx-1)) ?
                                        table[data2_idx-1][data1_idx-1]+1 : Math.max(left, up);
                }
            }

            for(int i=0; i<=L2; i++) {
                for(int j=0; j<=L1; j++) {
                    System.out.print(table[i][j]+" ");
                }
                System.out.println();
            }
        
            System.out.println("#"+tc+" "+table[L2][L1]);
        }
        sc.close();
    }
}