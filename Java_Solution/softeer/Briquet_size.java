import java.io.*;
import java.util.*;

public class Briquet_size {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] radiusArray = new int[N];

        for(int i=0; i<N; i++) {
            radiusArray[i] = Integer.parseInt(inputs[i]);
        }

        Arrays.sort(radiusArray);

        int midNum = radiusArray[N-1];
        int maxCount = 0;
        for(int i=2; i<=midNum; i++) {
            int count = 0;
            for(int j=0; j<N; j++) {
                if(radiusArray[j] % i == 0) count++;
            }

            maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);
    }

}
 