import java.util.*;
import java.io.*;

// 7 0 1
// 6 x 2
// 5 4 3

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K, answer;
    static List<Fireball>[][] map;
    
    public static boolean isWall(int x, int y) {
        if(x<=0 || x>N || y<=0 || y>N) return true;
        return false;
    }

    public static void moveFireBall() {
        Queue<Fireball> fireQ = new LinkedList<>();

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                int mapSize = map[i][j].size();
                for(int k=0; k<mapSize; k++) {
                    Fireball fb = map[i][j].remove(0);
                    // System.out.println("이동할 fb:" + fb.r + ", " + fb.c);

                    for(int s=0; s<fb.s; s++) {
                        int nr = fb.r + dx[fb.d];
                        int nc = fb.c + dy[fb.d];
                        if(isWall(nr, nc)) {
                            if(nr == N+1) {
                                nr = 1;
                            } else if(nr == 0) {
                                nr = N;
                            }
                            
                            if(nc == N+1) {
                                nc = 1;
                            } else if(nc == 0) {
                                nc = N;
                            }
                        }

                        fb.r = nr;
                        fb.c = nc;
                    }

                    // System.out.println("이동 후 fb:" + fb.r + ", " + fb.c);
                    fireQ.offer(fb);
                    // System.out.println();
                }
            }
        }

        // System.out.println("이동하기");
        while(!fireQ.isEmpty()) {
            Fireball fb = fireQ.poll();
            // System.out.println("이동할 fb:" + fb.r + ", " + fb.c);
            map[fb.r][fb.c].add(fb);
        }
    }

    public static void calculateFireball() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++){
                for(int k=0; k<map[i][j].size(); k++) {
                    answer += map[i][j].get(k).m;
                }
            }
        }
    }

    public static void initList() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
    }

    public static void divideFireball() {
        Queue<Fireball> fbQ = new LinkedList<>();

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                int mapSize = map[i][j].size();
                
                if(mapSize >= 2) {
                    int sumM = 0;
                    int sumS = 0;
                    int[] dArr = new int[mapSize];

                    for(int k=0; k<mapSize; k++) {
                        Fireball fb = map[i][j].remove(0);
                        sumM += fb.m;
                        sumS += fb.s;
                        dArr[k] = fb.d;
                    }

                    if(sumM/5 > 0) {
                        int m = sumM/5;
                        int s = sumS/mapSize;
                        boolean evenFlag = true;
                        boolean oddFlag = true;

                        for(int d=0; d<mapSize; d++) {
                            if(dArr[d]%2 == 0) oddFlag = false;
                            else evenFlag = false;
                        }

                        if(oddFlag || evenFlag) {
                            fbQ.offer(new Fireball(i,j,m,s,0));
                            fbQ.offer(new Fireball(i,j,m,s,2));
                            fbQ.offer(new Fireball(i,j,m,s,4));
                            fbQ.offer(new Fireball(i,j,m,s,6));
                        } else {
                            fbQ.offer(new Fireball(i,j,m,s,1));
                            fbQ.offer(new Fireball(i,j,m,s,3));
                            fbQ.offer(new Fireball(i,j,m,s,5));
                            fbQ.offer(new Fireball(i,j,m,s,7));
                        }
                    }
                }
            }
        }

        while(!fbQ.isEmpty()) {
            Fireball fb = fbQ.poll();
            map[fb.r][fb.c].add(fb);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = 0;

        map = new ArrayList[N+1][N+1];
        initList();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new Fireball(r, c, m, s, d));
        }

        while(K > 0) {
            K -= 1;
            moveFireBall();
            divideFireball();
        }

        calculateFireball();
        System.out.println(answer);
    }
}

class Fireball {
    int r;
    int c;
    int m;
    int s;
    int d;

    Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}