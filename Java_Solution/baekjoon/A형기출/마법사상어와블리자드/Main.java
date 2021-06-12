import java.util.*;
import java.io.*;

public class Main {
    static int N, M, totalScore;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    static int[][] marbles;
    static Queue<Integer> marbleQ;

    public static void castBlizzard(int d, int s) {
        int x = N/2;
        int y = N/2;

        while(s-- > 0) {
            x += dx[d];
            y += dy[d];

            marbles[x][y] = 0;
        }
    }

    public static boolean isWall(int x, int y) {
        return x<0 || x>=N || y<0 || y>=N;
    }

    public static int changeDir(int dir) {
        if(dir == 2) return 1;
        else if(dir == 1) return 3;
        else if(dir == 3) return 0;
        return 2;
    }

    public static void setMarbleQueue() {
        int x = N/2;
        int y = N/2;
        int dir = 2;
        int moveCnt = 0, flagCnt = 0;
        int targetCnt = 1;

        while(!isWall(x+dx[dir], y+dy[dir])) {
            x += dx[dir];
            y += dy[dir];

            if(marbles[x][y] != 0) {
                marbleQ.offer(marbles[x][y]);
                marbles[x][y] = 0;
            }

            moveCnt += 1;
            if(moveCnt == targetCnt) {
                moveCnt = 0;
                flagCnt += 1;
                dir = changeDir(dir);

                if(flagCnt == 2) {
                    targetCnt += 1;
                    flagCnt = 0;
                }
            }
        }
    }

    public static boolean searchSameMarbleNumber() {
        Deque<Integer> tempDeq = new LinkedList<>();
        int qSize = marbleQ.size();
        int curNum = 0, cnt = 0;

        while(!marbleQ.isEmpty()) {
            int num = marbleQ.poll();

            if(curNum == 0) {
                curNum = num;
            }

            if(curNum == num) {
                cnt += 1;
            } else {
                if(cnt >= 4) {
                    while(cnt-- > 0) {
                        totalScore += tempDeq.pollLast();
                    }
                }
                
                cnt = 1;
                curNum = num;
            }

            tempDeq.offer(num);
        }

        marbleQ.addAll(tempDeq);

        return qSize == marbleQ.size() ? false:true;
    }

    public static void updateMarbleQue() {
        Queue<Integer> newMarbleQ = new LinkedList<>();
        int curNum = 0, cnt = 0;

        while(!marbleQ.isEmpty()) {
            int num = marbleQ.poll();
            if(curNum == 0) {
                curNum = num;
            }
            
            if(curNum == num) {
                cnt += 1;
            } else {
                newMarbleQ.offer(cnt);
                newMarbleQ.offer(curNum);
                
                curNum = num;
                cnt = 1;
            }

            if(newMarbleQ.size() == N*N-1) break;
        }

        if(cnt > 0) {
            newMarbleQ.offer(cnt);
            newMarbleQ.offer(curNum);
        }

        marbleQ.clear();
        marbleQ.addAll(newMarbleQ);
    }

    public static void setMarlbesArr() {
        int num;
        int x = N/2;
        int y = N/2;
        int dir = 2;
        int moveCnt = 0, flagCnt = 0;
        int targetCnt = 1;

        while(!marbleQ.isEmpty()) {
            num = marbleQ.poll();
            x += dx[dir];
            y += dy[dir];

            if(isWall(x, y)) break;

            marbles[x][y] = num;
            moveCnt += 1;

            if(moveCnt == targetCnt) {
                flagCnt += 1;
                moveCnt = 0;
                dir = changeDir(dir);

                if(flagCnt == 2) {
                    targetCnt += 1;
                    flagCnt = 0;
                }
            }
        }

        marbleQ.clear();
    }

    public static void practiceMagicShark(int d, int s) {
        castBlizzard(d, s);
        setMarbleQueue();
        while(searchSameMarbleNumber()) {};
        updateMarbleQue();
        setMarlbesArr();
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        marbles = new int[N][N];
        marbleQ = new LinkedList<>();
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                marbles[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            
            practiceMagicShark(d, s);
        }

        System.out.println(totalScore);
    }   
}