// NN크기의 공간에 물고리 M 아기상어1, 한칸에 물고기 최대 1마리
// 크기를 가지고 있다. 상어:2, 초당 상하좌우 이동
// 자기보다 크면 못지나감, 나머지는 지나감
// 자기보다 작으면 잡아먹음, 같으면 못먹고 지나감
// 먹을수 있는 물고기 없으면 엄마 요청
// 1마리면 먹으러감
// 1마리보다 많으면 가까운 물고기부터
// 가까울 물고기가 많으면 가장 위에부터
// 이동과 동시에 먹구 빈칸
// 자신의 크기와 같은 수의 물고기 먹으면 크기 1증가

// 요청없이 몇초동안 물고기를 먹는지 구하라

import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, map[][], shark_x, shark_y, shark_age, resTime;
    static Deque<Fish> fishList;
    static int[] dx = {1,-1,0,0}, dy={0,0,1,-1};

    static class Fish implements Comparable<Fish> {
        int x;
        int y;
        int age;
    
        Fish(int x, int y, int age) {
            this.x=x;
            this.y=y;
            this.age=age;
        }
    
        @Override
        public int compareTo(Fish fish) {
            int this_diff = Math.abs(this.x-shark_x) + Math.abs(this.y-shark_y);
            int arg_diff = Math.abs(fish.x-shark_x) + Math.abs(fish.y-shark_y);
            if(this.age > fish.age) return 1;
            else if(this.age < fish.age) return -1;
            else {
                if(this_diff > arg_diff) return 1;
                else if(this_diff < arg_diff) return -1;
                else {
                    if(this.x > fish.x) return 1;
                    else if(this.x < fish.x) return -1;
                    else {
                        if(this.y > fish.y) return 1;
                    }
                }
            }
            
            return -1;
        }
    }

    static boolean wall(int x, int y) {
        if(x>=N || x<0 || y>=N || y<0) return true;
        return false;
    }

    static void BFS() {
        
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        map = new int[N][N];
        LinkedList<Fish> tempList = new LinkedList<>();
        fishList = new LinkedList<>();
    
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>=1 && map[i][j]<=6) {
                    if(map[i][j]==1) tempList.addFirst(new Fish(i,j,map[i][j]));
                    else tempList.addLast(new Fish(i,j,map[i][j]));
                } else if(map[i][j]==9) {
                    shark_x = i;
                    shark_y = j;
                    shark_age = 2;
                }
            }
        }
        if(tempList.size()==0) {
            System.out.println(0);
        } else {
            Collections.sort(tempList);
            fishList.addAll(tempList);
            
            
            
            System.out.println(resTime);
        }
    }
}