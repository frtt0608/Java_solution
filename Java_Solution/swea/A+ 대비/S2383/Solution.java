import java.util.*;
import java.io.*;

class Stair {
    int x;
    int y;
    int size;

    Stair(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
}

class Person {
    int num;
    int x;
    int y;
    int dist;
    int arriveTime;

    Person(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}
/**
 * Solution
 */
public class Solution {
    static int N, minTime;
    static int[][] map;
    static ArrayList<Stair> stairs;
    static ArrayList<Person> persons;
    static Person[] stairList1, stairList2, waitingList1, waitingList2;
    
    static void choiceStair(int idx, int[] where, int cnt1) {
        if(idx == persons.size()) {
            goStair(where, cnt1);
            return;
        }

        where[idx] = 1;
        choiceStair(idx+1, where, cnt1+1);
        where[idx] = 2;
        choiceStair(idx+1, where, cnt1);
    }

    static void goStair(int[] where, int cnt1) {
        int time = 0;
        int cnt = 0;
        int len = where.length;
        stairList1 = new Person[3];
        stairList2 = new Person[3];
        waitingList1 = new Person[cnt1];
        waitingList2 = new Person[len - cnt1];
        // ArrayList<Integer> distList = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        // int dist = 0;

        for(int i=0; i<len; i++) {
            Person p = persons.get(i);
            if(where[i]==1) {
                p.dist = Math.abs(p.x - stairs.get(0).x) + Math.abs(p.y - stairs.get(0).y);
                waitingList1[idx1] = p;
                idx1++;
                // dist = p.dist + stairs.get(0).size;
            } else {
                p.dist = Math.abs(p.x - stairs.get(1).x) + Math.abs(p.y - stairs.get(1).y);
                waitingList2[idx2] = p;
                idx2++;
                // dist = p.dist + stairs.get(1).size;
            }
            // distList.add(dist);
        }
        
        // distList.sort(Comparator.reverseOrder());
        // minTime = Math.min(minTime, distList.get(0));

        while(true) {
            
            // 계단1 통과자 확인하기
            for(int i=0; i<stairList1.length; i++) {
                if(stairList1[i] == null) continue;

                if(time - stairList1[i].arriveTime > stairs.get(0).size) {
                    // System.out.println(stairList1[i].num+"번째 사람//  "+"1번 계단 통과!   " + "time: " + time);
                    stairList1[i] = null;
                    cnt++;
                }
            }

            // 계단2 통과자 확인하기
            for(int i=0; i<stairList2.length; i++) {
                if(stairList2[i] == null) continue;

                if(time - stairList2[i].arriveTime > stairs.get(1).size) {
                    // System.out.println(stairList2[i].num+"번째 사람//  " + "2번 계단 통과!   " + "time: " + time);
                    stairList2[i] = null;
                    cnt++;
                }
            }

            if(cnt == where.length) break;

            // 계단1 입구 도착한 사람 확인하기
            for(int i=0; i<waitingList1.length; i++) {
                if(waitingList1[i] == null) continue;
                
                if(time - waitingList1[i].dist >= 0) {
                    for(int j=0; j<3; j++) {
                        if(stairList1[j] == null) {
                            Person p = waitingList1[i];
                            // System.out.println(p.num+"번째 사람//  "+"1번계단 입구 도착!  " + "time: " + time);
                            p.arriveTime = time;
                            stairList1[j] = p;
                            waitingList1[i] = null;
                            break;
                        }
                    }
                }
            }

            // 계단2 입구 도착한 사람 확인하기
            for(int i=0; i<waitingList2.length; i++) {
                if(waitingList2[i] == null) continue;

                if(time - waitingList2[i].dist >= 0) {
                    for(int j=0; j<3; j++) {
                        if(stairList2[j] == null) {
                            Person p = waitingList2[i];
                            // System.out.println(p.num+"번째 사람//  "+"2번계단 입구 도착!  " + "time: " + time);
                            p.arriveTime = time;
                            stairList2[j] = p;
                            waitingList2[i] = null;
                            break;
                        }
                    }
                }
            }

            time++;
            // System.out.println("--------");
        }

        // 최소 시간 구하기.
        minTime = Math.min(minTime, time);
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            stairs = new ArrayList<>();
            persons = new ArrayList<>();
            int idx = 1;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > 1) {
                        stairs.add(new Stair(i, j, map[i][j]));
                    } else if(map[i][j]==1) {
                        persons.add(new Person(idx, i, j));
                        idx++;
                    }
                }
            }

            minTime = Integer.MAX_VALUE;
            int[] where = new int[persons.size()];
            choiceStair(0, where, 0);
            // where[0] = 1;
            // where[1] = 1;
            // where[2] = 1;
            // where[3] = 2;
            // where[4] = 2;
            // where[5] = 2;
            // goStair(where, 3);

            System.out.println("#"+tc+" "+minTime);
        }
    }
}