import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");

        int H = Integer.parseInt(input[0]);
        int W = Integer.parseInt(input[1]);

        int map[][] = new int[H][W];
        String weather[][] = new String[H][W];
        
        boolean flag = false;
        int time = 0;

        for(int i=0; i<H; i++) {
            input = br.readLine().split("");
            for(int j=0; j<W; j++) {
                weather[i][j] = input[j];
            }
        }

        for(int i=0; i<H; i++) {
            flag = false;
            time = 0;
            for(int j=0; j<W; j++) {
                if(weather[i][j].equals("c")) {
                    map[i][j] = 0;
                    time = 0;
                    flag = true;
                } else {
                    if(!flag) map[i][j] = -1;
                    else map[i][j] = time;
                }   

                time++;
            }
        }

        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}