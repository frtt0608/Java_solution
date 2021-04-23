import java.util.*;
import java.io.*;

public class B19237 {
    static int N, M, K, remainedCnt;
    static int[] dx={0,-1,1,0,0}, dy={0,0,0,-1,1};
    static int[][] map, timer, smell;
    static int[] dirs;
    static int[][][] priority;
    static boolean isFinish;

    static class Shark {
        int x, y;
        int dir, num;

        Shark(int x, int y, int dir, int num) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.num = num;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<=0 || x>N || y<=0 || y>N) return true;
        return false;
    }

    public static void goTimer() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(timer[i][j] > 0) {
                    timer[i][j] -= 1;

                    if(timer[i][j] == 0) {
                        smell[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void setShark(Queue<Shark> que) {
        while(!que.isEmpty()) {
            Shark shark = que.poll();
    
            if(map[shark.x][shark.y] == 0) {
                map[shark.x][shark.y] = shark.num;
                smell[shark.x][shark.y] = shark.num;
                timer[shark.x][shark.y] = K;
            } else {
                remainedCnt -= 1;
                if(shark.num < map[shark.x][shark.y]) {
                    map[shark.x][shark.y] = shark.num;
                    smell[shark.x][shark.y] = shark.num;
                }
            }
        }
    }
    
    public static void moveShark() {
        int num=0, dir=0, nx=0, ny=0;
        boolean canMove;
        Queue<Shark> que = new LinkedList<>();

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(map[i][j] > 0) {
                    canMove = false;
                    num = map[i][j];
                    dir = dirs[num];

                    // 인접한 칸 중 비어있는 칸으로 이동
                    for(int d=1; d<5; d++) {
                        nx = i + dx[priority[num][dir][d]];
                        ny = j + dy[priority[num][dir][d]];

                        if(isWall(nx, ny)) continue;
                        if(smell[nx][ny] == 0) {
                            canMove = true;
                            dirs[num] = priority[num][dir][d];
                            que.offer(new Shark(nx, ny, priority[num][dir][d], num));
                            break;
                        }
                    }

                    // 위에서 이동하지 못했을 경우, 자신의 냄새가 있는 곳으로 이동
                    if(!canMove) {
                        for(int d=1; d<5; d++) {
                            nx = i + dx[priority[num][dir][d]];
                            ny = j + dy[priority[num][dir][d]];
    
                            if(isWall(nx, ny)) continue;
                            if(smell[nx][ny] == num) {
                                dirs[num] = priority[num][dir][d];
                                que.offer(new Shark(nx, ny, priority[num][dir][d], num));
                                break;
                            }
                        }
                    }

                    map[i][j] = 0;
                }
            }
        }
        
        goTimer();
        setShark(que);
    }

    public static void printMap() {
        System.out.println("상어 위치: ");
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                System.out.print(map[i][j]+", ");
            }
            System.out.println();
        }

        System.out.println("냄새 상태: ");
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                System.out.print(smell[i][j]+", ");
            }
            System.out.println();
        }
        
        System.out.println("남은 시간: ");
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                System.out.print(timer[i][j]+", ");
            }
            System.out.println();
        }

        System.out.println("-----------------------\n");
    }
    
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        remainedCnt = M;

        map = new int[N+1][N+1];
        smell = new int[N+1][N+1];
        timer = new int[N+1][N+1];
        dirs = new int[M+1];
        priority = new int[M+1][5][5];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] >= 1) {
                    timer[i][j] = K;
                    smell[i][j] = map[i][j];
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<M+1; i++) {
            dirs[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<M+1; i++) {
            for(int j=1; j<5; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=1; k<5; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int time = 0;
        while(time <= 1000) {
            time += 1;
            moveShark();

            if(remainedCnt == 1)
                break;
        }

        System.out.println(remainedCnt == 1 ? time:-1);
    }   
}