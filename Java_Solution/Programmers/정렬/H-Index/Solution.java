import java.util.Arrays;

class Solution {
    public int h_index_fcn1(int[] citations) {
        int answer = 0;
        int min = 0;
        int input_length = citations.length;
        
        for(int i=0; i<input_length; i++) {
            min = Math.min(citations[i], input_length - i);
            if(answer < min) answer = min;
        }
        
        return answer;
    }
    
    public int h_index_fcn2(int input_array[]) {
        if(input_array[input_array.length-1] >= input_array.length-1) {
            return input_array.length-1;
        }
        
        int h_index = 1;
        while(true) {
            if(h_index < input_array[h_index]) {
                h_index++;
            } else if(h_index > input_array[h_index]) {
                h_index--;
                break;
            } else break;
        }
        
        return h_index;
    }
    
    public int solution(int[] citations) {
        int answer = 0;
        int input_length = citations.length;
        Arrays.sort(citations);
        int input_array[] = new int[input_length+1];
        
        for(int i=1; i<=input_length; i++) {
            input_array[i] = citations[input_length-i];
        }
        
        //answer = h_index_fcn1(citations);
        answer = h_index_fcn2(input_array);
        
        return answer;
    }
}