// 점수 계산

import java.util.*;
import java.io.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int problemCnt = 8;
        int totalScore = 0;
        int maxScore = 0;
        int scoreIdx = 0;

        int[] score = new int[problemCnt];
        boolean[] visited = new boolean[problemCnt];

        for(int i=0; i<problemCnt; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        LinkedList<Integer> scoreList = new LinkedList<>();
        
        while(scoreList.size() < 5) {
            maxScore = -1;
            scoreIdx = -1;
            for(int i=0; i<problemCnt; i++) {
                if(visited[i]) continue;

                if(maxScore < score[i]) {
                    maxScore = score[i];
                    scoreIdx = i;
                }
            }

            scoreList.add(scoreIdx);
            visited[scoreIdx] = true;
            totalScore += score[scoreIdx];
        }

        Collections.sort(scoreList);

        System.out.println(totalScore);
        while(!scoreList.isEmpty()) {
            System.out.print(scoreList.poll()+1 + " ");
        }
    }
}