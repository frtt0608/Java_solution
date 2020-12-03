import java.util.*;
import java.io.*;

public class Solution {
    static int N, M, K, totalCell;
    static int[] dx={0,-1,1,0,0}, dy={0,0,0,-1,1};
    static List<Cell>[][] cellMap;

    public static int changeDir(int dir) {
        if(dir == 1) return 2;
        if(dir == 2) return 1;
        if(dir == 3) return 4;
        else return 3;
    }

    public static void moveCell() {
        Queue<Cell> cq = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int mapSize = cellMap[i][j].size();

                if(mapSize > 0) {
                    Cell c = cellMap[i][j].remove(0);

                    c.x += dx[c.dir];
                    c.y += dy[c.dir];
                    cq.offer(c);
                }
            }
        }

        while(!cq.isEmpty()) {
            Cell c = cq.poll();

            if(c.x==0 || c.y==0 || c.x==N-1 || c.y==N-1) {
                c.dir = changeDir(c.dir);
                c.count /= 2;
                // System.out.println(c.x + ", " + c.y + ": " + c.count);
            }

            if(c.count > 0)
                cellMap[c.x][c.y].add(c);
        }
    }

    public static void plusCell() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int mapSize = cellMap[i][j].size();
                
                if(mapSize >= 2) {
                    int sumCount = 0;
                    int maxCount = 0;
                    int dir = 0;

                    for(int k=0; k<mapSize; k++) {
                        Cell c = cellMap[i][j].remove(0);

                        sumCount += c.count;
                        if(maxCount < c.count) {
                            maxCount = c.count;
                            dir = c.dir;
                        }
                    }
                    // System.out.println(i + ", " + j + ": " + sumCount + ", " + dir);
                    cellMap[i][j].add(new Cell(i, j, sumCount, dir));
                }
            }
        }
    }

    public static void sumTotalCellCount() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(cellMap[i][j].size() > 0) {
                    totalCell += cellMap[i][j].remove(0).count;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            totalCell = 0;

            cellMap = new ArrayList[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    cellMap[i][j] = new ArrayList<>();
                }
            }

            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                cellMap[x][y].add(new Cell(x, y, count, dir));
            }

            while(M > 0) {
                moveCell();
                plusCell();

                M -= 1;
            }

            sumTotalCellCount();
            System.out.println("#"+tc+" "+totalCell);
        }
    }
}

class Cell {
    int x;
    int y;
    int count;
    int dir;

    Cell(int x, int y, int count, int dir) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.dir = dir;
    }
}