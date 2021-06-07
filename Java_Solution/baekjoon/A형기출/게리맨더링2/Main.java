import java.util.*;
import java.io.*;

public class Main {
    static int N, totalPeople, minResult;
    static int[][] map;

    public static boolean isWall(int x, int y) {
        return x<0 || x>=N || y<0 || y>=N;
    }

    public static void dividedLocation(int x, int y, int d1, int d2) {
        int minPeople = Integer.MAX_VALUE;
        int maxPeople = 0;
        int allPeople = totalPeople;
        int rangeY = 0;
        int[] people = new int[5];

        // 1구역
        for(int i=0; i<x+d1; i++) {
            if(i < x) rangeY = y;
            else rangeY -= 1;

            for(int j=0; j<=rangeY; j++) {
                people[1] += map[i][j];
            }
        }

        // 2구역
        for(int i=0; i<=x+d2; i++) {
            if(i <= x)  rangeY = y+1;
            else rangeY += 1;

            for(int j=rangeY; j<N; j++) {
                people[2] += map[i][j];
            }
        }

        // 3구역
        rangeY = y-d1-1;
        for(int i=x+d1; i<N; i++) {
            if(i < x+d1+d2) rangeY += 1;
            else rangeY = y+d2-d1;

            for(int j=0; j<rangeY; j++) {
                people[3] += map[i][j];
            }
        }

        // 4구역
        rangeY = y+d2+1;
        for(int i=x+d2+1; i<N; i++) {
            if(i <= x+d1+d2) rangeY -=1;
            else rangeY = y+d2-d1;

            for(int j=rangeY; j<N; j++) {
                people[4] += map[i][j];
            }
        }

        for(int i=1; i<5; i++) {
            allPeople -= people[i];
            minPeople = Math.min(minPeople, people[i]);
            maxPeople = Math.max(maxPeople, people[i]);
        }

        minPeople = Math.min(minPeople, allPeople);
        maxPeople = Math.max(maxPeople, allPeople);
        minResult = Math.min(minResult, maxPeople - minPeople);
    }

    public static void searchTotalCase() {
        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                for(int d1=1; d1<N; d1++) {
                    for(int d2=1; d2<N; d2++) {
                        if(isWall(x+d1, y-d1) ||
                            isWall(x+d1, y+d2) || 
                            isWall(x+d1+d2, y+d2-d1)) continue;

                        dividedLocation(x, y, d1, d2);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        minResult = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalPeople += map[i][j];
            }
        }

        searchTotalCase();
        System.out.println(minResult);
    }
}