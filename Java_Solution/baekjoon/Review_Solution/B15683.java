import java.util.*;
import java.io.*;

public class B15683 {
    static int N, M, minBlindSpot;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<CCTV> cctvs;
    
    static class CCTV {
        int x, y;
        int num;

        CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void searchBlindSpot() {
        int blindSpot = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0 && !visited[i][j]) {
                    blindSpot += 1;
                }
            }
        }

        minBlindSpot = Math.min(minBlindSpot, blindSpot);
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void checkedMap(int x, int y, int dir) {

        while(true) {
            x += dx[dir];
            y += dy[dir];

            if(isWall(x, y) || map[x][y] == 6) break;

            if(map[x][y] == 0 && !visited[x][y]) {
                visited[x][y] = true;
            }
        }
    }

    public static void initVisited() {
        for(int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    public static void managedOffice(int[] cctvDir) {
        int x, y, dir;
        CCTV cctv;
        initVisited();

        for(int i=0; i<cctvDir.length; i++) {
            cctv = cctvs.get(i);
            dir = cctvDir[i];
            x = cctv.x;
            y = cctv.y;
            
            switch(cctv.num) {
                case 1:
                    checkedMap(x, y, dir);
                    break;
                case 2:
                    checkedMap(x, y, dir);
                    checkedMap(x, y, (dir+2)%4);
                    break;
                case 3:
                    checkedMap(x, y, dir);
                    checkedMap(x, y, (dir+1)%4);
                    break;
                case 4:
                    checkedMap(x, y, dir);
                    checkedMap(x, y, (dir+1)%4);
                    checkedMap(x, y, (dir+2)%4);
                    break;
                case 5:
                    checkedMap(x, y, 0);
                    checkedMap(x, y, 1);
                    checkedMap(x, y, 2);
                    checkedMap(x, y, 3);
                    break;
            }
        }
    }

    public static void getCCTVTotalCase(int idx, int[] cctvDir) {
        if(idx == cctvDir.length) {
            managedOffice(cctvDir);
            searchBlindSpot();
            return;
        }

        switch(cctvs.get(idx).num) {
            case 1:
                for(int dir=0; dir<4; dir++) {
                    cctvDir[idx] = dir;
                    getCCTVTotalCase(idx+1, cctvDir);
                }
                break;
            case 2:
                for(int dir=0; dir<2; dir++) {
                    cctvDir[idx] = dir;
                    getCCTVTotalCase(idx+1, cctvDir);
                }
                break;
            case 3:
                for(int dir=0; dir<4; dir++) {
                    cctvDir[idx] = dir;
                    getCCTVTotalCase(idx+1, cctvDir);
                }
                break;
            case 4:
                for(int dir=0; dir<4; dir++) {
                    cctvDir[idx] = dir;
                    getCCTVTotalCase(idx+1, cctvDir);
                }
                break;
            case 5:
                cctvDir[idx] = 5;
                getCCTVTotalCase(idx+1, cctvDir);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());    

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        cctvs = new ArrayList<>();
        minBlindSpot = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        getCCTVTotalCase(0, new int[cctvs.size()]);
        System.out.println(minBlindSpot);
    }
}   
