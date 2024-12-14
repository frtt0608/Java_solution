import java.io.*;
import java.util.*;

public class B17413 {


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        System.out.println(reverseString(input));
    }

    public static String reverseString(String input) {
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        
        boolean tagFlag = false;
        int len = input.length();
        for(int i=0; i<len; i++) {
            char ch = input.charAt(i);
            if(ch == '<') {
                tagFlag = true;
                result.append(sb.reverse());
                sb = new StringBuilder();
                sb.append(ch);

            } else if(ch == '>') {
                tagFlag = false;
                sb.append(ch);
                result.append(sb.toString());
                sb = new StringBuilder();

            } else if(ch == ' ') {
                if(tagFlag) sb.append(ch);
                else {
                    result.append(sb.reverse()).append(ch);
                    sb = new StringBuilder();
                }
            } else {
                sb.append(ch);
                if(i == len-1) {
                    result.append(sb.reverse());
                }
            }
        }
        
        return result.toString();
    }
}
