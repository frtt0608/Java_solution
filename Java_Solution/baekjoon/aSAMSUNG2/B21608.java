import java.util.*;
import java.io.*;

public class B21608 {
    static int N;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] room;
    static Map<Integer, List<Integer>> likeMap;
    static PriorityQueue<Node> possibleNodes;

    static class Node implements Comparable<Node> {
        int r, c;
        int likeCnt, emptyCnt;

        Node(int r, int c, int likeCnt, int emptyCnt) {
            this.r = r;
            this.c = c;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Node node) {
            if(this.likeCnt == node.likeCnt) {
                if(this.emptyCnt == node.emptyCnt) {
                    if(this.r == node.r) {
                        return this.c - node.c;
                    }

                    return this.r - node.r;
                }

                return node.emptyCnt - this.emptyCnt;
            }

            return node.likeCnt - this.likeCnt;
        }
    }

    public static boolean isWall(int x, int y) {
        if(x<0 || x>=N || y<0 || y>=N) return true;
        return false;
    }

    public static void checkLocation(int r, int c, int num) {
        int emptyCnt = 0;
        int likeCnt = 0;
        List<Integer> likes = likeMap.get(num);

        for(int dir=0; dir<4; dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if(isWall(nr, nc)) continue;
            
            if(room[nr][nc] == 0)
                emptyCnt += 1;
            else if(likes.contains(room[nr][nc]))
                likeCnt += 1;
        }

        possibleNodes.offer(new Node(r, c, likeCnt, emptyCnt));
    }
    
    public static void locateStudent(int num) {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(room[r][c] != 0) continue;

                checkLocation(r, c, num);
            }
        }

        Node locate = possibleNodes.poll();
        room[locate.r][locate.c] = num;
    }

    public static int calculateResult() {
        int cnt, result = 0;

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                List<Integer> likes = likeMap.get(room[r][c]);
                cnt = 0;

                for(int dir=0; dir<4; dir++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if(isWall(nr, nc)) continue;

                    if(likes.contains(room[nr][nc])) {
                        cnt += 1;
                    }
                }

                if(cnt > 0)
                    result += Math.pow(10, cnt-1);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        room = new int[N][N];
        likeMap = new HashMap<>();
        possibleNodes = new PriorityQueue<>();

        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            List<Integer> likes = new ArrayList<>();
            possibleNodes.clear();

            for(int j=0; j<4; j++) {
                likes.add(Integer.parseInt(st.nextToken()));
            }

            likeMap.put(num, likes);
            locateStudent(num);
        }

        System.out.println(calculateResult());
    }
}