import java.util.*;
import java.io.*;

// 5% 틀

public class B17825 {
    static int maxScore;
    static int[] nums;
    static Node[] nodes;
    static int[][] map = {{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,100},
                            {10,13,16,19,25},
                            {20,22,24,25},
                            {30,28,27,26,25},
                            {25,30,35,40}};
    static int[][] visited;

    static class Node {
        int x, y;
        boolean isFinish = false;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void getTotalCase(int idx, int score) {
        if(idx == 10) {
            // System.out.println(score);
            maxScore = Math.max(maxScore, score);
            return;
        }

        for(int i=0; i<4; i++) {
            Node node = nodes[i];
            if(node.isFinish) continue;

            int num = 0;
            int ox = node.x;
            int oy = node.y;
            int x = node.x;
            int y = node.y;

            while(num < nums[idx]) {
                num += 1;
                y += 1;

                if(map[x][y] == 40) {
                    x = 0;
                    y = 20;
                } else if(map[x][y] == 25) {
                    x = 4;
                    y = 0;
                } else if(map[x][y] == 100) {
                    node.isFinish = true;
                    break;
                }
            }

            if(map[x][y] == 10) {
                x = 1;
                y = 0;
            } else if(map[x][y] == 20) {
                x = 2;
                y = 0;
            } else if(map[x][y] == 30 && x == 0) {
                x = 3;
                y = 0;
            }

            if(node.isFinish) {
                visited[ox][oy] = 0;
                getTotalCase(idx+1, score);
                visited[ox][oy] = 1;
                node.isFinish = false;
            } else {
                if(visited[x][y] == 0) {
                    // System.out.println(i+"번째 말>> "+x +", "+ y+": " + map[x][y]);
                    visited[ox][oy] = 0;
                    visited[x][y] = 1;
                    node.x = x;
                    node.y = y;
                    
                    // maxScore = Math.max(maxScore, score+map[x][y]);
                    getTotalCase(idx+1, score+map[x][y]);
                    visited[ox][oy] = 1;
                    visited[x][y] = 0;
                    node.x = ox;
                    node.y = oy;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N = Integer.parseInt(st.nextToken());
        nums = new int[10];
        nodes = new Node[4];
        visited = new int[5][22];

        for(int i=0; i<4; i++) {
            nodes[i] = new Node(0, 0);
        }

        for(int i=0; i<10; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        getTotalCase(0, 0);
        System.out.println(maxScore);
    }
}