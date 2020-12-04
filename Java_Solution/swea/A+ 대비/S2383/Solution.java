import java.util.*;
import java.io.*;
/**
 * Solution
 */
public class Solution {
    static int N, minTime, totalMinTime, cnt;
    static int[][] stair;
    static boolean[] visited;
    static List<Person> personList;
    static Queue<Person> stair1, stair2;

    static class Person {
        int x;
        int y;
        int targetStair;
        int arriveTime;
        int downTime;
    
        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void calculateArriveTime() {
            this.arriveTime = Math.abs(this.x - stair[targetStair][0]) +
                                Math.abs(this.y - stair[targetStair][1]);
        }
    }

    public static void downStair() {
        int size1 = stair1.size();
        int size2 = stair2.size();

        for(int i=0; i<size1; i++) {
            Person p = stair1.poll();
            if(minTime - p.downTime < stair[0][2]) {
                stair1.offer(p);
            }
        }

        for(int i=0; i<size2; i++) {
            Person p = stair2.poll();
            if(minTime - p.downTime < stair[1][2]) {
                stair2.offer(p);
            }
        }
    }

    public static void moveToStair() {
        for(int i=0; i<personList.size(); i++) {
            if(visited[i]) continue;

            Person p = personList.get(i);

            if(p.targetStair == 0 && p.arriveTime+1 <= minTime && stair1.size() < 3) {
                p.downTime = minTime;
                stair1.offer(p);
                visited[i] = true;
                cnt += 1;
            } else if(p.targetStair == 1 && p.arriveTime+1 <= minTime && stair2.size() < 3) {
                p.downTime = minTime;
                stair2.offer(p);
                visited[i] = true;
                cnt += 1;
            }
        }
    }

    public static void calculateMinTime() {
        visited = new boolean[personList.size()];
        cnt = 0;
        minTime = 0;

        while(true) {
            minTime += 1;

            downStair();
            moveToStair();

            if(cnt == personList.size() && stair1.isEmpty() && stair2.isEmpty()) {
                break;
            }
        }

        totalMinTime = Math.min(totalMinTime, minTime);
    }

    public static void totalCase(int idx) {
        if(idx == personList.size()) {
            calculateMinTime();
            return;
        }

        personList.get(idx).targetStair = 0;
        personList.get(idx).calculateArriveTime();
        totalCase(idx+1);

        personList.get(idx).targetStair = 1;
        personList.get(idx).calculateArriveTime();
        totalCase(idx+1);
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

            totalCase(0);
            System.out.println("#" + tc + " " + totalMinTime);
        }
    }
}

