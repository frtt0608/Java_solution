import java.util.*;
import java.io.*;

public class Main {
    static int N, sx, sy, dir, answer;
    static int[][] sand;
    static int[] dx = {0,1,0,-1}, dy={-1,0,1,0};

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void divideSand(int nx, int ny) {
        int one = sand[nx][ny]/100;
        int two = sand[nx][ny]/50;
        int five = sand[nx][ny]/20;
        int ten = sand[nx][ny]/10;
        int seven = (sand[nx][ny]*7)/100;

        int alpha = sand[nx][ny] - (one+two+seven+ten)*2 - five;

        if(isWall(nx+dx[dir], ny+dy[dir])) {
            answer += alpha;
        } else {
            sand[nx+dx[dir]][ny+dy[dir]] += alpha;
        }

        if(isWall(nx+dx[dir]*2, ny+dy[dir]*2)) {
            answer += five;
        } else {
            sand[nx+dx[dir]*2][ny+dy[dir]*2] += five;
        }

        if(isWall(nx+dx[dir]+dx[(dir+1)%4], ny+dy[dir]+dy[(dir+1)%4])) {
            answer += ten;
        } else {
            sand[nx+dx[dir]+dx[(dir+1)%4]][ny+dy[dir]+dy[(dir+1)%4]] += ten;
        }

        if(isWall(nx+dx[dir]+dx[(dir-1+4)%4], ny+dy[dir]+dy[(dir-1+4)%4])) {
            answer += ten;
        } else {
            sand[nx+dx[dir]+dx[(dir-1+4)%4]][ny+dy[dir]+dy[(dir-1+4)%4]] += ten;
        }


        int tDir = (dir+1)%4;
        if(isWall(nx+dx[tDir], ny+dy[tDir])) {
            answer += seven;
        } else {
            sand[nx+dx[tDir]][ny+dy[tDir]] += seven;
        }

        if(isWall(sx+dx[tDir], sy+dy[tDir])) {
            answer += one;
        } else {
            sand[sx+dx[tDir]][sy+dy[tDir]] += one;
        }

        if(isWall(nx+dx[tDir]*2, ny+dy[tDir]*2)) {
            answer += two;
        } else {
            sand[nx+dx[tDir]*2][ny+dy[tDir]*2] += two;
        }

        tDir = (dir-1+4)%4;
        if(isWall(nx+dx[tDir], ny+dy[tDir])) {
            answer += seven;
        } else {
            sand[nx+dx[tDir]][ny+dy[tDir]] += seven;
        }

        if(isWall(sx+dx[tDir], sy+dy[tDir])) {
            answer += one;
        } else {
            sand[sx+dx[tDir]][sy+dy[tDir]] += one;
        }

        if(isWall(nx+dx[tDir]*2, ny+dy[tDir]*2)) {
            answer += two;
        } else {
            sand[nx+dx[tDir]*2][ny+dy[tDir]*2] += two;
        }

    }

    public static void moveTonado(int count) {
        while(count > 0) {
            int nx = sx + dx[dir];
            int ny = sy + dy[dir];
            
            divideSand(nx, ny);

            sx = nx;
            sy = ny;
            if(sx == 0 && sy == 0) break;

            count -= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        sand = new int[N][N];
        sx = N/2;
        sy = N/2;
        dir = 0;
        answer = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                sand[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 1;
        int countFlag = 0;
        while(true) {
            if(countFlag == 2) {
                countFlag = 0;
                count += 1;
            }

            moveTonado(count);
            countFlag += 1;
            dir = (dir+1)%4;

            if(sx == 0 && sy == 0) break;
        }

        System.out.println(answer);
    }
}