import java.io.*;
import java.util.*;

public class B14719 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H = Integer.valueOf(st.nextToken());
        int W = Integer.valueOf(st.nextToken());
        String[] inputblockHeight = br.readLine().split(" ");
        int[] blocksHeight = new int[W];
        int maxHeight = 0;
        int maxIndex = 0;
        for (int i = 0; i < inputblockHeight.length; i++) {
            blocksHeight[i] = Integer.valueOf(inputblockHeight[i]);
            if(maxHeight < blocksHeight[i]) {
                maxHeight = blocksHeight[i];
                maxIndex = i;
            }
        }

        int result = fillRainyWaterByStart(blocksHeight, maxIndex)
                        + fillRainyWaterByEnd(blocksHeight, maxIndex);
    
        System.out.println(result);
    }

    public static int fillRainyWaterByStart(int[] blocksHeight, int maxIndex) {
        int result = 0;

        int pivot = 0;
        for (int i = 1; i <= maxIndex; i++) {
            if(blocksHeight[pivot] <= blocksHeight[i]) {
                for(int j=pivot+1; j<i; j++) {
                    result += (blocksHeight[pivot] - blocksHeight[j]);
                }
                pivot = i;
            }
        }

        return result;
    }

    public static int fillRainyWaterByEnd(int[] blocksHeight, int maxIndex) {
        int result = 0;

        int pivot = blocksHeight.length-1;
        for (int i = blocksHeight.length-2; i >= maxIndex; i--) {
            if(blocksHeight[pivot] <= blocksHeight[i]) {
                for(int j=pivot-1; j>i; j--) {
                    result += (blocksHeight[pivot] - blocksHeight[j]);
                }
                pivot = i;
            }
        }

        return result;
    }
}
 