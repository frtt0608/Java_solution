// 디지털 티비

import java.io.*;

public class Main {
    static int idx, KBS1idx, KBS2idx;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] channels = new String[N];
        idx = 0;
        KBS1idx = 0;
        KBS2idx = 0;
        sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            channels[i] = br.readLine();
            if(channels[i].equals("KBS1")) KBS1idx = i;
            if(channels[i].equals("KBS2")) KBS2idx = i;
        }

        for(int i=0; i<KBS1idx; i++) {
            sb.append(1);
        }
        idx = KBS1idx;

        while(idx != 0) {
            sb.append(4);
            idx -=1;
        }

        if(KBS1idx > KBS2idx) {
            KBS2idx += 1;
        }

        for(int i=0; i<KBS2idx; i++) {
            sb.append(1);
        }
        idx = KBS2idx;

        while(idx != 1) {
            sb.append(4);
            idx -=1;
        }

        System.out.println(sb.toString());
    }
}