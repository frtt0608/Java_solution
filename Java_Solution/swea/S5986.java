// 새샘이와 세 소수
import java.io.*;
import java.util.*;

public class S5986 {
    static int N, L;
    static int v[], table[];
    static LinkedList<Integer> prime = new LinkedList<>();

    static void primeNumber() {
        v[0]=1; v[1]=1; v[2]=1; v[3]=1;
        prime.add(new Integer(2));
        prime.add(new Integer(3));
        Boolean flag;
        for(int i=0; i<1000; i++) {
            if(i%2==0 || i%3==0) {
                v[i] = 1;
            }
        }

        for(int i=4; i<1000; i++) {
            flag = false;
            if(v[i]==1) continue;
            for(int j=2; j<i; j++) {
                if(i%j==0) {
                    flag = true;
                    break;
                }
            }
            if(flag==false) {
                prime.add(i);
                int cnt = i;
                while(cnt<1000) {
                    v[cnt] = 1;
                    cnt+=i;
                }
            }
        }
    }

    static void comb(int index, int total, int cnt) {
        if(total > 999) return;
        if(cnt==3) {
            table[total]+=1;
            return;
        }
        for(int i=index; i<prime.size(); i++) {
            comb(i, total+prime.get(i), cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        v=new int[1000];
        table = new int[1000];
        primeNumber();
        comb(0,0,0);
        int T=sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            System.out.println("#" + tc + " " + table[N]);
        }
    }
}