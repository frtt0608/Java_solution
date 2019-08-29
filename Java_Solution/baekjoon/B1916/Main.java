// 최소비용 구하기
import java.io.*;
import java.util.*;

class Bus implements Comparable<Bus>{
  int end;
  int val;

  Bus(int end, int val) {
    this.end = end;
    this.val = val;
  }

  public int getEnd() {
    return end;
  }

  public int getVal() {
    return val;
  }

  @Override
  public int compareTo(Bus bus) {
    return (this.val > bus.getVal()) ? 1:-1;
  }
}

public class Main {
  static int N;
  static int M;
  static int go;
  static int stop;

  static ArrayList<Bus>[] city;
  static int[] price;

  static void BFS(int start, int sum) {
    PriorityQueue<Bus> qu = new PriorityQueue<>();
    qu.add(new Bus(start, sum));
    int now = 0;
    int now_val = 0;

    while(!qu.isEmpty()) {
      Bus bus = qu.poll();
      now = bus.getEnd();
      now_val = bus.getVal();

      for(int i=0; i<city[now].size(); i++) {
        int current = city[now].get(i).end;
        int weigh = city[now].get(i).val;
        if(price[current] > now_val + weigh) {
          price[current] = now_val + weigh;
          qu.add(new Bus(current, price[current]));
        }

      }
    }
  }
  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    M = in.nextInt();
    city = new ArrayList[N+1];
    price = new int[N+1];

    for(int i=0; i<=N; i++) {
      city[i] = new ArrayList<Bus>();
      price[i] = Integer.MAX_VALUE;
    }

    
    for(int i=0; i<M; i++) {
      int s = in.nextInt();
      int e = in.nextInt();
      int p = in.nextInt();
      
      city[s].add(new Bus(e,p));
    }
    
    go = in.nextInt();
    stop = in.nextInt();
    
    BFS(go, 0);
    System.out.println(price[stop]);
  }
}