import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<String> croa;

    static void init() {
        croa.add("c=");
        croa.add("c-");
        croa.add("dz=");
        croa.add("d-");
        croa.add("lj");
        croa.add("nj");
        croa.add("s=");
        croa.add("z=");
    }
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("");
        croa = new ArrayList<>();
        init();        

        int cnt = 0;
        StringBuilder sb;

        loop:
        for(int i=0; i<input.length; i++) {
            sb = new StringBuilder();
            for(int j=i; j<input.length; j++) {
                sb.append(input[j]);

                if(croa.contains(sb.toString())) {
                    System.out.println(sb.toString() + ", " + "있음");
                    cnt += 1;
                    i += sb.length()-1;
                    continue loop;
                } else {
                    if(sb.length() == 3) {
                        System.out.println(sb.toString() + ", " + "없음");
                        cnt += 1;
                        continue loop;
                    }
                }
            }

            if(!croa.contains(sb.toString())) {
                System.out.println(sb.toString() + ", " + "없음");
                cnt += 1;
            }
        }

        System.out.println(cnt);
    }
}