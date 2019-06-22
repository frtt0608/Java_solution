import java.io.*;
import java.util.*;

public class Stamp {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int num = Integer.parseInt(in.readLine());
        for(int i=0; i<num; i++) {
            System.out.print('#');
        }
    }
}