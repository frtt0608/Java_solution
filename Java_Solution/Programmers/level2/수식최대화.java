import java.util.*;

class 수식최대화 {
    long answer;
    char[] operArr = {'*', '-', '+'};
    boolean[] visited;
    ArrayList<Character> opers;
    ArrayList<Long> nums;
    
    public long calc(long num1, long num2, char oper) {
        if(oper == '*') {
            return num1 * num2;   
        }
        else if(oper == '+') {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    }
    
    public void dfs(int cnt, char[] prior) {
        if(cnt == 3) {
            ArrayList<Long> copyNums = new ArrayList<>(nums);
            ArrayList<Character> copyOpers = new ArrayList<>(opers);
            
            for(int i=0; i<3; i++) {
                for(int j=0; j<copyOpers.size(); j++) {
                    if(copyOpers.get(j) == prior[i]) {
                        long num1 = copyNums.remove(j);
                        long num2 = copyNums.remove(j);
                        char oper = copyOpers.remove(j);
                        
                        long calc_res = calc(num1, num2, oper);
                        
                        copyNums.add(j, calc_res);
                        j -= 1;
                    }
                }
            }
            
            answer = Math.max(answer, Math.abs(copyNums.get(0)));
            return;
        }
        
        for(int i=0; i<3; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            prior[cnt] = operArr[i];
            dfs(cnt+1, prior);
            visited[i] = false;
        }
    }
    
    public long solution(String expression) {
        answer = 0;
        String tempEXP = expression.replace("*", " ").replace("+", " ").replace("-", " ");
        // System.out.println(tempEXP);
        String[] numArr = tempEXP.split(" ");
        opers = new ArrayList<>();
        nums = new ArrayList<>();
        visited = new boolean[3];
    
        for(String num: numArr) {
            nums.add(Long.parseLong(num));
        }
        
        for(int i=0; i<expression.length(); i++) {
            char oper = expression.charAt(i);
            
            if(oper >= '0' && oper <= '9') continue;
            
            opers.add(oper);
        }
        
        dfs(0, new char[3]);
        
        return answer;
    }
}