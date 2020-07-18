// 영역 구하기

import java.util.*;
import java.io.*;

public class Main {
    static int M, N, K, cnt, totalCnt;
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};
    static boolean[][] map;
    static LinkedList<Integer> cntList;

    static boolean wall(int x, int y) {
        if(x<0 || x>=M || y<0 || y>=N) return true;
        return false;
    }

    static void DFS(int x, int y) {
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(wall(nx, ny)) continue;
            if(map[nx][ny]) continue;
            
            map[nx][ny] = true;
            cnt += 1;
            DFS(nx, ny);
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[M][N];
        int[] xy = new int[4];
        cntList = new LinkedList<>();

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                xy[j] = Integer.parseInt(st.nextToken());
            }
            xy[1] = M - xy[1];
            xy[3] = M - xy[3];

            for(int x=xy[3]; x<xy[1]; x++) {
                for(int y=xy[0]; y<xy[2]; y++) {
                    map[x][y] = true;
                }
            }
        }

        for(int x=0; x<M; x++) {
            for(int y=0; y<N; y++) {
                if(map[x][y]) continue;

                totalCnt++;

                map[x][y] = true;
                cnt = 1;
                DFS(x,y);
                
                cntList.add(cnt);
            }
        }

        Collections.sort(cntList);

        System.out.println(totalCnt);
        for(int i=0; i<totalCnt; i++) {
            System.out.print(cntList.get(i) + " ");
        }
    }
}