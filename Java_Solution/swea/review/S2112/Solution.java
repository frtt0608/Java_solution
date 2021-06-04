import java.util.*;
import java.io.*;

public class Solution {
    static int D, W, K, minInsertCnt;
    static int[][] film;
    static boolean passFlag;

    public static boolean checkPerformance(int[][] tempFilm, int insertCnt) {
        int AB, cnt;
        boolean check;

        for(int j=0; j<W; j++) {
            AB = tempFilm[0][j];
            cnt = 1;
            check = false;
            for(int i=1; i<D; i++) {
                if(AB == tempFilm[i][j]) {
                    cnt += 1;
                } else {
                    AB = tempFilm[i][j];
                    cnt = 1;
                }

                if(cnt == K) {
                    check = true;
                    break;
                }
            }

            if(!check) {
                return false;
            }
        }

        return true;
    }

    public static int[][] copyFilm() {
        int[][] tempFilm = new int[D][W];

        for(int i=0; i<D; i++) {
            for(int j=0; j<W; j++) {
                tempFilm[i][j] = film[i][j];
            }
        }

        return tempFilm;
    }

    public static int[][] insertedMedicine(int[] insertInfo) {
        int[][] tempFilm = copyFilm();

        for(int i=0; i<D; i++) {
            if(insertInfo[i] != -1) {
                Arrays.fill(tempFilm[i], insertInfo[i]);
            }
        }

        return tempFilm;
    }

    public static void getTotalCase(int index, int target, int insertCnt, int[] insertInfo) {
        if(passFlag) return;

        if(target == insertCnt) {    
            int[][] tempFilm = insertedMedicine(insertInfo);
            passFlag = checkPerformance(tempFilm, insertCnt);

            if(passFlag) {
                // System.out.println(Arrays.toString(insertInfo));
                minInsertCnt = target;
            }
            return;
        }

        if(index >= D) return;

        insertInfo[index] = 0;
        getTotalCase(index+1, target, insertCnt+1, insertInfo);
        insertInfo[index] = 1;
        getTotalCase(index+1, target, insertCnt+1, insertInfo);
        insertInfo[index] = -1;
        getTotalCase(index+1, target, insertCnt, insertInfo);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            passFlag = false;
            minInsertCnt = 1000;
            int[] insertInfo = new int[D];

            for(int i=0; i<D; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            
            for(int cnt=0; cnt<=D; cnt++) {
                Arrays.fill(insertInfo, -1);
                getTotalCase(0, cnt, 0, insertInfo);

                if(passFlag) break;
            }

            System.out.println("#" + tc + " " + minInsertCnt);
        }
    }
}