import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N,M,K, supplyFood[][], Food[][], deadTree[][], allTreeCnt;
    static LinkedList<int[]> treeList[][];
    static int dx[] = {1,-1,0,0,1,1,-1,-1}, dy[]={0,0,1,-1,-1,1,1,-1};

    static void spring() {
        int treeSize = 0;
        int tree[]=new int[2];
        int min_tree; 

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                treeSize = treeList[i][j].size();
                for(int k=0; k<treeSize; k++) {
                    tree = treeList[i][j].get(k);
                    if(tree[0] <= Food[i][j]) {
                        min_tree = Math.min(Food[i][j]/tree[0], tree[1]);
                        Food[i][j] -= min_tree*tree[0];
                        treeList[i][j].set(k, new int[] {tree[0]+1, min_tree});
                        deadTree[i][j] += (tree[0]/2)*(tree[1]-min_tree);
                    } else {
                        deadTree[i][j] += (tree[0]/2) * tree[1];
                        treeList[i][j].remove(k);
                        k--;
                        treeSize--;
                    }
                }
            }       
        }
    }
    
    static void summer() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Food[i][j] += deadTree[i][j];
                deadTree[i][j] = 0;
            }
        }
    }

    static void autumn() {
        int tree[];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<treeList[i][j].size(); k++) {
                    if(treeList[i][j].get(k)[0] % 5 == 0) {
                        tree = treeList[i][j].get(k);
                        for(int dir=0; dir<8; dir++) {
                            int nx = i+dx[dir];
                            int ny = j+dy[dir];
                            if(nx>=N || nx<0 || ny>=N || ny<0) continue;
                            if(!treeList[nx][ny].isEmpty() && treeList[nx][ny].get(0)[0] == 1) {
                                treeList[nx][ny].get(0)[1] += tree[1];
                            } else {
                                treeList[nx][ny].addFirst(new int[] {1, tree[1]});
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Food[i][j]  += supplyFood[i][j];    
            }
        }
    }

    static void cntTree() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<treeList[i][j].size(); k++) {
                    allTreeCnt += treeList[i][j].get(k)[1];
                }
            }
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        supplyFood = new int[N][N];
        Food = new int[N][N];
        deadTree = new int[N][N];
        treeList = new LinkedList[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                treeList[i][j] = new LinkedList<>();
            }
        }

        for(int i=0; i<N; i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                supplyFood[i][j] = Integer.parseInt(st.nextToken());
                Food[i][j] = 5;
            }
        }

        init:for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int old = Integer.parseInt(st.nextToken());
            for(int j=0; j<treeList[x][y].size(); j++) {
                if(treeList[x][y].get(j)[0] == old) {
                    treeList[x][y].get(j)[1]+=1;
                    continue init;
                }
            }
            treeList[x][y].add(new int[] {old,1});
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                treeList[i][j].sort((int[] tree1, int[] tree2) -> Integer.compare(tree1[0], tree2[0]));
            }
        }

        while(K>0) {
            spring();
            summer();
            autumn();
            winter();
            K--;
        }

        cntTree();
        System.out.println(allTreeCnt);
    }
}