import java.util.*;
import java.io.*;

public class B19236 {
    static int maxScore;
    static int[] dx={-1,-1,0,1,1,1,0,-1}, dy={0,-1,-1,-1,0,1,1,1};
    static boolean[] dead;

    static class Fish {
        int x, y;
        int num, dir;

        Fish(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=4 || y<0 || y>=4) return true;
        return false;
    }

    public static void moveFish(int[][] fishNum, Fish[] tempFishes) {
        int x, y, nx, ny, dir, dirCnt, num;
        Fish fish;

        for(int i=1; i<17; i++) {
            if(dead[i]) continue;

            fish = tempFishes[i];
            nx = fish.x;
            ny = fish.y;
            x = fish.x;
            y = fish.y;
            dir = fish.dir;
            dirCnt = 0;

            while(dirCnt < 8) {
                nx = x + dx[dir];
                ny = y + dy[dir];

                if(isWall(nx, ny) || fishNum[nx][ny] == -1) {
                    dir = (dir+1)%8;
                    dirCnt += 1;
                } else {
                    break;
                }
            }

            // 이동할 수 없는 경우이므로 다음 물고기로 넘어감
            if(dirCnt == 8)
                continue;

            num = fishNum[nx][ny];
            if(num != 0) {
                // 가려는 위치에 다른 물고기 있으면 위치 교체
                tempFishes[num].x = x;
                tempFishes[num].y = y;
                fishNum[x][y] = num;
            } else {
                // 없으면 기존 위치는 null
                fishNum[x][y] = 0;
            }

            tempFishes[i] = new Fish(nx, ny, i, dir);
            fishNum[nx][ny] = i;
        }
    }

    public static int[][] copyMap(int[][] map) {
        int[][] tempMap = new int[4][4];

        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        return tempMap;
    }

    public static Fish[] copyFishes(Fish[] fishes) {
        Fish[] tempFishes = new Fish[17];
        Fish fish;

        for(int i=1; i<17; i++) {
            fish = new Fish(fishes[i].x, fishes[i].y, fishes[i].num, fishes[i].dir);
            tempFishes[i] = fish;
        }

        return tempFishes;
    }

    public static void getMaxFishNumber(Fish shark, int score, int[][] map, Fish[] fishes) {
        
        int[][] tempMap = copyMap(map);
        Fish[] tempFishes = copyFishes(fishes);
        moveFish(tempMap, tempFishes);
        // printFish(tempMap);

        int nx = shark.x;
        int ny = shark.y;

        while(true) {
            nx += dx[shark.dir];
            ny += dy[shark.dir];

            if(isWall(nx, ny)) {
                // System.out.println("끝: " + score + "\n");
                
                maxScore = Math.max(maxScore, score);
                return;
            }
            
            if(tempMap[nx][ny] > 0) {
                int num = tempMap[nx][ny];
                if(dead[num]) continue;
                    
                tempMap[shark.x][shark.y] = 0;
                tempMap[nx][ny] = -1;
                dead[num] = true;
                
                // System.out.println(nx+", "+ny+ " 잡은 물고기 번호: " + num);
                getMaxFishNumber(new Fish(nx, ny, -1, tempFishes[num].dir),
                        score+num, tempMap, tempFishes);

                tempMap[shark.x][shark.y] = -1;
                tempMap[nx][ny] = num;
                dead[num] = false;
            } 
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] fishNum = new int[4][4];
        Fish[] fishes = new Fish[17];
        dead = new boolean[17];

        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;

                fishNum[i][j] = num;
                fishes[num] = new Fish(i, j, num, dir);
            }
        }

        // 0,0에 물고기잡고 상어 입장.
        dead[fishNum[0][0]] = true;
        maxScore = fishNum[0][0];
        Fish shark = new Fish(0, 0, -1, fishes[fishNum[0][0]].dir);
        fishNum[0][0] = -1;
        
        getMaxFishNumber(shark, maxScore, fishNum, fishes);
        System.out.println(maxScore);
    }

    public static void printFish(int[][] map) {
        System.out.println("----------------");
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}