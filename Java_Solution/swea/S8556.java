// 북북서
import java.io.*;
import java.util.*;

public class S8556 {
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    int T = in.nextInt();
    for(int tc=1; tc<=T; tc++) {
      String data = in.next();
      String[] parse_data = data.split("");
      System.out.println(parse_data.length);
      for(int i=0; i<parse_data.length; i++) {
        if(parse_data[i]=="n") {
          
        } else if(parse_data[i]=="w") {

        }
      }
    }
  }
}
