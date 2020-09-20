import java.util.*;

class 풍선터트리기 {

    //한번에 해결하기
    public void method2(int[] a) {
        int len = a.length;

        int left = a[0];
        int right = a[len-1];

        int res = 2;

        for(int i=1; i<len; i++) {
            if(left > a[i]) {
                left = a[i];
                res += 1;
            }

            if(right > a[len-1-i]) {
                right = a[len-1-i];
                res += 1;
            }
        }

        //둘이 같다면 중복값 발생
        if(left == right)
            res -= 1;
    }

    public int solution(int[] a) {
        if(a.length <= 2) return a.length;
        
        HashSet<Integer> numSet = new HashSet<>();
        
        int min = a[0];
        for(int i=1; i<a.length; i++) {
            numSet.add(min);
            min = (int)Math.min(min, a[i]);
        }
        
        min = a[a.length-1];
        for(int i=a.length-2; i>=0; i--) {
            numSet.add(min);
            min = (int)Math.min(min, a[i]);
        }
        
        return numSet.size();
    }
}