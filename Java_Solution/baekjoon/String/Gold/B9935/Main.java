// 문자열 폭발

import java.util.*;
import java.io.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("");
        String bomb = br.readLine();
        String[] bombs = bomb.split("");
        int bomb_len = bomb.length();

        Stack<String> st = new Stack<>();
        Stack<String> temp_st = new Stack<>();

        for(int i=0; i<input.length; i++) {
            st.push(input[i]);

            if(st.size() >= bomb_len) {
                temp_st.clear();
                
                for(int j=0; j<bomb_len; j++) {
                    if(st.peek().equals(bombs[bomb_len-1-j])) {
                        temp_st.push(st.pop());
                    } else {
                        while(!temp_st.isEmpty()) {
                            st.push(temp_st.pop());
                        }
                        break;
                    }
                }
            }
        }

        if(st.size() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(st.toString().replace("[", "").replace("]", "").replace(", ", ""));
        }
    }
}