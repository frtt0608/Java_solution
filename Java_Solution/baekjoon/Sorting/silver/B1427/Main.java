// 소트인사이드

import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("");
        Arrays.sort(input, new Comparator<String>() {
            @Override
            public int compare(String num1, String num2) {
                if(Integer.parseInt(num1) < Integer.parseInt(num2)) {
                    return 1;
                } else return -1;
            }
        });

        for(String i:input)
            System.out.print(i);
    }
}