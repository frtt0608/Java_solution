import java.io.*;
import java.util.*;

public class B2933 {
    static int R, C;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static char[][] cave;
    static boolean isCluster;
    static ArrayList<Node> minerals;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int breakMineral(int h, boolean dir) {
        if(dir) {
            // left
            for(int j=0; j<C; j++) {
                if(cave[h][j] == 'x') {
                    return j;
                }
            }   
        } else {
            // right
            for(int j=C-1; j>=0; j--) {
                if(cave[h][j] == 'x') {
                    return j;
                }
            }
        }

        return -1;
    }

    public static boolean findClusterMinerals(int x, int y) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        boolean[][] visited = new boolean[R][C];
        visited[x][y] = true;
        minerals.add(new Node(x, y));

        while(!que.isEmpty()) {
            Node s = que.poll();
            x = s.x;
            y = s.y;

            if(x == R-1) {
                return true;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx<0 || nx>=R || ny<0 || ny>=C) continue;
                if(visited[nx][ny] || cave[nx][ny] == '.') continue;

                visited[nx][ny] = true;
                que.offer(new Node(nx, ny));
                minerals.add(new Node(nx, ny));
            }
        }

        return false;
    }

    public static void downMinerals() {
        boolean isPossible = true;

        while(true) {
            for(Node node: minerals) {
                cave[node.x][node.y] = '.';
            }

            for(Node node: minerals) {
                if(node.x + 1 == R || cave[node.x+1][node.y] == 'x') {
                    isPossible = false;
                    break;
                }
            }

            for(Node node: minerals) {
                if(isPossible) node.x += 1;
                cave[node.x][node.y] = 'x';
            }

            if(!isPossible) return;
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
        // StringBuilder sb = new StringBuilder();
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cave = new char[R][C];
        minerals = new ArrayList<>();

        for(int i=0; i<R; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                cave[i][j] = input[j];
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean dir = true;
        for(int i=0; i<N; i++) {
            int h = R - Integer.parseInt(st.nextToken());
            int y = breakMineral(h, dir);
            dir = !dir;
            if(y < 0) 
                continue;
                
            cave[h][y] = '.';

            for(int d=0; d<4; d++) {
                int nx = h + dx[d];
                int ny = y + dy[d];

                if(nx<0 || nx>=R || ny<0 || ny>=C) continue;
                if(cave[nx][ny] == 'x') {
                    minerals.clear();
                    isCluster = findClusterMinerals(nx, ny);
                    if(!isCluster) {
                        downMinerals();
                    }
                }
            }
        }

        printCave();
    }
}
