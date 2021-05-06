import java.io.*;
import java.util.*;

// 최장 증가 부분 수열
public class B2631 {
    static int N;
    static int[] line;

    public static int searchLocation(ArrayList<Integer> lines, int num) {
        int min = 0;
        int max = lines.size();
        
        while(min < max) {
            int mid = (min + max)/2;

            if(lines.get(mid) < num) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return max;
    }

    public static void getLongestIncreasingSequence(ArrayList<Integer> lines) {
        for(int i=0; i<N; i++) {
            int num = line[i];
            if(lines.size() == 0) {
                lines.add(num);
            } else {

                if(lines.get(lines.size()-1) < num) {
                    lines.add(num);
                } else {
                    int index = searchLocation(lines, num);
                    lines.set(index, num);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        line = new int[N];
        
        for(int i=0; i<N; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> lines = new ArrayList<>();
        getLongestIncreasingSequence(lines);

        System.out.println(N - lines.size());
    }
}
