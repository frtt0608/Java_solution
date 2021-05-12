import java.util.*;

class 로또 {
    
    public int setRanking(int sameCnt) {
        int rank = sameCnt != 0 ? 7-sameCnt : 6;
        return rank;
    }
    
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        Set<Integer> lottoNumber = new HashSet<>();
        int zeroCnt = 0;
        int sameCnt = 0;
        
        for(int lotto: lottos) {
            if(lotto == 0) zeroCnt += 1;
            else lottoNumber.add(lotto);
        }
        
        for(int win_num: win_nums) {
            if(lottoNumber.contains(win_num)) {
                sameCnt += 1;
            }
        }
        
        answer[1] = setRanking(sameCnt);
        answer[0] = setRanking(sameCnt + zeroCnt);
        
        return answer;
    }
}