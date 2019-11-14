// 무선충전
// #1 1200
// #2 3290
// #3 16620
// #4 40650
// #5 52710
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class S5644 {
    static int M, A, moveA[], moveB[], BC[], res;
    static int dx[]={0, -1,0,1,0}, dy[]={0, 0,1,0,-1};
    static int map[][];
    static Node v[][];

    static class Node {
        ArrayList<Integer> array;
    
        public Node() {
            this.array = new ArrayList<>();
        }
    }
    // Node class

    static Boolean checkWall(int x, int y) {
        if(x>10 || x<=0 || y>10 || y<=0) return true;
        return false;
    }
    // 벽 판별 함수

    static int max(ArrayList<Integer> array, int temp) {
        int max = 0;
        int index = 0;
        for(int i:array) {
            if(i==temp) continue;
            if(BC[i] > max) {
                max = BC[i];
                index = i;
            }
        }
        return index;
    }
    // array에서 최대 P 찾기

    static void init_map(int x, int y, int C, int P, int idx) {
        for(int c=1; c<=C; c++) {
            int up_x = x-c;
            int down_x = x+c;
            int right_y = y+c;
            int left_y = y-c;
            if(!checkWall(up_x, y)) { 
                map[up_x][y] += P;
                v[up_x][y].array.add(new Integer(idx));
                for(int n=1; n<=C-c; n++) {
                    if(!checkWall(up_x,y-n)) {
                        map[up_x][y-n] += P;
                        v[up_x][y-n].array.add(new Integer(idx));
                    }
                    if(!checkWall(up_x,y+n)) {
                        map[up_x][y+n] += P; 
                        v[up_x][y+n].array.add(new Integer(idx));
                    }
                }
            }
            if(!checkWall(down_x, y)) {
                map[down_x][y] += P;
                v[down_x][y].array.add(new Integer(idx));
                for(int n=1; n<=C-c; n++) {
                    if(!checkWall(down_x,y-n)) {
                        map[down_x][y-n] += P; 
                        v[down_x][y-n].array.add(new Integer(idx));
                    }
                    if(!checkWall(down_x,y+n)) {
                        map[down_x][y+n] += P; 
                        v[down_x][y+n].array.add(new Integer(idx));
                    }
                }
            }
            if(!checkWall(x, left_y)) {
                map[x][left_y] += P;
                v[x][left_y].array.add(new Integer(idx));
            }
            if(!checkWall(x, right_y)) {
                map[x][right_y] += P;
                v[x][right_y].array.add(new Integer(idx));
            }
        }
    }
    // map에 충전범위 초기화
    
    static void wireless_charge() {
        int Ax = 1;
        int Ay = 1;
        int Bx = 10;
        int By = 10;
        
        for(int i=0; i<M+1; i++) {
            Ax = Ax + dx[moveA[i]];
            Ay = Ay + dy[moveA[i]];
            Bx = Bx + dx[moveB[i]];
            By = By + dy[moveB[i]];
            // System.out.println(res);
            // System.out.println(i+":  "+Ax+", "+Ay+"  "+Bx+", "+By+"   "+map[Ax][Ay]+", "+map[Bx][By]);
            if(map[Ax][Ay]!=0 && map[Bx][By]==0) {
                res += BC[max(v[Ax][Ay].array, 100)];
            } 
            else if(map[Ax][Ay]==0 && map[Bx][By]!=0) {
                res += BC[max(v[Bx][By].array, 100)];
            }
            else if(map[Ax][Ay]!=0 && map[Bx][By]!=0) {
                if(Ax==Bx && Ay==By) {
                    if(v[Ax][Ay].array.size() == 1) {
                        res += BC[v[Ax][Ay].array.get(0)];
                    } else {
                        int idx = max(v[Ax][Ay].array, 100);
                        res += BC[idx];
                        res += BC[max(v[Ax][Ay].array, idx)];
                    }
                } else {
                    Boolean flag = false;
                    for(int n:v[Ax][Ay].array) {
                        if(v[Bx][By].array.contains(n)) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag) {
                        if(v[Ax][Ay].array.size()==1 && v[Bx][By].array.size()>1) {
                            int idx = max(v[Ax][Ay].array, 100);
                            res += BC[idx];
                            res += BC[max(v[Bx][By].array, idx)];
                        } else if(v[Ax][Ay].array.size()>1 && v[Bx][By].array.size()==1) {
                            int idx = max(v[Bx][By].array, 100);
                            res += BC[idx];
                            res += BC[max(v[Ax][Ay].array, idx)];
                        } else if(v[Ax][Ay].array.size()==1 && v[Bx][By].array.size()==1) {
                            res += BC[v[Ax][Ay].array.get(0)];
                        }
                        else {
                            int temp1 = 0;
                            int temp2 = 0;
                            int idx = 0;
                            idx = max(v[Ax][Ay].array, 100);
                            temp1 += BC[idx];
                            temp1 += BC[max(v[Bx][By].array, idx)];
                            idx = max(v[Bx][By].array, 100);
                            temp2 += BC[idx];
                            temp2 += BC[max(v[Ax][Ay].array, idx)];
                            if(temp1 >= temp2) res += temp1;
                            else res += temp2; 
                        }
                    } else {
                        res += BC[max(v[Ax][Ay].array, 100)];
                        res += BC[max(v[Bx][By].array, 100)];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
            M = sc.nextInt();
            A = sc.nextInt();
            moveA = new int[M+1];
            moveB = new int[M+1];
            map = new int[11][11];
            v = new Node[11][11];
            BC = new int[A];
            for(int i=1; i<11; i++) {
                for(int j=1; j<11; j++) {
                    v[i][j] = new Node();
                }
            }
            // v배열 초기화
            
            for(int i=1; i<M+1; i++) {
                moveA[i] = sc.nextInt();
            }
            for(int i=1; i<M+1; i++) {
                moveB[i] = sc.nextInt();
            }

            for(int i=0; i<A; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int C = sc.nextInt();
                int P = sc.nextInt();
                BC[i] = P;
                map[y][x] += P;
                v[y][x].array.add(new Integer(i));
                init_map(y, x, C, P, i);
            }
            // for(int i=1; i<11; i++) {
            //     for(int j=1; j<11; j++) {
            //         System.out.print(map[i][j]+" ");
            //     }
            //     System.out.println();
            // }
            res=0;
            wireless_charge();
            System.out.println("#" + tc + " " + res);
        }
        sc.close();
    }
}