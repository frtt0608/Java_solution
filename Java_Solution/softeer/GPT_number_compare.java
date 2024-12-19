import java.io.*;
import java.util.*;

public class GPT_number_compare {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for(int i=0; i<N; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, new Comparator<String>() {

            @Override
            public int compare(String str1, String str2) {
                
                int integerPart1 = 0;
                int floatPart1 = 0;

                int integerPart2 = 0;
                int floatPart2 = 0;

                String[] strArr1 = splitFloatStrings(str1);
                String[] strArr2 = splitFloatStrings(str2);

                if(!str1.contains(".")) {
                    integerPart1 = Integer.parseInt(str1);
                    floatPart1 = 0;
                    
                } else {
                    integerPart1 = Integer.parseInt(strArr1[0]);
                    floatPart1 = Integer.parseInt(strArr1[1]);
                }

                if(!str2.contains(".")) {
                    integerPart2 = Integer.parseInt(str2);
                    floatPart2 = 0;
                } else {
                    integerPart2 = Integer.parseInt(strArr2[0]);
                    floatPart2 = Integer.parseInt(strArr2[1]);
                }
            
                if(integerPart1 == integerPart2) {
                    if(!str1.contains(".")) return -1;
                    else if(!str2.contains(".")) return 1;

                    return floatPart1 - floatPart2;
                }

                return integerPart1 - integerPart2;
            }
        });

        for(int i=0; i<N; i++) {
            System.out.println(arr[i]);
        }
    }

    public static String[] splitFloatStrings(String str) {
        return str.split("\\.");
    }

}
 