import java.io.*;
import java.util.*;

public class S7728 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int T = Integer.parseInt(in.readLine());
        
        for(int tc=0; tc<T; tc++) {
            int cnt=0;
            List array = new ArrayList();
            String[] datas = in.readLine().split("");
            for(String data: datas) {
                if(array.contains(data)) {
                    continue;
                }
                else {
                    cnt += 1;
                    array.add(data);
                }
            }
            System.out.println("#" + (tc+1) + " " + (cnt));
        }
    }
}