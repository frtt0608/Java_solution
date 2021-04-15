// NM 직사각형 벽 또는 빈칸
// 바라보는 방향 r,c
// 현재 위치 청소
// 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색
// 청소되있으면 다시 왼쪽, 반시계방향으로 회전!
// 다 되있으면 바라보는 방향 유지, 뒤로 후진
// 모두 청소or 벽or 후진할수 없으면 작동 끝
// 청소된 칸 다시 청소x
// 0  1  2  3 
// 북 동 남 서
// 청소하는 칸의 개수 출력

import java.util.*;
import java.io.*;

/**
 * Main
 */

class XY {
    int x;
    int y;
    int dir;

    XY(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main {
    static int N, M, map[][], workCnt, visited[][];
    static int dir[][][] = {{{0,1,0,-1}, {-1,0,1,0}}, 
                            {{-1,0,1,0}, {0,-1,0,1}}, 
                            {{0,-1,0,1}, {1,0,-1,0}},
                            {{1,0,-1,0}, {0,1,0,-1}}};

    static void BFS(int x, int y, int d) {
        Queue<XY> que = new LinkedList<>();
        que.offer(new XY(x,y,d));
        if(map[x][y]==0) {
            visited[x][y] = 1;
            workCnt++;
        }
        
        while(!que.isEmpty()) {
            XY position = que.poll();
            int pos_x = position.x;
            int pos_y = position.y;
            int pos_dir = position.dir;

            for(int idx=0; idx<4; idx++) {
                int nx = pos_x + dir[pos_dir][0][0];
                int ny = pos_y + dir[pos_dir][1][0];
                //System.out.println(nx+", "+ny+": "+pos_dir);
                if(nx>=N || nx<0 || ny>=M || ny<0) {
                    continue;
                }
                if(visited[nx][ny]==1 || map[nx][ny]==1) {
                    //System.out.println("turn");
                    pos_dir=(pos_dir+3)%4;
                } else if(map[nx][ny]==0) {
                    //System.out.println("clean");
                    pos_dir=(pos_dir+3)%4;
                    visited[nx][ny] = 1;
                    map[nx][ny] = 2;
                    workCnt+=1;
                    que.offer(new XY(nx,ny,pos_dir));
                    break;
                }
                if(idx==3) {
                    nx = pos_x + dir[pos_dir][0][1];
                    ny = pos_y + dir[pos_dir][1][1];
                    if(map[nx][ny]==1) break;
                    else que.offer(new XY(nx,ny,pos_dir));
                }
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        int start_x = sc.nextInt();
        int start_y = sc.nextInt();
        int start_dir = sc.nextInt();

        map = new int[N][M];
        visited = new int[N][M];
        workCnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j]=sc.nextInt();
            }
        }

        BFS(start_x, start_y, start_dir);

        System.out.println(workCnt);
    }
}