import java.util.*;
import java.io.*;


class Shark {
    int r;
    int c;
    int speed;
    int dir;
    int zip;

    Shark(int r, int c, int speed, int dir, int zip) {
        this.r = r;
        this.c = c;
        this.speed = speed;
        this.dir = dir;
        this.zip = zip;
    }

    Shark(int r, int c, int zip) {
        this.r = r;
        this.c = c;
        this.zip = zip;
    }
}
/**
 * Main
 */
public class Main {
    static int R, C, M, totalZip;
    static int[] dr={-1,1,0,0}, dc={0,0,1,-1}; // 0:위, 1:아래, 2:오른쪽, 3:왼쪽
    static Shark map[][];

    static boolean wall(int r, int c) {
        if(r>R || r<=0 || c>C || c<=0) return true;
        return false;
    }

    static int changeDir(int dir) {
        if(dir==0) return 1;
        if(dir==1) return 0;
        if(dir==2) return 3;
        if(dir==3) return 2;
        return dir;
    }

    static Shark[][] init_map(Shark initMap[][]) {
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                initMap[i][j] = new Shark(i,j,0);
            }
        }
        return initMap;
    }

    static void captureShark(int c) {
        for(int i=1; i<=R; i++) {
            if(map[i][c].zip > 0) {
                System.out.println(map[i][c].zip);
                totalZip += map[i][c].zip;
                map[i][c] = new Shark(i,c,0);
                break;
            }
        }
    }

    static Shark[][] moveShark() {
        Shark copy_map[][] = new Shark[R+1][C+1];
        copy_map = init_map(copy_map);
        int temp = 0;
    
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                if(map[i][j].zip > 0) {
                    int r = map[i][j].r;
                    int c = map[i][j].c;
                    int speed = map[i][j].speed;
                    int dir = map[i][j].dir;
                    int zip = map[i][j].zip;

                    if(speed > 0) {
                        if(dir==0 || dir==1) {
                            temp = 2*R - 2;
                        } else {
                            temp = 2*C - 2;
                        }
                        speed %= temp;

                        for(int s=0; s<speed; s++) {
                            int nr = r + dr[dir];
                            int nc = c + dc[dir];
                            if(wall(nr,nc)) {
                                dir = changeDir(dir);
                                r += dr[dir];
                                c += dc[dir];
                            } else {
                                r=nr;
                                c=nc;
                            }
                        }
                    }
        
                    if(copy_map[r][c].zip == 0 || copy_map[r][c].zip < zip) {
                        copy_map[r][c] = new Shark(r,c,speed,dir,zip);
                    }
                }
            }
        }

        return copy_map;
    }


    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new Shark[R+1][C+1];

        map = init_map(map);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            map[r][c] = new Shark(r,c,s,d,z);
        }

        for(int i=1; i<=C; i++) {
            captureShark(i);
            map = moveShark();
            for(int j=1; j<=R; j++) {
                for(int k=1; k<=C; k++) {
                    if(map[j][k].zip > 0)
                        System.out.println(j+", "+k+": "+map[j][k].speed+" / "+map[j][k].zip);
                }
            }
            System.out.println("---------------------");
        }

        System.out.println(totalZip);
    }
}