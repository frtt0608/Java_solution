import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, map[][], minPerson, totalPerson;

    static void sumSection(int x, int y, int d1, int d2) {
        int visited[][] = new int[N+1][N+1];
        int sumSect[] = new int[6];
        int k;
        
        //sect1
        k = y-1;
        for(int i=1; i<=x-1; i++) {
            for(int j=1; j<=y; j++) {
                sumSect[1] += map[i][j];
                visited[i][j] = 1;
            }
        }
        for(int i=x; i<x+d1; i++) {
            for(int j=1; j<=k; j++) {
                sumSect[1] += map[i][j];
                visited[i][j] = 1;
            }
            k--;
        }

        //sect2
        k = y+1;
        for(int i=1; i<=x; i++) {
            for(int j=y+1; j<=N; j++) {
                sumSect[2] += map[i][j];
                visited[i][j] = 2;
            }
        }
        for(int i=x+1; i<=x+d2; i++) {
            k++;
            for(int j=k; j<=N; j++) {
                sumSect[2] += map[i][j];
                visited[i][j] = 2;
            }
        }

        //sect3
        k = y-d1;
        for(int i=x+d1; i<=x+d1+d2; i++) {
            for(int j=1; j<k; j++) {
                sumSect[3] += map[i][j];
                visited[i][j] = 3;
            }
            k++;
        }
        for(int i=x+d1+d2+1; i<=N; i++) {
            for(int j=1; j<y-d1+d2; j++) {
                sumSect[3] += map[i][j];
                visited[i][j] = 3;
            }
        }

        //sect4
        k = y+d2;
        for(int i=x+d2+1; i<=x+d1+d2; i++) {
            for(int j=N; j>=k; j--) {
                sumSect[4] += map[i][j];
                visited[i][j] = 4;
            }
            k--;
        }
        for(int i=x+d1+d2+1; i<=N; i++) {
            for(int j=y-d1+d2; j<=N; j++) {
                sumSect[4] += map[i][j];
                visited[i][j] = 4;
            }
        }

        //sect5
        sumSect[5] = totalPerson - sumSect[1] - sumSect[2] - sumSect[3] - sumSect[4];

        Arrays.sort(sumSect);
        int minDiff = sumSect[5] - sumSect[1];
        //System.out.println(x+","+y+" / "+d1+","+d2 + " : " + minDiff);
        minPerson = Math.min(minPerson, minDiff);
    }

    static void caseSection(int x, int y) {
        for(int d1=1; d1<=N-2; d1++) {
            for(int d2=1; d2<=N-2; d2++) {
                if(y-d1>=1 && y+d2<=N && x+d1+d2<=N) {
                    sumSection(x,y,d1,d2);
                }
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        minPerson = 999999;

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N;j ++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalPerson += map[i][j];
            }
        }

        for(int i=1; i<=N-2; i++) {
            for(int j=2; j<=N-1; j++) {
                caseSection(i,j);
            }
        }

        System.out.println(minPerson);
    }
}