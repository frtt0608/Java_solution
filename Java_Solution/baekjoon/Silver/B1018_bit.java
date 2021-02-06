import java.io.*;
import java.util.*;

public class B1018_bit {
    static int minCount;
    static int[][] chess;
    static int[][] firstBlack = {{0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0}};

    static int[][] firstWhite = {{1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1}};

    static public void paintfirstBlack(int x, int y) {
        int count = 0;
        int[][] newChess = new int[8][8];

        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                newChess[i-x][j-y] = chess[i][j] ^ firstBlack[i-x][j-y];
                if(newChess[i-x][j-y] == 1) {
                    count += 1;
                }
            }
        }

        minCount = Math.min(minCount, count);
    }

    static public void paintfirstWhite(int x, int y) {
        int count = 0;
        int[][] newChess = new int[8][8];

        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                newChess[i-x][j-y] = chess[i][j] ^ firstWhite[i-x][j-y];
                if(newChess[i-x][j-y] == 1) {
                    count += 1;
                }
            }
        }

        minCount = Math.min(minCount, count);
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        chess = new int[N][M];
        minCount = 25000;

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++) {
                if(input[j].equals("B")) {
                    chess[i][j] = 0;
                } else {
                    chess[i][j] = 1;
                }
            }
        }

        for(int i=0; i<=N-8; i++) {
            for(int j=0; j<=M-8; j++) {
                paintfirstBlack(i, j);
                paintfirstWhite(i, j);
            }
        }

        System.out.println(minCount);
    }
}
