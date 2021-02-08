import java.io.*;
import java.util.*;

public class B2751 {

    public static void mergeSort(int[] numArr, int s, int e) {
        if(s < e) {
            int m = (s+e)/2;
            mergeSort(numArr, s, m);
            mergeSort(numArr, m+1, e);
            mergeArr(numArr, s, e, m);
        }
    }

    public static void mergeArr(int[] numArr, int s, int e, int m) {
        int[] tempArr = new int[e-s+1];
        int i = s, j = m+1, k = 0;

        while(i <= m && j <= e) {
            if(numArr[i] <= numArr[j]) {
                tempArr[k] = numArr[i];
                i += 1;
            } else {
                tempArr[k] = numArr[j];
                j += 1;
            }
            k += 1;
        }

        while(i <= m) {
            tempArr[k] = numArr[i];
            k += 1;
            i += 1;
        }

        while(j <= e) {
            tempArr[k] = numArr[j];
            k += 1;
            j += 1;
        }

        for(int id=0; id<k; id++) {
            numArr[s+id] = tempArr[id];
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int N = Integer.parseInt(st.nextToken());
        int[] numArr = new int[N];
        for(int i=0; i<N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(numArr, 0, N-1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(numArr[i]).append('\n');
        }

        System.out.println(sb.toString());
    }
}
