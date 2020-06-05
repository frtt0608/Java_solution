// 의석이의 세로로 말해요
import java.io.*;
import java.util.*;

public class S5356 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();
        String[] temp = new String[5];
        for(int tc=1; tc<=T; tc++) {
            for(int i=0; i<5; i++) {
                temp[i] = in.next();
            }
            System.out.print("#" + tc + " ");
            for(int y=0; y<15; y++) {
                for(int x=0; x<5; x++) {
                    if(temp[x].length() <= y)
                        continue;
                    System.out.print(temp[x].substring(y,y+1));
                }
            }
            System.out.println();
        }
    }
}