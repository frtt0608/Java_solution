import java.util.*;
import java.io.*;

public class B11657 {
    static final int MAX = Integer.MAX_VALUE;
    static int N, M;
    static Bus[] buses;
    static long[] minTime;

    static class Bus {
        int start, end;
        int time;

        Bus(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }

    public static boolean calculateMinTime() {
        minTime[1] = 0;
        for(int i=0; i<N; i++) {
            for(Bus bus: buses) {
                int e = bus.end;

                if(minTime[bus.start] == MAX) continue;
                if(minTime[e] > minTime[bus.start] + bus.time) {
                    minTime[e] = minTime[bus.start] + bus.time;

                    if(i == N-1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        buses = new Bus[M];
        minTime = new long[N+1];
        Arrays.fill(minTime, MAX);
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            buses[i] = new Bus(start, end, time);
        }

        if(!calculateMinTime()) {
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
