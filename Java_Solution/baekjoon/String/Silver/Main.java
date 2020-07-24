import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int cnt = 0;
        Set<Character> groupSet = new HashSet<>();

        loop:
        for(int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            groupSet.clear();

            for(int j=0; j<input.length; j++) {
                char chr = input[j];
                if(!groupSet.contains(chr)) {
                    groupSet.add(chr);
                } else if(groupSet.contains(chr) && input[j-1] != chr){
                    continue loop;
                }
            }
            cnt += 1;
        }

        System.out.println(cnt);
    }
}