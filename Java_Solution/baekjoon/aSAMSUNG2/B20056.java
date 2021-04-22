import java.util.*;
import java.io.*;

public class B20056 {
    static int N, M, K, totalFire;
    static int[] dx={-1,-1,0,1,1,1,0,-1,-1}, dy={0,1,1,1,0,-1,-1,-1,0};
    static LinkedList<Fire>[][] map;

    static class Fire {
        int x, y;
        int m, s, d;

        Fire(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void moveFire() {
        Queue<Fire> que = new LinkedList<>();
        int x, y, nx, ny, s, d;
        Fire fire;

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {

                while(!map[i][j].isEmpty()) {
                    fire = map[i][j].pop();
                    x = fire.x;
                    y = fire.y;
                    s = fire.s%N;
                    d = fire.d;
                    
                    while(s > 0) {
                        s -= 1;
                        nx = x + dx[d];
                        ny = y + dy[d];

                        if(nx == 0) {
                            nx = N;
                        } else if(nx == N+1) {
                            nx = 1;
                        }

                        if(ny == 0) {
                            ny = N;
                        } else if(ny == N+1) {
                            ny = 1;
                        }

                        x = nx;
                        y = ny;
                    }

                    que.offer(new Fire(x, y, fire.m, fire.s, d));
                }
            }
        }

        while(!que.isEmpty()) {
            fire = que.poll();
            x = fire.x;
            y = fire.y;
            
            map[x][y].add(fire);
        }
    }

    public static void fireAndFire() {
        int totalM=0, totalS=0, size, s, m, oddCnt, evenCnt;
        Fire fire;

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(map[i][j].size() <= 1) continue;

                size = map[i][j].size();
                totalM = 0;
                totalS = 0;
                oddCnt = 0;
                evenCnt = 0;

                while(!map[i][j].isEmpty()) {
                    fire = map[i][j].remove(0);
                    totalM += fire.m;
                    totalS += fire.s;

                    if(fire.d%2 == 0) evenCnt += 1;
                    else oddCnt += 1;
                }

                m = totalM/5;
                if(m > 0) {
                    
                    s = totalS/size;

                    if(oddCnt == size || evenCnt == size) {
                        map[i][j].add(new Fire(i, j, m, s, 0));
                        map[i][j].add(new Fire(i, j, m, s, 2));
                        map[i][j].add(new Fire(i, j, m, s, 4));
                        map[i][j].add(new Fire(i, j, m, s, 6));
                    } else {
                        map[i][j].add(new Fire(i, j, m, s, 1));
                        map[i][j].add(new Fire(i, j, m, s, 3));
                        map[i][j].add(new Fire(i, j, m, s, 5));
                        map[i][j].add(new Fire(i, j, m, s, 7));
                    }
                }
            }
        }
    }

    public static void getTotalFireM() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                
                for(Fire fire: map[i][j]) {
                    totalFire += fire.m;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new LinkedList[N+1][N+1];

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[x][y].add(new Fire(x, y, m, s, d));
        }

        while(K > 0) {
            moveFire();
            fireAndFire();
            K -= 1;
        }   
        
        getTotalFireM();
        System.out.println(totalFire);
    }
}