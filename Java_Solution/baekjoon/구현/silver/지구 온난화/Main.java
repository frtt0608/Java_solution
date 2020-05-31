import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
/**
 * Main
 */
public class Main {
    static int R, C;
    static int[] dx={0,0,1,-1}, dy={1,-1,0,0};

    static boolean wall(int x, int y) {
        if(x<0 || x>=R || y<0 || y>=C) return true;
        return false;

    }
    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        ArrayList<Node> islandList = new ArrayList<>();
        int seaCnt = 0;
        int sX = R;
        int sY = C;
        int eX = 0;
        int eY = 0;

        String map[][] = new String[R][C];
        String newMap[][] = new String[R][C];

        for(int i=0; i<R; i++) {
            input = br.readLine().split("");
            for(int j=0; j<C; j++) {
                map[i][j] = input[j];
            }
        }

        for(int i=0; i<R; i++) {
            loop:
            for(int j=0; j<C; j++) {
                if(map[i][j].equals("X")) {
                    newMap[i][j] = "X";
                    seaCnt = 0;
                    for(int dir=0; dir<4; dir++) {
                        int nx = i+dx[dir];
                        int ny = j+dy[dir];
                        if(wall(nx,ny) || map[nx][ny].equals(".")) {
                            seaCnt++;
                            if(seaCnt==3) {
                                newMap[i][j] = ".";
                                continue loop;
                            }
                        }
                    }
                    islandList.add(new Node(i,j));
                } else newMap[i][j] = ".";
            }
        }

        for(Node node: islandList) {
            sX = Math.min(sX, node.x);
            sY = Math.min(sY, node.y);
            eX = Math.max(eX, node.x);
            eY = Math.max(eY, node.y);
        }

        for(int i=sX; i<=eX; i++) {
            for(int j=sY; j<=eY; j++) {
                System.out.print(newMap[i][j]);
            }
            System.out.println();
        }
    }
}