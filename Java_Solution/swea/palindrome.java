import java.io.*;
import java.util.*;

public class palindrome {
    public static String reverseString(String s){
        return (new StringBuffer(s)).reverse().toString();
    }
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int t = 10;
        String rwords;
        String cwords;
        String[][] arr = new String[8][8];

        for(int tc=0; tc<t; tc++) {
            int cnt = 0;
            int n = Integer.parseInt(in.readLine());
            for(int i=0; i<8; i++) {
                arr[i] = in.readLine().split("");
            } 

            for(int i=0; i<8; i++) {
                rwords="";
                cwords="";
                for(int j=0; j<8; j++) {
                    rwords += arr[i][j];
                    cwords += arr[j][i];
                }
                for(int k=0; k<=8-n; k++) {
                    if(rwords.substring(k, k+n).equals(reverseString(rwords.substring(k, k+n)))) {
                        cnt+=1;
                    }
                    if(cwords.substring(k, k+n).equals(reverseString(cwords.substring(k, k+n)))) {
                        cnt+=1;
                    }
                }
            }
            System.out.println("#" + (tc+1) + " " + cnt);
        }
    }
}