import java.util.*;
import java.io.*;

public class Main {
    static int N, K, zeroCount;
    static int[] beltArr;
    static boolean[] hasRobot;

    public static void rotateBelt() {
        int[] rotateBeltArr = new int[2*N];
        boolean[] rotateHasRobot = new boolean[N];

        rotateBeltArr[0] = beltArr[2*N-1];
        for(int i=1; i<2*N; i++) {
            rotateBeltArr[i] = beltArr[i-1];
        }

        // 내려가는 위치
        hasRobot[N-1] = false;
        for(int i=1; i<N; i++) {
            rotateHasRobot[i] = hasRobot[i-1];
        }

        beltArr = Arrays.copyOfRange(rotateBeltArr, 0, 2*N);
        hasRobot = Arrays.copyOfRange(rotateHasRobot, 0, N);
    }

    public static void moveRobot() {
        hasRobot[N-1] = false;
        for(int i=N-2; i>=0; i--) {
            if(hasRobot[i]) {
                if(!hasRobot[i+1] && beltArr[i+1] >= 1) {
                    hasRobot[i] = false;
                    hasRobot[i+1] = true;
                    beltArr[i+1] -= 1;
                }
            }
        }
    }

    public static void checkZero() {
        zeroCount = 0;

        for(int i=0; i<2*N; i++) {
            if(beltArr[i] == 0) {
                zeroCount += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        zeroCount = 0;
        beltArr = new int[2*N];
        hasRobot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) {
            beltArr[i] = Integer.parseInt(st.nextToken());
        }

        int stage = 0;

        while(true) {
            stage += 1;

            rotateBelt();
            moveRobot();
            
            if(!hasRobot[0] && beltArr[0] >= 1) {
                hasRobot[0] = true;
                beltArr[0] -= 1;
            }
            
            checkZero();

            if(zeroCount >= K) 
                break;
        }

        System.out.println(stage);
    }
}