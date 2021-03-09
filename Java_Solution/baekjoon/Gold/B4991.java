import java.io.*;
import java.util.*;

public class B4991 {
    static int w, h, dustCnt, minCount;
    static char[][] room;
    static Queue<Node> dustQue = new LinkedList<>();
    static boolean[][][] visited;

    static class Node {
        int id;
        int x, y, time;

        Node(int id, int x, int y, int time) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=h || y<0 || y>=w) return true;
        return false;
    }

    public static void cleanRoomWithBitmasking() {
        int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

        while(!dustQue.isEmpty()) {
            Node node = dustQue.poll();
            int x = node.x;
            int y = node.y;

            // if position is dust
            // node.id update & visited check
            if(room[x][y] >= '0' && room[x][y] <= '9') {
                node.id |= (1 << (room[x][y] - '0'));
            }

            // all visited
            // if dustCnt = 3 -> ('111' = 7)
            if(node.id == (1 << dustCnt) - 1) {
                minCount = node.time;
                break;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(isWall(nx, ny)) continue;
                if(room[nx][ny] == 'x') continue;
                if(visited[nx][ny][node.id]) continue;

                visited[nx][ny][node.id] = true;
                dustQue.offer(new Node(node.id, nx, ny, node.time+1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w==0 && h==0) break;

            dustQue.clear();
            room = new char[h][w];
            minCount = Integer.MAX_VALUE;
            dustCnt = 0;
            char dustId = '0';
            
            for(int i=0; i<h; i++) {
                char[] input = br.readLine().toCharArray();
                for(int j=0; j<w; j++) {
                    room[i][j] = input[j];
                    if(room[i][j] == 'o') {
                        dustQue.offer(new Node(0, i, j, 0));
                    } else if(room[i][j] == '*') {
                        dustCnt += 1;
                        room[i][j] = dustId;
                        dustId += 1;
                    }
                }
            }
            
            visited = new boolean[h][w][1 << dustCnt];
            cleanRoomWithBitmasking();

            if(minCount == Integer.MAX_VALUE) {
                sb.append("-1\n");
            } else {
                sb.append(minCount + "\n");
            }
        }

        System.out.println(sb.toString());
    }
}
