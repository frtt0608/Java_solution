// 지능형기차
import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);
        
        int num=0;
        int res = 0;
        for(int i=0; i<4; i++) {
            int lift = in.nextInt();
            int ride = in.nextInt();

            num+=ride-lift;
            if(res<num)
                res=num;
        }
        System.out.println(res);
    }
}