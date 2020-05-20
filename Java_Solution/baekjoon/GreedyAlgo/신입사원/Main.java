import java.io.*;
import java.util.*;

/**
 * Main
 */
// 정렬을 함으로써 서류성적 비교x
// 이제 나보다 높은 서류순위를 가진 사람들 중 가장 높은 면접순위를 firstScore라고 하자.
// 이 때, firstScore보다 나의 면접순위가 높다면 선발!!, 그리고 firstScore를 갱신하자
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int resCnt = 1;
            int applicant[] = new int[N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int documentScore = Integer.parseInt(st.nextToken())-1; // 서류 성적
                int interviewScore = Integer.parseInt(st.nextToken()); // 면접 성적
                applicant[documentScore] = interviewScore;
            }

            // 비교
            int firstScore = applicant[0];
            for(int i=1; i<N; i++) {
                if(firstScore > applicant[i]) {
                    resCnt += 1;
                    firstScore = applicant[i];       
                }
            }

            System.out.println(resCnt);
        }
    }
}