// NxM 직사각형, 연구소는 빈칸, 벽으로 이루어짐
// 바이러스는 상하좌우 인접한 빈 칸으로 퍼져나감
// 세울 수 있는벽의 개수는 3개, 꼭 3개를 세워야 함
// 바이러스가 퍼질 수 없는 곳을 안정 영역이라고 함
// 안정 영역의 최댓값

import java.util.*;
import java.io.*;

class XY {
    int x;
    int y;

    XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, map[][], max_savePoint, visited[][];
    static ArrayList<XY> virus;
    static int rx[] = {1,-1,0,0}, ry[] = {0,0,1,-1};

    static int[][] deep_copy(int copy_map[][]) {
        for(int idx=0; idx<N; idx++)
            System.arraycopy(map[idx], 0, copy_map[idx], 0, M);
        return copy_map;
    }

    static void install_wall(int x, int y, int wall_cnt) {
        if(wall_cnt==3) {
            //for(int i=0; i<N; i++)
                //System.out.println(Arrays.toString(map[i]));
            max_savePoint = Math.max(max_savePoint, BFS());
            //System.out.println("----------------");
            return;
        }
        
        for(int i=x; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j]==1 || map[i][j]!=0) continue;
                visited[i][j] = 1;
                map[i][j] = 1;
                install_wall(i,j,wall_cnt+1);
                visited[i][j] = 0;
                map[i][j] = 0;
            }
        }
    }

    static int BFS() {
        Queue<XY> que = new LinkedList<>();
        int copy_map[][] = new int[N][M];
        copy_map = deep_copy(copy_map);
        int savePoint = 0;

        for(XY xy : virus) que.offer(xy);
        
        while(!que.isEmpty()) {
            XY VIRUS = que.poll();
            int x = VIRUS.x;
            int y = VIRUS.y;

            for(int dir=0; dir<4; dir++) {
                int nx = x + rx[dir];
                int ny = y + ry[dir];
                if(nx>=N || nx<0 || ny>=M || ny<0) continue;
                if(copy_map[nx][ny]==0) {
                    copy_map[nx][ny] = 2;
                    que.offer(new XY(nx, ny));
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copy_map[i][j]==0) savePoint++;
            }
        }
        //System.out.println(savePoint);
        return savePoint;
    }

    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new int[N][M];
        max_savePoint = 0;
        virus = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j]==2) virus.add(new XY(i,j));
            }
        }

        install_wall(0,0,0);
        System.out.println(max_savePoint);
    }
}