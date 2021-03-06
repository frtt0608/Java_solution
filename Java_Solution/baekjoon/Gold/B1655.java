import java.io.*;
import java.util.*;

public class B1655 {
    static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // PriorityQueue<Integer> minQue = new PriorityQueue<>((num1, num2) -> (num1 - num2));
        PriorityQueue<Integer> minQue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return num1 - num2;
            }
        });

        PriorityQueue<Integer> maxQue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });

        N = Integer.parseInt(br.readLine());
        for(int i=1; i<N+1; i++) {
            int num = Integer.parseInt(br.readLine());
            
            if(minQue.size() == maxQue.size()) maxQue.offer(num);
            else minQue.offer(num);

            if(!minQue.isEmpty() && !maxQue.isEmpty()) {
                if(minQue.peek() < maxQue.peek()) {
                    int minQuePoll = minQue.poll();
                    minQue.offer(maxQue.poll());
                    maxQue.offer(minQuePoll);
                }
            }

            sb.append(maxQue.peek() + "\n");
        }

        System.out.println(sb.toString());
    }
}