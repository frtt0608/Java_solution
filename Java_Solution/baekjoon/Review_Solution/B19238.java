import java.util.*;
import java.io.*;

public class B19238 {
    static int N, M, K, curPassenger, passCnt;
    static int[] dx={1,0,-1,0}, dy={0,1,0,-1};
    static int[][] map;
    static int[][] passengers;
    static ArrayList<Integer>[][] targets;
    static Taxi curTaxi;
    static boolean isFinish;

    static class Taxi {
        int x, y;
        int d, fuel;

        Taxi(int x, int y, int d, int fuel) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.fuel = fuel;
        }
    }

    static class Person {
        int x, y;
        int diff;

        Person(int x, int y, int diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<=0 || x>N || y<=0 || y>N) return true;
        return false;
    }

    public static void getTargetPassenger(ArrayList<Person> persons) {
        persons.sort(new Comparator<Person>(){
            @Override
            public int compare(Person p1, Person p2) {
                if(p1.diff == p2.diff) {
                    if(p1.x == p2.x) {
                        return p1.y - p2.y;
                    }

                    return p1.x - p2.x;
                }

                return p1.diff - p2.diff;
            }
        });

        Person myPassenger = persons.get(0);
        curPassenger = passengers[myPassenger.x][myPassenger.y];
        passengers[myPassenger.x][myPassenger.y] = 0;
        curTaxi = new Taxi(myPassenger.x, myPassenger.y,
                            0, curTaxi.fuel-myPassenger.diff);
    }

    public static void takePassenger() {
        Queue<Taxi> que = new LinkedList<>();
        que.offer(curTaxi);
        boolean[][] visited = new boolean[N+1][N+1];
        visited[curTaxi.x][curTaxi.y] = true;
        ArrayList<Person> persons = new ArrayList<>();

        while(!que.isEmpty()) {
            Taxi taxi = que.poll();
            int x = taxi.x;
            int y = taxi.y;

            if(passengers[x][y] >= 1 && taxi.fuel >= 0) {
                persons.add(new Person(x, y, taxi.d));
            }

            if(taxi.fuel < 0) continue;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                que.offer(new Taxi(nx, ny, taxi.d+1, taxi.fuel-1));
            }
        }

        if(persons.size() > 0) {
            getTargetPassenger(persons);
        } else {
            isFinish = true;
        }
    }

    public static void moveTarget() {
        Queue<Taxi> que = new LinkedList<>();
        que.offer(curTaxi);
        boolean[][] visited = new boolean[N+1][N+1];
        visited[curTaxi.x][curTaxi.y] = true;

        while(!que.isEmpty()) {
            Taxi taxi = que.poll();
            int x = taxi.x;
            int y = taxi.y;

            if(taxi.fuel >= 0 && targets[x][y].contains(curPassenger)) {
                curTaxi = new Taxi(x, y, 0, taxi.fuel+taxi.d*2);
                return;
            }

            if(taxi.fuel < 0) continue;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(isWall(nx, ny) || map[nx][ny] == 1) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                que.offer(new Taxi(nx, ny, taxi.d+1, taxi.fuel-1));
            }
        }

        isFinish = true;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        passengers = new int[N+1][N+1];
        targets = new ArrayList[N+1][N+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                targets[i][j] = new ArrayList<>();
            }
        }

        st = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(st.nextToken());
        int ty = Integer.parseInt(st.nextToken());

        for(int i=1; i<M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            passengers[sx][sy] = i;
            targets[ex][ey].add(i);
        }

        curTaxi = new Taxi(tx, ty, 0, K);

        while(passCnt < M) {
            takePassenger();
            moveTarget();
            
            if(isFinish) {
                curTaxi.fuel = -1;
                break;
            }
            
            passCnt += 1;
        }

        System.out.println(curTaxi.fuel);
    }
}   