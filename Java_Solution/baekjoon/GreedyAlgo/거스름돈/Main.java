import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = 1000 - Integer.parseInt(br.readLine());
        int coins[] = {500, 100, 50, 10, 5, 1};
        int cnt = 0;

        for(int coin: coins) {
            while(money >= coin) {
                money -= coin;
                cnt += 1;
            }
        }

        System.out.println(cnt);
    }
}