// NN크기 땅, 땅에는 나라가 하나씩 존재
// r행 c열에 있는 나라에는 A[r][c]명이 살고 있음
// 인구이동은 다음과 같이 진행
// 1. 국경선 공유 두 국가 인구 차이 L이상 R이하면 하루동안 국경선 개방
// 2. 국경선 모두 개방시 인구이동 시작
// 3. 인접한 칸만을 이용해 이동할수 있으면, 그 나라는 연합
// 4. 연합을 이루는 각 칸의 인구수는 (연합의 인구수)/칸의 개수. 소수점 버림
// 5. 연합 해체, 국경선 닫는다.
// 인구이동이 몇번 일어나는지 구하라

import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;
    Node(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
/**
 * Main
 */
public class Main {
    static int N, L, R, map[][], resultCnt;
    static int movePeople[][];
    static int dx[]={0,1,0,-1}, dy[]={1,0,-1,0};
    static Queue<Node> que;

    static boolean wall(int x, int y) {
        if(x>=N || x<0 || y>=N || y<0) return true;
        return false;
    }

    static void BFS(int r, int c, int cnt) {
        // 초기화
        int visited[][] = new int[N][N];     // 방문국가 체크

        que.offer(new Node(r,c));
        int x=0; int y=0; int temp=0;
        while(!que.isEmpty()) {
            Node node = que.poll();
            x=node.x;
            y=node.y;
            visited[x][y] = 1;
            /*System.out.println(x+","+y);
            for(int i=0; i<N; i++) {
                System.out.println(Arrays.toString(movePeople[i]));
            }*/
            for(int i=0; i<4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(wall(nx,ny) || visited[nx][ny]==1) continue;
                temp=(int)Math.abs(map[x][y]-map[nx][ny]);
                //System.out.println(temp);
                if(temp>=L && temp<=R) {
                    movePeople[x][y]=cnt;
                    movePeople[nx][ny]=cnt;
                    que.offer(new Node(nx,ny));
                }
            }
        }   
    }

    static boolean checkmovePeople() {
        boolean flag = false;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(movePeople[i][j]==1) {
                    findUnited(i, j);
                    flag = true;
                }
            }
        }
        return flag;
    }

    static void findUnited(int x, int y) {
        ArrayList<Node> stateList = new ArrayList<>();
        stateList.add(new Node(x,y));
        que.offer(new Node(x,y));
        movePeople[x][y] = 0;
        int allPeople = map[x][y]; int cntState=1;
        while(!que.isEmpty()) {
            Node node = que.poll();

            for(int i=0; i<4; i++) {
                int nx = node.x+dx[i];
                int ny = node.y+dy[i];
                if(wall(nx,ny)) continue;
                if(movePeople[nx][ny]==1) {
                    que.offer(new Node(nx,ny));
                    movePeople[nx][ny] = 0;
                    cntState++;
                    allPeople+=map[nx][ny];
                    stateList.add(new Node(nx,ny));
                }
            }
        }

        int UnitedStatesPeoplecnt = allPeople/cntState;
        for(Node state:stateList) {
            map[state.x][state.y] = UnitedStatesPeoplecnt;
            System.out.print(state.x+","+state.y+" / ");
        }
        System.out.println();
        System.out.println("연합국가 수:" + cntState + "/ 모든 인구 수:"+allPeople+"/인구이동후:"+UnitedStatesPeoplecnt);
        resultCnt++;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        int cnt = 1;
        N=sc.nextInt();
        L=sc.nextInt();
        R=sc.nextInt();
        map = new int[N][N];
        que = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        while(flag) {
            movePeople = new int[N][N];
            cnt=1;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    BFS(i, j, cnt);
                    cnt++;
                }
            }
            flag = checkmovePeople();
            for(int i=0; i<N; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
        }
        System.out.println(resultCnt);
    }
}