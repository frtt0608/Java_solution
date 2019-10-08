// 세상의 모든 펠린드롬
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class S4522 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();
        for(int tc=1; tc<=T; tc++) {
            String data[] = sc.next().split("");
            int L = data.length;
            String res = "Exist";
            for(int i=0; i<L/2; i++) {
                if(!data[i].equals(data[L-1-i])) {
                    if(!data[i].equals("?") && !data[L-1-i].equals("?")) {
                        res="Not exist";
                        break;
                    }
                }
            }
            System.out.println("#"+tc+" "+res);
        }
    }
}