import java.util.*;
import java.io.*;

public class B5639 {
    static int N;
    static ArrayList<Integer> preOrder;
    static StringBuilder postOrder;

    public static void searchPostOrder(int start, int end) {
        if(end == start) return;
        int root = preOrder.get(start);
        int rootIndex = searchRoot(start, end, root);
        
        
        if(end - start == 1) {
            postOrder.append(root+"\n");
            return;
        }

        if(rootIndex == -1) {
            searchPostOrder(start+1, end);
        } else {
            searchPostOrder(start+1, rootIndex);
            searchPostOrder(rootIndex, end);
        }

        postOrder.append(root+"\n");
    }

    public static int searchRoot(int start, int end, int root) {
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
