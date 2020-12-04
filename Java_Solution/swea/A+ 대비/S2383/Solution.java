import java.util.*;
import java.io.*;
/**
 * Solution
 */
public class Solution {
    static int N, minTime, totalMinTime;
    static int[][] stair;
    static List<Person> personList;
    static Queue<Person> copyPersonQ;
    static Queue<Person> wait1, wait2;
    static Queue<Person> stair1, stair2;

    public static void downStair() {
        Queue<Person> tempQ1 = new LinkedList<>();
        Queue<Person> tempQ2 = new LinkedList<>();

        while(!stair1.isEmpty()) {
            Person p = stair1.poll();

            if(minTime - p.downTime < stair[0][2]) {
                tempQ1.offer(p);
            } else {
                // System.out.println(minTime + "분에 " + p.x + ", " + p.y + " 계단 통과   1번 계단 " + p.downTime);
            }
        }

        while(!stair2.isEmpty()) {
            Person p = stair2.poll();

            if(minTime - p.downTime < stair[1][2]) {
                tempQ2.offer(p);
            } else {
                // System.out.println(minTime + "분에 " + p.x + ", " + p.y + " 계단 통과   2번 계단 " + p.downTime);
            }
        }

        stair1.addAll(tempQ1);
        stair2.addAll(tempQ2);
    }

    public static void startDownToStair() {
        Queue<Person> tempQ1 = new LinkedList<>();
        Queue<Person> tempQ2 = new LinkedList<>();

        while(!wait1.isEmpty()) {
            Person p = wait1.poll();
            
            if(stair1.size() < 3 && minTime - p.arriveTime > 0) {
                // System.out.println(minTime + "에 " + p.x + ", " + p.y + " 1번 계단 내려가기 시작");
                p.downTime = minTime;
                stair1.offer(p);
            } else {
                tempQ1.offer(p);
            }
        }

        while(!wait2.isEmpty()) {
            Person p = wait2.poll();
            
            if(stair2.size() < 3 && minTime - p.arriveTime > 0) {
                // System.out.println(minTime + "에 " + p.x + ", " + p.y + " 2번 계단 내려가기 시작");
                p.downTime = minTime;
                stair2.offer(p);
            } else {
                tempQ2.offer(p);
            }
        }

        wait1.addAll(tempQ1);
        wait2.addAll(tempQ2);
    }

    public static int calculateDiff(Person p, int stx, int sty) {
        return Math.abs(p.x - stx) + Math.abs(p.y - sty);
    }

    public static void moveToStair() {
        Queue<Person> tempQ = new LinkedList<>();

        while(!copyPersonQ.isEmpty()) {
            Person p = copyPersonQ.poll();
            
            int diffPersonAndStair1 = calculateDiff(p, stair[0][0], stair[0][1]);
            int diffPersonAndStair2 = calculateDiff(p, stair[1][0], stair[1][1]);

            if(p.targetStair == 0 && minTime == diffPersonAndStair1) {
                // System.out.println(minTime + "에 " + p.x + ", " + p.y + " 1번 계단 도착");
                p.arriveTime = minTime;
                wait1.offer(p);
            } else if(p.targetStair == 1 && minTime == diffPersonAndStair2) {
                // System.out.println(minTime + "에 " + p.x + ", " + p.y + " 2번 계단 도착");
                p.arriveTime = minTime;
                wait2.offer(p);
            } else {
                tempQ.offer(p);
            }
        }

        copyPersonQ.addAll(tempQ);
    }

    public static void calculateMinTime(int[] target) {
        copyPersonQ = new LinkedList<>();


        for(int i=0; i<personList.size(); i++) {
            Person p = personList.get(i);
            p.targetStair = target[i];

            copyPersonQ.offer(p);
        }
        
        minTime = 0;
        while(!copyPersonQ.isEmpty() || !stair1.isEmpty() || !stair2.isEmpty() || !wait1.isEmpty() || !wait2.isEmpty()) {
            minTime += 1;

            moveToStair();
            downStair();
            startDownToStair();
        }

        totalMinTime = Math.min(totalMinTime, minTime);
    }

    public static void totalCase(int idx, int[] target) {
        if(idx == personList.size()) {
            calculateMinTime(target);
            return;
        }

        target[idx] = 0;
        totalCase(idx+1, target);
        target[idx] = 1;
        totalCase(idx+1, target);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            stair = new int[2][3];
            personList = new ArrayList<>();
            wait1 = new LinkedList<>();
            wait2 = new LinkedList<>();
            stair1 = new LinkedList<>();
            stair2 = new LinkedList<>();
            totalMinTime = Integer.MAX_VALUE;
            int idx = 0;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int num = Integer.parseInt(st.nextToken());

                    if(num == 1) {
                        personList.add(new Person(i, j));
                    } else if(num > 1) {
                        stair[idx][0] = i;
                        stair[idx][1] = j;
                        stair[idx][2] = num;
                        idx += 1;
                    }
                }
            }
            // calculateMinTime(new int[] {1,0,1});
            totalCase(0, new int[personList.size()]);

        
            System.out.println("#" + tc + " " + totalMinTime);
        }
    }
}

class Person {
    int x;
    int y;
    int targetStair;
    int arriveTime;
    int downTime;

    Person(int x, int y) {
        this.x = x;
        this.y = y;
    }
}