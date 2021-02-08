import java.io.*;
import java.util.*;

public class B1436 {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int N = Integer.parseInt(st.nextToken());
        
        int endOfWorld = 666;
        List<Integer> numList = new ArrayList<>();

        while(numList.size() <= 10000) {
            int copyEndOfWorld = endOfWorld;
            int sixCount = 0;
            while(copyEndOfWorld > 0) {
                int num = copyEndOfWorld%10;
                if(num == 6) {
                    sixCount += 1;
                } else {
                    sixCount = 0;
                }

                if(sixCount == 3) {
                    numList.add(endOfWorld);
                    break;
                }

                copyEndOfWorld /= 10;
            }
            
            endOfWorld += 1;
        }

        Collections.sort(numList);

        System.out.println(numList.get(N-1));
    }
}
