// 순열과 조합 연습
import java.io.*;
import java.util.*;


public class PCpratice {

    static void perm(int n, int r, LinkedList<Integer> perArr, int[] perChk) {
        if(perArr.size()==r) {
            for(int i:perArr) {
                System.out.print(i+" ");
            }
            return;
        }
        for(int i=0; i<n; i++) {
            if(perChk[i]==1) continue;
            perChk[i] = 1;
            perArr.add(i);
            perm(n,r,perArr, perChk);
            perChk[i] = 0;
            perArr.removeLast();
        }
    }

    static public void main(String[] args) throws IOException {
        LinkedList<Integer> list = new LinkedList<>();
        int n = 3;
        int r = 2;
        int[] chk = new int[n];
        perm(n,r,list,chk);
    }
}