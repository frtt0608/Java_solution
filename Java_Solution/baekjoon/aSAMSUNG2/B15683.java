import java.util.*;
import java.io.*;

public class B15683 {
    static int N, M, minBlindSpot;
    static int[] dx={1,0,-1,0}, dy={0,1,0,-1};
    static int[][] map;
    static ArrayList<CCTV> cctv;
    static boolean[][] visited;

    static class CCTV {
        int x, y;
        int num;

        CCTV(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void getBlindSpot() {
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

    public static void monitoredOffice(int nx, int ny, int dir) {
        
        while(true) {
            nx += dx[dir];
            ny += dy[dir];

            if(isWall(nx, ny) || map[nx][ny] == 6) break;

            if(map[nx][ny] == 0) {
                visited[nx][ny] = true;
            }
        }
    }

    public static void initVisited() {
        for(int i=0; i<N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    public static void managedOffice(int[] cctvDir) {
        int dir, x, y;
        CCTV cam;
        initVisited();

        for(int i=0; i<cctvDir.length; i++) {
            cam = cctv.get(i);
            x = cam.x;
            y = cam.y;
            dir = cctvDir[i];

            switch(cam.num) {
                case 1:
                    monitoredOffice(x, y, dir);
                    break;
                case 2:
                    monitoredOffice(x, y, dir);
                    monitoredOffice(x, y, dir+2);
                    break;
                case 3:
                    monitoredOffice(x, y, dir);
                    monitoredOffice(x, y, (dir+1)%4);
                    break;
                case 4:
                    monitoredOffice(x, y, dir);
                    monitoredOffice(x, y, (dir+1)%4);
                    monitoredOffice(x, y, (dir+2)%4);
                    break;
                case 5:
                    monitoredOffice(cam.x, cam.y, 0);
                    monitoredOffice(cam.x, cam.y, 1);
                    monitoredOffice(cam.x, cam.y, 2);
                    monitoredOffice(cam.x, cam.y, 3);
            }
        }
    }

    public static void rotateCCTVtotalCase(int idx, int[] cctvDir) {
        if(idx == cctvDir.length) {
            managedOffice(cctvDir);
            getBlindSpot();
            return;
        }

        switch(cctv.get(idx).num) {
            case 1:
                for(int dir=0; dir<4; dir++) {
                    cctvDir[idx] = dir;
                    rotateCCTVtotalCase(idx+1, cctvDir);
                }
                break;
            case 2:
                for(int dir=0; dir<2; dir++) {
                    cctvDir[idx] = dir;
                    rotateCCTVtotalCase(idx+1, cctvDir);
                }
                break;
            case 3:
                for(int dir=0; dir<4; dir++) {
                    cctvDir[idx] = dir;
                    rotateCCTVtotalCase(idx+1, cctvDir);
                }
                break;
            case 4:
                for(int dir=0; dir<4; dir++) {
                    cctvDir[idx] = dir;
                    rotateCCTVtotalCase(idx+1, cctvDir);
                }
                break;
            case 5:
                rotateCCTVtotalCase(idx+1, cctvDir);
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
        cctv = new ArrayList<>();
        minBlindSpot = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] >= 1 && map[i][j] <= 5) {
                    cctv.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        rotateCCTVtotalCase(0, new int[cctv.size()]);
        System.out.println(minBlindSpot);
    }
}