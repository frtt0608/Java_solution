// 전화번호 목록
// ["119", "97674223", "1195524421"]
// false
// ["12", "123", "1235", "567", "88"]
// true
// ["12", "123", "1235", "567", "88"]
// false

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        String temp1 = "";
        String temp2 = "";
        int length = 0;
        
        for(int i=0; i<phone_book.length-1; i++) {
            temp1 = phone_book[i];
            for(int j=i+1; j<phone_book.length; j++) {
                temp2 = phone_book[j];
                if(temp1.length() < temp2.length()) {
                    length = temp1.length();
                } else {
                    length = temp2.length();
                }
                if(temp1.substring(0,length).equals(temp2.substring(0,length))) {
                    return false;
                }
            }
        }
        return answer;
    }
}