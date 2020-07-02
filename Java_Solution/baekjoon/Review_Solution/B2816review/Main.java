// 디지털 티비

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] tv = new String[N];
        StringBuilder sb = new StringBuilder();
        int KBS1idx = 0;
        int KBS2idx = 0;

        for(int i=0; i<N; i++) {
            tv[i] = br.readLine();
            if(tv[i].equals("KBS1")) KBS1idx = i;
            if(tv[i].equals("KBS2")) KBS2idx = i;
        }

        for(int i=0; i<KBS1idx; i++) {
            sb.append(1);
        }

        for(int i=0; i<KBS1idx; i++) {
            sb.append(4);
        }

        if(KBS1idx > KBS2idx) KBS2idx++;

        for(int i=0; i<KBS2idx; i++) {
            sb.append(1);
        }

        for(int i=1; i<KBS2idx; i++) {
            sb.append(4);
        }

        System.out.println(sb.toString());
    }
}