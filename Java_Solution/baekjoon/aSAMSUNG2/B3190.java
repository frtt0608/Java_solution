import java.util.*;
import java.io.*;

public class B3190 {
    static int N, time;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    static int[][] map, dirMap;
    static boolean[][] visited;
    static Command[] commands;
    static Deque<Node> deq = new LinkedList<>();

    static class Command {
        int X;
        String C;

        Command(int X, String C) {
            this.X = X;
            this.C = C;
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static boolean movingSnake(int dir) {
        time += 1;

        Node head = deq.peekFirst();
        int nx = head.x + dx[dir];
        int ny = head.y + dy[dir];

        if(isWall(nx, ny) || visited[nx][ny]) {
            return false;
        }

        deq.addFirst(new Node(nx, ny));
        visited[nx][ny] = true;

        if(map[nx][ny] == 0) {
            Node tail = deq.pollLast();
            visited[tail.x][tail.y] = false;
        } else {
            map[nx][ny] = 0;
        }

        System.out.println(nx+", "+ny+" :" + dir);
        print();
        return true;
    }

    public static void print() {
        System.out.println("-------------------");
        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
    }

    public static void startDummy() {
        dirMap = new int[N][N];
        visited = new boolean[N][N];

        deq.offer(new Node(0, 0));
        visited[0][0] = true;
        int dir = 0;
        boolean isEnd = false;
        
        for(Command command: commands) {

            while(time < command.X) {
                if(!movingSnake(dir)) {
                    isEnd = true;
                    break;
                }
            }

            if(isEnd)
                break;

            if(command.C.equals("D")) {
                dir = (dir+1)%4;
            } else {
                dir = (dir+3)%4;
            }
        }

        while(!isEnd) {
            if(!movingSnake(dir)) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            map[x][y] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        commands = new Command[L];
        for(int i=0; i<L; i++) {
            // L: 왼쪽, D: 오른쪽
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();
            commands[i] = new Command(X, C);
        }

        startDummy();
        System.out.println(time);
    }
}