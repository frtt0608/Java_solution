import java.io.*;
import java.util.*;

public class Main {
    static int w, h, sx, sy, minCount;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static char[][] room;
    static boolean cannotClean;
    static ArrayList<Node> dusts;
    static boolean[] caseVisited;
    static int[][] distance;


    static class Node {
        int x, y;
        int time, cleanCount;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=h || y<0 || y>=w) return true;
        return false;
    }

    public static void calculateMinMoveCnt(int[] cases) {
        int x = sx;
        int y = sy;
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];

        boolean cleanFlag = false;
        int totalCount = 0;
        int casesIndex = 0;
        while(casesIndex < cases.length) {
            cleanFlag = false;

            que.clear();
            que.offer(new Node(x, y, 0));
            visited = new boolean[h][w];
            visited[x][y] = true;
            Node target = dusts.get(cases[casesIndex]);

            while(!que.isEmpty()) {
                Node node = que.poll();
                x = node.x;
                y = node.y;

                if(x == target.x &&
                    y == target.y) {
                        totalCount += node.time;
                        cleanFlag = true;
                        break;
                }

                for(int dir=0; dir<4; dir++ ) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(isWall(nx, ny)) continue;
                    if(room[nx][ny] == 'x') continue;
                    if(visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    que.offer(new Node(nx, ny, node.time+1));
                }
            }

            if(!cleanFlag) {
                cannotClean = true;
                return;
            }

            x = target.x;
            y = target.y;
            casesIndex += 1;
        }

        minCount = Math.min(minCount, totalCount);
    }

    public static void findTotalCase(int idx, int[] cases) {
        if(idx == dusts.size()) {
            calculateMinMoveCnt(cases);
            return;
        }

        if(cannotClean) return;

        for(int i=0; i<dusts.size(); i++) {
            if(caseVisited[i]) continue;

            caseVisited[i] = true;
            cases[idx] = i;
            findTotalCase(idx+1, cases);
            caseVisited[i] = false;
        }
    }

    public static void calDistance() {
        
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
            room = new char[h][w];
            dusts = new ArrayList<>();
            minCount = Integer.MAX_VALUE;
            cannotClean = false;
            
            for(int i=0; i<h; i++) {
                char[] input = br.readLine().toCharArray();
                for(int j=0; j<w; j++) {
                    room[i][j] = input[j];
                    if(room[i][j] == 'o') {
                        sx = i;
                        sy = j;
                    } else if(room[i][j] == '*') {
                        dusts.add(new Node(i, j));
                    }
                }
            }
            caseVisited = new boolean[dusts.size()];
            findTotalCase(0, new int[dusts.size()]);

            if(minCount == Integer.MAX_VALUE)
                minCount = -1;

            sb.append(minCount+"\n");
        }

        System.out.println(sb.toString());
    }
}