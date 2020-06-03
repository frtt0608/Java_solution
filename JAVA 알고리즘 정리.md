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

