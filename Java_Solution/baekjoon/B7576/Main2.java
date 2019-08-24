// 7576 토마토
import java.io.*;
import java.util.*;

public class Main2 {
    static int[][] tomato;
    static LinkedList<Integer[]> one;
    static int M;
    static int N;
    static int[] dr;
    static int[] dc;

    static class Node {
        int x;
        int y;
        int t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        public Node clone() throws CloneNotSupportedException {
            return (Node)super.clone();
        }
    }

    static Boolean checkWall(int x, int y) { 
        if(x >= N || x<0 || y >= M || y<0 || Math.abs(tomato[x][y]) == 1) return false;
        return true;
    }

    static Boolean checkZero() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(tomato[i][j] == 0 || tomato[N-1-i][M-1-j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static void BFS(LinkedList<Integer[]> one) {
        Queue<Node> qu = new LinkedList();
        int t = 0;
        for(Integer[] n:one) {
            qu.offer(new Node(n[0], n[1], n[2]));
        }
        while(!qu.isEmpty()) {
            Node node = qu.poll();
            t = node.t;
            for(int i=0; i<4; i++) {
                int nr = node.x + dr[i];
                int nc = node.y + dc[i];
                if(checkWall(nr, nc)) {
                    if(tomato[nr][nc] == 0) {
                        tomato[nr][nc] = 1;
                        qu.add(new Node(nr, nc, t+1));
                    }
                }
            }
        }

        if(!checkZero()) {
            System.out.println("-1");
        } else 
            System.out.println(t);
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        dr = new int[] {0,0,1,-1};
        dc = new int[] {1,-1,0,0};

        M = in.nextInt();
        N = in.nextInt();
        
        tomato = new int[N][M];
        one = new LinkedList();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tomato[i][j]  = in.nextInt();
                if(tomato[i][j] == 1) {
                    one.add(new Integer[] {i,j,0});
                }
            }
        }

        BFS(one);
    }
}