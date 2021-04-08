import java.util.*;
import java.io.*;

public class B17472 {
    static final int MAX = 10000;
    static int N, M, islandCnt, resultCnt;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1}, parentNode;
    static int[][] map, minRoute;
    static boolean[][] numberingVisited;

    static class Node {
        int count;
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int count, int x, int y) {
            this.count = count;
            this.x = x;
            this.y = y;
        }        
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }

    public static void searchSizeOfIsland(int x, int y) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        numberingVisited[x][y] = true;

        while(!que.isEmpty()) {
            Node node = que.poll();
            x = node.x;
            y = node.y;
            map[x][y] = islandCnt;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || map[nx][ny] == 0) continue;
                if(numberingVisited[nx][ny]) continue;

                numberingVisited[nx][ny] = true;
                que.offer(new Node(nx, ny));
            }
        }
    }

    public static void numberingIsland() {
        islandCnt = 1;
        numberingVisited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(numberingVisited[i][j]) continue;
                if(map[i][j] == 1) {
                    searchSizeOfIsland(i, j);
                    islandCnt += 1;
                }
            }
        }
    }

    public static void installedBridgeMinRoute(int x, int y, int dir, int numbering) {
        int nx, ny, count = 0;
        int curNum = numbering;

        while(true) {
            if(curNum != 0 && curNum != numbering) {
                if(count >= 2) {
                    minRoute[curNum][numbering] = Math.min(minRoute[curNum][numbering], count);
                    minRoute[numbering][curNum] = minRoute[curNum][numbering];
                }
                break;
            }
        
            nx = x + dx[dir];
            ny = y + dy[dir];
            if(isWall(nx, ny)) break;
            if(map[nx][ny] == numbering) break;

            if(map[nx][ny] == 0) {
                count += 1;
            }

            x = nx;
            y = ny;
            curNum = map[x][y];
        }
    }

    public static void searchSideIndexInIsland(int x, int y, int numbering) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Node node = que.poll();
            x = node.x;
            y = node.y;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || visited[nx][ny]) continue;

                if(map[nx][ny] == 0) {
                    installedBridgeMinRoute(x, y, dir, numbering);
                    
                } else if(map[nx][ny] == numbering) {
                    visited[nx][ny] = true;
                    que.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void searchIndexIsland() {
        boolean[] visited = new boolean[islandCnt];
        minRoute = new int[islandCnt][islandCnt];
        for(int i=0; i<islandCnt; i++) {
            Arrays.fill(minRoute[i], MAX);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[map[i][j]]) continue;

                if(map[i][j] > 0) {
                    visited[map[i][j]] = true;
                    searchSideIndexInIsland(i, j, map[i][j]);
                }
            }
        }
    }

    public static void unionNode(int s, int e) {
        s = findRoot(s);
        e = findRoot(e);

        if(s != e) {
            parentNode[e] = s;
        }
    }

    public static int findRoot(int num) {
        if(parentNode[num] == num) 
            return num;
        return parentNode[num] = findRoot(parentNode[num]);
    }

    public static void getTotalMinRoute() {
        parentNode = new int[islandCnt];
        for(int i=1; i<islandCnt; i++) {
            parentNode[i] = i;
        }

        ArrayList<Node> routes = new ArrayList<>();
        for(int i=1; i<islandCnt; i++) {
            for(int j=1; j<islandCnt; j++) {
                if(minRoute[i][j] != MAX) {
                    routes.add(new Node(minRoute[i][j], i, j));
                }
            }
        }
        routes.sort((Node node1, Node node2) -> (node1.count - node2.count));
        
        int connectCnt = 0;
        for(Node node: routes) {
            int count = node.count;
            int start = findRoot(node.x);
            int end = findRoot(node.y);

            if(start == end) continue;

            unionNode(start, end);
            resultCnt += count;
            connectCnt += 1;

            if(connectCnt == islandCnt - 2) break;
        }

        if(connectCnt != islandCnt - 2) {
            resultCnt = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());      
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        numberingIsland();
        searchIndexIsland();
        getTotalMinRoute();

        System.out.println(resultCnt);
    }
}   
