

import java.io.*;
// java.io.FileInputStream;
import java.util.*;
// java.util.Scanner;

public class FileRead {
    public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);
    int partInt = in.nextInt();
    in.nextLine();
    String str = in.next();
    }
}