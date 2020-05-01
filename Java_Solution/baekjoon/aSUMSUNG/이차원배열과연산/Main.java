import java.util.*;
import java.io.*;


class Node implements Comparable<Node> {
    int num;
    int cnt;

    Node(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node node) {
        if(this.cnt > node.cnt) return 1;
        else if(this.cnt == node.cnt) {
            if(this.num > node.num) return 1;
        }
        return -1;
    }
}
/**
 * Main
 */
public class Main {
    static int r, c, k, map[][], resTime;
    static int R, C;
    static HashMap<Integer, Integer> newMap;
    static PriorityQueue<Node> pq[];

    static void operator_R() {
        pq = new PriorityQueue[R];
        int maxC=0;
        
        // for문 돌려서
        // key가 있으면 value+1
        // 없으면 value=1 저장
        for(int i=0; i<R; i++) {
            newMap = new HashMap<>();
            pq[i] = new PriorityQueue<>();
            for(int j=0; j<C; j++) {
                if(map[i][j]==0) continue;
                if(newMap.containsKey(map[i][j])) {
                    newMap.put(map[i][j], newMap.get(map[i][j])+1);
                } else {
                    newMap.put(map[i][j], 1);
                }
            }
            maxC = Math.max(maxC, newMap.size());
            Iterator<Integer> newMapiter = newMap.keySet().iterator();
            while(newMapiter.hasNext()) {
                int key = newMapiter.next();
                pq[i].offer(new Node(key, newMap.get(key)));
            }
        }

        C=maxC*2;
        updateMap(0);
    }

    static void operator_C() {
        pq = new PriorityQueue[C];
        int maxR=0;
        
        // for문 돌려서
        // key가 있으면 value+1
        // 없으면 value=1 저장
        for(int i=0; i<C; i++) {
            newMap = new HashMap<>();
            pq[i] = new PriorityQueue<>();
            for(int j=0; j<R; j++) {
                if(map[j][i]==0) continue;
                if(newMap.containsKey(map[j][i])) {
                    newMap.put(map[j][i], newMap.get(map[j][i])+1);
                } else {
                    newMap.put(map[j][i], 1);
                }
            }
            maxR = Math.max(maxR, newMap.size());
            Iterator<Integer> newMapiter = newMap.keySet().iterator();
            while(newMapiter.hasNext()) {
                int key = newMapiter.next();
                pq[i].offer(new Node(key, newMap.get(key)));
            }
        }

        R=maxR*2;
        updateMap(1);
    }

    static void updateMap(int type) {
        map = new int[R][C];

        if(type==0) {
            updateMapR();
        } else {
            updateMapC();
        }
    }

    static void updateMapR() {
        int j;
        for(int i=0; i<R; i++) {
            j=0;
            while(!pq[i].isEmpty()) {
                Node node = pq[i].poll();
                map[i][j] = node.num;
                j++;
                map[i][j] = node.cnt;
                j++;
            }
            j++;
            while(j<C) {
                map[i][j] = 0;
                j++;
            }
        }
    }

    static void updateMapC() {
        int j;
        for(int i=0; i<C; i++) {
            j=0;
            while(!pq[i].isEmpty()) {
                Node node = pq[i].poll();
                map[j][i] = node.num;
                j++;
                map[j][i] = node.cnt;
                j++;
            }
            j++;
            while(j<R) {
                map[j][i] = 0;
                j++;
            }
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());
        R=3;
        C=3;
        map = new int[R][C];

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(true) {
            if(r<R && c<C && map[r][c]==k) break;
            if(R>=C) operator_R();
            else operator_C();
            
            /* for(int i=0; i<R; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println("--------------------"); */
            
            resTime++;
            if(resTime>100) {
                resTime = -1; 
                break;
            }
        }
        
        System.out.println(resTime);
    }
}