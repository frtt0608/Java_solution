# 알고리즘 정리

#### 1. 유클리드 호제법

   2개의 자연수 a, b에 대하여 a를 b로 나눈 나머지를 r이라 할 때, a와 b의 최대공약수는 b와 r의 최대 공약수와 같다.

   이를 재귀함수로 구현한다면 다음과 같다.

   ```java
   public void gcd(int a, int b) {
       if(a%b==0) return b;
       else {
           return gcd(b, a%b);
       }
   }
   ```

   많은 재귀호출로 스택초과가 우려된다면

   ```java
   int a;
   int b;
   int temp;
   
   while(b > 0) {
   	temp = a;
       a = b;
       b = temp%b;
   }
   ```

   



#### 2. 맨하탄 거리 측정법

유클리드 거리 측정법 = 두 점을 잇는 가장 짧은 거리의 길이(도로 사정x)

```java
// (a1, b1), (a2, b2)
a = Math.pow(a1-a2, 2);
b = Math.pow(b1-b2, 2);

d = Math.sqrt(a+b, 2);
```



맨하탄 거리 = 그냥 절대값을 바로 합산

```java
// (a1, b1), (a2, b2)
a = Math.abs(a1-a2);
b = Math.abs(b1-b2);

d = a + b;
```

맨하탄 거리는 항상 유클리드 거리보다 크거나 같다.





#### 3. 이진 탐색

BinarySearch로 구간이 큰 경우를 min + max = mid형태로 구간을 계속 반으로 쪼개면서 탐색해 최종적으로 조건에 맞는 해를 구하는 알고리즘 기법

```java
public void binarySearch() {
    int max = maxSize;
    int min = 0;
    int mid = 0
        
    while(max >= min) {
        mid = (max+min)/2;
        
        if (function() < mid) {
            min = mid+1
        } else {
            max = mid - 1
        }
    }
    
    return mid
}
```



이외에도 LIS알고리즘에서 쓰이는 이진탐색이 있다.