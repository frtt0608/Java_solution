// // NN 높이가 적힘
// // 한행 또는 한 열 전부가 길
// // 모든 칸의 높이 같거나,
// // 경사로를 놓는다
// // 경사로는 항상 1, 길이 L
// // 낮은칸에 놓는다, L개의 연속된 칸에 경사로의 바닥이 모두 접해야함
// // 높이차 1 // L개의 칸 연속
// // 경사로 중복x 

// import java.util.*;
// import java.io.*;

// /**
//  * Main
//  */
// class XY {
//     int x;
//     int y;
//     int dir;
//     XY(int x, int y, int dir) {
//         this.x = x;
//         this.y = y;
//         this.dir = dir;
//     }
// }

// public class Main {
//     static int N, L, map[][], route, visited[][];

//     static void BFS() {
//         Queue<XY> que = new LinkedList<>();
//         for(int i=0; i<N; i++) 
//             que.offer(new XY(0,i,1));
//         for(int j=0; j<N; j++)
//             que.offer(new XY(j,0,2));
//         int same_cnt = 0;
//         int temp = 0;
//         while(!que.isEmpty()) {
//             XY xy = que.poll();
//             int x = xy.x;
//             int y = xy.y;
//             int dir = xy.dir;
//             int idx = 0;
//             temp = map[x][y];



//             if(dir==1) {
//                 // 아래로
//                 same_cnt=1;
//                 loop1: while(idx<N) {
//                     if(temp==map[idx+1][y]) {
//                         same_cnt+=1;
//                         idx++;
//                         temp = map[idx][y];
//                     } else if(temp-map[idx+1][y]==1) {
//                         // 내리막길
//                         same_cnt=1;
//                         idx++;
//                         int p_temp = map[idx][y];
//                         while(idx<N) {
//                             if(same_cnt==L) {
//                                 for(int i=0; i<L; i++) {
//                                     if(visited[idx-i][y]==1) break loop1;
//                                     visited[idx-i][y] = 1;
//                                 }
//                                 break;
//                             }
//                             if(idx+1 < N && p_temp==map[idx+1][y]) {
//                                 idx++;
//                                 same_cnt++;
//                             }
//                             else break loop1;
//                         }
//                         same_cnt = 0;
//                         temp = map[idx][y];
//                     } else if(temp-map[idx+1][y]==-1){
//                         // 오르막길
//                         if(same_cnt>=L) {
//                             for(int i=0; i<L; i++) {
//                                 if(visited[idx-i][y]==1) break loop1;
//                                 visited[idx-i][y] = 1;
//                             }
//                             same_cnt=1;
//                             idx++;
//                             temp = map[idx][y];
//                         } else break loop1;
//                     } else break;
//                     if(idx>=N-1) {
//                         //System.out.println(x+","+y+":"+dir);
//                         route++;
//                         break;
//                     }
//                 }
//             } else {
//                 // 옆으로
//                 same_cnt=1;
//                 loop2: while(idx<N) {
//                     if(temp == map[x][idx+1]) {
//                         same_cnt++;
//                         idx++;
//                         temp = map[x][idx];
//                     } else if(temp-map[x][idx+1]==1) {
//                         // 내리막길
//                         same_cnt = 1;
//                         idx++;
//                         int p_temp = map[x][idx];
//                         while(true) {
//                             if(same_cnt==L) {
//                                 for(int i=0; i<L; i++) {
//                                     if(visited[x][idx-i]==2) break loop2;
//                                     visited[x][idx-i]=2;
//                                 }
//                                 break;
//                             }
//                             if(idx+1 < N && p_temp == map[x][idx+1]) {
//                                 idx++;
//                                 same_cnt++;
//                             } else break loop2;
//                         }
//                         same_cnt=0;
//                         temp = map[x][idx];
//                     } else if(temp-map[x][idx+1] == -1) {
//                         // 오르막길
//                         if(same_cnt>=L) {
//                             for(int i=0; i<L; i++) {
//                                 if(visited[x][idx-i]==2) break loop2;
//                                 visited[x][idx-i]=2;
//                             }
//                             same_cnt=1;
//                             idx++;
//                             temp = map[x][idx];
//                         } else break loop2;
//                     } else break;
//                     if(idx>=N-1) {
//                         //System.out.println(x+","+y+":"+dir);
//                         route++;
//                         break;
//                     }
//                 }
//             }
//         }
//     }

//     static public void main(String args[]) throws IOException {
//         System.setIn(new FileInputStream("input.txt"));
//         Scanner sc = new Scanner(System.in);

//         N=sc.nextInt();
//         L=sc.nextInt();
//         route = 0;
//         map = new int[N][N];
//         visited = new int[N][N];

//         for(int i=0; i<N; i++) {
//             for(int j=0; j<N; j++) {
//                 map[i][j] = sc.nextInt();
//             }
//         }

//         BFS();
//         System.out.println(route);
//     }
// }