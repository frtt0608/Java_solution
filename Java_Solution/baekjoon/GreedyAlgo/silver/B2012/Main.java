// 등수 매기기

import java.io.*;
import java.util.Arrays;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] grade = new int[N];
        long res = 0;
        
        for(int i=0; i<N; i++) {
            grade[i] = Integer.parseInt(br.readLine());
        }

        // Arrays.sort(grade, new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer num1, Integer num2) {
        //         return num2.compareTo(num1);
        //     }
        // });
        
        Arrays.sort(grade);

        for(int i=0; i<N; i++) {
            res += Math.abs(grade[i] - (i+1));
        }
        
        System.out.println(res);
    }
}