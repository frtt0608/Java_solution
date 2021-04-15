// 0 : 시계/ 1은 반시계

import java.util.*;
import java.io.*;


class Node {
    int x;
    int y;
    
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
/**
 * Main
 */
public class Main {
    static int N, M, T, map[][], resNum;
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};
    static double totalNum, totalCnt;

    static void rotatePlate(int x, int d, int k) {
        int tempX = x;
        while(x<=N) {
            if(d==0) {
                clockWise(x, k);
            } else {
                clockWise(x, k*(M-1));
            }
            x += tempX;
        }
    }

    static void clockWise(int x, int k) {
        k %= M;
        int tempMap[] = new int[M];
        
        for(int i=0; i<M; i++) {
            tempMap[(i+k)%M] = map[x][i];
        }

        for(int i=0; i<M; i++) {
            map[x][i] = tempMap[i];
        }
    }

    static boolean wall(int x, int y) {
        if(x>N || x<=0 || y>=M || y<0) return true;
        return false;
    }

    static void adjMap() {
        boolean visited[][] = new boolean[N+1][M];
        boolean flag = false;
        totalNum = 0;
        totalCnt = 0;
        Queue<Node> que;

        for(int i=N; i>0; i--) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==0) continue;
                totalCnt += 1;
                totalNum += map[i][j];

                que = new LinkedList<>();
                que.offer(new Node(i,j));

                while(!que.isEmpty()) {
                    Node node = que.poll();
                    int x = node.x;
                    int y = node.y;
                    
                    for(int dir=0; dir<4; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];
                        if(ny == M) ny=0;
                        else if(ny == -1) ny=M-1;
    
                        if(wall(nx,ny) || visited[nx][ny]) continue;
                        if(map[x][y] == map[nx][ny]) {
                            visited[x][y] = true;
                            visited[nx][ny] = true;
                            que.offer(new Node(nx, ny));
                            flag = true;
                        }
                    }
                }
            }
        }
        updateMap(visited, flag);
    }

    static void updateMap(boolean visited[][], boolean flag) {
        if(flag) {
            for(int i=1; i<=N; i++) {
                for(int j=0; j<M; j++) {
                    if(visited[i][j]) map[i][j] = 0; 
                }
            }
        } else {
            if(totalCnt==0) return;
            double avgNum = totalNum/totalCnt;

            for(int i=1; i<=N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j]==0) continue;

                    if(map[i][j] > avgNum) map[i][j] -= 1;
                    else if(map[i][j] < avgNum) map[i][j] += 1;
                }
            }
        }
    }

    static void totalSum() {
        for(int i=1; i<=N; i++) {
            for(int j=0; j<M; j++) {
                resNum += map[i][j];
            }
        }
    }

    static void printMap() {
        for(int i=1; i<=N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("-------------------------");
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N+1][M];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotatePlate(x,d,k);
            adjMap();
        }

        totalSum();
        printMap();
        System.out.println(resNum);
    }
}