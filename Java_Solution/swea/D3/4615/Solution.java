// 오셀로 게임
// 1 흑돌, 2 백돌
// 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    static int N,M, table[][], x, y, color, whiteCnt, blackCnt;
    static int[] dx={0,0,1,-1,1,-1,1,-1};
    static int[] dy={1,-1,0,0,1,-1,-1,1};

    static Boolean checkWall(int x, int y) {
        if(x>N || x<=0 || y>N || y<=0 || table[x][y]==0) return true;
        return false;
    }
    static void checkTable(int x, int y, int color) {
        int nx=0, ny=0, temp=0;

        for(int dr=0; dr<8; dr++) {
            nx = x+dx[dr];
            ny = y+dy[dr];
            if(checkWall(nx,ny)==false && table[nx][ny]!=color) {
                temp=0;
                while(true) {
                    nx += dx[dr];
                    ny += dy[dr];
                    temp+=1;
                    if(checkWall(nx,ny)) break;
                    if(table[nx][ny]==color) {
                        for(int i=temp; i>0; i--) {
                            nx -= dx[dr];
                            ny -= dy[dr];
                            table[nx][ny]=color;
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N=sc.nextInt();
            M=sc.nextInt();
            whiteCnt=0; blackCnt=0;
            table = new int[N+1][N+1];
            table[N/2][N/2] = 2;
            table[N/2+1][N/2] = 1;
            table[N/2][N/2+1] = 1;
            table[N/2+1][N/2+1] = 2;

            for(int m=0; m<M; m++) {
                y = sc.nextInt();
                x = sc.nextInt();
                color = sc.nextInt();
                table[x][y] = color;

                checkTable(x, y, color);
            }
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(table[i][j]==2) whiteCnt+=1;
                    else if(table[i][j]==1) blackCnt+=1;
                }
            }
            System.out.println("#"+tc+" "+blackCnt+" "+whiteCnt);
        }
        sc.close();
    }
}