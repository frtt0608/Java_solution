import java.util.*;
import java.io.*;


class Node {
    int x;
    int y;
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
/**
 * Main
 */
public class B16234 {
    static int N,L,R,map[][], visited[][], moveCnt;
    static int dx[] = {1,-1,0,0}, dy[]={0,0,1,-1};
    static int unitedPeople[][], unitedCnt;

    static boolean movePeople() {
        int allPeople = 0;
        boolean movingFlag = false;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j]==1) continue;

                allPeople = BFS(i,j);
                if(unitedCnt >= 1) {
                    updateMap(allPeople);
                    movingFlag = true;
                }
            }
        }
        return movingFlag;
    }

    static int BFS(int r, int c) {
        visited[r][c] = 1;
        int allPeople = map[r][c];
        int idx = 0;

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(r,c));
        updateUnitedPeople(idx, r, c);

        while(!que.isEmpty()) {
            Node node = que.poll();

            for(int i=0; i<4; i++) {
                int nx = node.x+dx[i];
                int ny = node.y+dy[i];
                if(wall(nx,ny) || visited[nx][ny]==1) continue;

                int temp = Math.abs(map[node.x][node.y] - map[nx][ny]);
                if(temp>=L && temp<=R) {
                    visited[nx][ny] = 1;
                    allPeople += map[nx][ny];
                    updateUnitedPeople(++idx, nx, ny);
                    que.offer(new Node(nx,ny));
                }
            }
        }
        unitedCnt = idx;
        return allPeople;
    }

    static boolean wall(int x, int y) {
        if(x>=N || x<0 || y>=N || y<0) return true;
        return false;
    }

    static void updateMap(int allPeople) {
        int afterMove = allPeople/(unitedCnt+1);

        for(int i=0; i<=unitedCnt; i++) {
            map[unitedPeople[i][0]][unitedPeople[i][1]] = afterMove;
        }
    }

    static void updateUnitedPeople(int idx, int x, int y) {
        unitedPeople[idx][0] = x;
        unitedPeople[idx][1] = y;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        map = new int[N][N];
        boolean flag = true;
        unitedPeople = new int[2500][2];
        unitedCnt = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while(flag) {
            visited = new int[N][N];
            flag = movePeople();
            if(flag) moveCnt++;
        }

        System.out.println(moveCnt);
    }
}