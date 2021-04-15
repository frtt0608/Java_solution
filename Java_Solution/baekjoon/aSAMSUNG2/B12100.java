import java.util.*;
import java.io.*;

public class B12100 {
    static int N, maxNumber;
    static int[] dx={-1,0,1,0}, dy={0,-1,0,1};
    static int[][] map;
    static boolean[][] visited;

    public static int[][] copyMap(int oMap[][]) {
        int nMap[][] = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                nMap[i][j] = oMap[i][j];
            }
        }

        return nMap;
    }

    // dir == 0
    public static int[][] movedUpDir(int[][] oMap, int dir) {
        visited = new boolean[N][N];
        int[][] nMap = copyMap(oMap);
        int ox, nx, ny;

        for(int y=0; y<N; y++) {
            for(int x=1; x<N; x++) {
                if(nMap[x][y] == 0) continue;
                nx = x;
                ny = y;
                
                while(true) {
                    ox = nx;
                    nx += dx[dir];

                    if(nx<0 || nx>=N)
                        break;
                    if(nMap[nx][ny] == 0) {
                        nMap[nx][ny] = nMap[ox][ny];
                        nMap[ox][ny] = 0;
                    } else {
                        if(nMap[nx][ny] == nMap[ox][ny]) {
                            if(!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                nMap[nx][ny] *= 2;
                                nMap[ox][ny] = 0;
                            }
                        }
                        break;
                    }
                }
            }
        }

        return nMap;
    }
 
    // dir == 2
    public static int[][] movedDownDir(int[][] oMap, int dir) {
        visited = new boolean[N][N];
        int[][] nMap = copyMap(oMap);
        int ox, nx, ny;

        for(int y=0; y<N; y++) {
            for(int x=N-2; x>=0; x--) {
                if(nMap[x][y] == 0) continue;
                nx = x;
                ny = y;
                
                while(true) {
                    ox = nx;
                    nx += dx[dir];

                    if(nx<0 || nx>=N)
                        break;
                    if(nMap[nx][ny] == 0) {
                        nMap[nx][ny] = nMap[ox][ny];
                        nMap[ox][ny] = 0;
                    } else {
                        if(nMap[nx][ny] == nMap[ox][ny]) {
                            if(!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                nMap[nx][ny] *= 2;
                                nMap[ox][ny] = 0;
                            }
                        }
                        break;
                    }
                }
            }
        }

        return nMap;
    }

    // dir == 3
    public static int[][] movedRightDir(int[][] oMap, int dir) {
        visited = new boolean[N][N];
        int[][] nMap = copyMap(oMap);
        int oy, nx, ny;

        for(int x=0; x<N; x++) {
            for(int y=N-2; y>=0; y--) {
                if(nMap[x][y] == 0) continue;
                nx = x;
                ny = y;

                while(true) {
                    oy = ny;
                    ny += dy[dir];

                    if(ny<0 || ny>=N)
                        break;
                    if(nMap[nx][ny] == 0) {
                        nMap[nx][ny] = nMap[nx][oy];
                        nMap[nx][oy] = 0;
                    } else {
                        if(nMap[nx][ny] == nMap[nx][oy]) {
                            if(!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                nMap[nx][ny] *= 2;
                                nMap[nx][oy] = 0;
                            }
                        }
                        break;
                    }
                }
            }
        }

        return nMap;
    }

    // dir == 1
    public static int[][] movedLeftDir(int[][] oMap, int dir) {
        visited = new boolean[N][N];
        int[][] nMap = copyMap(oMap);
        int oy, nx, ny;

        for(int x=0; x<N; x++) {
            for(int y=1; y<N; y++) {
                if(nMap[x][y] == 0) continue;
                nx = x;
                ny = y;

                while(true) {
                    oy = ny;
                    ny += dy[dir];

                    if(ny<0 || ny>=N)
                        break;
                    if(nMap[nx][ny] == 0) {
                        nMap[nx][ny] = nMap[nx][oy];
                        nMap[nx][oy] = 0;
                    } else {
                        if(nMap[nx][ny] == nMap[nx][oy]) {
                            if(!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                nMap[nx][ny] *= 2;
                                nMap[nx][oy] = 0;
                            }
                        } 
                        break;
                    }
                }
            }
        }

        return nMap;
    }

    public static int[][] moveMap(int[][] oMap, int dir) {

        if(dir == 0) {
            return movedUpDir(oMap, dir);
        } else if(dir == 1) {
            return movedLeftDir(oMap, dir);
        } else if(dir == 2) {
            return movedDownDir(oMap, dir);
        } else {
            return movedRightDir(oMap, dir);
        }
    }

    public static void getMaxNumber(int[][] oMap) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                maxNumber = Math.max(maxNumber, oMap[i][j]);
            }
        }
    }

    // dfs moving
    public static void moved2048Map(int cnt, int[][] oMap) {
        if(cnt == 5) {
            getMaxNumber(oMap);
            return;
        }

        for(int dir=0; dir<4; dir++) {
            moved2048Map(cnt+1, moveMap(oMap, dir));
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxNumber = Math.max(maxNumber, map[i][j]);
            }
        }

        moved2048Map(0, map);
        System.out.println(maxNumber);
    }
}