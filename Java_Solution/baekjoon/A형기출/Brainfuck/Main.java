import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int memorySize, codeSize, inputSize;
    static int max_loop_idx;
    static String input;
    static String[] code;
    static int[] pointArr, loop, checkLoop;
    static boolean flag;

    static void brainF() {
        int pointer = 0;
        int oper_idx = 0;
        int input_idx = 0;
        max_loop_idx = 0;
        flag = false;

        for(int i=0; i<50000000; i++) {
            if(oper_idx >= codeSize) {
                flag = true;
                return;
            }

            if(code[oper_idx].equals("+")) {
                pointArr[pointer] = (pointArr[pointer]+1) % 256;

            } else if(code[oper_idx].equals("-")) {
                pointArr[pointer] = (pointArr[pointer]-1 < 0) ? 255: pointArr[pointer]-1;

            } else if(code[oper_idx].equals("<")) {
                pointer = (pointer-1 < 0) ? memorySize-1: pointer-1;

            } else if(code[oper_idx].equals(">")) {
                pointer = (pointer+1) % memorySize;
                
            } else if(code[oper_idx].equals("[")) {
                if(pointArr[pointer]==0) {
                    oper_idx = loop[oper_idx]-1;
                }

            } else if(code[oper_idx].equals("]")) {
                if(pointArr[pointer]!=0) {
                    // checkLoop[oper_idx]++;
                    oper_idx = loop[oper_idx]-1;
                }

            } else if(code[oper_idx].equals(".")) {
                // print

            } else if(code[oper_idx].equals(",")) {
                pointArr[pointer] = (input_idx >= inputSize) ? 255:(int)input.charAt(input_idx);
                input_idx++;
            }

            oper_idx++;
            max_loop_idx = Math.max(max_loop_idx, oper_idx);
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());    
            memorySize = Integer.parseInt(st.nextToken());
            codeSize = Integer.parseInt(st.nextToken());
            inputSize = Integer.parseInt(st.nextToken());

            code = br.readLine().split("");
            input = br.readLine();
            pointArr = new int[memorySize+1];
            loop = new int[codeSize+1];
            checkLoop = new int[codeSize+1];

            Stack<Integer> loopStack = new Stack<>();
            
            int loop_idx = 0;
            for(int i=0; i<codeSize; i++) {
                if(code[i].equals("[")) {
                    loopStack.push(i);
                } else if(code[i].equals("]")) {
                    loop_idx = loopStack.pop();
                    loop[i] = loop_idx;
                    loop[loop_idx] = i;
                }
            }

            brainF();

            if(flag) System.out.println("Terminates");
            else {
                System.out.println("Loops " + loop[max_loop_idx]+" "+max_loop_idx);
            }
        }
    }
}