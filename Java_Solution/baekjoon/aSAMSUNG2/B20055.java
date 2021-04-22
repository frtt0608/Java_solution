import java.util.*;
import java.io.*;

public class B20055 {
    static int N, K, zeroCount;
    static int[] belt;
    static boolean isEnding = false;
    static boolean[] robot;


    public static void rotateBelt() {
        int temp = belt[2*N];
        for(int i=0; i<2*N-1; i++) {
            belt[2*N-i] = belt[2*N-1-i];
        }
        belt[1] = temp;

        for(int i=0; i<N-1; i++) {
            robot[N-i] = robot[N-1-i];
        }
        robot[1] = false;
    }

    public static void moveRobot() {
        if(robot[N])
            robot[N] = false;

        for(int i=N-1; i>=1; i--) {
            if(robot[i]) {
                if(belt[i+1] > 0 && !robot[i+1]) {
                    robot[i] = false;
                    robot[i+1] = true;
                    belt[i+1] -= 1;
                }
            }
        }
    }

    public static void locatedRobot() {
        if(!robot[1] && belt[1] > 0) {
            robot[1] = true;
            belt[1] -= 1;
        } 
    }

    public static void checkZeroCount() {
        zeroCount = 0;
        for(int i=1; i<2*N+1; i++) {
            if(belt[i] == 0) {
                zeroCount += 1;

                if(zeroCount >= K) {
                    isEnding = true;
                    return; 
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2*N+1];
        robot = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<2*N+1; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int turn = 0;

        while(true) {
            turn += 1;

            rotateBelt();
            moveRobot();
            locatedRobot();
            checkZeroCount();
            if(isEnding)
                break;
        }

        System.out.println(turn);
    }
}