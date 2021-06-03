import java.io.*;
import java.util.*;

/*
1. 롤링 해시 적용 -> 알파벳별 해시값 정하기
2. 첫번째 문자열을 2중 for문으로 문자열마다 해시값 저장(List1), List1 정렬
3. 두번째 문자열도 2중 for문으로 문자열마다 해시값 구하기,
이후, 같은 해시값이 있는지 List1을 이진탐색으로 찾아서 업데이트하기

*/



public class Main {
    static final int MODE = 524287;
    static final int p = 31;
    static int result;
    static List<Integer> primes;
    static List<HashInfo>[] hashs;

    static class HashInfo {
        int hashCode;
        int len;

        HashInfo(int hashCode, int len) {
            this.hashCode = hashCode;
            this.len = len;
        }
    }

    public static void setPrimeNumber() {
        boolean[] visited = new boolean[100000];

        for(int i=2; i<100000; i++) {
            if(visited[i]) continue;

            primes.add(i);
            int num = i*2;

            while(true) {
                if(num >= 100000) break;

                visited[num] = true;
                num += i;
            }
        }
    }

    public static void setHashList(String Sa) {
        int x, hashCode, num, len;

        for(int i=0; i<Sa.length(); i++) {
            x = 1;
            hashCode = 1;
            for(int j=i; j<Sa.length(); j++) {
                num = Sa.charAt(j) - 'a';
                len = j - i + 1;
                x = (x * primes.get(num)) % MODE;
                hashCode = (hashCode * primes.get(num + 26)) % MODE;
                hashs[x].add(new HashInfo(hashCode, len));
            }
        }
    }

    public static void searchMaximumLength(String Sb) {
        int x, hashCode, num, len;

        for(int i=0; i<Sb.length(); i++) {
            x = 1;
            hashCode = 1;

            for(int j=i; j<Sb.length(); j++) {
                num = Sb.charAt(j) - 'a';
                len = j - i + 1;
                x = (x * primes.get(num)) % MODE;
                hashCode = (hashCode * primes.get(num + 26)) % MODE;

                for(HashInfo info: hashs[x]) {
                    if(hashCode == info.hashCode && len == info.len) {
                        result = Math.max(result, len);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        String Sa = br.readLine();
        String Sb = br.readLine();
        result = 0;
        primes = new ArrayList<>();
        hashs = new ArrayList[MODE];

        for(int i=0; i<MODE; i++) {
            hashs[i] = new ArrayList<>();
        }

        setPrimeNumber();
        setHashList(Sa);  
        searchMaximumLength(Sb);

        System.out.println(result);
    }
}
