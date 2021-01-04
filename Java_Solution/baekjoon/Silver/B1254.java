import java.io.*;
import java.util.*;

public class B1254 {

    public static boolean checkPelindrome(String data) {
        for(int i=0; i<data.length()/2; i++) {
            if(data.charAt(i) != data.charAt(data.length()-i-1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String plain = br.readLine();
        int idx = 0;
        int answer = plain.length();

        while(true) {

            String data = plain.substring(idx, plain.length());
            if(checkPelindrome(data)) {
                answer += idx;
                break;
            }
            
            idx++;

            if(idx >= plain.length()) {
                answer += idx;
                break;
            }
        }

        System.out.println(answer);
    }
}
