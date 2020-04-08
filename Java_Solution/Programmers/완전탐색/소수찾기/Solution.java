// numbers "17" 3
// numbers "011" 2
// numbers "1234" 14


import java.util.Arrays;
import java.util.HashSet;

class Solution {
    HashSet<Integer> prime;
    
    public void searched(int n, int r, String array[], int visited[], String res) {
        if(n<=r) {
            if("".equals(res)) return;
            
            int num = Integer.parseInt(res);
            if(num==0 || num==1) return;
            if(eratos(num) != 0) {
                prime.add(num);
            }
        }
        
        for(int i=0; i<array.length; i++) {
            if(visited[i] == 1) continue;
            visited[i] = 1;
            searched(n, r+1, array, visited, res+array[i]);
            searched(n, r+1, array, visited, res);
            visited[i] = 0;
        }
    }
    
    public int eratos(int num) {
        for(int i=2; i<=(int)Math.sqrt(num); i++) {
            if(num%i==0) return 0;
        }
        return num;
    }
    
    public int solution(String numbers) {
        int answer = 0;
        prime = new HashSet<Integer>();
        
        String res = "";
        String array[] = numbers.split("");
        int visited[] = new int[array.length];
        System.out.println(Arrays.toString(array));
        
        for(int i=1; i<=array.length; i++) {
            searched(i, 0, array, visited, res);
        }
        
        answer += prime.size();
        return answer;
    }
}