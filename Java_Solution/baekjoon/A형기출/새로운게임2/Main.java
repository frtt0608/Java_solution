import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] dx = {0,0,0,-1,1}, dy = {0,1,-1,0,0};
    static int[][] chess;
    static Node[] nodes;
    static List<Integer>[][] nodePos;
    static List<Integer> movingNodes;
    static boolean isEnding;

    static class Node {
        int num;
        int x, y;
        int dir;

        Node(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static boolean isWall(int x, int y) {
        return x<=0 || x>N || y<=0 || y>N;
    }

    public static int changeDir(int dir) {
        if(dir == 1) return 2;
        else if(dir == 2) return 1;
        else if(dir == 3) return 4;
        return 3;
    }

    public static int getNodeIndex(int num, int x, int y) {
        int idx = 0;

        for(int i=0; i<nodePos[x][y].size(); i++) {
            if(nodePos[x][y].get(i) == num) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static void getMovingNodes(int idx, int x, int y) {
        int size = nodePos[x][y].size();
        for(int i=idx; i<size; i++) {
            int num = nodePos[x][y].remove(idx);
            movingNodes.add(num);
        }
    }

    public static void movingRedOrWhiteBlock(int type, int nx, int ny) {
        int node;

        if(type == 0) {
            for(int i=0; i<movingNodes.size(); i++) {
                node = movingNodes.get(i);
                nodes[node].x = nx;
                nodes[node].y = ny;
                nodePos[nx][ny].add(node);
            }
        } else {
            for(int i=movingNodes.size()-1; i>=0; i--) {
                node = movingNodes.get(i);
                nodes[node].x = nx;
                nodes[node].y = ny;
                nodePos[nx][ny].add(node);
            }
        }
    }

    public static void movingBlueBlock(int nx, int ny) {
        int node;

        for(int i=0; i<movingNodes.size(); i++) {
            node = movingNodes.get(i);
            nodes[node].x = nx;
            nodes[node].y = ny;
            nodePos[nx][ny].add(node);
        }
    }

    public static void moveInOrderNodes() {
        int x, y, nx, ny, dir, idx;
        movingNodes = new ArrayList<>();

        for(int num=1; num<K+1; num++) {
            movingNodes.clear();
            x = nodes[num].x;
            y = nodes[num].y;
            dir = nodes[num].dir;

            nx = x + dx[dir];
            ny = y + dy[dir];

            if(isWall(nx, ny) || chess[nx][ny] == 2) {
                nodes[num].dir = changeDir(dir);
                nx = x + dx[nodes[num].dir];
                ny = y + dy[nodes[num].dir];

                if(isWall(nx, ny) || chess[nx][ny] == 2) {
                    continue;
                }
            }

            idx = getNodeIndex(num, x, y);
            getMovingNodes(idx, x, y);
            movingRedOrWhiteBlock(chess[nx][ny], nx, ny);
            
            if(nodePos[nx][ny].size() >= 4) {
                isEnding = true;
                return;
            }
        }
    }

    public static void initNodePos() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                nodePos[i][j] = new ArrayList<>();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int turn = 0;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        chess = new int[N+1][N+1];
        nodes = new Node[K+1];
        nodePos = new ArrayList[N+1][N+1];
        isEnding = false;

        initNodePos();
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int num=1; num<K+1; num++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            nodes[num] = new Node(num, x, y, dir);
            nodePos[x][y].add(num);
        }

        while(true) {
            turn += 1;
            moveInOrderNodes();
            
            if(isEnding || turn > 1000) break;
        }

        System.out.println(isEnding ? turn:-1);
    }
}
