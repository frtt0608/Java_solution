import java.io.*;
import java.util.*;

public class B11657 {
    static final int MAX = Integer.MAX_VALUE;
    static int N, M;
    static long[] minTime;
    static Bus[] buses;
    static boolean isTimeMachine;

    static class Bus {
        int start, target;
        int time;

        Bus(int start, int target, int time) {
            this.start = start;
            this.target = target;
            this.time = time;
        }
    }

    public static void checkedTimeMachine() {
        minTime[1] = 0;

        for(int i=0; i<N; i++) {
            for(Bus bus: buses) {
                int s = bus.start;
                int e = bus.target;
                if(minTime[s] == MAX) continue;
                if(minTime[e] > minTime[s] + bus.time) {
                    minTime[e] = minTime[s] + bus.time;
                    if(i == N-1) {
                        isTimeMachine = true;
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minTime = new long[N+1];
        Arrays.fill(minTime, MAX);
        buses = new Bus[M];
        isTimeMachine = false;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            buses[i] = new Bus(start, target, time);
        }

        checkedTimeMachine();
        if(isTimeMachine) {
            sb.append(-1);
        } else {
            for(int i=2; i<N+1; i++) {
                sb.append(minTime[i] == MAX ? -1:minTime[i]);
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
