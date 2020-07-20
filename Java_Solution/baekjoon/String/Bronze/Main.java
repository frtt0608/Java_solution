import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine().trim();

        if(input.equals("")) {
            System.out.println(0);
        } else {
            String[] arr = input.split(" ");
            System.out.println(arr.length);
        }
    }
}