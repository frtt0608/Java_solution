import java.util.*;
import java.io.*;

public class B21611 {
    static int N, M, totalScore;
    static int[] dx = {0,-1,1,0,0}, dy = {0,0,0,-1,1};
    static int[][] marblesArr;
    static Queue<Integer> marblesQue;

    static class Node {
        int index, num;

        Node(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<=0 || x>N || y<=0 || y>N) return true;
        return false;
    }

    public static void castBlizzard(int d, int s) {
        int x = (N+1)/2;
        int y = (N+1)/2;

        while(s > 0) {
            x += dx[d];
            y += dy[d];

            marblesArr[x][y] = 0;
            s -= 1;
        }
    }

    public static void explodedMarbles(int cnt, Deque<Integer> newMarbles) {
        if(cnt >= 4) {
            totalScore += newMarbles.peekLast()*cnt;

            while(cnt > 0) {
                cnt -= 1;
                newMarbles.removeLast();
            }
        }
    }

    public static boolean countSameMarbles() {
        Deque<Integer> newMarbles = new LinkedList<>();
        int queSize = marblesQue.size();
        int num = 0;
        int cnt = 0;
        boolean isExploded = true;

        while(!marblesQue.isEmpty()) {
            int curNum = marblesQue.poll();

            if(num == curNum) {
                cnt += 1;
            } else {
                explodedMarbles(cnt, newMarbles);
                cnt = 1;
                num = curNum;
            }

            newMarbles.add(curNum);
        }

        explodedMarbles(cnt, newMarbles);

        if(queSize == newMarbles.size()) {
            isExploded = false;
        }

        while(!newMarbles.isEmpty()) {
            marblesQue.offer(newMarbles.poll());
        }
    
        return isExploded;
    }

    public static int changeDir(int d) {
        if(d == 3) return 2;
        else if(d == 2) return 4;
        else if(d == 4) return 1;
        else return 3;
    }

    public static void setMarblesQueue() {
        int x = (N+1)/2;
        int y = (N+1)/2;
        int changeCnt = 1;
        int flagCnt = 0;
        int cnt = 0;
        int d = 3;
    
        while(true) {
            cnt += 1;
            x += dx[d];
            y += dy[d];

            if(isWall(x, y)) break;
            if(marblesArr[x][y] != 0) {
                marblesQue.offer(marblesArr[x][y]);
            }

            if(changeCnt == cnt) {
                d = changeDir(d);
                cnt = 0;
                flagCnt += 1;
                
                if(flagCnt == 2) {
                    changeCnt += 1;
                    flagCnt = 0;
                }
            }
        }
    }

    public static void multiplyMarbles() {
        Queue<Integer> newMarbles = new LinkedList<>();
        int num = 0;
        int cnt = 0;

        while(!marblesQue.isEmpty()) {
            int curNum = marblesQue.poll();

            if(num == curNum) {
                cnt += 1;
            } else {
                if(cnt > 0) {
                    newMarbles.offer(cnt);
                    newMarbles.offer(num);
                }

                num = curNum;
                cnt = 1;
            }

            if(newMarbles.size() == N*N-1) break;
        }

        if(cnt > 0) {
            newMarbles.offer(cnt);
            newMarbles.offer(num);
        }

        marblesQue.clear();

        while(!newMarbles.isEmpty()) {
            marblesQue.offer(newMarbles.poll());
        }
    }

    public static void initMarblesArr() {
        for(int i=0; i<N+1; i++) {
            Arrays.fill(marblesArr[i], 0);
        }
    }

    private static void setMarblesArr() {
        
        int x = (N+1)/2;
        int y = (N+1)/2;
        int changeCnt = 1;
        int flagCnt = 0;
        int cnt = 0;
        int d = 3;

        while(!marblesQue.isEmpty()) {
            int curNum = marblesQue.poll();

            cnt += 1;
            x += dx[d];
            y += dy[d];

            if(isWall(x, y)) {
                break;
            }

            marblesArr[x][y] = curNum;

            if(changeCnt == cnt) {
                d = changeDir(d);
                cnt = 0;
                flagCnt += 1;

                if(flagCnt == 2) {
                    changeCnt += 1;
                    flagCnt = 0;
                }
            }
        }

        marblesQue.clear();
    }

    public static void practiceSkill(int d, int s) {
        castBlizzard(d, s);
        setMarblesQueue();
        while(true) {
            if(!countSameMarbles()) {
                break;
            }
        }
        multiplyMarbles();
        initMarblesArr();
        setMarblesArr();
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        marblesArr = new int[N+1][N+1];
        marblesQue = new LinkedList<>();
        totalScore = 0;

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                marblesArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            practiceSkill(d, s);
        }
        
        System.out.println(totalScore);
    }

    public static void printArr() {
        for(int i=1; i<N+1; i++) {
            System.out.println(Arrays.toString(marblesArr[i]));
        }
        System.out.println();
    }
}