import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int salt = Integer.parseInt(br.readLine());
        int temp = salt%5;
        int cnt = 0;

        if(salt >= 10) {
            if(temp == 0) {
                cnt = salt/5;
            } else if(temp == 1 || temp == 3) {
                cnt = salt/5 + 1;
            } else if(temp == 2 || temp == 4) {
                cnt = salt/5 + 2;
            }
        } else {
            if(salt%5 == 0) cnt = salt/5;
            else if(salt%3 == 0) cnt = salt/3;
            else if(salt == 8) cnt = 2;
            else {
                cnt = -1;
            }
        }
        System.out.println(cnt);
    }
}