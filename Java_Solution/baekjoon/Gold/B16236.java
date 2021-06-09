import java.io.*;
import java.util.*;

public class B16236 {
    static int N, sX, sY, sSize, feedCnt, spendTime;
    static int[] dx = {-1,0,1,0}, dy = {0,-1,0,1};
    static int[][] sea;
    static Fish shark;
    static PriorityQueue<Fish> canFeedFishes;

    static class Fish implements Comparable<Fish> {
        int x, y;
        int diff;

        Fish(int x, int y, int diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }

        @Override
        public int compareTo(Fish fish) {
            if(this.diff == fish.diff) {
                if(this.x == fish.x) {
                    return this.y - fish.y;
                }
    
                return this.x - fish.x;
            }

            return this.diff - fish.diff;
        }
    }

    public static boolean isWall(int x, int y) {
        return x<0 || x>=N || y<0 || y>=N;
    }

    public static void searchCanFeedFishes(int x, int y, int size) {
        Queue<Fish> que = new LinkedList<>();
        que.offer(new Fish(x, y, 0));
        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;
        canFeedFishes.clear();
        
        while(!que.isEmpty()) {
            Fish cur = que.poll();
            x = cur.x;
            y = cur.y;

            if(canFeedFishes.size() >= 4) break;
            
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || visited[nx][ny]) continue;
                if(size < sea[nx][ny]) continue;

                visited[nx][ny] = true;
                que.offer(new Fish(nx, ny, cur.diff+1));
                if(sea[nx][ny] != 0 && size > sea[nx][ny]) {
                    canFeedFishes.offer(new Fish(nx, ny, cur.diff+1));
                }
            }
        }
    }

    public static void feedingFishes() {
        sea[sX][sY] = 0;
        Fish fish = canFeedFishes.poll();
        sX = fish.x;
        sY = fish.y;
        
        feedCnt += 1;
        if(feedCnt == sSize) {
            sSize += 1;
            feedCnt= 0;
        }
        
        spendTime += fish.diff;
        sea[sX][sY] = 9;
    }

    public static void feedingBabyShark() {
        while(true) {
            searchCanFeedFishes(sX, sY, sSize);
            if(canFeedFishes.size() == 0) 
                return;

            feedingFishes();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];
        canFeedFishes = new PriorityQueue<>();
    
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());

                if(sea[i][j] == 9) {
                    sX = i;
                    sY = j;
                    sSize = 2;
                }
            }
        }

        feedingBabyShark();
        System.out.println(spendTime);
    }
}
