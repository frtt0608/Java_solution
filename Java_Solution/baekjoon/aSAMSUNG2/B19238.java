import java.util.*;
import java.io.*;

public class B19238 {
    static int N, M, tx, ty, nx, ny;
    static int curPassenger;
    static Taxi curTaxi;
    static int[] dx={-1,1,0,0}, dy={0,0,-1,1};
    static int[][] map, passenger;
    static ArrayList<Integer>[][] targets;
    static boolean isEnding;

    static class Taxi {
        int x, y, d;
        int fuel;

        Taxi(int x, int y, int d, int fuel) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.fuel = fuel;
        }
    }

    static class Person {
        int x, y;
        int d;

        Person(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<=0 || x>N || y<=0 || y>N) return true;
        return false;
    }

    public static void takePassenger() {
        Queue<Taxi> que = new LinkedList<>();
        que.offer(curTaxi);
        boolean[][] visited = new boolean[N+1][N+1];
        visited[curTaxi.x][curTaxi.y] = true;
        ArrayList<Person> persons = new ArrayList<>();

        while(!que.isEmpty()) {
            Taxi cur = que.poll();

            if(passenger[cur.x][cur.y] >= 1 && cur.fuel >= 0) {
                persons.add(new Person(cur.x, cur.y, cur.d));
            }

            if(cur.fuel < 0) {
                continue;
            }

            for(int dir=0; dir<4; dir++) {
                nx = cur.x + dx[dir];
                ny = cur.y + dy[dir];

                if(isWall(nx, ny) || map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                que.offer(new Taxi(nx, ny, cur.d+1, cur.fuel-1));
            }
        }

        if(persons.size() > 0) {
            persons.sort(new Comparator<Person>() {
                @Override
                public int compare(Person p1, Person p2) {
                    if(p1.d == p2.d) {
                        if(p1.x == p2.x) {
                            return p1.y - p2.y;
                        } else {
                            return p1.x - p2.x;
                        }
                    } else {
                        return p1.d - p2.d;
                    }
                }
            });
    
            Person p = persons.get(0);
            if(curTaxi.fuel >= p.d) {
                curTaxi = new Taxi(p.x, p.y, 0, curTaxi.fuel-p.d);
                curPassenger = passenger[p.x][p.y];
                passenger[p.x][p.y] = 0;
                return;
            }
        }
        
        isEnding = true;
    }

    public static void moveTarget() {
        Queue<Taxi> que = new LinkedList<>();
        que.offer(curTaxi);
        boolean[][] visited = new boolean[N+1][N+1];
        visited[curTaxi.x][curTaxi.y] = true;

        while(!que.isEmpty()) {
            Taxi cur = que.poll();

            if(targets[cur.x][cur.y].size() > 0 && cur.fuel >= 0) {
                for(int target: targets[cur.x][cur.y]) {
                    if(target == curPassenger) {
                        curTaxi = new Taxi(cur.x, cur.y, 0, cur.fuel+cur.d*2);
                        curPassenger = 0;
                        return;
                    }
                }
            }

            if(cur.fuel < 0) {
                continue;
            }

            for(int dir=0; dir<4; dir++) {
                nx = cur.x + dx[dir];
                ny = cur.y + dy[dir];

                if(isWall(nx, ny) || map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                que.offer(new Taxi(nx, ny, cur.d+1, cur.fuel-1));
            }
        }

        isEnding = true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        passenger = new int[N+1][N+1];
        targets = new ArrayList[N+1][N+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                targets[i][j] = new ArrayList<>();
            }
        }

        st = new StringTokenizer(br.readLine());
        tx = Integer.parseInt(st.nextToken());
        ty = Integer.parseInt(st.nextToken());

        for(int i=1; i<M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            passenger[sx][sy] = i;
            targets[ex][ey].add(i);
        }

        curTaxi = new Taxi(tx, ty, 0, fuel);
        while(M > 0) {
            takePassenger();
            moveTarget();
            
            if(isEnding) {
                curTaxi.fuel = -1;
                break;
            }
            
            M -= 1;
        }

        System.out.println(curTaxi.fuel);
    }
}