import java.util.*;
import java.io.*;

public class B17143 {
    static int R, C, M, getSharkSize;
    static int[] dx={-1,1,0,0}, dy={0,0,1,-1};
    static Shark[][] sharks;

    static class Shark {
        int x, y, s, d, z;

        Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void getShark(int j) {
        for(int i=1; i<R+1; i++) {
            if(sharks[i][j] != null) {
                // System.out.println("get shark: " + j + " >>> " + sharks[i][j].z);
                getSharkSize += sharks[i][j].z;
                sharks[i][j] = null;
                break;
            }
        }
    }

    public static int changeDir(int d) {
        switch(d) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 3;
            default: return 2;
        }
    }

    public static void moveShark(Shark[][] afterSharks, Shark shark) {
        int moveCnt=0;
        int nx=0, ny=0, d=shark.d;
        int sx = shark.x;
        int sy = shark.y;
        int speed = shark.s;

        if(d==0 || d==1) {
            speed %= ((R-1)*2);
        } else {
            speed %= ((C-1)*2);
        }

        while(moveCnt < speed) {
            moveCnt += 1;
            nx = sx + dx[d];
            ny = sy + dy[d];
            
            if(nx<=0 || nx>R || ny<=0 || ny>C) {
                d = changeDir(d);
                nx = sx + dx[d];
                ny = sy + dy[d];
            }

            sx = nx;
            sy = ny;
        }

        if(afterSharks[sx][sy] == null || afterSharks[sx][sy].z < shark.z) {
            afterSharks[sx][sy] = new Shark(sx, sy, shark.s, d, shark.z);
        }
    }

    public static void updateSharkLocate() {
        Shark[][] afterSharks = new Shark[R+1][C+1];

        for(int i=1; i<R+1; i++) {
            for(int j=1; j<C+1; j++) {
                if(sharks[i][j] != null) {

                    moveShark(afterSharks, sharks[i][j]);
                    sharks[i][j] = null;
                }
            }            
        }

        // sharks 배열 갱신
        for(int i=1; i<R+1; i++) {
            for(int j=1; j<C+1; j++) {
                if(afterSharks[i][j] != null) {
                    sharks[i][j] = afterSharks[i][j];
                    // System.out.println("이동후 상어 위치: " + i+", "+j + " >> " + sharks[i][j].z);
                }
            }            
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new Shark[R+1][C+1];
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); // speed
            int d = Integer.parseInt(st.nextToken())-1; // dir
            int z = Integer.parseInt(st.nextToken()); // size

            sharks[r][c] = new Shark(r, c, s, d, z);
        }

        for(int j=1; j<C+1; j++) {
            getShark(j);
            updateSharkLocate();
        }

        System.out.println(getSharkSize);
    }
}