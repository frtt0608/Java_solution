class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++) {
            int num = arr1[i] | arr2[i];
            StringBuilder sb = new StringBuilder();
            
            for(int j=0; j<n; j++) {
                String bit = num%2==1 ? "#":" ";
                sb.append(bit);
                num /= 2;
            }
            
            answer[i] = sb.reverse().toString();
        }
        
        return answer;
    }
}