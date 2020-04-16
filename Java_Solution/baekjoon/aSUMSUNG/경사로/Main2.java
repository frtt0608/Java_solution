// NN 높이가 적힘
// 한행 또는 한 열 전부가 길
// 모든 칸의 높이 같거나,
// 경사로를 놓는다
// 경사로는 항상 1, 길이 L
// 낮은칸에 놓는다, L개의 연속된 칸에 경사로의 바닥이 모두 접해야함
// 높이차 1 // L개의 칸 연속
// 경사로 중복x 

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

public class Main2 {
    static int N, L, map[][], route;

    static void BFS() {
        Queue<XY> que = new LinkedList<>();
        for(int i=0; i<N; i++) 
            que.offer(new XY(0,i,1));
        for(int j=0; j<N; j++)
            que.offer(new XY(j,0,2));
            
        while(!que.isEmpty()) {
            XY xy = que.poll();

            if(Runway(xy.x, xy.y, xy.dir)) route++;
        }
    }

    static Boolean Runway(int x, int y, int dir) {
        int arr[] = new int[N];
        int visited[] = new int[N];
        int idx = 0;

        for(int i=0; i<N; i++) 
            arr[i] = (dir==1) ? map[x+i][y] : map[x][y+i];
        
        while(idx<N) {
            if(idx>=N-1) return true;

            if(Math.abs(arr[idx] - arr[idx+1]) > 1) return false;

            if(arr[idx] == arr[idx+1]) idx++;
            else if(arr[idx]-arr[idx+1]==1) {
                for(int up=idx+1; up<=idx+L; up++) {
                    if(up>=N || arr[idx+1]!=arr[up] || visited[up]==1) return false;
                    visited[up] = 1;
                }
                idx+=L;
            } else if(arr[idx]-arr[idx+1]==-1) {
                for(int down=idx; down>idx-L; down--) {
                    if(down<0 || arr[idx]!=arr[down] || visited[down]==1) return false;
                    visited[down] = 1;
                }
                idx++;
            }
        }
        return true;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        L=sc.nextInt();
        route = 0;
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        BFS();
        System.out.println(route);
    }
}