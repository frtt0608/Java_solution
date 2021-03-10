import java.util.*;
import java.io.*;

public class B4991 {
    static int H, W, totalDustCnt, minMoveCnt;
    static char[][] room;
    static Queue<Node> dustQue;
    static StringBuilder sb;

    static class Node {
        int id;
        int x, y;
        int moveCnt;

        Node(int id, int x, int y, int moveCnt) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.moveCnt = moveCnt;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=H || y<0 || y>=W) return true;
        return false;
    }

    public static void calculateMinMoveCnt() {
        int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
        boolean[][][] visited = new boolean[H][W][1 << totalDustCnt];
        
        while(!dustQue.isEmpty()) {
            Node pos = dustQue.poll();
            int x = pos.x;
            int y = pos.y;

            if(room[x][y] >= '0' && room[x][y] <= '9') {
                pos.id |= (1 << (room[x][y] - '0'));
            }

            if(pos.id == (1 << totalDustCnt) - 1) {
                minMoveCnt = pos.moveCnt;
                return;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny)) continue;
                if(room[nx][ny] == 'x') continue;
                if(visited[nx][ny][pos.id]) continue;

                visited[nx][ny][pos.id] = true;
                dustQue.offer(new Node(pos.id, nx, ny, pos.moveCnt+1));
            }
        }
    }

    public static void settingInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while(true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            if(W==0 && H==0) break;
            
            char dustId = '0';
            dustQue.clear();
            minMoveCnt = -1;
            room = new char[H][W];
            totalDustCnt = 0;

            for(int i=0; i<H; i++) {
                char[] input = br.readLine().toCharArray();
                for(int j=0; j<W; j++) {
                    room[i][j] = input[j];

                    if(input[j] == 'o') {
                        dustQue.offer(new Node(0, i, j, 0));
                    } else if(input[j] == '*') {
                        room[i][j] = dustId;
                        dustId += 1;
                        totalDustCnt += 1;
                    }
                }
            }

            calculateMinMoveCnt();
            sb.append(minMoveCnt + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        dustQue = new LinkedList<>();
        sb = new StringBuilder();
        settingInput();

        System.out.println(sb.toString());
    }
}   
