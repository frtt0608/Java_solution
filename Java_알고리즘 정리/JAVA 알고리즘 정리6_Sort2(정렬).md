# JAVA 알고리즘 정리6

## Sort2

### Sort(정렬)

1. #### 퀵정렬

   <https://new93helloworld.tistory.com/105?category=691027>

   퀵정렬은 합병 정렬과 동일하게 분할정복법을 사용하나  방법에서 차이를 보인다. 퀵정렬에서는 정렬할 데이터가 주어지면 하나의 값을 기준 값(pivot)으로 사용하여 정렬을 실행한다.

   어떤 값을 기준값으로 잡느냐가 퀵정렬 성능의 관건이다.

   

   1) 분할: 

   하나의 값을 `pivot`으로 선정한 후, 데이터들을 `pivot`보다 큰 값과 작은 값으로 분류한다. 그렇기 때문에 합병정렬과 같이 반으로 갈라지는 것을 보장하지 않는다. 그렇기 때문에 기준값에 따라 최악 / 최선의 상황이 나뉜다.

   2) 정복:

   분할한 양쪽을 각각 재귀로 퀵 정렬한다.

   3) 합병:
   할일이 없다! 이미 분할정복과정에서 정렬 완료


   ![](https://t1.daumcdn.net/cfile/tistory/267ADA4D591BA55F10)

   위의 과정들을 표현한 그림이다. 파티션 후에는 재귀적으로 정렬해주면 되기 때문에 결국 퀵 정렬에서 중요한 것은 파티션이다.

   ```java
   quickSort(A[], p, r) {
       if(p<r) then{
           q = partition(A,p,r); // 분할
           quickSort(A,p,q-1);  // 왼쪽 부분배열 정렬
           quickSort(A,q+1,r);  // 오른쪽 부분배열 정렬
       }
   }
   
   partition(A[], p, r) {
       배열 A[p...r]의 원소들을 A[r]을 기준으로 양쪽으로 재배치하고
           A[r]이 자리한 위치를 return
   }
   ```

   퀵 정렬에 대한 슈도 코드이다. 배열 A의 인덱스 p에서 r사이에 있는 데이터를 정렬한다.

   여기서도 조건문으로 p가 r보다 작은 경우에만 알고리즘이 실행되도록 한다. 다음으로 partition이라는 함수가 pivot값을 기준으로 전체 데이터를 나눠주고 pivot인덱스를 반환하는 역할을 한다. 따라서, q는 피봇이 된다.

   [p, q-1]은 배열의 왼쪽부분, 작은 값이고 [q+1, r]까지는 배열의 오른쪽 부분, 큰 값이다.

   재귀적으로 quickSort함수를 호출하여 정렬해준다.

   ![](https://t1.daumcdn.net/cfile/tistory/210A3D4D591BA56019)

   정렬은 재귀로 동작하기 때문에 실제로 자세히 봐야하는 것은 partition함수이다. 여기에서는 인덱스의 마지막 값을 기준값으로 설정하도록 하겠다. 기준값을 중앙으로 옮기면 불필요한 연산이 발생하므로 기준값 앞에서 작은값과 큰값을 정렬한 후 마지막에 기준값을 원래 위치로 이동시킨다.

   때문에 위의 그림에서 j를 기준으로 설명하자면 현재 인덱스 j의 값이 기준값보다 크다면 j를 증가시켜 다음값을 보러가면 된다. 그러나 인덱스 j의 값이 기준값보다 크다면 앞쪽으로 보내야 하는데, 이때 i값을 1 증가시킨 후 그 값과 교환을 해준다.

   ![](https://t1.daumcdn.net/cfile/tistory/235F884D591BA56117)

   

   ![](https://t1.daumcdn.net/cfile/tistory/225C714D591BA5AC19)

   위에서 본 과정을 나타낸 그림이다.

   여기서 기준값은 마지막 인덱스인 15이다. i와 j를 증가시키며 첫번째 인덱스부터 기준값과 비교하면서 정렬을 수행한다. 모든 정렬이 완료되면 인덱스 i+1의 값과 기준값의 위치를 바꿔준다.

   ```java
   Partition(A,p,r){
       x <- A[r];
       i <- p-1;
       for j <- p to r-1
           if A[j] <= x then
               i<-i+1;
       		exchange A[i] and A[j];
      	exchange A[i+1] and A[r];
       return i+1;
   }
   ```

   Partition을 슈도코드로 나타내었다. 앞서 설명한 위치변환과 인덱스 증가로 정렬을 완료하고 마지막으로 피봇의 인덱스를 리턴해준다.

   ![](https://t1.daumcdn.net/cfile/tistory/255CD24B591BA61025)

   파티션을 하는데 걸린시간은 데이터의 개수가 n개일 때 n-1번의 비교가 이루어진다.

   최악의 경우부터 생각해보면 기준값이 최대/최소값일 때, 분할은 0개와 나머지 전체로 나누어지므로 결국 데이터는 아무 변화가 없고 똑같은 루틴이 반복되므로 시간 복잡도는 O(n^2)가 된다.

   거꾸로 최선의 경우는 항상 절반으로 분할되는 경우로 Merag정렬과 동일한 시간 복잡도를 가지며 원리 또한 같다.

   퀵정렬의 성능은 Partition이 얼마나 밸런스 있게 나뉘냐에 따라 결정된다는 것이다. 극단적인 경우만 아니라면 퀵 정렬의 시간복잡도는 nlogn이 되므로 실제로 상당히 빠른 정렬 방법이 된다.

   ![](https://t1.daumcdn.net/cfile/tistory/2142AE4D591BA56319)

   마지막으로 기준값을 선택하는 방법이다.

   

2. #### 힙정렬

   <https://new93helloworld.tistory.com/106?category=691027>

   > 힙 정렬은 바이너리 힙, 이진 힙이라고 부르는 자료구조를 이용하는 정렬 알고리즘이다. (여기서, 힙은 메모리 영역인 힙과는 다른 말이다.)

   힙 정렬의 특징은 다음과 같다.

   1) 최악의 경우에는 시간 복잡도가 nlogn이 되는 빠른 정렬

   2) 힙 정렬은 알고리즘을 구현하는데 추가 배열 필요x

   3) 힙이라는 자료구조를 이용해서 정렬

   따라서 알고리즘을 구현하기 전에 힙이라는 자료구조에 대해 알아보도록 하자.

   ![](https://t1.daumcdn.net/cfile/tistory/2460943D591BA66526)

   힙(Heap)은

   1) Complete binary tree

   ​	: Binary tree란 자식 노드가 2개씩 분화되는 tree이다. 따라서, 위의 2개는 tree는 모두 이진 트리이며 자식 노드가 생성될 때 왼쪽부터 생성된다.

   
   
    2) Heap property
	2-1) MIN-heap property - 부모는 자식보다 데이터가 크거나 같다.
   	2-2) MAX-heap property - 부모는 자식보다 데이터가 작거나 같다.

   ​	2개의 조건으로 나뉜다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/257FF03D591BA66629)

   (a)는 3개 다 heap이다.

   (b)는 3개 다 heap이 아니다. (heap property를 만족x)
   
   (c)는 2개 다 heap이 아니다.  (complete binary tree의 모양x)


   ![](https://t1.daumcdn.net/cfile/tistory/22229D3D591BA66729)

   개념적으로는 Binary Tree지만 실제적으로는 heap을 1차원 배열로 표현할 수 있다.

   같은 레벨에서 왼쪽부터 배열에 저장하면 1차원 배열이 된다.

   일반적인 트리가 아닌 Complete Binary Tree는 인덱스만으로 부모/자식의 관계를 알 수 있다.

   어떤 노드가 i번이라면 자식에 대해서 `왼쪽은 2*i번`, `오른쪽은 2*i + 1번`

   거꾸로 생각한다면 어떤 노드가 j번이라면 부모는 `j/2번에서 소수점을 제외한 수`가 된다.


   ![](https://t1.daumcdn.net/cfile/tistory/23298E3D591BA66829)

   우리가 heap이라는 자료구조를 다루기 위해 필요한 연산이 있는데, 그것은 max-heapify라고 하자.

   연산을 수행할 전제 조건은 다음과 같다.

   1) 트리 전체 모양은 Complete Binary Tree이다.

   2) 왼쪽 서브트리는 그 자체로 Heap이다.

   3) 오른쪽 서브트리는 그 자체로 Heap이다.

   여기서 유일하게 루트만이 Heap Property를 만족했을 때 이를 만족시키게 변화시키는 것을 MAX-heapify연산이라고 정의한다.

   ![](https://t1.daumcdn.net/cfile/tistory/224D423D591BA66932)

   루트 노드가 정렬되기 위해서는 누군가가 루트의 자리를 대체하고 루트는 그 자리로 가야한다.

   루트 노드에서 자신의 노드 중에 더 큰 값은 선택한다. 그리고 그 값과 자리를 바꾼다. 이 과정을 마지막 레벨까지 반복한다.

   ```java
   MAX-Heapify(A, i) {
       if there is no child of A[i]
           return;
       k <- index of the biggest child of i;
       if A[i] >= A[k]
           return;
       exchange A[i] and A[k];
       MAX-Heapify(A, k);
   }
   ```

   위의 과정은 Heapify를 재귀로 구현한 것이다.

   첫번째 조건은 base case이다. 자식 노드가 없다는 것은 마지막 레벨까지 왔다는 뜻이므로 현재 노드는 리프(leaf)이다. 따라서 return한다.

   만약 자식이 있다면 더 큰쪽을 k라고 하고, 자식이 1명만 있는 노드일 경우 1개의 노드를 k라고 지정한다.

   자식노드보다 크다면 정렬된 것이므로 리턴한다. 그렇지 않으면 큰 쪽의 자식 노드와 자리를 변경하며 MAX-Heapify를 재귀 호출한다.

   ```java
   MAX-Heapift(A, i) {
       while A[i] has a child do
           k <- index of the biggest child of i;
       	if A[i] >= A[k];
       		return;
       	exchange A[i] and A[k];
       	i=k;
       end
   }
   ```

   같은 함수를 iterate하게 구현하였다.

   MAX-Heapify의 시간 복잡도는 루브부터 마지막 레벨까지 비교, 교환 연산을 하므로 트리의 높이보다 많은 시간이필요하지 않다. 따라서 시간 복잡도는 높이에 의해서 결정되며, O(h)이다.

   일반적인 이진트리가 아니라 Complete Binary Tree이므로 노드의 개수를 n이라고 했을 때 시간 복잡도는 O(logn)이 된다.


3. #### Heapify

   > 어떻게 heap정렬을 할 것인가? 그 전에 정렬하기 위해서는 heap을 만들어야 한다.
   >
   > 정렬할 1차원 배열을 Complete Binary Tree로 해석하고 leaf가 아닌 그 다음 레벨의 노드에서부터 Heapify연산을 통해 heap으로 만들어준다.

   ```java
   BUILD-MAX_HEAP
   heap-size[A] <- length[A]
   for i <- |length[A]/2| downto 1
       do MAX-HEAPIFY(A, i)
   ```

   따라서 슈도코드는 다음과 같다. (미리 Heapify연산을 구현)

   힙을 만드는데의 시간 복잡도는 Heapify의 연산 시간 log(n)과 for문이 n/2번 돌기 때문에 빅오로 표기하면 O(nlogn)이 된다. 이는 루트 노드만 고려하였기 때문에, 정확하게 계산한다면 시간 복잡도는 O(n)이 된다.

   일단 힙을 만드는데는 n의 시간이 걸리지만 정렬을 하는데 시간이 nlogn이기 때문에 결과적으로 시간 복잡도는 nlogn이 나오게 된다.

   ![](https://t1.daumcdn.net/cfile/tistory/254ED242591BA7232F)

   위의 그림은 Tree를 Heap으로 만드는 과정이다.

   힙 정렬은 다음과 같은 순서를 따른다.

   1) 주어진 데이터를 힙으로 만든다.

   2) 힙에서 최대값(루트)을 가장 마지막 값과 바꾼다.

   3) 힙의 크기가 1 줄어든 것으로 간주한다. 즉, 마지막 값은 힙의 일부가 아닌 것으로 간주한다.

   4) 루트 노드에 대해서 Heapify(1)한다.

   5) 2~4번을 반복한다.

   데이터를 힙으로 만들면 인덱스 0의 값이 가장 최대값이므로 마지막 값과 바꾼다. 그리고 마지막 값은 정렬된 값으로 간주하고 더 이상 신경쓰지 않는다.

   ```java
   HeapSort(A)
       BUILD-MAX-HEAP(A)			   // O(n)
       for i <- heap-size downto 2 do // n-1 times
           exchange A[1] <-> A[i]	   // O(1)
           heap_size <- heap_size-1   // O(1)
           MAX-HEAPIFY(A, 1)		   // Olog((n))
   ```

   완성된 힙 정렬의 슈도 코드이다.

   MAX-HEAPIFY의 시간 복잡도는 logn의 밑은 2이다.

   따라서 총 시간 복잡도는 nlogn(밑은 2)가 된다.
   
   
   
4. #### Heap의 응용, 우선순위 큐(Priority queue)

   > Queue는 FIFO구조를 가진 자료구조로 우선순위 큐는 이러한 큐의 한 종류이다.
   >
   > 우선순위 큐는 최대 우선 큐와 최소 우선 순위 큐로 나뉜다. 우리는 최대 우선 순위 큐를 중심으로 알아보도록 하겠다.

   1) INSERT(x)  : 새로운 원소 x를 삽입

   2) EXTRACT_MAX : 최대값을 삭제하고 반환

   반대로 최소 우선 순위 큐는 EXTRACT_MAX 대신 EXTRACT_MIN을 지원한다. MAX HEAP을 이용해서 최대 우선 순위 큐를 구현할 수 있다.

   ![](https://t1.daumcdn.net/cfile/tistory/27555543591BA75A2C)

   먼저 INSERT에 대해 알아보도록 하자.

   현재 Heap의 상황은 Complete Binary Tree와 MAX Heap Property를 만족한다.

   INSERT로 새로운 노드를 추가하려면 마지막 leat에 추가될 수 밖에 없으며 이때 MAX Heap Property를 만족시키기위해 Heap Sort를 이용해서 전체 Heap을 정렬시켜서 Max Heap의 형태를 만들도록 해야 한다.

   ```java
   MAX-HEAP_INSERT(A, key) {
       heap_size = heap_size+1;
       A[heap_size] = key;
       i = heap_size;
       while(i>1 and A[Parent(i)] < A[i]) {
   		exchange A[i] and A[Parent(i)];
           i = Parent(i);
       }
   }
   ```

   슈도 코드로 MAX-Heap-Insert를 나타내었다. 위의 코드에서 A가 Heap이다.

   Heap의 사이즈를 1 증가시키고 그 자리에 새로운 key값을 넣어준다. i는 새로 추가된 노드이다. 그 후 while문에서 i>1이라는 것은 root노드가 아니라는 뜻이며 A[Parent(i)] < A[i] 부모노드에 저장된 값보다 크다는 것(MAX Heap이 아니라는 것)이다. 위의 조건에 해당되면 부모 노드와 값을 교환해준다.

   다시 말해 root 노드가 될때까지 또는, 자신의 부모 노드보다 작을 때까지 교환연산을 진행하게 되는 것이다. 이때 시간 복잡도는 트리에 높이에 비례하게 되나 Heap은 Complete Binary Tree이므로 O(nlogn)이다.

   ![](https://t1.daumcdn.net/cfile/tistory/2552FC43591BA75B31)

   다음은 EXTRACT_MAX를 표현한 그림이다. Heap은 아무 노드나 삭제할 수 없다. 여전히 Complete Binary Tree를 만족하기 위해서는 마지막 노드를 삭제할 수 밖에 없다.

   따라서 root노드 값을 삭제하고 마지막 노드에 있던 값을 root노드로 옮겨주게 된다. 이후 Heap property복원을 위해서 sort를 진행하게 된다.

   ```java
   HEAP-EXTRACT-MAX(A) {
   	if heap-size[A] < 1
           then error "heap underflow"
       max <- A[1]
       A[1] <- A[heap-size[A]]
       heap-size[A] <- heap-size[A]-1
       MAX-HEAPIFY(A, 1)
       return max
   }
   ```

   EXTRACT-MAX의 슈도코드이다. size가 1보다 작을 경우 Dequeue연산을 진행할 수 없으므로 에러메세지를 출력한다.

   그렇지 않으면 A[1]의 값(root)을 리턴하기 위해 MAX에 넣어주고, 마지막 값을 루트로 이동시켜준 후 마지막 노드를 삭제한다.

   마지막으로 Sort함수를 호출하면 EXTRACT-MAX연산이 완료된다.
   
5. #### 정렬의 Low Bound / 시간복잡도의 최선은 왜 O(nlogn)일까

   > 왜 O(nlogn)이라는 시간복잡도가 최선일까? 미리 결론부터 이야기하자면 Comparison Sort(비교 정렬)에 대해서는 nlogn보다 더 나은 복잡도가 없다는 것이다.

   비교정렬은 데이터들의 크기 관계가 정의되어있다면 어떤 데이터형에서도 적용이 가능하다. 어떤 좋은 Comparison Sort도 nlogn을 넘지 않기 때문에 이와 같은 개념을 `하한`이라고 한다.

   ![](https://t1.daumcdn.net/cfile/tistory/22146242591BA79F26)

   이를 위해서 Decision Tree라는 것을 이용해 `하한`에 대해 알아보자.

   위의 그림은 Insertion Sort에 대한 예제인데, Decision Tree는 이러한 과정을 하나의 Tree로 나타낸 것이다.

   정렬할 데이터가 a(1), b(2), c(3)라면 맨 처음(root) 순서는 a(1)와 b(2)를 비교하는 것이다. 이에 따라 선택지는 2개로 분화되게 된다. a(1)가 큰 경우와 b(2)가 큰 경우이다.
   
   이와 같이 데이터를 비교할 때, 데이터 값에따라 나오는 경우의 수는 모든 순열에 해당되기 때문에 이 decision tree에서 leaf노드는 n!개가 된다.
   
   따라서, decision tree를 이용해 구해본 최악의 경우 시간 복잡도는 이 트리의 높이에 비례한다.(비교 연산)
   
   그렇다면 이진 트리에서 leaf node가 n!일 때 트리의 높이는
   
   `height >= log(n!) = O(nlogn)`이 된다.
   
   
