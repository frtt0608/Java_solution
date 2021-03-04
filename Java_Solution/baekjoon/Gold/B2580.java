import java.io.*;
import java.util.*;

public class B2580 {
    static int N, count;
    static int[][] sdoku;
    static boolean[][][] parts;
    static boolean[][] vertical, horizontal;
    static boolean totalFlag;
    static ArrayList<Node> nodes;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void printSdoku() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(sdoku[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void fillSdoku(int idx) {
        if(idx >= nodes.size()) {
            totalFlag = true;
            printSdoku();
            return;
        }

        int x = nodes.get(idx).x;
        int y = nodes.get(idx).y;

        for(int i=1; i<10; i++) {
            if(totalFlag) return;

            if(parts[x/3][y/3][i]) continue;
            if(vertical[y][i]) continue;
            if(horizontal[x][i]) continue;

            parts[x/3][y/3][i] = true;
            vertical[y][i] = true;
            horizontal[x][i] = true;
            sdoku[x][y] = i;

            fillSdoku(idx+1);

            parts[x/3][y/3][i] = false;
            vertical[y][i] = false;
            horizontal[x][i] = false;
            sdoku[x][y] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = 9;
        sdoku = new int[N][N];
        parts = new boolean[3][3][N+1];
        vertical = new boolean[N][N+1];
        horizontal = new boolean[N][N+1];
        totalFlag = false;

        nodes = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                sdoku[i][j] = Integer.parseInt(st.nextToken());
                if(sdoku[i][j] == 0) {
                    nodes.add(new Node(i, j));
                } else {
                    parts[i/3][j/3][sdoku[i][j]] = true;
                    vertical[j][sdoku[i][j]] = true;
                    horizontal[i][sdoku[i][j]] = true;
                }
            }
        }

        fillSdoku(0);
    }
}