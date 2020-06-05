// 안경이 없어!
import java.io.*;
import java.util.*;


public class S7272 {
    static String zero[], one[], left[], right[], data[], res;
    
    static int check(String data) {
        for(String str:zero) {
            if(str.equals(data)) return 0;
        }
        for(String str:one) {
            if(str.equals(data)) return 1;
        }
        return 2;
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        zero = new String[] {"C", "E", "F", "G", "H",
                            "I", "J", "K", "L", "M",
                            "N", "S", "T", "U", "V",
                            "W", "X", "Y", "Z"};
        one = new String[] {"A", "D", "O", "P", "Q", "R"};

        int T = sc.nextInt();
        sc.nextLine();
        for(int tc=1; tc<=T; tc++) {
            data = sc.nextLine().split(" ");
            left = data[0].split("");
            right = data[1].split("");
            int L = left.length;
            int R = right.length;
            res = "SAME";
            if(L!=R) res="DIFF";
            else {
                for(int i=0; i<L; i++) {
                    if(check(left[i]) != check(right[i])) {
                        res="DIFF";
                        break;
                    }
                }
            }
            System.out.println("#" + tc + " " + res);

        }
        sc.close();
    }
}