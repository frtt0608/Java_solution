import java.util.*;
import java.io.*;

public class Main2 {
    static int N, M, gas, tx, ty, count, pi;
    static int[] dx={0,0,1,-1}, dy={1,-1,0,0};
    static int[][] map;
    static Person[] persons;
    static boolean[] vp;
    static Person target;

    static class Person {
        int sx, sy;
        int ex, ey;
        int diff;

        Person(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
    }

    static class Taxi {
        int x, y;
        int gas;
        
        Taxi(int x, int y, int gas) {
            this.x = x;
            this.y = y;
            this.gas = gas;
        }
    }

    public static void goDeparture() {
        Queue<Taxi> tq = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        tq.offer(new Taxi(tx, ty, gas));
        visited[tx][ty] = true;
        boolean flag = false;

        while(!tq.isEmpty()) {
            Taxi t = tq.poll();
            int x = t.x;
            int y = t.y;

            if(x == target.ex && y == target.ey) {
            
                // System.out.println("도착 전 가스: " + t.gas);
                vp[pi] = true;
                map[target.sx][target.sy] = 0;
                gas = t.gas + (gas - t.gas)*2;
                // System.out.println("도착 후 가스: " + gas);
                tx = x;
                ty = y;
                flag = true;
                count += 1;
                break;
            }

            if(t.gas == 0) {
                continue;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny)) continue;
                if(map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                if(map[nx][ny] == 9) {
                    if(nx != target.ex || ny != target.ey) {
                        continue;
                    }
                }
                tq.offer(new Taxi(nx, ny, t.gas-1));
            }
        }

        if(!flag) {
            gas = 0;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void pickupPerson() {
        target = null;
        for(int i=0; i<M; i++) {
            if(vp[i]) continue;

            Queue<Taxi> tq = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            tq.offer(new Taxi(tx, ty, gas));
            visited[tx][ty] = true;

            while(!tq.isEmpty()) {
                Taxi t = tq.poll();
                int x = t.x;
                int y = t.y;

                if(t.gas == 0) continue;

                if(x == persons[i].sx && y == persons[i].sy) {
                    Person p = persons[i];
                    p.diff = gas - t.gas;
                    if(target == null) {
                        target = p;
                        pi = i;
                    } else {
                        if(target.diff > p.diff) {
                            target = p;
                            pi = i;
                        } else if(target.diff == p.diff) {
                            if(target.sx > p.sx) {
                                target = p;
                                pi = i;
                            } else if(target.sx == p.sx) {
                                if(target.sy > p.sy) {
                                    target = p;
                                    pi = i;
                                }
                            }
                        }
                    }
                    break;
                }

                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
    
                    if(isWall(nx, ny)) continue;
                    if(map[nx][ny] == 1) continue;
                    if(visited[nx][ny]) continue;
    
                    visited[nx][ny] = true;
                    if(map[nx][ny] == 9) {
                        if(nx != persons[i].sx || ny != persons[i].sy) {
                            continue;
                        }
                    }
                    tq.offer(new Taxi(nx, ny, t.gas-1));
                }
            }
        }

        if(target != null) {
            tx = target.sx;
            ty = target.sy;
            gas -= target.diff;
            // System.out.println("태운 승객 위치: " + target.sx + "," + target.sy + " >> " + target.diff);
            // System.out.println("남은 가스:" + gas);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        persons = new Person[M];
        vp = new boolean[M];
        count = 0;
        pi = 0;
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        tx = Integer.parseInt(st.nextToken())-1;
        ty = Integer.parseInt(st.nextToken())-1;
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken())-1;
            int sy = Integer.parseInt(st.nextToken())-1;
            int ex = Integer.parseInt(st.nextToken())-1;
            int ey = Integer.parseInt(st.nextToken())-1;

            Person p = new Person(sx, sy, ex, ey);
            persons[i] = p;
            map[sx][sy] = 9;
        }

        while(gas > 0) {
            pickupPerson();

            if(target == null || gas <= 0) {
                break;
            }
            
            goDeparture();
            if(count == M) break;
        }

        System.out.println(count == M ? gas:-1);
    }
}
