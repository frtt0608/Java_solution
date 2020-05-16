import java.util.*;
import java.io.*;


// 경기전 타순을 정한다.
// 9번타자까지 쳤는데 3아웃이 아니면 1번부터 다시
// 1: 안타, 2: 2루타, 3: 3루타, 4: 홈런, 0: 아웃
// 가장 좋아하는 선수인 1번 선수를 4번타자
/*
** Main
*/
// 완전탐색
// 1. perm 타석 세우기
// 2. 게임 진행
// 3. 최댓값 찾기


public class Main {
    static int N, player_input[][], maxScore;

    static public void setPlayer(int idx, int playerArr[], int visited[]) {
        if(idx==10) {
            //System.out.println(Arrays.toString(playerArr));
            // startGame
            startGame(playerArr);
            return;
        }

        for(int i=1; i<10; i++) {
            if(visited[i]==1) continue;
            visited[i] = 1;
            playerArr[i] = idx;
            setPlayer(idx+1, playerArr, visited);
            visited[i] = 0;
        }
    }

    static public void startGame(int number[]) {
        int order = 1;
        int outCnt;
        int bat[] = new int[3];
        int score = 0;

        for(int turn=1; turn<=N; turn++) {
            outCnt = 0;
            bat = new int[3];

            while(outCnt < 3) {
                if(order%10 == 0) order += 1;

                int res = player_input[turn][number[order%10]];
                if(res == 0) {
                    outCnt++;
                } else if(res == 1) {
                    for(int num=2; num>=0; num--) {
                        if(bat[num] > 0) {
                            bat[num] -= 1;
                            if(num==2) score += 1;
                            else bat[num+1] += 1;
                        }
                    }
                    bat[0] += 1;
                } else if(res == 2) {
                    for(int num=2; num>=0; num--) {
                        if(bat[num] > 0) {
                            bat[num] -= 1;
                            if(num>=1) score += 1;
                            else bat[num+2] += 1;
                        }
                    }
                    bat[1] += 1;
                } else if(res == 3) {
                    for(int num=2; num>=0; num--) {
                        if(bat[num] > 0) {
                            bat[num] -= 1;
                            score += 1;
                        }
                    }
                    bat[2] += 1;
                } else if(res==4) {
                    for(int num=2; num>=0; num--) {
                        if(bat[num] > 0) {
                            bat[num] -= 1;
                            score += 1;
                        }
                    }
                    score += 1;
                }

                order += 1;
            }
        }

        maxScore = Math.max(maxScore, score);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        player_input = new int[N+1][10];
        int playerArr[] = new int[] {0,0,0,0,1,0,0,0,0,0};
        int visited[] = new int[] {0,0,0,0,1,0,0,0,0,0};

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<10; j++) {
                player_input[i][j] = Integer.parseInt(st.nextToken());        
            }
        }

        setPlayer(2, playerArr, visited);

        System.out.println(maxScore);
    }
}