import java.io.*;
import java.util.*;

public class B9663 {
    static int N, count;
    static boolean[] vertical, diagonal, reverseDiagonal;

    static boolean[][] chess;

    public static void setN_Queen(int row) {
        if(row >= N) {
            count += 1;
            return;
        }

        for(int col=0; col<N; col++) {
            if(checkLine(row, col)) {
                vertical[col] = true;
                diagonal[row+col] = true;
                reverseDiagonal[row-col+N-1] = true;
                chess[row][col] = true;

                setN_Queen(row+1);
                vertical[col] = false;
                diagonal[row+col] = false;
                reverseDiagonal[row-col+N-1] = false;
                chess[row][col] = false;
            }
        }
    }

    public static boolean checkLine(int row, int col) {
        if(vertical[col]) return false;
        if(diagonal[row+col]) return false;
        if(reverseDiagonal[row-col+N-1]) return false;
        
        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        vertical = new boolean[N];
        diagonal = new boolean[2*N - 1];
        reverseDiagonal = new boolean[2*N - 1];
        chess = new boolean[N][N];

        setN_Queen(0);
        System.out.println(count);
    }
}