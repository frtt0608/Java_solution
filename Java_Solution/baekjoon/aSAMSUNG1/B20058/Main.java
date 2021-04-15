import java.util.*;
import java.io.*;

public class Main {
    static int N, Q, L, total_Ice, maxPart;
    static int[] dx={1,0,-1,0}, dy={0,1,0,-1};
    static int[][] iceAge;
    static boolean[][] visited;

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static int[][] copyMap(int[][] origin) {
        int[][] target = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                target[i][j] = origin[i][j];
            }
        }

        return target;
    }

    public static void meltIce() {
        int[][] tempIceAge = copyMap(iceAge);
        
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                if(iceAge[x][y] == 0) continue;
                int count = 0;

                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(isWall(nx, ny)) continue;
                    if(iceAge[nx][ny] == 0) continue;

                    count += 1;
                }

                if(count < 3) {
                    tempIceAge[x][y] -= 1;
                    total_Ice -= 1;
                }
            }
        }

        iceAge = copyMap(tempIceAge);
    }

    public static void rotateArr(int sx, int ex, int sy, int ey) {
        int len = ex-sx+1;
        int[][] tempIce = new int[len][len];

        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                tempIce[i][j] = iceAge[ex-j][sy+i];
            }
        }
    
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                iceAge[sx+i][sy+j] = tempIce[i][j];
            }
        }
    }

    public static void findMaxPart(int sx, int sy) {
        Queue<Node> iq = new LinkedList<>();
        iq.offer(new Node(sx, sy));
        visited[sx][sy] = true;
        int count = 1;

        while(!iq.isEmpty()) {
            Node n = iq.poll();
            int x = n.x;
            int y = n.y;

            maxPart = Math.max(maxPart, count);

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(iceAge[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                count += 1;
                iq.offer(new Node(nx, ny));
            }
        }
    }

    public static void printMap() {
        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(iceAge[i]));
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        N = (int)Math.pow(2, n);
        Q = Integer.parseInt(st.nextToken());
        total_Ice = 0;
        maxPart = 0;
        iceAge = new int[N][N];
        visited = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                iceAge[i][j] = Integer.parseInt(st.nextToken());
                total_Ice += iceAge[i][j];
            }
        }

        String[] Larr = br.readLine().split(" ");

        int q = 0;
        while(q < Q) {
            L = Integer.parseInt(Larr[q]);
            L = (int)Math.pow(2, L);
            q += 1;

            int sx = 0;
            int sy = 0;

            if(L > 1) {
                while(sx < N) {
                    sy = 0;
                    while(sy < N) {
                        rotateArr(sx, sx+L-1, sy, sy+L-1);
                        sy += L;
                    }
    
                    sx += L;
                }
            }

            meltIce();
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j] || iceAge[i][j] == 0) continue;

                findMaxPart(i, j);
            }
        }

        System.out.println(total_Ice);
        System.out.println(maxPart);
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}