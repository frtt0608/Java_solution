import java.io.*;
import java.util.*;

public class B6087 {
    static int W, H, minCount;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static char[][] map;
    static Node startLazer, endLazer;

    static class Node {
        int x, y;
        int cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=H || y<0 || y>=W) return true;
        return false;
    }

    public static void installedMirroirMinCount() {
        Queue<Node> que = new LinkedList<>();
        que.offer(startLazer);
        int[][] visited = new int[H][W];

        while(!que.isEmpty()) {
            Node m = que.poll();

            if(endLazer.x == m.x && endLazer.y == m.y) {
                minCount = Math.min(minCount, m.cnt-1);
                return;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = m.x + dx[dir];
                int ny = m.y + dy[dir];

                while(!isWall(nx, ny) && map[nx][ny] != '*') {
                    if(visited[nx][ny] == 0) {
                        visited[nx][ny] = m.cnt + 1;
                        que.offer(new Node(nx, ny, m.cnt+1));
                    }

                    nx += dx[dir];
                    ny += dy[dir];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        minCount = Integer.MAX_VALUE;

        for(int i=0; i<H; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                map[i][j] = input[j];
                if(map[i][j] == 'C') {
                    if(startLazer == null) {
                        startLazer = new Node(i, j, 0);
                    } else {
                        endLazer = new Node(i, j, 0);
                    }
                }
            }
        }

        installedMirroirMinCount();
        System.out.println(minCount);
    }
}
