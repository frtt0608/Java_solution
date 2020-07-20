// 적록색약

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] RGB, dx={1,-1,0,0}, dy={0,0,1,-1};
    static String[][] map;
    static boolean[][] visited;

    static boolean wall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    static void DFS(int x, int y, String color) {
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(wall(nx, ny)) continue;
            if(visited[nx][ny]) continue;

            if(map[nx][ny].equals(color)) {
                visited[nx][ny] = true;
                DFS(nx, ny, color);
            }
        }
    }

    static void RG_DFS(int x, int y) {
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(wall(nx, ny)) continue;
            if(visited[nx][ny]) continue;

            if(map[nx][ny].equals("R") || map[nx][ny].equals("G")) {
                visited[nx][ny] = true;
                RG_DFS(nx, ny);
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        RGB = new int[2];
        visited = new boolean[N][N];
        int RG = 0;

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<N; j++) {
                map[i][j] = input[j];
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j]) continue;

                DFS(i, j, map[i][j]);

                if(map[i][j].equals("B")) {
                    RGB[0] += 1;
                } else {
                    RGB[1] += 1;
                }
            }
        }

        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j]) continue;
                if(map[i][j].equals("B")) continue;

                RG_DFS(i, j);
                RG += 1;
            }
        }

        System.out.println(RGB[0] + RGB[1]);
        System.out.println(RG + RGB[0]);
    }
}