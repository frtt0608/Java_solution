# JAVA 알고리즘 정리 4

## 해싱

### 해싱(Hashing)

1. #### 해싱

   > 해쉬 테이블은 다이나믹 셋을 구현하는 또 다른 방법이다. 해쉬 함수라는 특별한 함수를 통해 어떤 곳에 저장할 것인지 지정하는 것을 말한다. 

   <https://new93helloworld.tistory.com/146?category=691027>

   ![](https://t1.daumcdn.net/cfile/tistory/2744703859117EC226)

   해쉬테이블은 큰 배열이라고 생각하면 된다. 위의 그림은 0번지 부터 m-1번째까지, m개의 슬롯을 가지고 있는 해쉬 테이블이다.

   ` h: U -> {0,1,2,3,4,5, . . . , m-1}`

   ![](https://t1.daumcdn.net/cfile/tistory/2506194A5911844C1F)

   해쉬 함수를 사용하다보면 똑같은 위치에 데이터가 들어가는 경우가 있는데 이를 `충돌`이라고 한다. 즉 H(k1) = H(k2)인 상황에 발생하는 것이다.

   이러한 상황을 해결하기위한 방법 중 대표적으로 `chaining`과 `open addressing`이 있다.



   ![](https://t1.daumcdn.net/cfile/tistory/23648E4C591184A333)

   1. #### chaining

      충돌이 일어나는 이유는 하나의 슬롯에 하나의 데이터만 저장할 수 있기 때문이다. 이를 예방하기 위한 chaining의 개념은 간단하다.

      각각의 슬롯에 저장되는 데이터를 연결리스트로 만들어서 많은 데이터를 	저장할 수 있게 하는 것이다. 즉, 슬롯에는 첫번째 연결리스트의 주소값만 가지게 된다.

      1) `키의 삽입`

      - 키 k를 리스트 T[(k)]의 맨 앞에 삽입 : 시간 복잡도 O(1)
      - 중복된 키가 들어올 수 있고, 중복 저장이 허용되지 않는다면 삽입시 리스트를 검색해야 함
      - 따라서 시간 복잡도는 연결 리스트의 길이에 비례한다.

      2) `키의 검색`

      - 리스트 T[h(k)]로부터 키를 검색 후 삭제
      - 시간 복잡도는 키가 저장된 리스트의 길이에 비례

      3) `키의 삭제`

      - 리스트 T[h(k)]로부터 키를 검색 후 삭제
      - 일단 키를 검색해서 찾은 이후에 O(1)시간에 삭제 가능

      4) `최악의 경우!`

      모든 키가 테이블 상의 하나의 슬롯으로 해싱되는 경우이다.

      - 길이가 n인 연결 리스트가 만들어짐

      - 따라서 최악의 경우 탐색 시간은 O(n) + 해쉬 함수를 계산하는 시간

      그러므로, 평균 복잡도는 키들이 여러 슬롯에 얼마나 잘 분배되느냐에 의해서 결정된다. 

      따라서 우리는 SUHA(Simple Uniform Hasing Assumptiom)라고 가정하고 해싱을 진행하는데, 이는 각각의 키가 모든 슬롯에 대해 균등한 확률로, 독립적으로 해싱된다는 가정이다.

      그러나 이는 성능 분석을 위한 가정으로 deterministic한 해시 함수는 현실에서  불가능하다. 인덱스가 해시 함수에 적용될 확률은 변하지 않고 0과 1로 고정되어있기 때문이다.

      각 슬롯에 저장되는 키의 평균 개수를 Load factor라고 했을 때

      `Load factor = a = n/m`
      n: 테이블에 저장될 키의 개수, m: 해쉬 테이블의 크기, 즉 연결리스트의 개수
      위와 같이 식을 도출할 수 있다.
      또한 연결리스트 T[j]의 길이를 n_j라고 한다면 E[n_j] = a가 되고, 만약 n=O(m)이면 평균 검색 시간은 O(1)이다.



   2. ##### Open Addressing

         충돌을 해결하는 기법 중에 2번째는 open addressing이다.

         해시 테이블 슬롯에 키는 하나씩만 저장한다. 이때 충돌이 일어나면 그 슬롯이아니라 다른 슬롯에 저장해야한다는 것을 뜻한다.

         open addressing은 여러가지 종류가 있는데,

         1) Linear Probing

         2) Quardratic Probing

         3) Double Hashing

         위의 3종류가 대표적이다.

         1. `Linear Probing`

            ![](https://t1.daumcdn.net/cfile/tistory/273E843C5911978224)

            (a)에서는 순서대로 인덱스에 들어가며 충돌이 일어나지 않는다.

            (b)에서는 B5가 들어가야하는데 A5가 이미 들어가 있으므로 충돌이 일어난다. 이때 Linear Probing을 수행하는데, 해싱을 했을 때 이미 다른 키에 의해서 해당 인덱스가 차있다면, Linear하게 인덱스를 탐색해서 다음 빈 인덱스에 해당 키를 채워넣는 방법이다.

            즉, h(k), h(k+1), h(k+2)순으로 탐색하며 빈 인덱스를 찾아 키를 채워넣는다. 테이블의 끝에 들어가면 다시 처음으로 Circulate하게 돌아오게 된다.

            따라서, A5가 5번 인덱스를 차지하고 있으므로 B5는 6번 인덱스에 들어가게 된다.

            만약 search를 한다면, 해당 인덱스로 접근을 한 뒤, 빈 슬롯이 나올 때까지 다음 슬롯으로 연속해서 search를 진행하게 된다. 이런 부분이 chaining과 비슷한 부분이라고 할 수 있다.

            Linear Probing의 단점은 클러스터링이다.

            `테이블의 키들이 연속으로 뭉쳐있는 것을 클러스터링이 심하다`라고 했는데 긴 클러스터가 생기면 새로운 키가 삽입될 때 해싱의 결과가 클러스터 끝으로 해싱될 확률이 점점 높아진다. 이런식으로 클러스터링이 발생하게된다면 눈이 커지는 것과 비슷하게 클러스터가 점점 커지고, 검색 시간이 길어지게 된다.

         2. `Quardratic Probing`

            ![](https://t1.daumcdn.net/cfile/tistory/2559C04259119B1B2F)

            그래서 Linear Probing의 단점을 보완하기 위한 것 중 하나가 Quardratic Probing이다. 이는 probing의 순서를 조금 변경한 것으로 충돌발생 시 다음과같은 수식으로 해싱을 시도한다.

            `h(k)`, `h(k)+1`, `h(k)+2^2`, `h(k)+3^2`

            이것은 Probing의 한가지 방법이며 항상 위와 같은 식으로 동작하진 않는다. 이 방법도 Circular하게 돌아간다.

            클러스터링이 심해지는 것은 피할 수 있지만 다른 단점도 존재한다.

         3. `Double Hashing`

            처음부터 해시 함수를 2개 만드는 것이다.

            `h(k, i) = (h_1(k) + i*h_2(k)) mod m`

            이후 위와 같은 식으로 키를 저장하게 되는데, 기본적은 개념은 Quardratic Probing과 비슷하게 클러스터링을 줄이려고 띄엄띄엄 저장하는 것이다.

            ![](https://t1.daumcdn.net/cfile/tistory/26795A4059119BCA23)

            Open addressing의 경우 단순히 값을 삭제할 경우 오류가 발생한다. 가령 A2, B2, C2가 순서대로 동일한 해시함수를 가져서 Linear Probing으로 충돌을 해결한다. B2를 삭제한 후 C2를 감색한다고 했을 때 중간의 B2가 삭제됐으므로 C2까지 도달하지 못한다.

            따라서 B2를  삭제하고 C2를 삭제된 B2의 자리에 놓아야한다.

   3. ##### 좋은 해시 함수란?

         현실에서 key들은 랜덤하지 않다. 그러므로 key들의 어떤 특정한 패턴을 가지더라도 해시 함수의 값이 불규칙적이게 되도록하는 것이 바람직하다. 즉, 해시 함수의 값이 key의 특정 부분에 의해서만 결정되지 않도록 해야 한다.

         1. `Division`

            이는 key를 테이블 사이즈로 나누어서 나머지를 구하는 연산이다.

            ```java
            h(k) = k mod m
            ```

            0에서 m-1의 인덱스로 깔끔하게 나온다. 장점은 한번의 mod연산으로 계산되어 매우 빠르다. 단점은, 어떤 m값에 대해서는 해시 함수값이 key값의 특정 부분에 의해서 결정되는 경우가 있다.

            가령, m = 2^p이면 키의 하위 비트 p가 해시 함수값이 되는 것이다.

         2. `Multiplication`

            0에서 1사이의 상수 A를 선택하고 K_a의 소수 부분만을 선택한다.

            소수 부분에 m을 곱한 후 소수점 아래를 버린다.

            예를 들면 다음과 같다.

            ```
            m=8, word size = w = 5, k=21일 때
            1) A = 13/32를 선택
            2) K_a = 21 * 13/32 = 273/32 = 8 + 17/32
            3) m(K_a mod 1) = 8 * 17/32 = 17/4 = 4.xxx
            4) 즉, h(21) = 4가 된다.
            ```

         3. `JAVA`

            자바는 해시를 체계적으로 제공해주는 프로그래밍 언어 중 하나로 java의 object클래스가 hashcode()라는 메소드를 가지고 있다. 따라서, 자바의 모든 클래스가 hashcode메소드를 상속받아 가지고 있다.

            이 메서드는 32비트 정수를 반환한다.

            만약 x.equals(y)이면 x.hashcode() === y.hashcode()이다.

            하지만 역은 성립하지 않는다.

            object클래스 안의 hashcode메소드는 객체의 메모리 주소를 반환하는 걸로 알려져있다. 필요에따라 이 메소드를 `오버라이드`해서 사용한다.

            ```java
            public final class String {
                private final char[] s;
                ...;
                public int hashcode() {
                    int hash = 0;
                    for(int i=0; i<length(); i++) {
                        hash = s[i] + (31 * hash);
                    }
                    return hash;
                }
            }
            ```

            ![](https://t1.daumcdn.net/cfile/tistory/223CF8435911DE5E08)
            
            위는 자바에서 String클래스의 hashcode메소드를 예로 든 것이다. hashcode내부의 31진수처럼 표현해서 사용하는 것을 볼 수 있다.
            
            
            
            ```java
            public class Record {
                private String name;
                private int id;
                private double value;
                ...;
                public int hashcode() {
                int hash = 17; // nonzero constant
                hash = 31 * hash + name.hashcode();
                hash = 31 * hash + Integer.valueOf(id).hashcode();
                hash = 31 * hash + Double.valueOf(value).hashcode();
                return hash;
                }
            }
            ```
            
            만약 사용자가 정의 클래스에서 사용하고 싶다면 해당 클래스 내에서 hashcode를 오버라이딩해주면 된다.
            
            
            
            ```java
            private int hash(Key key) {
                return (key.hashcode() & 0x7fffffff) % M;
            }
            ```
            
            hash code와 hash함수를 구분할 필요가 있는데 hashcode는 임의의 -2^31에서 2^31사이의 정수이다. 따라서, 이 값을 바로 해쉬함수의 값으로 쓰는 것이 아니라 내가 원하는 테이블 안에 들어가는 인덱스로 변환해주는 함수가 필요하다.
            
            위의 코드는 첫 비트를 양수로 변환해준 후 m으로 나눈 나머지를 구하는 코드이다. 이 함수를 수행하면 0에서 m-1까지의 인덱스가 나오게 된다.
            
            
            
            ```java
            HashSet<MyKey> set = new HashSet<MyKey>();
                    set.add(MyKey);
                    if(set.contains(thsKey))
                        ...;
                    int k = set.size();
                    set.remove(theKey);
                    Iterator<MyKey> it = set.iterator();
                    while (it.hasNext()) {
                        MyKey key = it.next();
                        if(key.equals(aKey))
                            it.remove();
                    }
            ```
            
            자바가 제공해주는 해시 테이블은 Hashmap이라는 Treemap과 유사한 인터페이스를 제공하며, 이는 내부적으로 하나의 배열을 해시 테이블로 사용한다. 또한 chaining으로 충돌을 해결한다. 
            
            그리고 Load factor를 지정할 수 있는 특징을 가지고 있다. 이 때, 저장된 키의 개수가 Load factor를 초과하면 더 큰 배열을 할당하고 저장된 키들을 재배치(re-hashing)한다.
            
            마지막으로 Hashset이라는 인터페이스도 제공하는데 set이라는 이름대로 집합의 성질을 지닌다. 해당 원소의 추가, 제거, 심지어 이터레이터까지 제공하게 된다. 이러한 인터페이스를 제공하는 것이 Hashset이다.