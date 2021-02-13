import java.io.*;
import java.util.*;

public class B2565_binary {

    static int N;
    static int[] dp;
    static ArrayList<Line> lineList;
    
    static class Line {
        int A, B;

        Line(int A, int B) {
            this.A = A;
            this.B = B;
        }
    }

    public static int binarySearchIndex(int min, int max, int num) {
        int mid = 0;

        while(min <= max) {
            mid = (min+max)/2;

            if(dp[mid] < num) min = mid + 1;
            else if(dp[mid] > num) max = mid - 1;
            else
                return mid;
        }

        return min;
    }

    public static int searchLIS() {
        int index = 0;

        for(Line line: lineList) {
            if(dp[index] < line.B) {
                index += 1;
                dp[index] = line.B;
            } else {
                int idx = binarySearchIndex(0, index-1, line.B);
                dp[idx] = line.B;
            }
        }

        return index;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        lineList = new ArrayList<>();
        
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            lineList.add(new Line(A, B));
        }

        Collections.sort(lineList, new Comparator<Line>(){
            @Override
            public int compare(Line L1, Line L2) {
                return L1.A - L2.A;
            }
        });

        int index = searchLIS();
        System.out.println(N-index);
    }
}
