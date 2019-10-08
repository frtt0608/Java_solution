// 진기의 최고급 붕어빵
// 0초부터 M초까지 K개 붕어빵 생산
import java.io.*;
import java.util.*;

public class S1860 {
    static int table[];
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
    
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            table = new int[N]; 
            int time = 0, cnt=0, index=0; int max=0;
            String res = "Impossible";
            for(int i=0; i<N; i++) {
                table[i] = sc.nextInt();
                if(max<table[i]) max=table[i];
            }
            Arrays.sort(table);
            while(true) {
                if(time%M==0 && time!=0) {cnt+=K;}
                if(table[index]==time) {
                    for(int i=index; i<table.length; i++) {
                        if(time==table[i]) {
                            index += 1;
                            cnt -= 1;
                        }
                        else {break;}
                    }
                }
                if(cnt<0) {
                    res="Impossible";
                    break;
                }
                if(time==max && cnt>=0) {
                    res="Possible";
                    break;
                }
                time+=1;
            }
            System.out.println("#" + tc + " " + res);
        }   
        sc.close();
    }
}
