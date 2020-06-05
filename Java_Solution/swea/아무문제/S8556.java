// 북북서
import java.io.*;
import java.util.*;

public class S8556 {
  static int a,cnt;
  static int n;
  static int[] M, d;
  static Boolean flag;
  static void dir(int N) {
    if(flag==false) {
      if(N==1) a=0;
      else a=90;
    } else {
      if(N==1) {
        M[cnt] = -90;
        d[cnt] = pow(2, n);
      } else {
        M[cnt] = 90;
        d[cnt] = pow(2, n);
      }
      cnt+=1;
    }
  }
  static int pow(int n, int r) {
    int res = 1;
    for(int i=0; i<r; i++) {
      res *= n;
    }
    return res;
  }
  static int max() {
    int res = 0;
    for(int i:d) 
      if(res<i) res=i;
    if(res==0) res=1;
    return res;
  }
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      String data = in.next();
      M=new int[20]; // 분자배열
      d=new int[20]; // 분모배열
      a=0; cnt=0; n=1; flag=false;
      String[] parse_data = data.split("");
      for(int i=parse_data.length-1; i>=0; i--) {
        if(parse_data[i].equals("n")) {
          dir(1);
          if(flag==false) {
            flag=true;
            continue;
          }
          n+=1;
        } else if(parse_data[i].equals("w")) {
          dir(0);
          if(flag==false) {
            flag=true;
            continue;
          }
          n+=1;
        }
      }
      int res = max();
      int res_d = a*res;
      for(int i=0; i<20; i++) {
        if(d[i]==0) continue;
        res_d += M[i]*(res/d[i]);
      }
      while(res>=2 && res_d%2 == 0) {
        res_d/=2;
        res/=2;
      }
      System.out.print("#"+tc+" ");
      if(res_d==0) System.out.println(0);
      else if(res==1) System.out.println(res_d);
      else System.out.println(res_d+"/"+res);
    }
    in.close();
  }
}
