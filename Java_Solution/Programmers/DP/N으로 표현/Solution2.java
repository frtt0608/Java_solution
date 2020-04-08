import java.util.*;

class Solution {
    private int N, number;
    HashMap<Integer, HashSet<Integer>> mapDP;
    HashSet<Integer> setDP;
    int temp, answer, num1, num2;
    
    public void DP(int cnt, int continue_N) {
        setDP = new LinkedHashSet<>();
        continue_N *= N;
        setDP.add(continue_N);
        
        if(number == continue_N) {
            if(answer==-1 || answer > cnt) {
                answer = cnt;
                return;
            }
        }
        
        for(int i=1; i<=cnt/2; i++) {
            Iterator<Integer> iter1 = mapDP.get(i).iterator();
            Iterator<Integer> iter2 = mapDP.get(cnt-i).iterator();
        
            while(iter1.hasNext()) {
                num1 = iter1.next();
                while(iter2.hasNext()) {
                    num2 = iter2.next();
                
                    for(Operator_enum_ver op: Operator_enum_ver.values()) {
                        temp = op.Calculate(num1, num2);
                        if(cnt==4 && i==2 && number==110) {
                            System.out.println(cnt + ": " + num1+ "," + num2);
                        }
                        if(temp == number) {
                            if(answer==-1 || answer > cnt) {
                                answer = cnt;
                                return;
                            }
                        }
                        setDP.add(temp);
                    }
                }
            }
        }
    }
    
    public int solution(int N, int number) {
        answer = -1;
        this.N = N; this.number = number;
        temp=0; num1=0; num2=0;
        mapDP = new HashMap<>();
        setDP = new HashSet<>();
        setDP.add(N);
        mapDP.put(1, setDP);
        
        int continue_N = 11;
        int cnt = 2;
        while(continue_N <= 11111111) {
            DP(cnt, continue_N);
            mapDP.put(cnt, setDP);
            continue_N = continue_N*10 + 1;
            cnt++;
        }
        
        return answer;
    }
    
    public enum Operator_enum_ver {
        Plus {
            int Calculate(int num1, int num2) {return num1+num2;}
        },
        Mul {
            int Calculate(int num1, int num2) {return num1*num2;}
        },
        Minus {
            int Calculate(int num1, int num2) {return num1-num2;}
        },
        BackMinus {
            int Calculate(int num1, int num2) {return num2-num1;}
        },
        Div {
            int Calculate(int num1, int num2) {
                if(num2==0) {return 0;}
                else {return num1/num2;}
            }
        },
        BackDiv {
            int Calculate(int num1, int num2) {
                if(num1==0) {return 0;}
                else {return num2/num1;}
            }
        };
        
        abstract int Calculate(int num1, int num2);
    }
}