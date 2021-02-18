import java.util.*;
import java.io.*;

public class B5639_recur {
    static final int MAX_SIZE = 100001;
    static ArrayList<Integer> preOrder;
    static StringBuilder postOrder;

    public static void searchPostOrder(int start, int end) {
        int root = preOrder.get(start);
        int rightIndex = find(start, end, root);
        
        if(end == start) return;
        else if(end == start + 1) {
            postOrder.append(root + "\n");
            return;
        }

        if(rightIndex == -1) {
            searchPostOrder(start+1, end);
        } else {
            searchPostOrder(start+1, rightIndex);
            searchPostOrder(rightIndex, end);
        }

        postOrder.append(root + "\n");
    }

    public static int find(int start, int end, int root) {
        for(int i=start; i<end; i++) {
            if(root < preOrder.get(i)) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        preOrder = new ArrayList<>();
        postOrder = new StringBuilder();

        while(true) {
            String input = br.readLine();
            if(input == null) break;

            int num = Integer.parseInt(input);
            preOrder.add(num);
        }

        searchPostOrder(0, preOrder.size());
        System.out.println(postOrder.toString());
    }
}