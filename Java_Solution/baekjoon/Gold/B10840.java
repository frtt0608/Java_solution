import java.io.*;
import java.util.*;

public class B10840 {
    static final int MODE = 524287;
    static final int p = 31;
    static int result;
    static List<Integer> primes;
    static List<int[]>[] hashs;
    

    public static long computeHashCode(String str) {
        long hashCode = 0;
        long p_pow = 1;

        for(int i=0; i<str.length(); i++) {
            hashCode = (hashCode + (str.charAt(i)-'a'+1) * p_pow) % MODE;
            p_pow = p_pow * p % MODE;
        }

        return hashCode;
    }

    public static boolean isPrime(int n) {
        for(int i=2; i*i<=n; i++) {
            if(n%i == 0) return false;
        }

        return true;
    }

    public static void getPrime() {
        for(int i=2; i<=10000; i++) {
            if(isPrime(i)) {
                primes.add(i);
            }
        }
    }

    public static void initHashs() {
        for(int i=0; i<MODE; i++) {
            hashs[i] = new ArrayList<>();
        }
    }

    public static void compareString(String Sa, String Sb) {
        int x, y, now, len;
        boolean flag = false;

        for(int i=0; i<Sa.length(); i++) {
            x = 1;
            y = 1;
            for(int j=i; j<Sa.length(); j++) {
                now = Sa.charAt(j) - 'a';
                len = j - i + 1;
                x = (x * primes.get(now)) % MODE;
                y = (y * primes.get(now + 26)) % MODE;
                hashs[x].add(new int[] {y, len});
            }
        }

        for(int i=0; i<Sb.length(); i++) {
            x = 1;
            y = 1;
            for(int j=i; j<Sb.length(); j++) {
                now = Sb.charAt(j) - 'a';
                len = j - i + 1;
                x = (x * primes.get(now)) % MODE;
                y = (y * primes.get(now + 26)) % MODE;

                flag = false;

                for(int k=0; k<hashs[x].size(); k++) {
                    int[] element = hashs[x].get(k);
                    
                    if(element[0] == y && element[1] == len) {
                        flag = true;
                    }
                }

                if(flag) {
                    result = Math.max(result, len);
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

        getPrime();
        initHashs();
        compareString(Sa, Sb);

        System.out.println(result);
    }
}
