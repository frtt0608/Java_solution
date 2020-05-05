// 1~K말 순서대로 이동
// 위에 말도 함께 이동
// 이동하려는 칸의 색깔에 따라 다름

// 흰색: 그 칸으로 이동, 말이 이미 있으면 가장 위로
// 빨간색: 쌓여있는 순서가 반대로
// 파란색: 이동방향 반대로하고 한칸 이동,
// 대신 이동하려는 칸이 파란색인 경우 가만히
// 체스판을 벗어나는 경우는 파란색과 같다.

import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;
    int dir;

    Node(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
/**
 * Main
 */
public class Main {
    static int N, K, map[][], resTurn;
    static ArrayList<Node> chessList;
    static ArrayList<Integer> chessMap[][];
    static int[] dx = {0,0,0,-1,1}, dy={0,1,-1,0,0};
    
    static int changeDir(int dir) {
        if(dir==1) dir=2;
        else if(dir==2) dir=1;
        else if(dir==3) dir=4;
        else dir=3;
        return dir;
    }

    static boolean wall(int x, int y) {
        if(x>N || x<=0 || y>N || y<=0) return true;
        return false;
    }

    static int moveChess(int idx) {
        ArrayList<Integer> tempChessList = new ArrayList<>();
        ArrayList<Integer> newChessList = new ArrayList<>();

        boolean flag = false;
        Node node = chessList.get(idx);
        int x = node.x;
        int y = node.y;
        int dir = node.dir;
        int nx;
        int ny;

        for(Integer chess: chessMap[x][y]) {
            if(chess==idx) flag=true;

            if(flag) {
                tempChessList.add(chess);
            } else {
                newChessList.add(chess);
            }
        }

        nx = x + dx[dir];   
        ny = y + dy[dir];

        if(wall(nx,ny) || map[nx][ny]==2) { // 파랑칸 or 벗어날때
            dir = changeDir(dir);
            nx = x + dx[dir];
            ny = y + dy[dir];
        } 
        
        if(!wall(nx,ny) && map[nx][ny]==0) { // 흰색일 때

            chessMap[x][y].clear();
            for(Integer chess: newChessList) {
                chessMap[x][y].add(chess);
            }

            for(Integer chess: tempChessList) {
                chessMap[nx][ny].add(chess);
                if(chess==idx) chessList.set(idx, new Node(nx, ny, dir));
                else {
                    chessList.set(chess, new Node(nx, ny, chessList.get(chess).dir));
                }
            }
            x=nx;
            y=ny;
            
        } else if(!wall(nx,ny) && map[nx][ny]==1) { // 빨간색일 때

            chessMap[x][y].clear();
            for(Integer chess: newChessList) {
                chessMap[x][y].add(chess);
            }

            for(int i=tempChessList.size()-1; i>=0; i--) {
                int chess = tempChessList.get(i);
                chessMap[nx][ny].add(chess);
                if(chess==idx) chessList.set(idx, new Node(nx, ny, dir));
                else {
                    chessList.set(chess, new Node(nx, ny, chessList.get(chess).dir));
                }
            }
            x=nx;
            y=ny;

        } else {
            chessList.set(idx, new Node(x, y, dir));
        }

        // for(Integer chess: chessMap[nx][ny]) {
        //     System.out.print(chess+", ");
        // }
        // System.out.println();
        return chessMap[x][y].size();
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        chessMap = new ArrayList[N+1][N+1];
        chessList = new ArrayList<>();
        chessList.add(new Node(-1,-1,0)); // idx 0 값

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                chessMap[i][j] = new ArrayList<>();
            }
        }

        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            chessMap[x][y].add(i);
            int dir = Integer.parseInt(st.nextToken());
            chessList.add(new Node(x, y, dir));
        }

        loop:
        while(true) {
            resTurn++;
            for(int i=1; i<=K; i++) {
                if(moveChess(i) >= 4) {
                    break loop;
                }
            }
            if(resTurn > 1000) {
                resTurn = -1; 
                break;
            }
        }

        // for(int i=1; i<=K; i++) {
        //     if(moveChess(i) >= 4) {
        //         break;
        //     }
        // }

        System.out.println(resTurn);
    }
}