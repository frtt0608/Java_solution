import java.io.*;

public class Main {
    static boolean[] broken = new boolean[10];

    public static int isPossible(int target) {
        if(target == 0) {
            return broken[0] ? 0: 1;
        }

        int cnt = 0;
        while(target > 0) {
            if(broken[target%10]) {
                return 0;
            }

            cnt += 1;
            target /= 10;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int channel = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String[] input = new String[0];

        if(M > 0) {
            input = br.readLine().split(" ");
            for(int i=0; i<M; i++) {
                broken[Integer.parseInt(input[i])] = true;
            }
        }

        int answer = Math.abs(channel - 100);

        if(answer > 0) {
            for(int i=0; i<1000000; i++) {
                int target = i;
                int cnt = isPossible(target);
    
                if(cnt > 0) {
                    int pressButton = Math.abs(target - channel);
                    answer = Math.min(answer, cnt + pressButton);
                }
            }
        }
        

        System.out.println(answer);
    }
}