class 숫자문자열과영단어 {
    public int solution(String s) {
        String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int num=0; num<10; num++) {
            s = s.replace(arr[num], Integer.toString(num));
        }
        
        return Integer.parseInt(s);
    }
}