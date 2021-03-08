import java.util.*;
import java.io.*;

public class B1655 {
    static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        PriorityQueue<Integer> ASCque = new PriorityQueue<>((num1, num2) -> num1-num2);
        PriorityQueue<Integer> DESCque = new PriorityQueue<>((num1, num2) -> num2-num1);

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
        
            if(ASCque.size() == DESCque.size()) {
                DESCque.offer(num);
            } else {
                ASCque.offer(num);
            }

            if(!ASCque.isEmpty() && !DESCque.isEmpty()) {
                if(ASCque.peek() < DESCque.peek()) {
                    int temp = ASCque.poll();
                    ASCque.offer(DESCque.poll());
                    DESCque.offer(temp);
                }
            }

            sb.append(DESCque.peek()+"\n");
        }

        System.out.println(sb.toString());
    }
}   
