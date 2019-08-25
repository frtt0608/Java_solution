// 1697 숨바꼭질
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int K;
  static int[] v;

  static int BFS(int w) {
    LinkedList<Integer[]> qu = new LinkedList();
    int time = 0;
    Integer[] temp = new Integer[] {w, time};
    int location = w;
    qu.add(temp);
    while(!qu.isEmpty() && location != K) {
      location = qu.peek()[0];
      time = qu.peek()[1];
      qu.poll();
      
      if(location == K) return time;
    
      v[location]=1;
      
      if(location-1 <= 100000 && v[location-1] != 1)
        qu.add(new Integer[] {location-1, time+1});
      if(location*2 <= 100000 && v[location*2] != 1)
        qu.add(new Integer[] {location*2, time+1});
      if(location+1 >= 0 && v[location+1] != 1)
        qu.add(new Integer[] {location+1, time+1});
    }
    return time;
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    K = in.nextInt();
    v = new int[100005];
    int res = BFS(N);
    System.out.println(res);
  }
}