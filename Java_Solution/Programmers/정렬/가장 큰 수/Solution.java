// 가장 큰수
// numbers [6, 10, 2]
// return "6210"
// numbers [3, 30, 34, 5, 9]
// return "9534330"


import java.util.Arrays;
import java.util.Comparator;

class Solution {
    
    public String solution(int[] numbers) {
        String answer = "";
        int input_length = numbers.length;
        String input_array[] = new String[input_length];
        for(int i=0; i<numbers.length; i++) {
            input_array[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(input_array, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return (str2+str1).compareTo(str1+str2);
            }
        });
        
        for(int i=0; i<numbers.length; i++) {
            answer += input_array[i];
        }
        if("0".equals(answer.substring(0,1))) {
            answer = "0";
        }
        
        return answer;
    }
}