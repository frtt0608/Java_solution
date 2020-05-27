import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, map[][], allPeople, minResult;

    static void assignPoint() {
        for(int i=0; i<N-2; i++) {
            for(int j=1; j<N-1; j++) {
                divideSection(i,j);
            }
        }
    }

    static void divideSection(int x, int y) {
        for(int d1=1; d1<=N-2; d1++) {
            for(int d2=1; d2<=N-2; d2++) {
                if(y-d1 < 0 || y+d2 >= N || x+d1+d2 >= N) continue;
                checkSection(x, y, d1, d2);
            }
        }
    }

    static void checkSection(int x, int y, int d1, int d2) {
        int section[] = new int[5];

        // sec1
        for(int i=0; i<x; i++) {
            for(int j=0; j<=y; j++) {
                section[0] += map[i][j];
            }
        }
        for(int i=0; i<d1; i++) {
            for(int j=0; j<=y-1-i; j++) {
                section[0] += map[x+i][j];
            }
        }

        // sec2
        for(int i=0; i<x; i++) {
            for(int j=y+1; j<N; j++) {
                section[1] += map[i][j];
            }
        }
        for(int i=0; i<=d2; i++) {
            for(int j=y+1+i; j<N; j++) {
                section[1] += map[x+i][j];
            }
        }

        // sec3
        for(int i=0; i<d2; i++) {
            for(int j=0; j<y-d1+i; j++) {
                section[2] += map[x+d1+i][j];
            }   
        }
        for(int i=x+d1+d2; i<N; i++) {
            for(int j=0; j<y-d1+d2; j++) {
                section[2] += map[i][j];
            }
        }

        //sec 4
        for(int i=1; i<=d1; i++) {
            for(int j=y+d2+1-i; j<N; j++) {
                section[3] += map[x+d2+i][j];
            }
        }
        for(int i=x+d1+d2+1; i<N; i++) {
            for(int j=y-d1+d2; j<N; j++) {
                section[3] += map[i][j];
            }
        }

        section[4] = allPeople - section[0] - section[1] - section[2] - section[3];
        int minPeople = 999999;
        int maxPeople = 0;
        for(int people: section) {
            minPeople = Math.min(minPeople, people);
            maxPeople = Math.max(maxPeople, people);
        }

        // System.out.println(Arrays.toString(section));

        minResult = Math.min(minResult, maxPeople - minPeople);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        minResult = 999999;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                allPeople += map[i][j];
            }
        }
        assignPoint();
        // checkSection(2, 4, 2, 1);

        System.out.println(minResult);
    }
}