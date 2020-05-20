import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int minPackage = 1000;
        int minPiece = 1000;
        int minPrice = 0;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int pacakgePrice = Integer.parseInt(st.nextToken());
            int piecePrice = Integer.parseInt(st.nextToken());
            minPackage = Math.min(minPackage, pacakgePrice);
            minPiece = Math.min(minPiece, piecePrice);
        }
        
        if(N >= 6) {
            if(N*minPiece < minPackage*(int)(N/6)) {
                minPrice = N * minPiece;
                N = 0;
            } else {
                minPrice = minPackage * (int)(N/6);
                N %= 6;
            }
        }

        if(N>0) {
            if(N*minPiece > minPackage) {
                minPrice += minPackage;
            } else {
                minPrice += minPiece * N;
            }
        }

        System.out.println(minPrice);
    }
}