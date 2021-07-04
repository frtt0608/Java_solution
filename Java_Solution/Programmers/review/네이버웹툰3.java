import java.util.*;
import java.io.*;

public class 네이버웹툰3 {
    static int result;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        compareString(s, t);
        System.out.println(result);
    }

    public static void compareString(String s, String t) {
        int[] table = new int[s.length()];
        int size = t.length();
        int i = 0;
        int j = 0;

        while(i < s.length()) {
            char sc = s.charAt(i);
            char st = t.charAt(j);
            
            if(sc == st) {
                if(i==0 || j==0) table[i] = 1;
                else table[i] = table[i-1] + 1;
                i += 1;
                j += 1;
                
            } else {
                if(j == 0) {
                    table[i] = 0;
                    i += 1;
                }

                j = 0;
            }

            if(j == size) {
                i -= 1;
                result += 1;
                
                if(i-size+1 >= 0) {
                    s = s.substring(0, i-size+1) + s.substring(i+1, s.length());    
                }
                
                if(i-size > 0) {
                    i -= size;
                    j = table[i];
                } else {
                    i = 0;
                    j = 0;
                }
            }
        }
    }
}