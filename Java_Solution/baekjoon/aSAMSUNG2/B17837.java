import java.util.*;
import java.io.*;

// 5% 틀

public class B17837 {
    static int N, K;
    static int[] dx={0,0,-1,1}, dy={1,-1,0,0};
    static int[][] map;
    static Node[] nodes;
    static ArrayList<Node>[][] chesses;
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

    public static int changeDir(int dir) {
        switch(dir) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 3;
            default: return 2;
        }
    }

    public static int findNodeIndex(int x, int y, int num) {
        int idx=0;

        for(int i=0; i<chesses[x][y].size(); i++) {
            if(chesses[x][y].get(i).num == num) {
                idx = i;
                break;
            }
        }

        return idx;
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void startChessGame() {
        Node tempNode;
        int x, y, nx, ny, idx, size;
        ArrayList<Node> moveNodes = new ArrayList<>();

        for(int i=0; i<K; i++) {
            moveNodes.clear();
            x = nodes[i].x;
            y = nodes[i].y;

            // 이동하려는 Node를 포함한 위에있는 node들 저장
            idx = findNodeIndex(x, y, nodes[i].num);
            size = chesses[x][y].size();
            for(int j=idx; j<size; j++) {
                moveNodes.add(chesses[x][y].remove(idx));
            }

            // 이동
            nx = x + dx[nodes[i].dir];
            ny = y + dy[nodes[i].dir];

            if(isWall(nx, ny) || map[nx][ny] == 2) {
                nodes[i].dir = changeDir(nodes[i].dir);
                nx = x + dx[nodes[i].dir];
                ny = y + dy[nodes[i].dir];
            }

            if(isWall(nx, ny) || map[nx][ny] == 2) {
                nx = x;
                ny = y;
                
                for(int j=0; j<moveNodes.size(); j++) {
                    tempNode = moveNodes.get(j);
                    nodes[tempNode.num].x = nx;
                    nodes[tempNode.num].y = ny;
                    chesses[nx][ny].add(tempNode);
                }
                
            } else if(map[nx][ny] == 1) {
                // 만약 빨간색인 경우, moveNodes 반대로 저장
                for(int j=moveNodes.size()-1; j>=0; j--) {
                    tempNode = moveNodes.get(j);
                    nodes[tempNode.num].x = nx;
                    nodes[tempNode.num].y = ny;
                    chesses[nx][ny].add(tempNode);
                }

            } else if(map[nx][ny] == 0) {
                // 그대로 이동하여 chesses에 저장
                for(int j=0; j<moveNodes.size(); j++) {
                    tempNode = moveNodes.get(j);
                    nodes[tempNode.num].x = nx;
                    nodes[tempNode.num].y = ny;
                    chesses[nx][ny].add(tempNode);
                }
            }

            if(chesses[nx][ny].size() >= 4) {
                isEnding = true;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        nodes = new Node[K];
        chesses = new ArrayList[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                chesses[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;

            chesses[x][y].add(new Node(i, x, y, dir));
            nodes[i] = new Node(i, x, y, dir);
        }

        int turn = 1;
        while(turn <= 1000) {
            startChessGame();
            if(isEnding)
                break;

            turn += 1;
        }
        System.out.println(isEnding ? turn:-1);
    }
}