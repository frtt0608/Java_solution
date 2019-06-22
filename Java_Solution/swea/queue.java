import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class queue {
    
    static int N;
    static int M;
    static int map[][];
    static boolean check[];
    static int result[];
    static int answer;
    static int min;
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        N=s.nextInt();
        M=s.nextInt();
        result = new int[N+1];
        
        map = new int[N+1][N+1];
        for(int i=0;i<M;i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            map[a][b] = map[b][a] =1;
        }
        
        for(int i=1;i<=N;i++) {
            check = new boolean[N+1];
            BFS(i);
        }
        min=Integer.MAX_VALUE;
        
        for(int i=1;i<=N;i++) {
            min=Math.min(min, result[i]);
        }
        
        for(int i=1;i<=N;i++) {
            if(min == result[i]) {
                System.out.println(i);
            return;    
            }
        }
    }
    
    static void BFS(int start) {
        Queue<XY> q = new LinkedList<>();
        check[start]=true;
        q.add(new XY(start,0));
        int res=0;
        while(!q.isEmpty()) {
            int visit=q.peek().x;
            int cnt=q.poll().dnt;
            for(int i=1;i<=N;i++) {
                if(!check[i] && map[visit][i]==1) {
                    check[i]=true;
                    q.add(new XY(i,cnt+1));
                    result[i]+=cnt+1;
                }
            }
        }  
    }

    static class XY{
        int x;
        int dnt;
        XY(int x,int dnt){
            this.x=x;
            this.dnt=dnt;
        }
    }
}