// 좌표정렬하기

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Integer[][] numArr = new Integer[N][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            numArr[i][0] = Integer.parseInt(st.nextToken());
            numArr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numArr, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] arr1, Integer[] arr2) {
                if(arr1[1].equals(arr2[1])) {
                    return arr1[0].compareTo(arr2[0]);
                } else {
                    return arr1[1].compareTo(arr2[1]);
                }
            }
        });

        for(int i=0; i<N; i++) {
            System.out.println(numArr[i][0] + " " + numArr[i][1]);
        }
    }
}