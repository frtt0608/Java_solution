import java.io.*;
import java.util.*;


class Student {
    int num;
    int reco;
    int time;

    Student(int num, int reco, int time) {
        this.num = num;
        this.reco = reco;
        this.time = time;
    }
}
/**
 * Main
 */
public class Main {
    static int N, M, reco[];

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        reco = new int[M];
        ArrayList<Student> stuArr = new ArrayList<>();
        boolean flag = false;

        String input[] = br.readLine().split(" ");
        
        for(int i=0; i<M; i++) {
            reco[i] = Integer.parseInt(input[i]);
        }

        loop:
        for(int i=0; i<M; i++) {
            // for(int k=0; k<N; k++) System.out.print(visited[k][0] + " ");
            // System.out.println();
            flag = false;
            if(stuArr.contains(reco[i])) {
                
            }

            if(!flag && stuArr.size() < N) {
                visited[cnt][0] = reco[i];
                visited[cnt][1] = 1;
                visited[cnt][2] = i;
            } else if(stuArr.size() >= N) {
                int minReco = 10001;
                int minTime = 10001;
                int target = 0;
                recoArr.clear();

                // 추천수 비교
                for(int j=0; j<N; j++) {
                    if(visited[j][0]!=0) {
                        if(minReco > visited[j][1]) {
                            recoArr.clear();
                            minReco = visited[j][1];
                            recoArr.add(j);
                        } else if(minReco == visited[j][1]) {
                            recoArr.add(j);
                        }
                    }
                }
                // 최소 추천수가 여러개인 경우,
                if(recoArr.size() >= 2) {
                    // 시간 비교
                    for(Integer reco: recoArr) {
                        if(minTime > visited[reco][2]) {
                            minTime = visited[reco][2];
                            target = reco;
                        }
                    }
                } else {
                    target = recoArr.get(0);
                }

                visited[target][0] = reco[i];
                visited[target][1] = 1;
                visited[target][2] = i;
            }
        }

        int res[] = new int[N];
        for(int i=0; i<N; i++) {
            res[i] = visited[i][0];
        }

        Arrays.sort(res);

        for(int r:res) {
            if(r!=0)
                System.out.print(r+" ");
        }
    }
}