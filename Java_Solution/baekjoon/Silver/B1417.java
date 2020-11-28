import java.io.*;
import java.util.*;

public class B1417 {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int target =  Integer.parseInt(br.readLine());
        int answer = 0;

        if(N > 1) {
            int[] votes = new int[N-1];
            for(int i=0; i<N-1; i++)
                votes[i] = Integer.parseInt(br.readLine());
    
            if(N > 1) {
                while(true) {
                    Arrays.sort(votes);
                    if(target > votes[N-2])
                        break;
                    else {
                        votes[N-2] -= 1;
                        target += 1;
                        answer += 1;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}