import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Solution {
    static String data;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            data = sc.next();
            
        }
    }
}