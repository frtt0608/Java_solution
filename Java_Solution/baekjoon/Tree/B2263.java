import java.util.*;
import java.io.*;

public class B2263 {
    static int N;
    static int[] inOrder, postOrder, inOrderIndex;
    static StringBuilder preOrder;

    public static void findParentNode(int inOrderSi, int inOrderEi,
                                        int postOrderSi, int postOrderEi) {
        if(inOrderSi>inOrderEi || postOrderSi>postOrderEi) return;

        int root = postOrder[postOrderEi];
        preOrder.append(root + " ");

        int rootIndex = inOrderIndex[root];
        int leftTreeSize = rootIndex - inOrderSi;    
    
        findParentNode(inOrderSi, rootIndex-1, postOrderSi, postOrderSi+leftTreeSize-1);
        findParentNode(rootIndex+1, inOrderEi, postOrderSi+leftTreeSize, postOrderEi-1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        inOrder = new int[N+1];
        inOrderIndex = new int[N+1];
        postOrder = new int[N+1];
        preOrder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inOrderIndex[inOrder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        findParentNode(1, N, 1, N);
        System.out.println(preOrder.toString());
    } 
}