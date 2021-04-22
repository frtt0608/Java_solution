import java.util.*;
import java.io.*;

// 5% 틀

public class B17822 {
    static int N, M, T, totalNumber;
    static int[] dx={0,0,-1,1}, dy={1,-1,0,0};
    static int[][] map;
    static boolean[][] visited;
    static boolean flag;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void initVisited() {
        for(int i=1; i<N+1; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    public static void sumTotalNumber() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<M+1; j++) {
                totalNumber += map[i][j];
            }
        }
    }

    public static void getAverageNumber() {
        int sumNum=0, cnt=0;
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<M+1; j++) {
                if(map[i][j] != 0) {
                    sumNum += map[i][j];
                    cnt += 1;
                }
            }
        }

        if(cnt > 0) {
            float avg = (float)sumNum/cnt;

            for(int i=1; i<N+1; i++) {
                for(int j=1; j<M+1; j++) {
                    if(map[i][j] != 0) {
                        if(map[i][j] < avg) {
                            map[i][j] += 1;
                        } else if(map[i][j] > avg) {
                            map[i][j] -= 1;
                        }
                    }
                }
            }
        }
    }

    public static void removeSameNumber(int x, int y, int num) {
        int nx, ny;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Node cur = que.poll();
            x = cur.x;
            y = cur.y;

            for(int dir=0; dir<4; dir++) {
                nx = x + dx[dir];
                ny = y + dy[dir];

                if(nx<=0 || nx>N) continue;
                if(ny == 0) {
                    ny = M;
                } else if(ny == M+1) {
                    ny = 1;
                }

                if(map[nx][ny] == num) {
                    map[nx][ny] = 0;
                    visited[nx][ny] = true;
                    que.offer(new Node(nx, ny));
                    flag = true;
                    // System.out.println(num);
                }
            }
        }
    }

    public static void searchSameNumber() {
        flag = false;
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<M+1; j++) {
                if(visited[i][j]) continue;
                if(map[i][j] != 0) {
                    removeSameNumber(i, j, map[i][j]);
                }
            }
        }

        if(!flag) {
            getAverageNumber();
        }
    }

    public static void rotateMap(int x, int d, int k) {
        int temp, rotateCnt;

        for(int i=x; i<N+1; i+=x) {
            rotateCnt = k;

            if(d == 0) {
                while(rotateCnt > 0) {
                    temp = map[i][M];
                    for(int j=0; j<M-1; j++) {
                        map[i][M-j] = map[i][M-j-1];
                    }
                    map[i][1] = temp;
                    rotateCnt -= 1;
                }
            } else {
                while(rotateCnt > 0) {
                    temp = map[i][1];
                    for(int j=1; j<M; j++) {
                        map[i][j] = map[i][j+1];
                    }
                    map[i][M] = temp;
                    rotateCnt -= 1;
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
        T = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<M+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // x의 배수
            // d:0 시계방향
            // k번 회전
            rotateMap(x, d, k);
            searchSameNumber();
            initVisited();
        }

        sumTotalNumber();
        print();

        System.out.println(totalNumber);
    }

    public static void print() {
        for(int i=1; i<N+1; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}