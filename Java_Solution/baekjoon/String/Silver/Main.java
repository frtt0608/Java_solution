import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] num;
    
    static void sell() {
        for(int i=0; i<10; i++) {
            num[i] += 1;
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        num = new int[10];
        int cnt = 0;

        for(int i=0; i<input.length; i++) {
            int idx = input[i] - '0';

            if(num[idx] == 0) {
                if(idx == 6 && num[9] > 0) {
                    num[9] -= 1;
                } 
                else if(idx == 9 && num[6] > 0) {
                    num[6] -= 1;
                } 
                else {
                    sell();
                    num[idx] -= 1;
                    cnt += 1;
                }
            } else {
                num[idx] -= 1;
            }
        }
        
        System.out.println(cnt);
    }
}