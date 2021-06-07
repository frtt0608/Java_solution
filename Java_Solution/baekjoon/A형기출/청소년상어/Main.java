import java.util.*;
import java.io.*;

public class Main {
    static int maxNumber;
    static Fish[] fishes;
    static int[] dx = {0,-1,-1,0,1,1,1,0,-1}, dy = {0,0,-1,-1,-1,0,1,1,1};
    static int[][] map;
    
    static class Fish {
        int num;
        int x, y;
        int dir;
        boolean live = true;

        Fish(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static boolean isWall(int x, int y) {
        return x<0 || x>=4 || y<0 || y>=4;
    }

    public static void moveFishes(Fish[] tFishes, int[][] tMap) {
        int x, y, nx, ny, cnt;

        for(int i=1; i<17; i++) {
            if(!tFishes[i].live) continue;

            Fish fish = tFishes[i];
            cnt = 0;
            x = fish.x;
            y = fish.y;

            while(cnt < 8) {
                nx = x + dx[fish.dir];
                ny = y + dy[fish.dir];

                if(isWall(nx, ny) || tMap[nx][ny] == -1) {
                    fish.dir = (fish.dir + 1)%9;
                    if(fish.dir == 0) 
                        fish.dir = 1;
                        
                    cnt += 1;
                } else {
                    if(tMap[nx][ny] != 0) {
                        tFishes[tMap[nx][ny]].x = x;
                        tFishes[tMap[nx][ny]].y = y;
                        tMap[x][y] = tMap[nx][ny];
                    } else {
                        tMap[x][y] = 0;
                    }
                    
                    tFishes[i].x = nx;
                    tFishes[i].y = ny;
                    tFishes[i].dir = fish.dir;
                    tMap[nx][ny] = i;

                    break;
                }
            }
        }
    }
    
    public static int[][] copyMapArr(int[][] map) {
        int[][] tMap = new int[4][4];

        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                tMap[i][j] = map[i][j];
            }
        }

        return tMap;
    }

    public static Fish[] copyFishesArr(Fish[] fishes) {
        Fish[] tFishes = new Fish[17];

        for(int i=1; i<17; i++) {
            tFishes[i] = new Fish(i, fishes[i].x, fishes[i].y, 
                                    fishes[i].dir);
            tFishes[i].live = fishes[i].live;
        }

        return tFishes;
    }

    public static void moveSharkTotalCase(Fish shark, int[][] map, Fish[] fishes, int totalNumber) {
        Fish[] tFishes = copyFishesArr(fishes);
        int[][] tMap = copyMapArr(map);
        moveFishes(tFishes, tMap);

        int x = shark.x;
        int y = shark.y;
        int dir = shark.dir;

        while(true) {
            x += dx[dir];
            y += dy[dir];

            if(isWall(x, y)) {
                maxNumber = Math.max(maxNumber, totalNumber);
                break;
            }

            if(tMap[x][y] != 0) {
                int getNum = tMap[x][y];
                if(!tFishes[getNum].live) continue;

                tFishes[getNum].live = false;
                tMap[shark.x][shark.y] = 0;
                tMap[x][y] = -1;

                moveSharkTotalCase(new Fish(-1, x, y, tFishes[getNum].dir),
                    tMap, tFishes, totalNumber+getNum);

                tFishes[getNum].live = true;
                tMap[shark.x][shark.y] = -1;
                tMap[x][y] = getNum;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        fishes = new Fish[18];
        map = new int[4][4];

        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                map[i][j] = num;
                fishes[num] = new Fish(num, i, j, dir);
            }
        }

        fishes[map[0][0]].live = false;
        maxNumber = map[0][0];
        Fish shark = new Fish(-1, 0, 0, fishes[map[0][0]].dir);
        map[0][0] = -1;

        moveSharkTotalCase(shark,
            map, fishes, maxNumber);
        System.out.println(maxNumber);
    }
}
