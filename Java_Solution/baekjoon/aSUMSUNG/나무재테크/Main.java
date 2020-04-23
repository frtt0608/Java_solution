// NN 땅
// 양분이 모든 칸에 5
// 봄 -> 나이만큼 양분+, 나이+1, 여러나무면 어린나무부터, 양분이 부족하면 사망
// 여름 -> 죽은나무가 양분으로(나이/2)
// 가을 -> 나무 번식, 5의배수, 인접한 8개칸
// 겨울 -> 땅에 양분추가 A[r][c]만큼
// K년이 지난후 나무 개수


import java.util.*;
import java.io.*;


class Tree implements Comparable<Tree>{
    int x;
    int y;
    int age;

    Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree tree) {
        return this.age >= tree.age ? 1:-1;
    }
}
/**
 * Main
 */
public class Main {
    static int N, M, K, A[][], food[][], treeCnt;
    static int dx[] = {0,0,1,-1,1,1,-1,-1}, dy[] = {1,-1,0,0,-1,1,-1,1};
    static Deque<Tree> treeList;
    static Stack<Tree> supplyFood;

    static boolean wall(int x, int y) {
        if(x>N || x<=0 || y>N || y<=0) return true;
        return false;
    }

    static void spring() {
        Deque<Tree> updateTree = new LinkedList<>();
        int size = treeList.size();
        for(int i=0; i<size; i++) {
            Tree tree = treeList.poll();

            if(food[tree.x][tree.y] >= tree.age) {
                food[tree.x][tree.y] -= tree.age;
                tree.age += 1;
                updateTree.addLast(tree);
            } else {
                supplyFood.add(tree);
            }
        }

        while(!updateTree.isEmpty()) {
            treeList.addLast(updateTree.poll());
        }
    }

    static void summer() {
        while(!supplyFood.isEmpty()) {
            Tree tree = supplyFood.pop();
            food[tree.x][tree.y] += tree.age/2;
        }
    }

    static void autumn() {
        Stack<Tree> createTree = new Stack<>();
        for(Tree tree:treeList) {
            if(tree.age%5==0) {
                for(int dir=0; dir<8; dir++) {
                    int nx = tree.x+dx[dir];
                    int ny = tree.y+dy[dir];
                    if(wall(nx,ny)) continue;
                    createTree.add(new Tree(nx,ny,1));
                }
            }
        }

        while(!createTree.isEmpty())
            treeList.addFirst(createTree.pop());
    }

    static void winter() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                food[i][j] += A[i][j];
            }
        }
    }

    static public void main(String args[]) throws IOException {
        //long start = System.currentTimeMillis();
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        K=sc.nextInt();
        A=new int[N+1][N+1];
        food=new int[N+1][N+1];
        supplyFood=new Stack<>();
        treeList = new LinkedList<>();
        
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                A[i][j] = sc.nextInt();
                food[i][j] = 5;
            }
        }
        
        for(int i=0; i<M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int age = sc.nextInt();
            treeList.addLast(new Tree(x,y,age));
        }

        while(K>0) {
            spring();
            summer();
            autumn();
            winter();
            K--;
        }

        treeCnt = treeList.size();
        //long end = System.currentTimeMillis();
        System.out.println(treeCnt);
        //System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }
}