import java.util.*;
import java.io.*;

public class B14888 {
    static int N, maxVal, minVal;
    static int[] nums, opers;

    // opers -> +, -, x, /
    public static void insertOperation(int index, int value) {
        if(index == N) {
            maxVal = Math.max(maxVal, value);
            minVal = Math.min(minVal, value);
            return;
        }

        for(int i=0; i<4; i++) {
            if(opers[i] > 0) {
                opers[i] -= 1;

                switch(i) {
                    case 0:
                        insertOperation(index+1, value+nums[index]);
                        break;
                    case 1:
                        insertOperation(index+1, value-nums[index]);
                        break;
                    case 2:
                        insertOperation(index+1, value*nums[index]);
                        break;
                    case 3:
                        insertOperation(index+1, value/nums[index]);
                }

                opers[i] += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        maxVal = Integer.MIN_VALUE;
        minVal = Integer.MAX_VALUE;
        nums = new int[N];
        opers = new int[4];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            opers[i] = Integer.parseInt(st.nextToken());
        }

        insertOperation(1, nums[0]);
        System.out.println(maxVal+"\n"+minVal);
    }
}