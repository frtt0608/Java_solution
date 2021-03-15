import java.util.*;
import java.io.*;

public class B6087 {
    static int W, H, minCount;
    static char[][] map;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static Node start, end;

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

    public static void installedMirror() {
        Queue<Node> que = new LinkedList<>();
        que.offer(start);
        int[][] mirrorCounts = new int[H][W];

        while(!que.isEmpty()) {
            Node s = que.poll();

            if(s.x == end.x && s.y == end.y) {
                minCount = mirrorCounts[s.x][s.y];
                return;
            }
            
            for(int dir=0; dir<4; dir++) {
                int nx = s.x + dx[dir];
                int ny = s.y + dy[dir];

                while(!isWall(nx, ny) && map[nx][ny] != '*') {
                    if(mirrorCounts[nx][ny] == 0) {
                        mirrorCounts[nx][ny] = s.cnt + 1;
                        que.offer(new Node(nx, ny, mirrorCounts[nx][ny]));
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

        for(int i=0; i<H; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                map[i][j] = input[j];

                if(map[i][j] == 'C') {
                    if(start == null) {
                        start = new Node(i, j, 0);
                    } else {
                        end = new Node(i, j, 0);
                    }
                }
            }
        }

        installedMirror();
        System.out.println(minCount-1);
    }
}   
