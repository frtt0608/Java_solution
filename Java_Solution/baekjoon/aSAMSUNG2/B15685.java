import java.util.*;
import java.io.*;

public class B15685 {
    static final int size = 201;
    static int N, squareCount, x, y;
    static int[] dx={1,0,-1,0}, dy={0,-1,0,1};
    static boolean[][] map;
    static ArrayList<Integer> dirs;

    public static void getSquareCount() {
        for(int i=0; i<size-1; i++) {
            for(int j=0; j<size-1; j++) {
                if(map[i][j] && map[i+1][j] &&
                    map[i][j+1] && map[i+1][j+1]) {
                        squareCount += 1;
                }
            }
        }
    }

    public static void rotateDragonCurve(int dir, int g) {
        if(g != 0) {
            dir = (dir+1)%4;
        }

        x += dx[dir];
        y += dy[dir];
        dirs.add(dir);
        map[y][x] = true;
    }

    public static void searchDirList() {
        for(int i=dirs.size()-1; i>=0; i--) {
            rotateDragonCurve(dirs.get(i), 1);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new boolean[size][size];
        dirs = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dirs.clear();

            map[y][x] = true;
            rotateDragonCurve(dir, 0);
            while(g > 0) {
                searchDirList();
                g--;
            }
        }

        getSquareCount();
        System.out.println(squareCount);
    }
}