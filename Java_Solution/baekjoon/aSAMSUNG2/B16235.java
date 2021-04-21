import java.util.*;
import java.io.*;

public class B16235 {
    static int N, M, K, aliveTreeCnt;
    static int[] dx={0,-1,-1,-1,0,1,1,1}, dy={1,1,0,-1,-1,-1,0,1};
    static int[][] S2D2, food, deadTree;
    static LinkedList<Integer>[][] trees;
    

    public static boolean isWall(int x, int y) {
        if(x<=0 || x>N || y<=0 || y>N) return true;
        return false;
    }

    public static void sortTreeAge() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                trees[i][j].sort((Integer age1, Integer age2) -> (age1 - age2));
            }
        }
    }

    public static void springEatFood() {
        int size, age;

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                
                size = trees[i][j].size();

                for(int k=0; k<size; k++) {
                    age = trees[i][j].poll();
                
                    if(age <= food[i][j]) {
                        food[i][j] -= age;
                        age += 1;
                        trees[i][j].offer(age);
                    } else {
                        deadTree[i][j] += age/2;   
                    }
                }
           }
        }
    }

    public static void summerChangeFood() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                food[i][j] += deadTree[i][j];
                deadTree[i][j] = 0;
            }
        }
    }

    public static void fallGetTree() {
        int nx, ny;

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                
                for(int age: trees[i][j]) {
                    if(age%5 != 0) continue;

                    for(int dir=0; dir<8; dir++) {
                        nx = i + dx[dir];
                        ny = j + dy[dir];
                        if(isWall(nx, ny)) continue;

                        trees[nx][ny].offer(1);
                    }
                }
            }
        }
    }

    public static void winterAddFood() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                food[i][j] += S2D2[i][j];
            }
        }
    }

    public static void getAliveTreeCnt() {
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                System.out.print(trees[i][j].size() + " ");
                aliveTreeCnt += trees[i][j].size();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S2D2 = new int[N+1][N+1];
        food = new int[N+1][N+1];
        deadTree = new int[N+1][N+1];
        trees = new LinkedList[N+1][N+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                food[i][j] = 5;
                S2D2[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new LinkedList<>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            trees[x][y].offer(z);
        }

        while(K > 0) {
            K--;
            springEatFood();
            summerChangeFood();
            fallGetTree();
            winterAddFood();

            sortTreeAge();
        }

        getAliveTreeCnt();
        System.out.println(aliveTreeCnt);
    }
}