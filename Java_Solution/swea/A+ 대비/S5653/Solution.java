import java.util.*;
import java.io.*;

class Cell implements Comparable<Cell> {
    int x;
    int y;
    int time;
    int life;
    boolean flag;

    Cell(int x, int y, int time, int life, boolean flag) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.life = life;
        this.flag = flag;
    }

    @Override
    public int compareTo(Cell cell) {
        return cell.life - this.life;
    }
}
/**
 * Solution
 */
public class Solution {
    static int N, M, K;

    static PriorityQueue<Cell> cultivateCell(int t, PriorityQueue<Cell> que, boolean[][] visited) {
        int size = que.size();
        int dx[] = {1,-1,0,0};
        int dy[] = {0,0,1,-1};
        PriorityQueue<Cell> tempQue = new PriorityQueue<>();

        for(int i=0; i<size; i++) {
            Cell c = que.poll();

            // 시간 - 배양시작 시간 = 생명력이면 활성화!
            if(!c.flag) {
                if(t - c.time == c.life) {
                    tempQue.add(new Cell(c.x, c.y, c.time, c.life, true));
                } else {
                    tempQue.add(c);
                }
            } else {
                // 활성화세포 중 번식하기
                if(t - c.time <= c.life*2) {
                    for(int dir=0; dir<4; dir++) {
                        int nx = c.x + dx[dir];
                        int ny = c.y + dy[dir];
                        if(visited[nx][ny]) continue;
                        visited[nx][ny] = true;
                        tempQue.add(new Cell(nx, ny, t, c.life, false));
                    }
                    if(t - c.time < c.life*2) {
                        tempQue.add(c);
                    }
                } else {
                    // 활성화세포 중 시간이 다 지났으면 죽은세포로 포함x
                    // que.add(new Cell(c.x, c.y, c.time, 0, true));
                }
            }
        }

        return tempQue;
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            int len = 1001;
            int map[][] = new int[len][len];
            boolean visited[][] = new boolean[len][len];
            PriorityQueue<Cell> que = new PriorityQueue<>();
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[len/2-N+i][len/2-M+j] = Integer.parseInt(st.nextToken());
                    if(map[len/2-N+i][len/2-M+j]==0) continue;
                    visited[len/2-N+i][len/2-M+j] = true;
                    que.add(new Cell(len/2-N+i, len/2-M+j, 0, map[len/2-N+i][len/2-M+j], false));
                }
            }

            for(int t=1; t<=K; t++) {
                // System.out.println(que.size());
                que = cultivateCell(t, que, visited);
            }

            System.out.println("#" + tc + " " + que.size());
        }
    }
}
