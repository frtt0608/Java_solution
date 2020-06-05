// 규영이와 인영이의 카드게임
import java.io.*;
import java.util.*;

public class S6808 {
    static int Acard[], Bcard[], visit[], lose, win;

    static void perm(int As, int Bs, int n, int v[]) {
        if(n==9) {
            if(As > Bs) win+=1;
            else if(Bs > As) lose+=1;
            return;
        }
        int temp = Acard[n];
        for(int i=0; i<9; i++) {
            if(v[i]==1) continue;
            v[i]=1;
            if(temp > Bcard[i]) perm(As+temp+Bcard[i], Bs, n+1, v);
            else if(temp < Bcard[i]) perm(As, Bs+temp+Bcard[i], n+1, v);
            else perm(As, Bs, n+1, v);
            v[i]=0;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            Acard = new int[9];
            Bcard = new int[9];
            visit = new int[9];
            win=0; lose=0;
            for(int i=0; i<9; i++) {
                Acard[i] = sc.nextInt();
            }
            int index=0; Boolean flag;
            for(int i=1; i<=18; i++) {
                flag=true;
                for(int j:Acard) {
                    if(i==j) {flag=false; break;}
                }
                if(flag) {Bcard[index]=i; index+=1;}
            }
            perm(0, 0, 0, visit);
            System.out.println("#" + tc + " " + win + " " + lose);
        }
        sc.close();
    }
}