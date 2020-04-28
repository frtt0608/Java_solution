// NN크기의 공간에 물고리 M 아기상어1, 한칸에 물고기 최대 1마리
// 크기를 가지고 있다. 상어:2, 초당 상하좌우 이동
// 자기보다 크면 못지나감, 나머지는 지나감
// 자기보다 작으면 잡아먹음, 같으면 못먹고 지나감
// 먹을수 있는 물고기 없으면 엄마 요청
// 1마리면 먹으러감
// 1마리보다 많으면 가까운 물고기부터
// 가까운 물고기가 많으면 가장 위에부터, 가장 왼쪽부터
// 이동과 동시에 먹구 빈칸
// 자신의 크기와 같은 수의 물고기 먹으면 크기 1증가

// 요청없이 몇초동안 물고기를 먹는지 구하라

import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, map[][], shark_x, shark_y, shark_age, shark_feed, fishCnt, resTime;
    static PriorityQueue<Fish> fishList;
    static int[] dx = {1,-1,0,0}, dy={0,0,1,-1};

    static class Fish implements Comparable<Fish>{
        int x;
        int y;

        Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Fish fish) {
            if(this.x < fish.x) return -1;
            else if(this.x == fish.x) {
                if(this.y < fish.y) return -11;
                else return 1;
            }
            return 1;
        }
    }

    static boolean wall(int x, int y) {
        if(x>=N || x<0 || y>=N || y<0) return false;
        return true;
    }

    static boolean catchFish() {
        Queue<Fish> que = new LinkedList<>();
        fishList = new PriorityQueue<>();
        int visited[][] = new int[N][N];

        que.offer(new Fish(shark_x, shark_y));
        visited[shark_x][shark_y] = 1;
        int moving = 0;

        while(!que.isEmpty()) {
            
            moving++;

            int qsize = que.size();
            for(int i=0; i<qsize; i++) {
                Fish fish = que.poll();
                int x = fish.x;
                int y = fish.y;

                for(int dir=0; dir<4; dir++) {
                    int nx = x+dx[dir];
                    int ny = y+dy[dir];
                    if(wall(nx,ny) && visited[nx][ny]==0 && map[nx][ny] <= shark_age) {
                        visited[nx][ny] = 1;
                        que.offer(new Fish(nx, ny));
                        if(map[nx][ny] > 0 && map[nx][ny] < shark_age) {
                            fishList.add(new Fish(nx, ny));
                        }
                    }
                }
            }

            if(!fishList.isEmpty()) {
                resTime += moving;
                checkFish(fishList.poll());
                
                return true;
            }
        }
        return false;
    }

    static void checkFish(Fish fish) {
        map[fish.x][fish.y] = 0;
        shark_x = fish.x;
        shark_y = fish.y;
        shark_feed++;

        if(shark_feed == shark_age) {
            shark_age++;
            shark_feed=0;
        }
    }


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map = new int[N][N];
        shark_feed = 0;
        resTime = 0;
        shark_age = 2;
        fishCnt = 0;
    
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int age = Integer.parseInt(st.nextToken());
                if(age==9) {
                    shark_x = i;
                    shark_y = j;
                } else if(age>0 && age<=6) {
                    map[i][j] = age;
                    fishCnt++;
                }
            }
        }
        boolean flag = true;
        while(flag && fishCnt>0) {
            flag = catchFish();
        }
        System.out.println(resTime);
    }
}
