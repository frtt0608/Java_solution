import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class reMain {
  static int N,M,map[][];
  static int Rx, Ry, Bx, By, Ox, Oy;
  static int dx[]={1,-1,0,0}, dy[]={0,0,1,-1};
  static String data[];
  static int visit[][][][];

  static void BFS(int Rx, int Ry, int Bx, int By, int c) {
    LinkedList<Integer[]> qu = new LinkedList<Integer[]>();
    qu.add(new Integer[] {Rx, Ry, Bx, By, c});
    visit[Rx][Ry][Bx][By]=1;
    int cnt = c;
    int oRx, oRy, oBx, oBy;
    int nRx, nRy, nBx, nBy;
    while(!qu.isEmpty()) {
      Integer rb[] = qu.poll();
      oRx = rb[0]; oRy=rb[1];
      oBx = rb[2]; oBy=rb[3];
      cnt = rb[4];
      if(cnt>10) { System.out.println("-1"); return; }
      if(map[oBx][oBy]==9) continue;
      if(map[oRx][oRy]==9) { System.out.println(cnt); return; }

      for(int i=0; i<4; i++) {
        Rx = oRx; Ry=oRy; Bx=oBx; By=oBy;
        while(true) { 
          nRx = Rx + dx[i];
          nRy = Ry + dy[i];
          if(map[nRx][nRy]==1) break;
          if(map[nRx][nRy]==9) {
            Rx = nRx; Ry = nRy;
            break;
          }
          Rx = nRx; Ry = nRy;
        }
        while(true) {
          nBx = Bx + dx[i];
          nBy = By + dy[i];
          if(map[nBx][nBy]==1) break;
          if(map[nBx][nBy]==9) {
            Bx = nBx; By = nBy;
            break;
          }
          Bx=nBx; By=nBy;
        }
        if(Rx==Bx && Ry==By) {
          if(map[Rx][Ry]==9 && map[Bx][By]==9) continue;
          if((Math.abs(Rx - oRx) + Math.abs(Ry-oRy)) > (Math.abs(Bx-oBx) + Math.abs(By-oBy))) {
            Rx-=dx[i]; 
            Ry-=dy[i];
          } else {
            Bx-=dx[i]; 
            By-=dy[i];
          }
        }
        if(visit[Rx][Ry][Bx][By]==1) continue;
        visit[Rx][Ry][Bx][By]=1;
        qu.add(new Integer[] {Rx, Ry, Bx, By, cnt+1});
      }
    }
    System.out.println("-1");
    return;
  }
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("reinput.txt"));
    Scanner sc = new Scanner(System.in);

    N=sc.nextInt();
    M=sc.nextInt();
    visit = new int[N][M][N][M];
    map = new int[N][M];
    for(int i=0; i<N; i++) {
      data = sc.next().split("");
      for(int j=0; j<M; j++) {
        if(data[j].equals("#")) map[i][j] = 1;
        else if(data[j].equals(".")) map[i][j] = 0;
        else if(data[j].equals("R")) {map[i][j]=0; Rx=i;Ry=j;}
        else if(data[j].equals("B")) {map[i][j]=0; Bx=i;By=j;}
        else if(data[j].equals("O")) {map[i][j]=9; Ox=i;Oy=j;}
      }
    }
    BFS(Rx,Ry,Bx,By,0);
  }
}