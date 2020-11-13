import java.util.*;

public class 경주로건설 {

    static class Solution {
        static int N;
        static int[][][] price;
        static Queue<Node> que;
        
        public static void initPriceAndQueue(int[][] board) {
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++)
                Arrays.fill(price[i][j], 999999);
            
            price[0][0][0] = 0;
            price[0][0][1] = 0;
            price[0][0][2] = 0;
            price[0][0][3] = 0;
            
            if(board[1][0] == 0) {
                price[1][0][0] = 100;
                que.offer(new Node(1,0,0));
            }
            
            if(board[0][1] == 0) {
                price[0][1][3] = 100;
                que.offer(new Node(0,1,3));
            }
        }
        
        public static boolean isWall(int x, int y) {
            if(x>=N || x<0 || y>=N || y<0) return true;
            return false;
        }
        
        public static void findMinPrice(int[][] board) {
            int[] dx = {1,0,-1,0}, dy = {0,-1,0,1};
            
            while(!que.isEmpty()) {
                Node node = que.poll();
                int x = node.x;
                int y = node.y;
                int dir = node.dir;
                
                for(int ndir=0; ndir<4; ndir++) {
                    int nx = x + dx[ndir];
                    int ny = y + dy[ndir];
                    
                    if(isWall(nx, ny) || board[nx][ny] == 1) continue;
                    if(dir == ndir) {
                        if(price[nx][ny][dir] >= price[x][y][dir] + 100) {
                            price[nx][ny][dir] = price[x][y][dir] + 100;
                            que.offer(new Node(nx, ny, ndir));
                        }
                    } else {
                        if(price[nx][ny][ndir] >= price[x][y][dir] + 600) {
                            price[nx][ny][ndir] = price[x][y][dir] + 600;
                            que.offer(new Node(nx, ny, ndir));
                        }
                    }
                }
            }
        }
        
        public static int solution(int[][] board) {
            int answer = 999999;
            N = board.length;
            price = new int[N][N][4];
            que = new LinkedList<>();
            initPriceAndQueue(board);
            findMinPrice(board);
            
            for(int i=0; i<4; i++) {
                answer = Math.min(answer, price[N-1][N-1][i]);
            }
            
            return answer;
        }
        
        public static void main(String[] args) {
            int[][] board = {};
            solution(board);
        }
    }

    static class Node {
        int x;
        int y;
        int dir;
        
        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
