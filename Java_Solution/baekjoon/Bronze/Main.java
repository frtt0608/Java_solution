import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb;
    static int mock;

    static public void addRemainNumber(String target, int index) {
        for(int i=index; i>=0; i--) {
            int num = target.charAt(i) - '0';
            int addResult = (num + mock) % 10;
            sb.append(Integer.toString(addResult));
            mock = (num + mock) / 10;
        }

        if(mock == 1) {
            sb.append("1");
        }
    
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();
        int Ai, Bi;

        mock = 0;
        sb = new StringBuilder();

        for(Ai=A.length()-1, Bi=B.length()-1; Ai>=0 && Bi>=0; Ai--, Bi--) {
            int Anum = A.charAt(Ai) - '0';
            int Bnum = B.charAt(Bi) - '0';
            int addResult = (Anum + Bnum + mock) % 10;
            sb.append(Integer.toString(addResult));
            mock = (Anum + Bnum + mock) / 10;
        }

        if(Ai < 0) {
            addRemainNumber(B, Bi);
        } else if(Bi < 0) {
            addRemainNumber(A, Ai);
        }

        System.out.println(sb.reverse().toString());
    }
}