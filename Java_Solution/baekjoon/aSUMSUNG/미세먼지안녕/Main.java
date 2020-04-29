import java.io.*;
import java.util.*;


class Node {
    int x;
    int y;
    int dust;

    Node(int x, int y, int dust) {
        this.x=x;
        this.y=y;
        this.dust=dust;
    }
}
/**
 * Main
 */
public class Main {
    static int R, C, T, map[][], totalDust;
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};
    static LinkedList<Integer> cleaner;

    static boolean wall(int x, int y) {
        if(x<=0 || x>=R+1 || y<=0 || y>=C+1) return true;
        return false;
    }

    static int[][] BFS(int copy_map[][]) {
        Queue<Node> que = new LinkedList<>();
        for(int i=1; i<R+1; i++) {
            for(int j=1; j<C+1; j++) {
                if(copy_map[i][j] > 0) {
                    que.offer(new Node(i,j,map[i][j]));
                }
            }
        }

        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            int dust = node.dust;
            int cnt = 0;

            for(int dir=0; dir<4; dir++) {
                int nx = x+dx[dir];
                int ny = y+dy[dir];
                if(wall(nx,ny) || map[nx][ny]==-1) continue;
                copy_map[nx][ny] += dust/5;
                cnt++;
            }
            if(cnt>0) {
                copy_map[x][y] -= dust;
                copy_map[x][y] += dust - dust/5*cnt;
            }
        }

        return copy_map;
    }

    static void airCleaner_top() {
        int cleaner_x = cleaner.get(0);
        int x = cleaner_x;
        int y = 1;

        while(true) {
            x--;
            map[x][y] = map[x-1][y];
            if(x==2) break;
        }
        map[1][y] = map[1][y+1];
        x=1;
        while(true) {
            y++;
            map[x][y] = map[x][y+1];
            if(y==C-1) break;
        }
        map[1][C] = map[2][C];
        y=C;
        while(true) {
            x++;
            map[x][y] = map[x+1][y];
            if(x==cleaner_x-1) break;
        }
        map[cleaner_x][y] = map[cleaner_x][y-1];
        x = cleaner_x;
        while(true) {
            y--;
            map[x][y] = map[x][y-1];
            if(y==3) break;
        }
        map[x][2] = 0;
    }

    static void airCleaner_down() {
        int cleaner_x = cleaner.get(1);
        int x = cleaner_x;
        int y = 1;

        while(true) {
            x++;
            map[x][y] = map[x+1][y];
            if(x==R-1) break;
        }
        map[R][y] = map[R][y+1];
        x=R;
        while(true) {
            y++;
            map[x][y] = map[x][y+1];
            if(y==C-1) break;
        }
        map[x][C] = map[x-1][C];
        y=C;
        while(true) {
            x--;
            map[x][y] = map[x-1][y];
            if(x==cleaner_x+1) break;
        }
        map[cleaner_x][y] = map[cleaner_x][y-1];
        x = cleaner_x;
        while(true) {
            y--;
            map[x][y] = map[x][y-1];
            if(y==3) break;
        }
        map[x][2] = 0;
    }

    static void checkDust() {
        for(int i=1; i<R+1; i++) {
            for(int j=1; j<C+1; j++) {
                //System.out.print(map[i][j]);
                if(map[i][j]>0) totalDust+=map[i][j];
            }
            //System.out.println();
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];
        cleaner = new LinkedList<>();

        for(int i=1; i<R+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<C+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==-1) {
                    cleaner.add(i);
                }
            }
        }

        while(T>0) {
            map = BFS(map);
            airCleaner_top();
            airCleaner_down();
            T--;
        }
        checkDust();
        System.out.println(totalDust);
    }   
}