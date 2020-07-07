// 도서관

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] books;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        books = new int[N];
        input = br.readLine().split(" ");
        int pivot = 0;
        int walking = 0;
        
        for(int i=0; i<N; i++) {
            books[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(books);
        System.out.println(Arrays.toString(books));
        
        for(int i=0; i<N; i++) {
            if(books[i] > 0) {
                pivot = i;
                break;
            }
        }

        for(int i=0; i<pivot; i+=M) {
            walking -= books[i] * 2;
            System.out.println(walking);
        }

        for(int i=N-1; i>=pivot; i-=M) {
            walking += books[i] * 2;
        }
        System.out.println(walking);
        
        walking -= Math.max(-books[0], books[N-1]);
        System.out.println(walking);
    }
}
