// 폴리오미노

import java.io.*;

public class Main1 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String board = br.readLine();
        board = board.replace("XXXX", "AAAA");
        board = board.replace("XX", "BB");

        if(board.contains("X")) {
            System.out.println(-1);
        } else{
            System.out.println(board);
        }
    }
}