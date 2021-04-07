import java.io.*;
import java.util.*;

public class B17472 {
    static final int MAX = 1000000;
    static int N, M, numbering, minRoute;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1}, parent;
    static int[][] map, isNumbering, islandVisited;
    static boolean[] mVisited;

    static class Node {
        int num, x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public static void searchOneValue() {
        numbering = 1;
        isNumbering = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isNumbering[i][j] == 1) continue;
                if(map[i][j] == 1) {
                    numberingIsland(i, j, numbering);
                    numbering += 1;
                }
            }
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=M) return true;
        return false;
    }
 
    public static void numberingIsland(int x, int y, int numbering) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        isNumbering[x][y] = 1;

        while(!que.isEmpty()) {
            Node node = que.poll();
            map[node.x][node.y] = numbering;

            for(int dir=0; dir<4; dir++) {
                int nx = node.x + dx[dir];
                int ny = node.y + dy[dir];

                if(isWall(nx, ny) || visited[nx][ny]) continue;
                if(map[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                isNumbering[nx][ny] = 1;

                que.offer(new Node(nx, ny));
            }
        }
    }

    public static void installedBridge(Node curNode, int numbering, int count, int dir) {
        int curNum = curNode.num;
        int x = curNode.x;
        int y = curNode.y;
        int nx, ny;

        while(true) {
            if(curNum != numbering) {
                if(count >= 2) {
                    // System.out.println(numbering + " >> " + curNum + " : " + count);
                    islandVisited[numbering][curNum] = Math.min(islandVisited[numbering][curNum], count);
                    islandVisited[curNum][numbering] = islandVisited[numbering][curNum];
                }
    
                break;
            }

            nx = x + dx[dir];
            ny = y + dy[dir];
            if(isWall(nx, ny)) break;
            if(map[nx][ny] == numbering) break;

            if(map[nx][ny] == 0) {
                count += 1;

            } else if(map[nx][ny] != numbering) {
                curNum = map[nx][ny];
            }

            x = nx;
            y = ny;
        }
    }

    public static void searchSideIsland(int x, int y, int num) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;

        while(!que.isEmpty()) {
            Node node = que.poll();

            for(int dir=0; dir<4; dir++) {
                int nx = node.x + dx[dir];
                int ny = node.y + dy[dir];
    
                if(isWall(nx, ny) || visited[nx][ny]) continue;
                
                if(map[nx][ny] == 0) {
                    Node curNode = new Node(num, nx, ny);
                    installedBridge(curNode, num, 1, dir);
                    
                } else if(map[nx][ny] == num) {
                    visited[nx][ny] = true;
                    que.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void calculateMinRoute() {
        mVisited = new boolean[numbering];
        mVisited[0] = true;
        for(int i=1; i<numbering; i++) {
            Arrays.fill(islandVisited[i], MAX);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(mVisited[map[i][j]]) continue;

                if(map[i][j] > 0) {
                    mVisited[map[i][j]] = true;
                    searchSideIsland(i, j, map[i][j]);
                }
            }
        }
    }

    public static void union(int numA, int numB) {
        numA = find(numA);
        numB = find(numB);

        if(numA != numB) {
            parent[numB] = numA;
        }
    }
    
    public static int find(int num) {
        if(parent[num] == num) {
            return parent[num];
        } else {
            return parent[num] = find(parent[num]);
        }
    }

    public static void kruskalMST() {
        parent = new int[numbering];
        for(int i=0; i<numbering; i++) {
            parent[i] = i;
        }

        ArrayList<int[]> bridge = new ArrayList<>();
        for(int i=1; i<numbering; i++) {
            for(int j=1; j<numbering; j++) {
                if(islandVisited[i][j] != MAX) {
                    bridge.add(new int[] {i, j, islandVisited[i][j]});
                }
            }
        }

        bridge.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[2] - arr2[2];
            }
        });

        int connectCount = 0;
        for(int i=0; i<bridge.size(); i++) {
            int s = bridge.get(i)[0];
            int e = bridge.get(i)[1];
            int len = bridge.get(i)[2];

            s = find(s);
            e = find(e);
            if(s == e) continue;

            union(s, e);
            minRoute += len;
            connectCount += 1;

            if(connectCount == numbering - 2) break;
        }

        if(connectCount != numbering-2) {
            minRoute = -1;
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

        searchOneValue();
        islandVisited = new int[numbering][numbering];
        
        calculateMinRoute();
        kruskalMST();
        System.out.println(minRoute);
    }
}
