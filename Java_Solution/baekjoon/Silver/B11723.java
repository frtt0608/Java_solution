import java.io.*;
import java.util.*;

public class B11723 {
    static final int MAX = 20;
    static int N, S;
    static StringBuilder sb = new StringBuilder();

    public static void add(int x) {
        S |= x;
    }

    public static void remove(int x) {
        if((S & x) != 0) {
            S ^= x;
        }
    }

    public static void check(int x) {
        if((S & x) != 0) {
            sb.append(1+"\n");
        } else {
            sb.append(0+"\n");
        }
    }

    public static void toggle(int x) {
        if((S & x) != 0) {
            S ^= x;
        } else {
            S |= x;
        }
    }

    public static void all() {
        S |= ((1 << MAX+1) - 1);
    }
    
    public static void empty() {
        S = 0;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        S = 0;
        String oper;
        int x = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            oper = st.nextToken();
            if(!oper.equals("all") && !oper.equals("empty")) {
                x = Integer.parseInt(st.nextToken());
            }

            switch(oper) {
                case "add":
                    add(1 << x);
                    break;
                case "remove":
                    remove(1 << x);
                    break;
                case "check":
                    check(1 << x);
                    break;
                case "toggle":
                    toggle(1 << x);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
            }
        }
        System.out.println(sb.toString());
    }
}
