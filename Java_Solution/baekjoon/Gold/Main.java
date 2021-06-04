import java.io.*;
import java.util.*;

/*
1. 알파벳별 해시값(소수) 적용
2. 문자열마다 해시값 적용 -> 충돌방지를 위한 체이닝 기법
3. 2번째 문자열 비교할 때, 같은 해시값의 같은 길이가 있는지 이진탐색
*/

public class Main {
    static final int MODE = 524287;
    static final int p = 31;
    static int result;
    static int[] primes;
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
        primes[0] = 1;

        for(int i=1; i<26; i++) {
            primes[i] = (primes[i-1] * p) % MODE;
        }
    }

    public static void setHashList(String str) {
        int hashCode;
        char[] strArr = str.toCharArray();

        for(int i=0; i<strArr.length; i++) {
            hashCode = 0;

            for(int j=i; j<strArr.length; j++) {
                hashCode = (hashCode + primes[strArr[j]-'a']) % MODE;
                hashs[hashCode].add(new HashInfo(hashCode, j-i+1));
            }
        }

        for(int i=0; i<MODE; i++) {
            hashs[i].sort((HashInfo info1, HashInfo info2) -> 
                            (info1.len - info2.len));
        }
    }

    public static void searchMaximumLength(String str) {
        int hashCode;
        char[] strArr = str.toCharArray();

        for(int i=0; i<strArr.length; i++) {
            hashCode = 0;

            for(int j=i; j<strArr.length; j++) {
                hashCode = (hashCode + primes[strArr[j]-'a']) % MODE;

                for(HashInfo info: hashs[hashCode]) {
                    if(info.len == j-i+1) {
                        result = Math.max(result, j-i+1);
                        break;
                    }
                }

                // if(hashs[(int)hashCode].size() > 0) {
                //     if(binarySearchHashCode((int)hashCode, j-i+1)) {
                //         result = Math.max(result, j-i+1);
                //     }
                // }
            }
        }
    }

    public static boolean binarySearchHashCode(int hashCode, int len) {
        int min = 0;
        int max = hashs[hashCode].size()-1;
        int mid;

        while(min <= max) {
            mid = (min + max)/2;

            if(hashs[hashCode].get(mid).len > len) {
                max = mid-1;
            } else if(hashs[hashCode].get(mid).len < len) {
                min = mid+1;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String strA = br.readLine();
        String strB = br.readLine();
        result = 0;
        primes = new int[26];
        hashs = new ArrayList[MODE];

        for(int i=0; i<MODE; i++) {
            hashs[i] = new ArrayList<>();
        }

        setPrimeNumber();
        setHashList(strA);
        searchMaximumLength(strB);

        System.out.println(result);
    }
}
