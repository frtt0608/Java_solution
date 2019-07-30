# JAVA 알고리즘 정리 3

## 메모이제이션과 비트마스킹

### 재귀와 메모이제이션(동적 계획법)

1. 재귀함수

   > 재귀란 함수가 자기 자신을 호출하는 것을 의미합니다.

   재귀를 사용할 경우 가독성이 뛰어나고 코드를 축소할 수 있어서 좋으나 성능상으로는 컴퓨터에 부담을 주기에 좋은 방법은 아니다.

   예) Factorial 함수
   

   ```java
   public class Factorial {
       static int factorial(int number) {
           if(number>0) {
               return number* factorial(number-1);
           }
           else {
               return 1;
           }
       }
       public static void main(String[] args) {
           System.out.println(factorial(2));
           System.out.println(factorial(3));
       }
   }
   ```

      동적 프로그래밍 또는 동적 계획법은 주어진 문제를 세분화하여 최적의 해법을 찾아내는 방법이다. 이는 중복된 부분을 줄이고 불필요한 요소를 배제함으로써 연산의 효율을 높일 수 있다.

   예) Fibonacci

   ```java
   import java.util.HashMap;
   
   public class DynamicProgramming {
       static int cnt=0, index=30, temp=0;
       static double before, after = 0; //시간 측정용
        
       public static void main(String[] args) {
           //일반 피보나치 재귀함수
           before = System.currentTimeMillis();
           temp = fn_Fibonacci(index);
           after = System.currentTimeMillis();
           System.out.println("1. 피보나치\n f(" + index + ")일 때, 결과값 : " + temp + "\n함수 호출 횟수 : " + cnt + ", 수행시간 : " + (after-before) + "ms");
           cnt = 0;
           temp = 0;
            
           //DP 피보나치 재귀함수
           HashMap<Integer, Integer> dp = new HashMap<Integer, Integer>(); //중복연산 방지
                    
           before = System.currentTimeMillis();
           temp = fn_DP_Fibonacci(index, dp);
           after = System.currentTimeMillis();
           System.out.println("\n2. 동적계획법 피보나치\nf(" + index + ")일 때, 결과값 : " + temp + "\n함수 호출 횟수 : " + cnt + ", 수행시간 : " + (after-before) + "ms");
       }
     
       public static int fn_Fibonacci(int n){
           cnt++;
           if(n == 0){
               return 0;
           }else if(n <= 2){
               return 1;
           }else{
               return fn_Fibonacci(n-1) + fn_Fibonacci(n-2);  
           }
       }
        
       public static int fn_DP_Fibonacci(int n, HashMap<Integer, Integer> dp){
           cnt++;
           if(dp.containsKey(n)){ //이미 연산했던 적이 있다면, 그 값을 꺼내 돌려준다.
               return dp.get(n);
           }else if(n == 0){
               return 0;
           }else if(n <= 2){
               return 1;
           }else{
               int value = fn_DP_Fibonacci(n-1, dp) + (fn_DP_Fibonacci(n-2, dp));
               dp.put(n, value);
               return value;
           }
       }
   }
   ```

   HashMap 클래스는 key와 value 값을 묶어 한 쌍으로 저장하는 자료구조를 가진다.

   이러한 기억을 위한 별도의 장치를 `메모이제이션`이라고 한다.



### 비트마스킹

1. 비트연산자

   > 모든 자릿수가 비트(0 또는 1)로 이루어진 값에 대하여 아래의 연산이 가능하다.

   | 연산자                | 예시                 |
   | --------------------- | -------------------- |
   | AND (a&b)             | 111 & 101 = 101      |
   | OR (a\|b)             | 101 \| 100 = 101     |
   | XOR (a ^ b)           | 1011 ^ 1100 = 0111   |
   | NOT (~a)              | ~(1011) = 0100       |
   | LEFT SHIFT (a << b)   | 1 << 5 = 100000      |
   | RIGHT SHIFT ( a >> b) | 1101101 >> 2 = 11011 |

    

2. 다중 비트연산

   예) data = ?

   1. 공집합 or 꽉찬집합

      공집합: int data = 0

      꽉찬집합: int data = (1 << 10 ) - 1

   2. 원소 추가

         data = data | (1 << n)

         축약: data |= (1 <<  n)

      3. 원소의 포함여부 확인

         if ( a & (1 << n)) ?

      4. 원소 삭제

         a = a & ~(1 << n)

         축약 : a &= ~(1 << n)

      5. 원소의 토글

         a = a ^ (1 << n)

         a ^= (1 << n)

      6. 그 외 집합 연산

   | 집합                                     | 예시                         |
   | ---------------------------------------- | ---------------------------- |
   | 합집합                                   | int added = ( a \| b);       |
   | 교집합                                   | int intersection = ( a & b); |
   | 차집합                                   | int removed = (a & ~b);      |
   | a와 b 중 하나에만 들어있는 원소들의 집합 | int toggled = (a ^ b);       |

   7. 최하위 비트 찾아내기

      int least_bit = data & -data

      -data는 2의 보수 연산법으로 `~(data) + 1 = -data`가 된다.

   8. 최소 비트 지우기

      int remove_least_bit = data & (data -1)



3. 비트마스킹을 통한 완전탐색

   먼저 단순 for문이 아닌 재귀를 통한 DFS탐색으로 완전탐색을 해본다.

   만약 0, 1, 2라는 3개의 숫자 카드 중 2개만 뽑는다고 한다면 다음과 같다.

   ```java
   static int[] arr = new int[2];
   private static void solve(int idx, int cnt) {
       // cnt(선택 갯수)가 2가 되면 출력
       if (cnt == 2) {
           System.out.println(arr[0] + "  " + arr[1]);
           return;
       }
       
       // idx가 2를 벗어나면 종료
       if( idx >= 3) return;
       
       // 자신을 포함하면
       arr[cnt] = idx;
       solve(idx+1, cnt+1);
       // 자신을 미포함하면
       solve(idx+1,cnt);
   }
   ```

   하지만 재귀를 통한 함수는 시간이 오래걸린다. 대신에 비트마스킹을 사용해보겠다.

   Integer.bitCount(n)을 넣어주면 비트가 1인 개수를 return해준다.

   ```java
      for(int i=0;i<(1<<3);i++) {
          if(Integer.bitCount(i)==2) {
              for(int j=0;j<3;j++) {
                  if(((1<<j)&i)>0) {
                      System.out.print(j+"       ");
                  }
              }
              System.out.println();
          }
      }
   ```





### 재귀(추가)

> 재귀는 두가의 case로 구성되어있다.

* Base Case: 적어도 하나의 Recursion에 빠지지 않는 경우
* Recursive Case: recursion을 돌면서 Base Case에 수렴하게 하는 경우

ex) Base / Recursive, 최대 공약수

```java
public static void func(int k) {
    // Base Case
    if(k<=0)
        return;
    else{
        System.out.println("Hello world!")
        // Recursive Case
        func(k-1);
    }
}
```

```java
public static int gcd(int p, int q){                         
    if(q==0)
        return p;
    else
        return gcd(q,p%q);
}
```

다시 종합해 본다면 재귀함수는 적어도 하나의 base case를 가지고 있어야한다. 즉, 순환되지 않고 종료되는 case가 있어야 한다. 또한 모든 case는 base case로 수렴해야 한다.

추가적으로 코드를 작성하는 방법으로는 `암시적 매개변수를 명시적 매개변수로 바꾸어라!` 이다. 예제를 통해 알아본다.

ex) 순차탐색

```java
int search(int [] data, int n, int target) {                 
    for(int i=0; i<n; i++) {
        if(data[i]==target)
            return i;        
    }
    return -1;
}
```

다음은 입력이 배열로 주어졌을 때, 이 배열 안에서 자신이 찾는 값이 있는지, 그리고 어디에 있는지 찾는 함수이다. 즉 data[0]에서 data[n-1] 사이의 target을 검색하는 것이다.

하지만 검색구간의 시작 인덱스 0은 보통 생략한다. 즉, 시작 인덱스 0은 암시적 매개변수이다.

```java
int search(int [] data, int begin, int end, int target) {
    if(begin>end)
        return -1;
    else if(target == data[begin])
        return begin;
    else
        return search(data, begin+1, end, target;
}
```

앞서 말한 것처럼 암시적 매개변수를 명시적으로 변환해주었다. 즉, 시작 인덱스 0 을 begin으로 명시적으로 표기해준다.

이렇게 하는 이유는 무엇일까? 함수가 다시 자신을 재귀로 호출할 때 필요한 매개변수들까지 표현할 수 있는 일반적인 형태를 가져야 하기 때문이다. 즉, 매개변수를 명시적으로 지정해주지 않으면 재귀 호출 시 시작구간이 달라지므로 그것을 표현할 방법이 없어지기 때문이다.