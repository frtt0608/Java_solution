# JAVA 알고리즘 7

## 정렬 속도에 대해 알아보기

### Sort

1. #### Sorting in linear time(선형시간 알고리즘)

    <https://new93helloworld.tistory.com/110?category=691027>

    먼저 count알고리즘이다. count 알고리즘의 조건은 다음과 같다.

    1) n개의 정수를 정렬하라(단, 모든 정수에서 0과 n사이의 정수이다.)

    예) n명의 학생들의 시험 점수를 정렬하라.(단, 모든 점수는 100이하의 양의 정수이다. 

    k이하의 정수라는 사전정보가 있고 이 정보를 이용하여 정렬하므로 Comparison Sort가 아니다.

    ![](https://t1.daumcdn.net/cfile/tistory/2152EE3E591BA7E02A)
    count알고리즘은 k가 비교적 작을 때 적용할 수 있는 알고리즘이다. 따라서 k가 5일 때 예를 들어보자
    C라는 배열은 카운터이다. 카운트 알고리즘은 입력 배열을 처음부터 끝까지 스캔하면서 그 값들이 각각 몇개가 있는지 카운트해서 C라는 배열에 저장해준다.

    ```java
    int A[n];
    int C[k] = {0,};
    for(int i=1; i<=n; i++)
        C[A[i]]++;
    for(int s=1, i=0; i<=k; i++) {
        for(int j=0; j<c[i]; j++) {
            A[s++] = j;
        }
    }
    ```

    간단한 방법으로 코드를 작성했다. 그러나 실제 프로그램에서 저장되어있는 데이터들은 key값과 value값이 함께 있다.

    그래서 위와 같은 코드는 정렬된 key값만 옮기므로 좋은 코드가 아니라고 할 수 있다.

    ![](https://t1.daumcdn.net/cfile/tistory/2173E63E591BA7E11F)

    따라서 같이 붙어다니는 데이터까지 이동해야 한다. 이를 위해서 (a)에서 (b)로 넘어갈 때 배열 C의 누적합을 구한다. 이 누적합이 가지고 있는 정보를 어떻게 해석할까.

    예를 들면 배열 A의 마지막 값은 3인데, 누적합 배열 C에서 인덱스 3이 7이라는 것은 3보다 작거나 같은 것이 총 7개라는 것이다. 따라서 새로운 배열 B 인덱스 7번에 3을 넣어준다.

    만약 또 3이 나온다면 이미 7번자리를 3이 차지했으므로 7번 바로 앞, 즉 6번자리에 3을 넣어준다. 이와 같은 분류작업을 반복하면 정렬이 완료된다.

    ```java
    Counting-Sort(A,B,k) {
        for i <- 0 to k
            do C[i] <- 0
        for j <- 1 to length[A]
            do C[A[j]] <- C[A[j]+1]
        // C[i] now contains the number of elements equal to i
        for i <- 1 to k
            do C[i] <- C[i] + C[i-1]
        // C[i] now contains the number of elements less than or equal to i
        for j <- length[A] downto 1
            do B[C[A[j]]] <- A[j]
                C[A[j]] <- C[A[j]]-1
    }
    ```

    이를 소스로 정리하면 다음과 같다.

    첫번째 for문은 카운트 배열 C를 0으로 초기화하고, 두번째 for문은 카운트를 하며, 세번째 for문은 누적합은을 구한다.

    마지막으로, 누적합을 이용해서 배열 A를 마지막부터 처음까지 살펴보면서 배열 B의 인덱스 C[A[j]]가 되는 곳에 A[j]를 저장한다. 그리고 나서 그 값에 해당하는 카운트 값을 1 감소시켜준다.

    이 알고리즘의 시간 복잡도는 세타(n+k)이다. 단점은 k가 클수록 비효율적이라는 점이다. 

    그리고 count정렬은 안정 정렬 알고리즘(입력에 동일한 값이 있을 때 입력에 먼저 나오는 값이 출력에서도 먼저 나온다)이다.

    

2. #### Sort - Radix Sort와 정렬의 속도 비교

    <https://new93helloworld.tistory.com/111?category=691027>

    ![](https://t1.daumcdn.net/cfile/tistory/2574E943591BA83234)

    이어서 다음 non-comparison sort중 하나인 raidx sort를 알아보자.

    radix sort의 조건은

    1) n개의 d자리 정수들 (n=7, d=3)

    2) 가장 낮은 자리수부터 정렬을 한다.

    위의 그림은 각 자릿수를 비교하며 정렬을 진행하게 된다. 여기서 중요한 것은 각 자릿수로 정렬을 진행할 때 다른 자리는 생각하지 않는다

    ```java
    RADIX_SORT(A,d) {
        for i <- 1 to d
            do use a stable sort to sort array A on digit i
    }
    ```

    다음은 radix sort의 슈도코드인데 정말 간단하다. i번째 자릿수를 비교해서 stable하게 연산을 진행하고, 가장 낮은 자릿수부터 가장 높은 자릿수까지 d번 반복하면 되기 때문이다.

    시간 복잡도는 O(d(n+k))가 된다.

    여기서 n은 데이터의 개수이고 k는 데이터가 가질 수 있는 서로 다른 값의 개수이다.


    지금까지 배운 알고리즘의 시간복잡도를 비교하면 다음과 같다.

    ![](https://t1.daumcdn.net/cfile/tistory/22703643591BA83334)

    ![](https://t1.daumcdn.net/cfile/tistory/2652FC43591BA83334)

    ![](https://t1.daumcdn.net/cfile/tistory/2353CF43591BA8342E)

    ![](https://t1.daumcdn.net/cfile/tistory/2160C843591BA83535)



3. #### JAVA에서의 정렬

   <https://new93helloworld.tistory.com/112?category=691027>

   > 정렬 알고리즘은 실제로 기초적이고 매우 많이 사용되기 때문에 직접 구현하지 않고 제공되는 경우가 많다.

   ```java
   int[] data = new int[capacity];
   // data[0]에서 data[capacity-1]까지, 데이터가 꽉 차있는 경우에는 다음과 같이 정렬한다.
   Arrays.sort(data);
   
   // 배열이 꽉 차있지 않고 data[0]에서 data[size-1]까지 size개의 int만 있다면 다음과 같이 한다.
   Arrays.sort(data,0,size);
   ```

   ```java
   String[] fruits = new String[] {"pineapple", "Apple", "Oraneg"};
   
   // 문자열은 사전식으로 정렬이 되어있기 때문이다.
   Arrays.sort(fruits);
   for(String name: fruits) {
   	System.out.println(name);
   }
   ```

   위는 단순히 java에 있는 sort메소드를 이용한 코드이다.

   

   이제 사용자 정의 객체에 대해서 알아보자

   ```java
   public class Fruit{
       public String name;
       public int quantity;
       public Fruit(String name, int quantity){                                                               
           this.name = name;
           this.quantity = quantity;
       }
   }
    
   // somewher in your program
   Fruit [] fruits =  new Fruit[4];
   fruits[0] = new Fruit("Pineapple", 70);
   fruits[1] = new Fruit("Apple", 70);
   fruits[2] = new Fruit("Orange", 70);
   fruits[3] = new Fruit("Banana", 70);
    
   Arrays.sort(fruits);
   ```

   위의 코드는 사용자 정의 객체 Fruit를 선언해서 객체를 하나하나 만들어 배열에 저장한 후 sort메소드를 사용한 예제이다.

   그러나 sort메소드를 사용하면서 의문점이 발생한다. 무엇을 기준으로 정렬을 하는 것인지 모호하기 때문이다. 이와 같은 상황에서는 올바르게 동작하지 않는다. 

   따라서 sort메소드를 사용하기 위해서는 적절한 기준 데이터가 있어야한다.

   ```java
   public class Fruit implements Comparable<Fruit>{
       public String name;
       public int quantity;
       public Fruit(String name, int quantity){                                                                 
           this.name = name;
           this.quantity = quantity;
       }
       public int compareTo(Fruit other){
           return name.compareTo(other.name);
       }
   }
    
   // somewher in your program
   Fruit [] fruits =  new Fruit[4];
   fruits[0] = new Fruit("Pineapple", 70);
   fruits[1] = new Fruit("Apple", 70);
   fruits[2] = new Fruit("Orange", 70);
   fruits[3] = new Fruit("Banana", 70);
    
   Arrays.sort(fruits);
   ```

   이와 같은 상황에서는 먼저 객체들 사이의 크기 관계를 어딘가에서 지정해주어야 한다.

   이를 위해서 2개의 방법이 있는데 첫번째로 Comparable Interface를 사용하는 것이다. Comparable Interface에서 구현해야하는 메소드는 comparaTo이다. 이 함수에서 비교할 기준값을 리턴해 주게 된다.

   ```java
   public int compareTo(Fruit other) {
       return quantity - other.quantity;
   }
   ```

   만약 재고 수량에 의한 비교를 하고싶다면 위와 같은 코드를 작성해 주면 된다.

   

   ```java
   Comparator<Fruit> nameComparator = new Comparator<Fruit>(){
       public int compare(Fruit fruit1, Fruit fruit2){
           return fruit1.name.compareTo(fruit2.name);
       }
   };
    
   Comparator<Fruit> quantComparator = new Comparator<Fruit>(){                                               
       public int compare(Fruit fruit1, Fruit fruit2){
           return fruit1.quantity - fruit2.quantity;
       }
   };
    
   Arrays.sort(fruits, nameComparator);
   Arrays.sort(fruits, quantComparator);
   ```

   위와 같이 Comparator 인터페이스로 compare메소드를 구현함으로써 각각의 조건에 맞는 case를 설정하고 경우에 따라 해당 객체의 compare메소드를 사용하면 된다.

   따라서 Fruit Class에 Comparable 상속받으면 다음과 같다.

   ```java
   public class Fruit implements Comparable<Fruit>{
       public String name;
       public int quantity;
       public Fruit(String name, int quantity){                       
           this.name = name;
           this.quantity = quantity;
       }
       public int compareTo(Fruit other){
           return name.compareTo(other.name);
       }
    
       public static Comparator<Fruit> nameComparator = new Comparator<Fruit>(){                               
           public int compare(Fruit fruit1, Fruit fruit2){
               return fruit1.name.compareTo(fruit2.name);
           }
       };
    
       public static Comparator<Fruit> quantComparator = new Comparator<Fruit>(){       
           public int compare(Fruit fruit1, Fruit fruit2){
               return fruit1.quantity - fruit2.quantity;
           }
       };
   }
   ```

   comparator의 위치를 보통 다음과 같이 작성한다. 타겟이 되는 객체 안에 public static으로 작성한다. 이렇게하면 각 객체마다 메모리의 낭비없이 하나의 comparator만 사용하면 되기 때문이다.

