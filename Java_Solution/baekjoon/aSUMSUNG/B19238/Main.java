import java.util.*;
import java.io.*;

public class Main {
    static int N, M, gas, tx, ty, count;
    static int[] dx={0,0,1,-1}, dy={1,-1,0,0};
    static int[][] map;
    static Person[] persons;
    static Person target;

    static class Person {
        int pos;
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

        while(!tq.isEmpty()) {
            Taxi t = tq.poll();
            int x = t.x;
            int y = t.y;

            if(t.gas < 0) {
                break;
            }

            if(x == target.ex && y == target.ey) {
                gas = t.gas + (gas - t.gas)*2;
                tx = x;
                ty = y;
                count += 1;
                return;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || map[nx][ny] == -1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                tq.offer(new Taxi(nx, ny, t.gas-1));
            }
        }

        gas = 0;
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void pickupPerson() {
        Queue<Taxi> tq = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        tq.offer(new Taxi(tx, ty, gas));
        visited[tx][ty] = true;
        int curDistance = -1;
        List<Person> pl = new ArrayList<>();
        
        target = null;
        while(!tq.isEmpty()) {
            Taxi t = tq.poll();
            int x = t.x;
            int y = t.y;

            if(curDistance != -1 && gas-t.gas > curDistance) {
                break;
            }

            if(map[x][y] >= 1) {
                Person p = persons[map[x][y]-1];
                p.diff = gas - t.gas;
                pl.add(p);
                curDistance = p.diff;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || map[nx][ny] == -1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                tq.offer(new Taxi(nx, ny, t.gas-1));
            }
        }
        
        if(pl.size() > 0) {
            for(int i=0; i<pl.size(); i++) {
                Person p = pl.get(i);
                if(target == null) {
                    target = p;
                } else {
                    if(target.pos > p.pos) {
                        target = p;
                    }
                }
            }

            tx = target.sx;
            ty = target.sy;
            map[tx][ty] = 0;
            gas -= target.diff;
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
        count = 0;
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    map[i][j] = -1;
                }
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
            p.pos = sx * 100 + sy;
            persons[i] = p;
            map[sx][sy] = i+1;
        }

        while(true) {
            pickupPerson();

            if(target == null || gas <= 0) {
                break;
            }
            
            goDeparture();
            if(gas <= 0 || count == M)
                break;
        }

        System.out.println(count == M ? gas:-1);
    }
}
