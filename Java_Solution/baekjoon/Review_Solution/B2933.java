import java.util.*;
import java.io.*;

public class B2933 {
    static int R, C;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static char[][] cave;
    static ArrayList<Node> minerals = new ArrayList<>();

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int breakMinerals(boolean flag, int x) {
        int res = -1;

        if(flag) {
            for(int y=0; y<C; y++) {
                if(cave[x][y] == 'x') {
                    return y;
                }
            }
        } else {
            for(int y=C-1; y>=0; y--) {
                if(cave[x][y] == 'x') {
                    return y;
                }
            }
        }

        return res;
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=R || y<0 || y>=C) return true;
        return false;
    }

    public static boolean findMineralCluster(int x, int y) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        boolean[][] visited = new boolean[R][C];
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Node s = que.poll();
            minerals.add(s);

            if(s.x == R-1) return true;

            for(int dir=0; dir<4; dir++) {
                int nx = s.x + dx[dir];
                int ny = s.y + dy[dir];

                if(isWall(nx, ny)) continue;
                if(cave[nx][ny] == '.') continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                que.offer(new Node(nx, ny));
            }
        }

        return false;
    }

    public static void downToBottomCluster() {
        boolean canDown = true;

        while(canDown) {
            for(Node m: minerals) {
                cave[m.x][m.y] = '.';
            }
    
            for(Node m: minerals) {
                if(m.x+1 == R || cave[m.x+1][m.y] == 'x') {
                    canDown = false;
                    break;
                }
            }
    
            for(Node m: minerals) {
                if(canDown) {
                    m.x += 1;
                }
                cave[m.x][m.y] = 'x';
            }
        }
    }

    public static void printCave() {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                System.out.print(cave[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cave = new char[R][C];

        for(int i=0; i<R; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                cave[i][j] = input[j];
            }
        }

        int N = Integer.parseInt(br.readLine());
        boolean leftFlag = true;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int x = R - Integer.parseInt(st.nextToken());
            int y = breakMinerals(leftFlag, x);
            leftFlag = !leftFlag;
            if(y == -1) continue;

            cave[x][y] = '.';

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                minerals.clear();

                if(isWall(nx, ny)) continue;
                if(cave[nx][ny] == '.') continue;

                if(!findMineralCluster(nx, ny)) {
                    downToBottomCluster();
                }
            }
        }

        printCave();
    }
}   
