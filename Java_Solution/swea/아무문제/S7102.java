import java.io.*;
import java.util.*;

public class S7102 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int T = Integer.parseInt(in.readLine());
        for(int tc=0; tc<T; tc++) {
            List array = new ArrayList();
        
            String[] nm = in.readLine().split(" ");
            int N = Integer.parseInt(nm[0]);
            int M = Integer.parseInt(nm[1]);
            
            if(N==M) {
                array.add(M+1);
            }
            else if(N > M) {
                while(M < N+1) {
                    M += 1;
                    array.add(M);
                }
            }
            else {
                while(N < M+1) {
                    N += 1;
                    array.add(N);
                }
            }

            System.out.print("#" + (tc+1) + " ");
            for(int i=0; i<array.size(); i++) {
                System.out.print(array.get(i) + " ");
            }
            System.out.println();
        }
    }
}