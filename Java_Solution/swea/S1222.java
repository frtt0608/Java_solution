// 계산기1
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class S1222 {
    static int N;
    static String num[];
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int N=sc.nextInt(); sc.nextLine();
        num = new String[N];
        num = sc.next().split("");
        
    }
}