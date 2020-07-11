// 수 정렬하기3

import java.io.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[10001];
        int idx = 0;

        for(int i=0; i<N; i++) {
            idx = Integer.parseInt(br.readLine());
            nums[idx] += 1;
        }

        for(int i=1; i<10001; i++) {
            for(int j=0; j<nums[i]; j++) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
    }
}