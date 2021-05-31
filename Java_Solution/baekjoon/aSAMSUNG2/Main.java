import java.util.*;
import java.io.*;

public class Main {
    static int N, M, maxRainbow, totalScore;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] map;
    static boolean[][] totalVisited;
    static ArrayList<Node> target;
    
    static class Node {
        int num;
        int r, c;

        Node(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }
    }

    public static boolean isWall(int r, int c) {
        if(r<0 || r>=N || c<0 || c>=N) return true;
        return false;
    }

    public static void compareBlocks(ArrayList<Node> blocks, int rainbow) {
        if(target.size() == 0) {
            target = new ArrayList<>(blocks);
            maxRainbow = rainbow;
        } else {

            if(blocks.size() > target.size()) {
                target = new ArrayList<>(blocks);
                maxRainbow = rainbow;

            } else if(blocks.size() == target.size()) {
                if(rainbow > maxRainbow) {
                    target = new ArrayList<>(blocks);
                    maxRainbow = rainbow;

                } else if(rainbow == maxRainbow) {
                    if(blocks.get(0).r > target.get(0).r) {
                        target = new ArrayList<>(blocks);
                        maxRainbow = rainbow;

                    } else if(blocks.get(0).r == target.get(0).r) {
                        if(blocks.get(0).c > target.get(0).c) {
                            target = new ArrayList<>(blocks);
                            maxRainbow = rainbow;
                        }
                    }
                }
            }
        }
    }

    public static void sortBlocks(ArrayList<Node> blocks) {
        blocks.sort(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                if(node1.r > node2.r) {
                    return node1.c - node2.c;
                }

                return node1.r - node2.r;
            }
        });

        while(blocks.get(0).num == 7) {
            Node node = blocks.remove(0);
            blocks.add(node);
        }
    }

    public static void makeBlockGroup(int num, int r, int c) {
        int rainbow = 0;
        ArrayList<Node> blocks = new ArrayList<>();
        blocks.add(new Node(num, r, c));

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(num, r, c));

        boolean[][] visited = new boolean[N][N];
        visited[r][c] = true;

        while(!que.isEmpty()) {
            Node cur = que.poll();
            r = cur.r;
            c = cur.c;

            for(int dir=0; dir<4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if(isWall(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == -1) continue;

                if(map[nr][nc] == num || map[nr][nc] == 7) {
                    Node nNode = new Node(map[nr][nc], nr, nc);
                    visited[nr][nc] = true;

                    que.offer(nNode);
                    blocks.add(nNode);

                    if(map[nr][nc] == 7)
                        rainbow += 1;
                    else {
                        totalVisited[nr][nc] = true;
                    }
                }
            }
        }

        if(blocks.size() >= 2) {
            sortBlocks(blocks);
            compareBlocks(blocks, rainbow);
        }
    }

    public static void getMaximumBlocks() {
        totalVisited = new boolean[N][N];
        
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(totalVisited[r][c]) continue;
                if(map[r][c] == 0 || map[r][c] == 7 || map[r][c] == -1) continue;

                makeBlockGroup(map[r][c], r, c);
            }
        }
    }

    public static void removeBlocks() {
        totalScore += target.size()*target.size();

        while(!target.isEmpty()) {
            Node node = target.remove(0);
            map[node.r][node.c] = 0;
        }
    }

    public static void downBlocks() {

        for(int j=0; j<N; j++) {
            for(int i=N-2; i>=0; i--) {
                if(map[i][j] == -1 || map[i][j] == 0) continue;

                int cur = i;

                while(true) {
                    if(cur+1 < N && map[cur+1][j] == 0) {
                        cur += 1;
                    } else {
                        break;
                    }
                }

                if(cur != i) {
                    map[cur][j] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    }

    public static int[][] rotateBlocks() {
        int[][] tempMap = new int[N][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                tempMap[i][j] = map[j][N-1-i];
            }
        }

        return tempMap;
    }

    public static void printMap() {
        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    public static void startAutoPlay() {

        while(true) {
            getMaximumBlocks();
            if(target.size() == 0) return;
    
            removeBlocks();            
            downBlocks();
            
            map = rotateBlocks();
            downBlocks();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        target = new ArrayList<>();
        totalScore = 0;

        // 무지개 블록:7로 변경
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) {
                    map[i][j] = 7;
                }
            }
        }

        startAutoPlay();
        System.out.println(totalScore);
    }
}