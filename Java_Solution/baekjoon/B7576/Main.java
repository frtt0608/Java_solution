// 7576 토마토
import java.io.*;
import java.util.*;

public class Main {
    static int[][] tomato;
    static LinkedList<Integer[]> one;
    static int M;
    static int N;
    static int[][] v;
    static int[] dr;
    static int[] dc;
    static int res;

    static Boolean checkWall(int x, int y) { 
        if(x >= N || x<0 || y >= M || y<0 || Math.abs(tomato[x][y]) == 1) return false;
        return true;
    }
    
    static void BFS(LinkedList<Integer[]> one) {
        LinkedList<Integer[]> qu = new LinkedList<>();
        LinkedList<Integer[]> stock = new LinkedList<>();
        Boolean flag = false;
        qu.addAll(one);
        while(true) {
            stock.addAll(qu);
            flag = false;
            while(!stock.isEmpty()) {
                int r = stock.peek()[0];
                int c = stock.peek()[1];
                stock.poll();
                qu.poll();
                for(int i=0; i<4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(checkWall(nr, nc)) {
                        if(tomato[nr][nc] == 0) {
                            tomato[nr][nc] = 1;
                            qu.add(new Integer[] {nr,nc});
                            flag = true;
                        }
                    }
                }
            }
            if(flag) {
                res += 1;
            } else {
                return;
            }
        }
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

        res=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tomato[i][j]  = in.nextInt();
                if(tomato[i][j] == 1) {
                    one.add(new Integer[] {i,j});
                }
            }
        }
        BFS(one);
        Boolean error = true;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(tomato[i][j] == 0 || tomato[N-1-i][M-1-j] == 0) {
                    error = false;
                    break;
                }
            }
            if(error==false)
                break;
        }
        if(error == false) {
            System.out.println("-1");
        } else 
            System.out.println(res);
    }
}