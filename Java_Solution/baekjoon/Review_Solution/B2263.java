import java.util.*;
import java.io.*;

public class B2263 {
    static int N;
    static int[] inOrder, postOrder, inOrderIndex;
    static StringBuilder preOrder;

    public static void searchRootNode(int inOrderSi, int inOrderEi, int postOrderSi, int postOrderEi) {
        if(inOrderSi > inOrderEi || postOrderSi > postOrderEi) return;

        int root = postOrder[postOrderEi];
        int rootIndex = inOrderIndex[root];
        int leftTreeSize = rootIndex - inOrderSi;
        preOrder.append(root+" ");

        searchRootNode(inOrderSi, rootIndex-1, postOrderSi, postOrderSi+leftTreeSize-1);
        searchRootNode(rootIndex+1, inOrderEi, postOrderSi+leftTreeSize, postOrderEi-1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        postOrder = new int[N];
        inOrderIndex = new int[N+1];
        preOrder = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inOrderIndex[inOrder[i]] = i;
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        searchRootNode(0, N-1, 0, N-1);
        System.out.println(preOrder.toString());
    }
}   
