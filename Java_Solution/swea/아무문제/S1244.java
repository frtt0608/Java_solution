// 최대상금
import java.io.*;
import java.util.*;

public class S1244 {
  static int t, res, L;
  static String data[];

  static int StringtoInt(String[] data) {
    int temp = 0;
    int ten=1;
    for(int i=data.length-1; i>=0; i--) {
      temp += Integer.parseInt(data[i])*ten;
      ten*=10;
    }
    return temp;
  }

  static String[] copy(String[] combArray) {
    String[] res = new String[L];
    for(int i=0; i<L; i++) {
      res[i] = combArray[i];
    }
    return res;
  }

  static void comb(int index, String[] combArray, int cnt) {
    if(cnt>=t) {
      int temp = StringtoInt(combArray);
      if (res<temp) res=temp;
      return;
    }
    if(index >= L-1) {
      if(cnt < t) {
        String data[] = copy(combArray);
        while(cnt<t) {
          cnt += 1;
          String str = data[L-1];
          data[L-1] = data[L-2];
          data[L-2] = str;
        }
        int temp = StringtoInt(data);
        if(res<temp) res=temp; 
      }
      return;
    }
    for(int i=index+1; i<data.length; i++) {
      String str = combArray[index];
      combArray[index] = combArray[i];
      combArray[i] = str;
      comb(index+1, combArray, cnt+1);
      combArray[i] = combArray[index];
      combArray[index] = str;
      comb(index+1, combArray, cnt);
    }
  }

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for(int tc=1; tc<=T; tc++) {
      data = sc.next().split("");
      L = data.length;
      t = sc.nextInt();
      res=StringtoInt(data);
      comb(0,data,0);
      System.out.println("#" + tc + " " + res);
    }
  }
}