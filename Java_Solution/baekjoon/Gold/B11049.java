import java.io.*;
import java.util.*;


public class B11049 {
    static int N;
    static int[][] arr, minCountDp;

    // 행렬 연산의 순서는 바꿀 수 없다.
    // 전체 행렬을 반씩 쪼개서 접근하기
    // 0, 1, 2, 3의 행렬이 있을 때, 01, 12, 23 계산하고,
    // 01과 2, 0과 12 중 더 작은 값을 dp에 저장하기
    public static void calculateMinCount() {
        // i: 연산 횟수, 
        // j: 범위 이동? (012) -> (123),
        // k: 경우의 수만큼 계산 if(012) -> (01) + 2, 0 + (12)
        for(int i=1; i<N; i++) {
            for(int j=0; j<N-i; j++) {
                minCountDp[j][j+i] = Integer.MAX_VALUE;
                for(int k=0; k<i; k++) {
                    int minCount = minCountDp[j][j+k] +
                                    minCountDp[j+k+1][i+j] + 
                                        arr[j][0]*arr[j+k][1]*arr[i+j][1];

                    minCountDp[j][j+i] = Math.min(minCountDp[j][j+i], minCount);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        minCountDp = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            
        }

        calculateMinCount();
        System.out.println(minCountDp[0][N-1]);
    }
}