import java.io.*;
import java.util.*;

public class B1002 {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            // x, y중심으로 갖고 반지름이 r인 원
            int res = 1;
            double diff = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));

            if(diff == 0) {
                if(r1 == r2)
                    res = -1;
                else {
                    res = 0;
                }
            } else {
                if(diff < Math.abs(r1-r2) || diff > r1+r2) {
                    res = 0;
                } else if(diff == Math.abs(r1-r2) || diff == r1+r2) {
                    res = 1;
                } else if(diff < r1+r2) {
                    res = 2;
                }
            }

            System.out.println(res);
        }
    }
}
