// 오늘부터 N+1일째 되는날 퇴사
// 최대 상담 수
// 하루에 하나씩 서로 다른 상담
// 각 상담 완료 시간 Ti, 금액 Pi
// 1일에 상담하면 2,3일은 상담불가
// 최대 수익이 걸리는 프로그램!

import java.util.*;
import java.io.*;

public class Main {
    static int N, counselor[][], maxPrice;

    static void DFS(int day, int price) {
        if(day==N) {
            //System.out.println(price);
            maxPrice = Math.max(maxPrice, price);
            return;
        }
        
        for(int i=day; i<N; i++) {
            if(day+counselor[i][0] <= N)
                DFS(i+counselor[i][0], price + counselor[i][1]);
            else
                DFS(i+1, price);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        counselor = new int[N][2];
        maxPrice = 0;
        for(int i=0; i<N; i++) {
            counselor[i][0] = sc.nextInt();
            counselor[i][1] = sc.nextInt();
        }

        DFS(0, 0);
        System.out.println(maxPrice);
    }
}