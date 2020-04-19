// 좌표평면의 x축은 가로, y축은 세로
// 세가지 속성
// 시작점, 시작방향, 세대
// 0세대는 길이가 1인 선분
// 1세대는 0세대 끝 점을 기준으로 시계방향 90도 회전시켜서 붙인 선분
// 2세대 이후도 1세대를 만든 방법을 이용해서 만든다.
// 즉, K세대 커브는 K-1세대 커브를 끝점을 기준으로 90도 회전후 붙인 것
// 100, 100인 격자 위에 커브 N개있다.
// 입력 0:x좌표 증가, 1:y좌표 감소, 2: x좌표 감소, 3: y좌표 증가


import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, map[][], cntSquare, newX, newY;
    static int dx[] = {1,0,-1,0}, dy[] = {0,-1,0,1};
    static ArrayList<Integer> dirList;

    static void rotateDir(int y, int x, int dir, int g) {
        if(g!=0) dir = (dir+1)%4;
        newX = x + dx[dir];
        newY = y + dy[dir];
        //System.out.println(newX+","+newY);
        dirList.add(dir);
        map[newY][newX] = 1;
    }

    static void dragonCurve() {
        for(int i=dirList.size()-1; i>=0; i--) {
            rotateDir(newY, newX, dirList.get(i), 1);
        }
    }

    static void findSquare() {
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(map[i][j]==1 && map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j+1]==1) 
                    cntSquare+=1;
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        map = new int[101][101];
        cntSquare = 0;
        
        for(int i=0; i<N; i++) {
            dirList = new ArrayList<>();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();
            map[y][x] = 1;
            rotateDir(y,x,d,0);
            while(g>0) {
                dragonCurve();
                g--;
            }
        }
        findSquare();
        // for(int i=0; i<10; i++) {
        //     System.out.println(Arrays.toString(map[i]));
        // }

        System.out.println(cntSquare);
    }
}