import java.io.*;
import java.util.*;

public class S2070 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer token;
        String answer;
        int a;
        int b;
        int t = Integer.parseInt(in.readLine());
        for(int i=0; i<t; i++) {
            String[] data = in.readLine().split(" ");
            a = Integer.parseInt(data[0]);
            b = Integer.parseInt(data[1]);
            if (a>b) {
                answer = ">";
            }
            else if (a<b) {
                answer = "<";
            }
            else {
                answer = "=";
            }
            System.out.println("#" + (i+1) + " " + answer);
        }
    }
}