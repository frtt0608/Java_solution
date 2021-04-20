import java.util.*;
import java.io.*;

public class B14890 {
    static int N, L, roadCount;
    static int[][] map;
    static boolean[][][] visited;

    public static void installRunway(int si, int sj, int type, boolean dir) {

        if(type == 0) {
            if(dir) {
                for(int j=sj; j<sj+L; j++) {
                    visited[si][j][type] = true;
                }
            } else {
                for(int j=sj; j>sj-L; j--) {
                    visited[si][j][type] = true;
                }
            }
        } else {
            if(dir) {
                for(int i=si; i<si+L; i++) {
                    visited[i][sj][type] = true;
                }
            } else {
                for(int i=si; i>si-L; i--) {
                    visited[i][sj][type] = true;
                }
            }
        }
    }

    public static void findCanPassbyRoad() {
        int sHeight, sCnt, diffHeight, nCnt;
        boolean flag;
        visited = new boolean[N][N][2];

        for(int i=0; i<N; i++) {
            flag = true;
            sHeight = map[i][0];
            sCnt = 1;

            for(int j=1; j<N; j++) {
                if(visited[i][j][0]) continue;

                diffHeight = Math.abs(sHeight - map[i][j]);
                if(diffHeight == 0) {
                    sCnt += 1;
                } else if(diffHeight == 1) {
                    nCnt = 0;

                    if(sHeight < map[i][j]) {
                        if(sCnt >= L) {
                            for(int k=j-1; k>j-1-L; k--) {
                                if(k < 0) break;
                                if(!visited[i][k][0]) {
                                    nCnt += 1;
                                } else {
                                    break;
                                }

                                if(nCnt == L) break;
                            }

                            if(nCnt == L) {
                                sCnt = 1;
                                installRunway(i, j-1, 0, false);
                            } else {
                                flag = false;
                                break;
                            }

                        } else {
                            flag = false;
                            break;
                        }

                    } else {
                        for(int k=j; k<j+L; k++) {
                            if(k >= N) break;
                            if(map[i][j] == map[i][k]) {
                                nCnt += 1;
                            } else {
                                flag = false;
                                break;
                            }

                            if(nCnt == L) break;
                        }
                        
                        if(nCnt == L) {
                            sCnt = 1;
                            installRunway(i, j, 0, true);
                        } else {
                            flag = false;
                            break;
                        }
                    }
                } else {
                    flag = false;
                    break;
                }

                sHeight = map[i][j];
            }

            if(flag) {
                System.out.println("가로방향: " + i);
                roadCount += 1;
            }
        }

        for(int j=0; j<N; j++) {
            flag = true;
            sHeight = map[0][j];
            sCnt = 1;

            for(int i=1; i<N; i++) {
                if(visited[i][j][1]) continue;
                diffHeight = Math.abs(sHeight - map[i][j]);

                if(diffHeight == 0) {
                    sCnt += 1;
                } else if(diffHeight == 1) {
                    nCnt = 0;

                    if(sHeight < map[i][j]) {
                        if(sCnt >= L) {
                            for(int k=i-1; k>i-1-L; k--) {
                                if(k < 0) break;
                                if(!visited[k][j][1]) {
                                    nCnt += 1;
                                } else {
                                    break;
                                }

                                if(nCnt == L) break;
                            }

                            if(nCnt == L) {
                                sCnt = 1;
                                installRunway(i-1, j, 1, false);
                            } else {
                                flag = false;
                                break;
                            }

                        } else {
                            // System.out.println("x: " + i + ", " + j);
                            flag = false;
                            break;
                        }

                    } else {
                        for(int k=i; k<i+L; k++) {
                            if(k >= N) break;
                            if(map[i][j] == map[k][j]) {
                                nCnt += 1;
                            } else {
                                break;
                            }

                            if(nCnt == L) break;
                        }
                        
                        if(nCnt == L) {
                            sCnt = 1;
                            installRunway(i, j, 1, true);
                        } else {
                            // System.out.println("x: " + i + ", " + j);
                            flag = false;
                            break;
                        }
                    }
                } else {
                    // System.out.println("x: " + i + ", " + j);
                    flag = false;
                    break;
                }

                sHeight = map[i][j];
            }

            if(flag) {
                System.out.println("세로방향: " + j);
                roadCount += 1;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findCanPassbyRoad();
        System.out.println(roadCount);
    }
}