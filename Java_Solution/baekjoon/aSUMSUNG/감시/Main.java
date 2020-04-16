// NM 사무실
// CCTV K개 5종류
// 벽 통과x
// 회전 가능 90도
// 0은 빈칸, 6은 벽, 1~5는 CCTV번호
// 사각지대의 최소크기

import java.util.*;
import java.io.*;

class XY {
    int x;
    int y;
    int dir;

    XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    XY(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
/**
 * Main
 */
public class Main {
    static int N, M, office[][], min_square, camCnt, copy_office[][];
    static ArrayList<XY> camList;
    static int spin_cam1[][] = {{0,1,0,-1},{1,0,-1,0}};

    static Boolean WALL(int x, int y) {
        if(x>=N || x<0 || y>=M || y<0) return true;
        return false;
    }

    static void perm(int cam_idx, int visited[]) {
        if(camCnt == cam_idx) {
            for(int i=0; i<N; i++) System.arraycopy(office[i], 0, copy_office[i], 0, M);
            for(int i=0; i<camCnt; i++) {
                XY xy = camList.get(i);
                spin_cam(xy.x, xy.y, visited[i]);
            }
            int temp = Search_square();
            min_square = Math.min(min_square, temp);
            return;
        }
        for(int i=1; i<5; i++) {
            if(visited[cam_idx]!=0) continue;
            visited[cam_idx] = i;
            perm(cam_idx+1, visited);
            visited[cam_idx] = 0;
        }
    }

    static void spin_cam(int x, int y, int dir) {
        int nx=x; int ny=y;
        dir+=3;
        //System.out.println(copy_office[x][y]+","+dir);
        if(copy_office[x][y]==1) {
            while(true) {
                nx = nx + spin_cam1[0][dir%4];
                ny = ny + spin_cam1[1][dir%4];
                if(WALL(nx, ny) || copy_office[nx][ny]==6) break;
                if(copy_office[nx][ny]>=1 && copy_office[nx][ny] <=4) continue;
                copy_office[nx][ny]=1;
            }
        } else if(copy_office[x][y]==2) {
            while(true) {
                nx=nx+spin_cam1[0][dir%4];
                ny=ny+spin_cam1[1][dir%4];
                if(WALL(nx, ny) || copy_office[nx][ny]==6) break;
                if(copy_office[nx][ny]>=1 && copy_office[nx][ny] <=4) continue;
                copy_office[nx][ny]=1;
            } nx=x; ny=y;
            while(true) {
                nx=nx+spin_cam1[0][(dir+2)%4];
                ny=ny+spin_cam1[1][(dir+2)%4];
                if(WALL(nx, ny) || copy_office[nx][ny]==6) break;
                if(copy_office[nx][ny]>=1 && copy_office[nx][ny] <=4) continue;
                copy_office[nx][ny]=1;
            }
        } else if(copy_office[x][y]==3) {
            for(int i=0; i<=1; i++) {
                nx=x; ny=y;
                while(true) {
                    nx=nx+spin_cam1[0][(dir+i)%4];
                    ny=ny+spin_cam1[1][(dir+i)%4];
                    if(WALL(nx, ny) || copy_office[nx][ny]==6) break;
                    if(copy_office[nx][ny]>=1 && copy_office[nx][ny] <=4) continue;
                    copy_office[nx][ny]=1;
                }
            }
        } else if(copy_office[x][y]==4) {
            for(int i=-1; i<=1; i++) {
                nx=x; ny=y;
                while(true) {
                    nx=nx+spin_cam1[0][(dir+i)%4];
                    ny=ny+spin_cam1[1][(dir+i)%4];
                    if(WALL(nx, ny) || copy_office[nx][ny]==6) break;
                    if(copy_office[nx][ny]>=1 && copy_office[nx][ny] <=4) continue;
                    copy_office[nx][ny]=1;
                }
            }
        } else {
            for(int i=0; i<4; i++) {
                nx=x; ny=y;
                while(true) {
                    nx=nx+spin_cam1[0][(dir+i)%4];
                    ny=ny+spin_cam1[1][(dir+i)%4];
                    if(WALL(nx, ny) || copy_office[nx][ny]==6) break;
                    if(copy_office[nx][ny]>=1 && copy_office[nx][ny] <=4) continue;
                    copy_office[nx][ny]=1;
                }
            }
        }
        // for(int i=0; i<N; i++) {
        //     for(int j=0; j<M; j++) {
        //         System.out.print(copy_office[i][j]);
        //     }
        //     System.out.println();
        // }
    }

    static int Search_square() {
        int res = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copy_office[i][j]==0) res+=1;
            }
        }
        //System.out.println(res);
        return res;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        office = new int[N][M];
        copy_office = new int[N][M];
        camList = new ArrayList<>();
        min_square = 100; camCnt = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                office[i][j] = sc.nextInt();
                if(office[i][j]>=1 && office[i][j]<=5) {
                    camList.add(new XY(i,j));
                    camCnt += 1;
                }
            }
        }
        int visited[] = new int[camCnt];
        perm(0,visited);
        System.out.println(min_square);
    }
}