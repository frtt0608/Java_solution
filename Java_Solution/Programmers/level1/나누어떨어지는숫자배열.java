import java.util.*;

// class Solution {
//     public int[] solution(int[] arr, int divisor) {
//         LinkedList<Integer> numList = new LinkedList<>();
        
//         for(int num: arr) {
//             if(num % divisor == 0) {
//                 numList.add(num);
//             }
//         }
        
//         if(numList.size() == 0) {
//             int[] answer = {-1};
//             return answer;
//         } else {
//             int[] answer = new int[numList.size()];
//             int idx = 0;
//             while(!numList.isEmpty()) {
//                 answer[idx] = numList.poll();
//                 idx++;
//             }
//             Arrays.sort(answer);
//             System.out.println(Arrays.toString(answer));
//             return answer;
//         }
//     }
// }


// Arrays의 메소드 활용
class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = Arrays.stream(arr).filter(num -> num % divisor == 0).toArray();
        
        if(answer.length == 0) answer = new int[] {-1};
        else {
            Arrays.sort(answer);
        }
        
        return answer;
    }
}