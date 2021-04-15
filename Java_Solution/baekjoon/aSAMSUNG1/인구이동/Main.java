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
    static int N, L, R, map[][], resultCnt, visited[][];
    static int dx[]={0,0,1,0,-1}, dy[]={0,1,0,-1,0};
    static Queue<Node> que;
    static ArrayList<Node> movePeople;

    static boolean wall(int x, int y) {
        if(x>=N || x<0 || y>=N || y<0) return true;
        return false;
    }

    static int BFS(int r, int c) {
        visited[r][c] = 1;
        que.offer(new Node(r,c));
        int temp=0;
        int totalPeople=map[r][c];
        movePeople.add(new Node(r,c));

        while(!que.isEmpty()) {
            Node node = que.poll();
            /*System.out.println(x+","+y);
            for(int i=0; i<N; i++) {
                System.out.println(Arrays.toString(movePeople[i]));
            }*/
            for(int i=1; i<=4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(wall(nx,ny) || visited[nx][ny]==1) continue;
                temp = Math.abs(map[node.x][node.y]-map[nx][ny]);
                if(temp>=L && temp<=R) {
                    visited[nx][ny] = 1;
                    que.offer(new Node(nx,ny));
                    totalPeople+=map[nx][ny];
                    movePeople.add(new Node(nx,ny));
                }
            }
        }
        return totalPeople;
    }

    static boolean checkMove() {
        boolean flag = false;
        int totalPeople=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j]==1) continue;
                movePeople = new ArrayList<>();
                totalPeople = BFS(i, j);

                if(movePeople.size()>1){
                    updateMap(totalPeople);
                    flag = true;
                }
            }
        }
        return flag;
    }

    static void updateMap(int totalPeople) {
        int people = totalPeople/movePeople.size();
        for(Node node:movePeople) {
            map[node.x][node.y] = people;
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
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
            visited = new int[N][N];
            flag = checkMove();
            if(flag) resultCnt++;
        }
        System.out.println(resultCnt);
    }
}