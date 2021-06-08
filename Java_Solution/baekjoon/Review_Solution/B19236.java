import java.util.*;
import java.io.*;

public class B19236 {
    static final int SIZE = 4;
    static final int fishNum = 17;
    static int maxNumber;
    static int[] dx = {-1,-1,0,1,1,1,0,-1}, dy = {0,-1,-1,-1,0,1,1,1};

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

    public static int[][] copyFishMap(int[][] map) {
        int[][] tempMap = new int[SIZE][SIZE];
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
        
        return tempMap;
    }

    public static Fish[] copyFishes(Fish[] fishes) {
        Fish[] tempFishes = new Fish[fishNum];
        for(int i=1; i<fishNum; i++) {
            tempFishes[i] = new Fish(i, fishes[i].x, fishes[i].y, fishes[i].dir);
            tempFishes[i].live = fishes[i].live;
        }

        return tempFishes;
    }

    public static boolean isWall(int x, int y) {
        return x<0 || x>=SIZE || y<0 || y>=SIZE;
    }

    public static void moveFishes(int[][] fishMap, Fish[] fishes) {
        int x, y, nx, ny, dir, rotateCnt;

        for(int i=1; i<fishNum; i++) {
            if(!fishes[i].live) continue;

            rotateCnt = 0;
            x = fishes[i].x;
            y = fishes[i].y;
            dir = fishes[i].dir;

            while(rotateCnt < 8) {
                nx = x + dx[dir];
                ny = y + dy[dir];

                if(isWall(nx, ny) || fishMap[nx][ny] == -1) {
                    dir = (dir + 1)%8;
                    rotateCnt += 1;
                    continue;
                } else {
                    if(fishMap[nx][ny] != 0) {
                        fishes[fishMap[nx][ny]].x = x;
                        fishes[fishMap[nx][ny]].y = y;
                        fishMap[x][y] = fishMap[nx][ny];
                    } else {
                        fishMap[x][y] = 0;
                    }
    
                    fishMap[nx][ny] = i;
                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    fishes[i].dir = dir;
                    
                    break;
                }
            }
        }
    }

    public static void searchTotalCase(Fish shark, int totalNumber,
                                        int[][] fishMap, Fish[] fishes) {
        int[][] tempFishMap = copyFishMap(fishMap);
        Fish[] tempFishes = copyFishes(fishes);

        moveFishes(tempFishMap, tempFishes);

        int x = shark.x;
        int y = shark.y;
        int dir = shark.dir;

        while(!isWall(x, y)) {
            x += dx[dir];
            y += dy[dir];

            if(isWall(x, y)) {
                maxNumber = Math.max(maxNumber, totalNumber);
                break;
            }

            if(tempFishMap[x][y] != 0) {
                int target = tempFishMap[x][y];
                tempFishes[target].live = false;
                tempFishMap[shark.x][shark.y] = 0;
                tempFishMap[x][y] = -1;

                searchTotalCase(new Fish(-1, x, y, tempFishes[target].dir),
                    totalNumber+target, tempFishMap, tempFishes);
                
                tempFishes[target].live = true;
                tempFishMap[x][y] = target;
                tempFishMap[shark.x][shark.y] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] fishMap = new int[SIZE][SIZE];
        Fish[] fishes = new Fish[fishNum];

        for(int i=0; i<SIZE; i++) {
            st = new StringTokenizer(br.readLine()); 
            for(int j=0; j<SIZE; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                fishMap[i][j] = num;
                fishes[num] = new Fish(num, i, j, dir);
            }
        }

        int firstTarget = fishMap[0][0];
        fishes[firstTarget].live = false;
        Fish shark = new Fish(-1, 0, 0, fishes[firstTarget].dir);
        fishMap[0][0] = -1;
        maxNumber = firstTarget;

        searchTotalCase(shark, maxNumber, fishMap, fishes);
        System.out.println(maxNumber);
    }
}   