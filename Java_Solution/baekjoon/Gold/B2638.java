import java.io.*;
import java.util.*;

public class B2638 {
    static int N, M, time, cheeseCount;
    static int[][] cheese;
    static boolean[][] visited;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void meltedCheese() {
        int[] dx={1,0,-1,0}, dy={0,1,0,-1};

        Deque<Node> que = new LinkedList<>();
        que.offer(new Node(0, 0));
        visited[0][0] = true;
        int size = 0;
        
        while(cheeseCount > 0) {
            size = que.size();

            while(size > 0) {
                Node v = que.poll();
                size -= 1;

                for(int dir=0; dir<4; dir++) {
                    int nx = v.x + dx[dir];
                    int ny = v.y + dy[dir];
    
                    if(isWall(nx, ny)) continue;
                    if(visited[nx][ny]) continue;

                    if(cheese[nx][ny] >= 1) {
                        cheese[nx][ny] += 1;
                        if(cheese[nx][ny] >= 3) {
                            visited[nx][ny] = true;

                            cheese[nx][ny] = 0;
                            cheeseCount -= 1;
                            que.addLast(new Node(nx, ny));
                        }
                    } else {
                        size += 1;
                        visited[nx][ny] = true;
                        que.addFirst(new Node(nx, ny));
                    }
                }
            }

            time += 1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
                
                if(cheese[i][j] == 1)
                    cheeseCount += 1;
            }
        }

        meltedCheese();
        System.out.println(time);
    }
}
