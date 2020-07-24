// 문자열 폭발

import java.util.*;
import java.io.*;

public class Main1 {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();
        int input_len = input.length;
        int bomb_len = bomb.length;
        char[] st = new char[input_len];
        int minusIdx = 0;
        boolean flag = false;

        for(int i=0; i<input.length; i++) {
            st[i - minusIdx] = input[i];
            
            if(input[i] == bomb[bomb_len - 1] && i - minusIdx >= bomb_len - 1) {
                flag = true;
                for(int j=1; j<bomb_len; j++) {
                    if(bomb[bomb_len-1-j] != st[i - minusIdx - j]) {
                        flag = false;
                        break;
                    }
                }

            } else {
                flag = false;
            }

            if(flag) {
                minusIdx += bomb_len;
            }
        }

        if(minusIdx == input_len) {
            System.out.println("FRULA");
        } else {
            System.out.println(new String(st, 0, input_len - minusIdx));
        }
    }
}