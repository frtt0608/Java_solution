import java.util.*;
import java.io.*;

public class B21609 {
    static int N, M, maxRainbow, totalScore;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int[][] blockArr;
    static boolean[][] totalVisited;
    static List<Node> targetBlock;

    static class Node {
        int num;
        int x, y;

        Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public static void searchBlock() {
        totalVisited = new boolean[N][N];
        targetBlock = new LinkedList<>();
        maxRainbow = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(totalVisited[i][j]) continue;
                if(blockArr[i][j] == 0 || blockArr[i][j] == -1 || blockArr[i][j] == 7) continue;

                searchSameBlock(blockArr[i][j], i, j);
            }
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void sortTotalBlock(List<Node> totalBlock) {
        totalBlock.sort(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                if(node1.x == node2.x) {
                    return node1.y - node2.y;
                }
                return node1.x - node2.x;
            }
        });

        while(totalBlock.get(0).num == 7) {
            totalBlock.add(totalBlock.remove(0));
        }
    }

    public static void searchSameBlock(int num, int x, int y) {
        List<Node> totalBlock = new LinkedList<>();
        totalBlock.add(new Node(num, x, y));
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(num, x, y));

        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;
        int rainbow = 0;

        while(!que.isEmpty()) {
            Node cur = que.poll();
            x = cur.x;
            y = cur.y;
        
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || visited[nx][ny]) continue;

                if(blockArr[nx][ny] == num || blockArr[nx][ny] == 7) {
                    Node next = new Node(blockArr[nx][ny], nx, ny);
                    totalBlock.add(next);
                    que.offer(next);
                    visited[nx][ny] = true;

                    if(blockArr[nx][ny] == 7) {
                        rainbow += 1;
                        totalVisited[nx][ny] = true;
                    }
                }
            }
        }

        if(totalBlock.size() >= 2) {
            sortTotalBlock(totalBlock);
            updateTargetBlocks(rainbow, totalBlock);
        }
    }

    public static void changeTargetBlock(int rainbow, List<Node> totalBlock) {
        targetBlock = new LinkedList<>(totalBlock);
        maxRainbow = rainbow;
    }

    public static void updateTargetBlocks(int rainbow, List<Node> totalBlock) {
        if(targetBlock.size() == 0) {
            changeTargetBlock(rainbow, totalBlock);

        } else {
            if(totalBlock.size() > targetBlock.size()) {
                changeTargetBlock(rainbow, totalBlock);

            } else if(totalBlock.size() == targetBlock.size()) {
                if(rainbow > maxRainbow) {
                    changeTargetBlock(rainbow, totalBlock);
                
                } else if(rainbow == maxRainbow) {
                    if(totalBlock.get(0).x > targetBlock.get(0).x) {
                        changeTargetBlock(rainbow, totalBlock);

                    } else if(totalBlock.get(0).x == targetBlock.get(0).x) {
                        if(totalBlock.get(0).y > targetBlock.get(0).y) {
                            changeTargetBlock(rainbow, totalBlock);
                        }
                    }
                }
            }
        }
    }

    public static void removeTargetBlocks() {
        totalScore += targetBlock.size() * targetBlock.size();

        while(!targetBlock.isEmpty()) {
            Node target = targetBlock.remove(0);
            blockArr[target.x][target.y] = 0;
        }
    }

    public static void downBlockArr() {
        int ni;

        for(int j=0; j<N; j++) {
            for(int i=N-2; i>=0; i--) {
                if(blockArr[i][j] == -1 || blockArr[i][j] == 0) continue;

                ni = i;

                while(ni+1 < N && blockArr[ni+1][j] == 0) {
                    ni += 1;
                }

                if(ni != i) {
                    blockArr[ni][j] = blockArr[i][j];
                    blockArr[i][j] = 0;
                }
            }
        }
    }

    public static int[][] rotateBlockArr() {
        int[][] tempArr = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                tempArr[i][j] = blockArr[j][N-1-i];
            }
        }

        return tempArr;
    }

    public static void startAutoPlay() {
        while(true) {
            searchBlock();
            if(targetBlock.size() == 0) return;

            removeTargetBlocks();
            downBlockArr();
            blockArr = rotateBlockArr();
            downBlockArr();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blockArr = new int[N][N];
        totalVisited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                blockArr[i][j] = Integer.parseInt(st.nextToken());

                if(blockArr[i][j] == 0) {
                    blockArr[i][j] = 7;
                }
            }
        }

        startAutoPlay();
        System.out.println(totalScore);
    }
}