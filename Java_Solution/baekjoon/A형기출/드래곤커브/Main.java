import java.util.*;
import java.io.*;

public class Main {
    static final int SIZE = 201;
    static int totalSquareCnt;
    static int[] dy = {1,0,-1,0}, dx = {0,-1,0,1};
    static boolean[][] map;

    static class Node {
        int x, y;
        int dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void drawDragonCurve(int x, int y, int d, int g) {
        Stack<Integer> nodeStack = new Stack<>();
        Queue<Integer> nodeQue = new LinkedList<>();
        nodeStack.add(d);
        map[x][y] = true;

        while(g-- >= 0) {

            while(!nodeStack.isEmpty()) {
                int dir = nodeStack.pop();

                x += dx[dir];
                y += dy[dir];
                map[x][y] = true;
                nodeQue.offer(dir);
            }

            int qSize = nodeQue.size();
            for(int i=0; i<qSize; i++) {
                int dir = nodeQue.poll();

                nodeStack.add((dir+1)%4);
                nodeQue.offer(dir);
            }
        }
    }

    public static void checkSquare() {
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(map[i][j] && map[i+1][j] &&
                    map[i][j+1] && map[i+1][j+1]) {
                    totalSquareCnt += 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        map = new boolean[SIZE][SIZE];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            drawDragonCurve(x, y, d, g);
        }

        checkSquare();
        System.out.println(totalSquareCnt);
    }
}