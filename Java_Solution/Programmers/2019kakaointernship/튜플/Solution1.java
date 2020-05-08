// 하... 노답..

import java.util.*;

class Solution1 {
    public int[] solution(String s) {
        int[] answer = {};
        int size = 0;
        String num = "";

        LinkedList<int[]> s_list = new LinkedList<>();
        int temp_Arr[] = new int[500];
        int temp[];
        int idx = 0;
        int cnt = 500;

        for(int i=1; i<s.length()-1; i++) {
            if(s.charAt(i) == '{') {
                temp_Arr = new int[500];
                cnt = 500;
            } else if(s.charAt(i) == '}') {
                if(!num.equals("")) {
                    temp_Arr[idx] = Integer.parseInt(num);
                    idx=0;
                    num = "";
                } 
                for(int j=0; j<500; j++) {
                    if(temp_Arr[j]==0) {
                        cnt = j;
                        break;
                    }
                }
                temp = new int[cnt];
                System.arraycopy(temp_Arr, 0, temp, 0, cnt);

                s_list.add(temp);
            } else {
                if(s.charAt(i) == ',') {
                    if(!num.equals("")) {
                        temp_Arr[idx] = Integer.parseInt(num);
                        idx++;
                        num = "";
                    }
                } else {
                    num += Character.toString(s.charAt(i));
                }
            }
        }
        answer = new int[s_list.size()];
        int answer_idx = 0;

        Collections.sort(s_list, (int arr1[], int arr2[]) -> {
            return arr1.length - arr2.length;
        });
        Boolean flag = false;

        for(int i=0; i<s_list.size(); i++) {
            size = s_list.get(i).length;
            for(int j=0; j<size; j++) {
                flag = false;
                if(answer_idx==0) {
                    answer[answer_idx] = s_list.get(i)[j];
                    answer_idx++;
                }
                for(int k=0; k<answer_idx; k++) {
                    if(answer[k] == s_list.get(i)[j]) {
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    answer[answer_idx] = s_list.get(i)[j];
                    answer_idx++;
                    break;
                }
            }
        }
        return answer;
    }
}