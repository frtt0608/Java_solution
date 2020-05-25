import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main1 {
    static String input_N[], input_M[];
    static int N, M, cnt;


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Set<String> inputSet = new HashSet<>();
        ArrayList<String> resList = new ArrayList<>();
        String inputM = "";

        for(int i=0; i<N; i++) {
            inputSet.add(br.readLine());
        }
        for(int i=0; i<M; i++) {
            inputM = br.readLine();
            if(inputSet.contains(inputM)) {
                resList.add(inputM);
            }
        }

        Collections.sort(resList);

        System.out.println(resList.size());
        for(String res: resList) {
            System.out.println(res);
        }
    }
}