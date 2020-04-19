// 크기 NN 빈칸, 치킨집, 집
//r,c 1부터시작
// "치킨거리" 각 집은 치킨거리를 가지고 있다
// 도시의 치킨거리는 모든 집의 치킨거리의합
// 최대 M개 치킨집, 나머지 폐업

// 완탐, perm
// BFS?
// 13개 중에 12개를 선택 시, 항목마다 경우는 2가지 뿐이다.
// for문을 쓰면서 섣불리 하나하나 선택하지 말것

import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, map[][], minChicken;
    static ArrayList<Node> home, dak;
    static int dx[] = {0,0,1,-1}, dy[] = {1,1,0,0};

    // M을 지우면서 perm
    static void perm(int dakCnt, int dak_idx, int visited[]) {
        if(M==dakCnt) {
            int temp = 0;
            for(int i=0; i<home.size(); i++) {
                temp += findShortLength(home.get(i), visited);
            }
            minChicken = Math.min(minChicken, temp);
            return;
        }
        if(dak_idx >= dak.size()) return;

        visited[dak_idx] = 1;
        perm(dakCnt+1, dak_idx+1, visited);
        visited[dak_idx] = 0;
        perm(dakCnt, dak_idx+1, visited);
    }

    static int findShortLength(Node Home, int visited[]) {
        int length = 99999; int temp = 0;
        for(int i=0; i<visited.length; i++) {
            if(visited[i]==1) {
                temp = calDak_length(Home.x, Home.y, dak.get(i).x, dak.get(i).y);
                length = Math.min(length, temp);
            }
        }
        //System.out.println(length);
        return length;
    }

    static int calDak_length(int homeX, int homeY, int dakX, int dakY) {
        return (int)Math.abs(homeX-dakX) + (int)Math.abs(homeY-dakY);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        home = new ArrayList<>(); dak = new ArrayList<>();
        map = new int[N][N];
        minChicken = 999999;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j]==1) home.add(new Node(i,j));
                else if(map[i][j]==2) dak.add(new Node(i,j));
            }
        }
        int visited[] = new int[dak.size()];
        perm(0, 0, visited);

        System.out.println(minChicken);
    }
}
