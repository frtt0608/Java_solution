import java.util.*;
import java.io.*;

public class Solution {
    static int N, minTime;
    static int[] expectedTime;
    static List<Person> persons;
    static Stair[] stairs;
    static Queue<Person>[] waiting;
    static PriorityQueue<Integer>[] stairPQ;

    static class Person {
        int time = 0;
        int x, y;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Stair {
        int len;
        int x, y;

        Stair(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }

    public static void downStair(int[] targets) {
        boolean[] visited = new boolean[persons.size()];
        int passPerson = 0;
        int time = 0;

        while(passPerson < persons.size()) {
            time += 1;

            // 계단 입구에 도착했는지 확인
            for(int i=0; i<persons.size(); i++) {
                if(!visited[i]) {
                    if(expectedTime[i] == time) {
                        Person person = persons.get(i);
                        person.time = time;
                        // System.out.println((i+1)+"번 사람이 "+targets[i]+"번 계단 입구 도착: " + time);
                        waiting[targets[i]].offer(persons.get(i));
                        visited[i] = true;
                    }
                }
            }

            // 계단을 다 내려온 사람은 PQ에서 제거
            for(int i=1; i<3; i++) {
                while(!stairPQ[i].isEmpty()) {
                    if(time - stairPQ[i].peek() == stairs[i].len) {
                        // System.out.println(stairPQ[i].peek()+"시간에 " + i +"번째 계단 탈출");
                        stairPQ[i].poll();
                        passPerson += 1;
                    } else {
                        break;
                    }
                }
            }

            // 입구에서 계단으로 내려가기 시작
            for(int i=1; i<3; i++) {
                while(!waiting[i].isEmpty()) {
                    if(stairPQ[i].size()<3 && time - waiting[i].peek().time >= 1) {
                        // System.out.println(time+"시간에 "+i+"번째 계단 내려가기 시작");
                        waiting[i].poll();
                        stairPQ[i].offer(time);
                    } else {
                        break;
                    }
                }
            }
        }

        minTime = Math.min(minTime, time);
    }

    public static void calculateExpectedTime(int[] targets) {
        for(int i=0; i<targets.length; i++) {
            Person person = persons.get(i);

            expectedTime[i] = Math.abs(person.x - stairs[targets[i]].x)
                                + Math.abs(person.y - stairs[targets[i]].y); 
        }
    }

    public static void getTotalCase(int index, int[] targets) {
        if(index >= persons.size()) {
            calculateExpectedTime(targets);
            downStair(targets);
            return;
        }

        targets[index] = 1;
        getTotalCase(index+1, targets);
        targets[index] = 2;
        getTotalCase(index+1, targets);
    }

    public static void initStructure() {
        persons = new ArrayList<>();
        stairs = new Stair[3];
        minTime = 10000000;

        waiting = new LinkedList[3];
        stairPQ = new PriorityQueue[3];
        for(int i=1; i<3; i++) {
            waiting[i] = new LinkedList<>();
            stairPQ[i] = new PriorityQueue<>((Integer time1, Integer time2) -> (time1 - time2));
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            initStructure();

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int input = Integer.parseInt(st.nextToken());

                    if(input == 1) {
                        persons.add(new Person(i, j));
                    } else if(input > 1) {
                        if(stairs[1] == null) {
                            stairs[1] = new Stair(input, i, j);
                        } else {
                            stairs[2] = new Stair(input, i, j);
                        }
                    }
                }
            }

            expectedTime = new int[persons.size()];
            getTotalCase(0, new int[persons.size()]);

            // int[] targets = new int[] {1, 1, 1, 1, 2, 2};
            // calculateExpectedTime(targets);
            // downStair(targets);

            System.out.println("#"+tc+" "+minTime);
        }
    }
}