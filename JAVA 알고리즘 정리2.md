# JAVA 알고리즘 정리 2

## Heap과 Graph

# Heap

 &#9989; <https://kingpodo.tistory.com/30?category=805745> 

> 힙은 각 노드의 키 값이 그 자식의 키 값보다 작지 않은 완전 이진 트리이다. 이것을 최대 힙이라고 한다.  또한 힙을 각 노드의 키 값이 그 자식의 키값보다 크지 않은 완전 이진 트리로 정의하기도 하는데 이것을 최소 힙이라고 한다. 

1. 힙에서의 삽입

   삽입해야 하는 노드의 키 값을 부모노드와 비교하여 위치를 변경하여 최대 힙 또는 최소 힙 형태를 만들어야 합니다.

   ```java
   public static void insertHeap(int[] heap, int size, int item) {
       // heap의 배열과 size는 현 heap의 원소의 개수, item은 삽입 원소
       if(size == heap.length-1) {
           // 힙이 만원이 상태라면 종료
           System.out.println("Heap size 초과");
           return;
       }
       int i = ++size; 
       // 다음 item이 들어갈 원소 위치
       while(true) {
           if(i==1) {break;}
           // 힙의 원소가 하나도 없었따면 반복문 탈출
           if(item < heap[i/2]) {break;}
           // 아이템이 부모 노드보다 작다면 반복문 탈출
           heap[i] = heap[i/2];
           // 부모노드의 키 값을 자식노드로 이동
           i /= 2;
           // i의 위치를 즉 원소가 들어갈 자리를 부모노드로 변경
       }
       heap i] = item;
       // 정해진 i위치에 item삽입
   }
   ```



2. 힙에서의 삭제

   힙에서 원소의 삭제는 루트의 있는 원소를 제거하고 나머지 트리가 다시 힙이 되도록 재조정해야 합니다.

   ```java
   public static int deleteHeap(int[] heap, int size) {
       // 힙으로부터 원소 삭제
       int n = size;
       // Heap안에 있는 마지막 원소의 다음 위치
       if(n == 0) {return 999;}
       // 삭제할 원소가 없다면 에러
       int item = heap[1]; // 삭제 노드를 item에 저장
       int temp = heap[n]; // 마지막 원소를 temp에 저장
       
       n--; // 마지막 원소의 삭제
       int i = 1;
       // 원소를 가르키는 변수 최초는 삭제하는 루트
       int j = 2;
       // j변수는 i변수의 왼쪽 서브트리를 가르키는 변수
       while(j <= n) {
           // 서브트리의 위치가 현 원소의 수보다 커진다면 힙의 크기를 초과하니까 종료
           if(j = heap[j]) {
               // temp가 현 서브트리보다 크다는 것은 현 i위치가 원소의 위치가 맞다는 것이다.
               break;
           }
           heap[i] = heap[j];
           // 서브트리를 한 레벨 위로 이동
           i = j; // 비교할 노드의 위치를 바꿔줌
           j *= 2; // j의 서브트리 위치를 이동한다.
       }
       heap[i] = temp; // 마지막 원소를 i위치에 넣어준다.
       return item;
   }
   ```

3. 완전 이진트리 힙으로 만들기

   힙으로 되어있지 않은 완전 이진트리를 힙으로 변환하기 위해서는 역 레벨 순서로 힙의 내부 노드 각각을 루트 노드로 하는 서브트리를 차례대로 힙으로 만들면 된다.

   ```java
   public static void makeHeap(int[] bt, int size) {
       // 완전 이진트리를 Heap형태로 만든다.
       // bt는 Heap이 아닌 완전 이진 트리(binary tree), size는 원소의 개수
       for(int i=size/2; i>=1; i--) {
           // 서브트리가 있는 마지막 노드, 레벨순서의 역으로 시작
           int p=i;
           // 선택된 노드의 번호를 저장
           for(int j=2*p; j<=size; j*=2) {
               // j는 p노드의 왼쪽 서브트리를 가리킨다.
               // j는 전체 트리보다 커질 수 없고 증감식에서는 다음 서브트리를 가리킨다.
               if(j < size) {
                   // j의 부모노드가 오른쪽 자식 노드가 없는지 있는지 검사! 있다면
                   if(bt[j] < bt[j+1]) {
                       // 두 서브트리를 비교하여 큰 것을 선택
                       j++;
                   }
               }
               if(bt[p] < bt[j]) {break;}
               // 현 노드가 서브노드부터 크다면 다음레벨로 이동
               int temp = bt[p]; // 그것이 아니라면 둘을 교환
               bt[p] = bt[j];
               bt[j] = temp;
               p = j; // 부모 노드를 한 레벨 밑으로 이동
           }
       }
   }
   ```



### 그래프(graph)

&#9989; <https://kingpodo.tistory.com/45?category=805745>

> 그래프는 G=(V,E)로 정의된다. 여기서 V는 공백이 아닌 노드 또는 정점의 유한 집합으로 정점만을  표현할 때는 V(G)라고 표기. E는 상이한 두 정점을 잇는 간선의 유한 집합으로 간선만을 표현할 때는 E(G)라 표기합니다.

 1. 방향그래프, 무방향그래프

    * 무방향 그래프: 간선을 표현하는 두 정점의 쌍에 순서 즉 방향이 없는 그래프입니다. 따라서 두 정점 V0과 V1을 잇는 간선 (V0, V1)과 (V1, V0)는 똑같은 간선을 나나타낸다.
    * 방향 그래프: 모든 간선을 순서가 있는 두 정점의 쌍으로 표현하는 즉, 간선이 방향을 가진 그래프입니다. 방향 그래프에서는 하나의 간선을 V0 -> V1을 <V0, V1>이라 표기하며 V0를 tail, V1를 Head라 합니다. 또한 방향 그래프에서는 <V0, V1>과 <V1, V0>는 다른 간선입니다. 방향 그래프에서는 이 간선을 아크라고도 합니다.

    

	2.  완전 그래프

    간선을 최대한으로 가진 그래프를 말한다. n개의 정점을 가진 무방향 그래프의 최대 간선 수는 n(n-1)/2입니다. 그러나 n개의 정점을 가진 방향 그래프의 최대 간선은 n(n-1)개가 됩니다.

    

	3. 인접과 부속

    * 인접: 간선 (V0, V1)가 무방향 그래프 G의 간선일 때 노드 V0와 V1은 서로 인접한다고 합니다.
    * 부속: 간선 (V0, V1)가 무방향 그래프 G의 간선일떄 간선 (V0, V1)은 정점 V0와 V1의 부속이 된다고 합니다.

    

	4. 부분 그래프

	5. 경로

	6. 사이클

    첫번째 지점과 마지막 정점이 동일한 단순 경로를 말한다. 따라서 무방향 그래프에서는 사이클의 길이는 항상 3이상이고 방향 그래프에서 사이클의 길이는 항상 2이상이다. 또한 이러한 사이클이 없는 그래프를 DAG(Directed Acyclic Graph)라고 한다.



7. 강한 연결, 약한 연결

   방향 그래프 G에서 V(G)에 있는 서로 다른 모든 정점의 쌍 u와 v에 대해 u에서 v까지의 방향 경로와 v에서 u까지의 방향경로가 있을 때 `강한 연결`이라고 한다. 만일 이때 u에서 v까지나 v에서 u까지 어느 하나의 경로만 있다면 `약한 연결`이다.



8. 차수

   * 정점의 차수는 그래프에서 그 정점에 부속된 간선의 수를 말한다.
   * 진입 차수: 만약 그래프가 방향 그래프라면 정점 v의 진입 차수는 정점 v를 head로 하는 아크의 수를 말합니다.
   * 진출 차수: 만약 그래프가 방향 그래프라면 정점 v의 진출 차수는 정점 v를 tail로 하는 아크의 수를 말합니다.

   아래 그래프 a의 정점 V0의 차수는 3이며 그래프 b의 정점 V0의 진입 차수는 2, 진출 차수는 3입니다.



### 그래프의 표현(인접 행렬, 인접 리스트, 인접 다중 리스트)

&#9989; <https://kingpodo.tistory.com/46?category=805745>

> 그래프를 표현하는 방법은 여러가지가 있지만 그 중 대표적인 방법이 인접행렬, 인접 리스트, 인접 다중 리스트이다. 이때 어떤 표현 방법을 선택하느냐 하는 것은 그래프에 수행시키려는 연산과 이용하려는 응용에 달려 있다고 하겠다.

1. 인접 행렬

   그래프 G=(V,E)를 n >= 1(n은 정점의 수)의 정점이 가진 그래프라고 하였을 때, 그래프 G에 대한 인접행렬의 크기는 n*n이며 a[n,n]크기의 2차원 배열로 표현된다. 이때 a[n,n]에서 a[i,j] ∈ E(G)라면 1값을 아니라면 0의 값을 가지게 된다.

   무방향 그래프의 경우 인접행렬은 간선간의 특성상 대칭이 된다.

   방향 그래프는 인접행렬의 표현 방식에서 진입 차수와 진출 차수는 a[i,j] ∈ E(G)의 경우에 i 행의 합을 구하면 진출 차수이고 i 열의 합을 구하면 진입 차수이다.

2. 인접 리스트

   인접 리스트로 그래프를 표현하는 방법은 n개의 정점을 각각에 대해 인접한 정점들의 리스트로 만드는 것이다. 그러기 위해서는 아래 그림과 같이 리스트의 노드 구조를 정점필드와 주소필드로 구성하여야 한다. 그래야만 어떤 정점 i에 대한 인접 리스트에 정점 i와 인접한 정점들을 나타내는 노드들을 포함시키게 할 수 있다.

   ![](https://t1.daumcdn.net/cfile/tistory/9992FC4B5B543F5C0F?download)

   ![](https://t1.daumcdn.net/cfile/tistory/9992FC4B5B543F5C0F?download)

   아래와 같은 방식을 사용할 때에는 정점 n개와 간선 e개를 가진 무방향 그래프의 경우 n개의 헤더 노드와 2*e개의 리스트 노드가 필요하게 된다. 그리고 각 리스트 노드는 link필드를 가지게 된다. 그러나 포인터를 사용하지 않고 인접 리스트의 노드를 순차적으로 묶어서 저장하는 방법도 있다. 이 경우 배열 vertex[]를 사용한다면 배열의 크기는 `n + 2e + 1`이 된다. 이는 link의 공간이 필요없기 때문에 공간의 사용을 줄일 수 있다.

   아래 그림은 위 <a>그림의 그래프를 배열의 순차 표현으로 나타내고 있다.

   * 정점이 4개, 간선이 5개이기 때문에 n+2e+1로 계산하면 배열의 크기는 15가 됩니다.
   * 배열의 0~n-1까지 자리는 각 정점별 인접 리스트의 시작 위치를 나타냅니다.
   * 배열의 n번째 자리는 배열의 크기를 나타냅니다. 여기서 배열의 크기는 15입니다.
   * 이후 n+1 ~ n+2e+1 -1자리까지는 각 정점의 인접 리스트를 담고 있습니다. 예로 배열 [7], [8], [9]에는 1번 정점의 인접 리스트입니다.

   ![](https://t1.daumcdn.net/cfile/tistory/9930F7405B555FFE07?download)

3. 인접 다중 리스트

   무방향 인접 리스트의 표현에서 각 간선은 (i,j)는 실제로 i정점과 j정점 두 개의 인접 리스트를 포하하게 된다. 즉 하나의 간선은 두 개의 노드의 인접 리스트를 표현할 수 있습니다. 즉, 다중 리스트란 노드들이 여러 리스트들이 공용하는 리스트를 말합니다.

   ![](https://t1.daumcdn.net/cfile/tistory/99A8594D5B556F0B24?download)

   위 그림은 그림의 <a>그래프를 인접 다중 리스트로 나타낸 것입니다. 우선 간선을 표현하는 노드 리스트를 보면 M은 마크 비트로 이 간선이 이미 검사되었는지 여부를 확인합니다. i, j는 각 간선에 부속된 정점입니다. i-link, j-link는 각 정점의 인접 리스트의 링크를 나타냅니다.

   정점 0에 대한 인접리스트는 다음과 같이 식별합니다. 정점 0은 최초 E0로 이동하고 E0리스트의 정점 0의 인접리스트의 링크와 연결된 E1으로 이동하게 됩니다. 다시 한번 E1리스트의 정점 0의 인접리스트 링크를 확인하니 null이기 때문에 리스트가 끝나게 됩니다.

   위 그래프의 모든 정점의 인접 리스트

   * 정점0: E0 -> E1
   * 정점1: E0 -> E2 -> E3
   * 정점2: E2 -> E4
   * 정점3: E1 -> E3 -> E4



### 그래프의 순회 ( 깊이 우선 탐색(DFS) )

> 그래프의 순회란 주어진 어떤 정점에서 출발하여 체계적으로 그래프의 모든 정점을 순회하는 것으로 깊이 우선 탐새과 너비 우선 탐색이 있다.

1. 깊이 우선 탐색

   주어진 무방향 그래프 G= (V,E)가 있을 때 정점 i에서 DFS수행하는 방법

   1. 정점 i를 방문한다.

   2. 정점 i에 인접한 정점 중에서 아직 방문하지 않은 정점이 있으면 이 정점을 모두 stack에 저장한다.

   3. stack에서 정점을 삭제하며 새로운 i를 설정하고 다시 1단계부터 수행한다.

   4. stack이 공백이 되면 연산 종료

      visited[n] = true / false

2. 수행과정

   <https://kingpodo.tistory.com/47?category=805745>

   ```java
   public class DFS {
       public static void depthFS(int[][] graph, int vertex) {
           // 깊이 우선 탐색을 수행하는 함수
           // vertex는 시작점
           Stack stack = new Stack(100);
           // 깊이 우선 탐색에서 사용할 stack의 생성
           boolean[] visited = new boolean[graph.length];
           // visited 배열의 생성
           for(int i=0; i<visited.length; i++) {
               // visited의 배열을 false로 초기화
               visited[i] = false;
           }
           stack.push(vertex);
           while(!stack.isEmpty()) {
               // stack이 공백이 될때까지 실행.
               int v = stack.pop();
               // stack에 있는 데이터를 pop한다.
               if(visited[v] == false) {
                   // pop한 정점이 방문하지 않은 상태라면
                   System.out.print(v + " ");
                   // 방문을 하고
                   visited[v] = true;
                   // visited배열을 true로 바꿔준다.
                   for(int i=graph[v].length-1; i>=0; i--) {
                       // 방문한 정점에 인접한 정점을 찾는다.
                       if(graph[v][i] == 1) {
                           if(visited[i] == false) {
                               // 인접한 정점 중에 방문을 안한 정점이 있다면
                               stack.push(i); // stack에 push한다.
                           }
                       }
                   }
               }
           }
       }
       public static void main(String[] args) {
           int[][] graph = {
               {0,1,1,1,0,0,0}.
               {1,0,0,0,1,0,0},
               {1,0,0,0,1,1,0},
               {1,0,0,0,0,1,0},
               {0,1,1,0,0,0,1},
               {0,0,1,1,0,0,1},
               {0,0,0,0,1,1,0}
          };
          depthFS(graph,0);
       }
   }
   ```

   ```java
   // DFS에 사용한 stack의 소스코드
   class Stack {
       int stack[];
       int top;
       
       public Stack(int n) {
           stack = new int[n];
           top = -1;
       }
       public void push(int item) {
           if(top >= stacl.length-1) {
               // top가 배열의 크기보다 크면 stack이 full상태
               System.out.println("Stack Overflow");
               return;
           } else {
               // stack이 full상태가 아니라면
               top = top + 1;
               stack[top] = item;
           }
       }
       
       public int pop() {
           if(top <= -1) {
               // top이 -1이면 Stack이 공백을 의미한다.
               System.out.println("Stack Empty");
               return 99;
           } else {
               // Stack이 공백이 아니라면 배열이 top인덱스 값을 출력하고 top을 감소시킨다.
               return stack[top--];
           }
       }
       
       public boolean isEmpty() {
           if(top <= -1) {
               // top이 -1이면 이는 Stack의 공백을 의미
               return true;
           } else {
               // 공백이 아니면 배열의 인덱스 값을 출력하고 top을 감소시킨다.
               return false;
           }
       }
   }
   ```


### 그래프의 순회(너비 우선 탐색(BFS))

> 그래프의 순회란 주어진 어떤 정점에서 출발하여 체계적으로 그래프의 모든 정점을 순회하는 것으로 깊이 우선 탐새과 너비 우선 탐색이 있다.

1. 너비 우선 탐색

   주어진 무방향 그래프 G=(V,E)가 있을 때 정점 i에서 BFS를 수행하는 방법은 아래와 같습니다.

   1. 정점 i를 방문한다.
   2. 정점 i에 인접한 정점 중에서 아직 방문하지 않은 정점이 있다면 이 정점을 모두 Queue에 저장한다.
   3. Queue에서 정점을 삭제하여 새로운 i를 선정하고 다시 단계 1을 수행한다.
   4. Queue가 모두 공백이 되면 연산을 종료한다.

2. 수행과정

   <https://kingpodo.tistory.com/48?category=805745>

   ```java
   public class BFS {
       public static void breadthFS(int[][] graph, int vertex) {
           // 너비 우선 탐색을 하는 메소드
           // vertex는 시작점
           Queue queue = new Queue(100);
           boolean[] visited = new boolean[graph.length];
           // visited 배열의 생성
           for(int i=0; i<visited.length; i++) {
               // visited 배열을 false로 초기화
               visited[i] = false;
           }
           queue.enqueue(vertex);
           while(!queue.isEmpty()) {
               int v = queue.dequeue();
               if(visited[v] == false) {
                   System.out.print(v + " ");
                   visited[v] = true;
                   for(int i=0 ; i<graph[v].length; i++) {
                       // 방문한 정점에 인접한 정점을 찾는다.
                       if(graph[v][i] == 1) {
                           if(visited[i] == false) {
                               // 인접한 정점 중에 방문을 안한 정점이 있다면
                               queue.enqueue(i);
                               // queue에 enqueue한다.
                           }
                       }
                   }
               }
           }
       }
       public static void main(String[] args) {
           int[][] graph = {
               {0,1,1,1,0,0,0}.
               {1,0,0,0,1,0,0},
               {1,0,0,0,1,1,0},
               {1,0,0,0,0,1,0},
               {0,1,1,0,0,0,1},
               {0,0,1,1,0,0,1},
               {0,0,0,0,1,1,0}
           };
           breathFS(graph, 0);
       }
   }
   ```

   아래는 너비 우선 탐색을 수행할 때 사용된 Queue의 소스코드입니다.

   ```java
   class Queue{
       int[] q;
       int rear;
       int front;
       
       public Queue(int n) {
           q = new int[n];
           rear = -1;
           front = -1;
       }
       
       pubic boolean isEmpty() {
           if(front == rear) { return true;} // Queue가 공백인 상태
           else { return false} // Queue가 dataㅇ 있는 상태
       }
   
       public void enqueue(int item) {
           if(rear == q.length-1) {
               System.out.println("Queue Full");
               return;
               // Queue가 full인 상태 데이터 삽입 불가
           } else {
               rear = rear + 1; // rear를 증가
               q[rear] = item; // rear위치에 item 삽입
           }
       }
       
       public int dequeue() {
           if(isEmpty()) {
               // Queue가 공백이면 99를 반환
               System.out.print("Queue Empty");
               return 99;
           } else {
               // Queue에 데이터가 있다면 front값을 1증가 후 데이터를 반환
               front = front + 1;
               return q[front];
           }
       }
   }
   ```


### 신장 트리와 최소 비용 신장 트리

> 신장트리는 연결 그래프의 부분 그래프로서 그 그래프의 모든 정점과 간선의 부분 집합으로 구성되는 트리로 모든 노드는 적어도 하나의 간선에 연결되어 있어야 합니다. 단 그래프에 사이클이 형성이 되면 안됩니다. 이러한 신장트리는 연결 그래프일 때 깊이 우선 탐색이나 너비 우선 탐색을 통하여 구현이 가능합니다. 이때 주어진 연결 그래프에 대한 신장트리는 하나가 아니라 다양할 수 있습니다. 아래 그림은 신장트리의 예를 보여주고 있습니다.

![](https://t1.daumcdn.net/cfile/tistory/9995153D5B580CE705?download)

![](https://t1.daumcdn.net/cfile/tistory/9995883D5B580CE805?download)



1. 최소 비용 신장트리란?

   그래프의 간선에 가중치가 부여된 그래프를 가중치 그래프 또는 네트워크라고 합니다. 이때 가중치란 비용이나 거리를 의미하는 값이 될 수 있습니다. 가중치가 부여된 무방향 그래프의 신장 트리 비용은 신장 트리를 구성하는 모든 간선의 비용을 합한 것입니다. `최소비용 신장트리는 트리를 구성하는 간선들의 가중치를 합한것이 최소가 되는 신장트리 입니다.` 이러한 최소비용 신장트리를 찾아내는 방법은 여러가지가 있습니다.



### 프림 알고리즘

> n개의 정점을 가지는 그래프에서 최소 신장 트리를 구하기 위해서는 n-1개의 간선을 선택해야 합니다. 이 간선들이 모여서 트리를 이루어야하며 그 가중치 합은 최소가 되야합니다. 이러한 최소 신장 트리를 해결하는 `프림 알고리즘은 greedy method`에 근거합니다. 이는 그래프에서 정점과 그에 따른 간선을 하나씩 선택하여 트리를 구성하되 그 선택기준은 지금까지 선택된 간선들의 가중치 합이 가장 적게 증가하도록 하는 것입니다. 즉 비용이 가장 적은 간선을 선택합니다.

1. 수행과정

   아래는 주어진 무방향 그래프 G = (V, E)가 있을 때 프림 알고리즘의 수행과정입니다.

   1. 트리의 시작점으로 정점 v1을 정합니다.
   2. 나머지 각 정점에서 v1과 연결된 간선의 가중치를 조사한 후, 가장 작은 가중치를 가지는 간선과 그에 따른 정점을 트리에 추가한다.
   3. 트리에 이미 포함된 정점을 제외한 외부의 나머지 각 정점 중에서 트리와의 거리가 가장 가까운 즉 비용이 가장 적은 정점을 연결하여 간선을 트리에 추가한다.
   4. 트리에 정점과 간선을 추가하는 단계 3의 과정을 트리 내 간선의 개수가 n-1이 될 때 까지 반복한다.

   <https://kingpodo.tistory.com/50>

   ```java
   public class PrimMST {
       final static int infi = 999;
       // 연결이 되지 않음, 가중치 무한대를 뜻함
       public static void prim(int[][] c, int n, int[][] t, int vertex) {
           // c배열은 그래프의 인접행렬, n은 정점의 개수, t배열은 연결된 간선 배열, vertex는 시작 정점
           int[] from = new int[n];
           // 현재 부분 배열이 어디로부터 연결된지 표시하는 from배열 생성
           int[] dist = new int[n];
           // 현재 부분 배열의 각 정점마다의 가중치를 표시
           for(int i=0; i<n; i++) {
               // 최초 시작정점은 첫번째 정점임으로 vertex로 초기화
               from[i] = vertex;
               dist[i] = c[vertex][i];
           }
           for(int i=0; i<n-1; i++) {
               // n-1은 연결될 간선의 수
               int best = isBest(dist);
               // dist배열 중 0을 제외한 최소값을 가진 원소의 인덱스
               t[0][i] = from[best] + 1;
               // 가중치가 가장 적은 간선을 선택해서 넣어준다. +1은 정점이 1부터 시작해서 넣음.
               t[1][i] = best + 1;
               dist[best] = 0;
               // 부분 트리로 선택된 정점까지의 가중치는 0으로 변경
               for(int j=1; j<n; j++) {
                   // dist배열과 from배열의 최신화
                   if(c[best][j] < dist[j]) {
                       // 최근에 선택된 정점에 인접한 정점에 가중치와 기존 dist배열과 비교
                       from[j] = best;
                       dist[j] = c[best][j];
                   }
               }
           }
       }
       
       public static int isBest(int[] dist) {
           // dist배열 중 0을 제외한 최소값을 가진 원소의 인덱스를 반환하는 메소드
           int best = 0;
           for(int i=0; i<dist.length; i++) {
               if(dist[i] != 0) {
   	            best = i;
                   break;
               }
           }
           for(int j=0; j<dist.length; j++) {
               // dist에서 0이아닌 값 중 값이 가장 작은 인덱스를 찾는다.
               if(dist[j] != 0 && dist[j] < dist[best]) {
                   best = j;
               }
           }
           return best;
       }
       
       public static void main(String[] args) {
           int[][] graph = {
               {0,6,7,infi,10,9},
               {6,0,8,infi,infi,infi},
               {7,8,0,4,5,infi},
               {infi,infi,4,0,3,11},
               {10,infi,5,3,0,11},
               {9,infi,infi,infi,11,0}
           };
           int[][] t = new int[2][graph.length-1];
           prim(graph, graph.length, t, 0);
           for(int i=0; i<t[0].length; i++) {
               System.out.println(t[0][i] + " " + (t[1][i]));
           }
       }
   }
   ```



### 크루스칼 알고리즘

> n개의 정점을 가지는 그래프에서 최소 신장 트리를 구하기 위해서는 n-1개의 간선을 선택해야 합니다. 이 간선들이 모여서 트리를 이루어야하며 그 가중치 합은 최소가 되도록 해야합니다. 크루스칼 알고리즘은 간선을 하나씩 추가하여 최종 지점이 되면 비로소 신장 트리의 모습을 갖추게 됩니다.

> 프림 알고리즘이 하나의 정점을 기초로 외부 정점을 추가하면서 트리를 성장시키는 방식인데 크루스칼 알고리즘은 간선을 선택하기 때문에사이클이 생성될 수 있으며 이러한 사이클을 배제시켜야 합니다.

1. 크루스칼 알고리즘의 수행과정

   아래는 주어진 무방향 그래프 G = (V, E)가 있을 때 크루스칼 알고리즘의 수행과정.

   1. 그래프 G의 모든 간선들을 오름차순으로 정렬한다.
   2. 가장 가중치가 작은 간선을 선택한다.
   3. 남은 간선들 중에 가장 가중치가 작은 간선을 찾은 후, 이 간선을 추가해도 사이클이 생기지 않는다면 이 간선은 선택한다.
   4. 선택된 간선들의 개수가 n-1이 될때까지 단계3과정을 반복한다.

   <https://kingpodo.tistory.com/51?category=805745>

   ```java
   public class KruskalMST {
   	public static void kruskal(int[][] e, int n, int[][] t) {
           // e[][]은 간선을 오름차순으로 정렬한 배열, n은 정점의 수, t[][]는 선택된 간선을 저장하는 배열
           int edgCount = 0; // 현재 추가된 간선의 수
           int i = 0; //  e[][]배열을 순회하기위한 변수
           int v1, v2; // 선택된 정점의 임시 저장 변수
           int s1, s2; // 같은 서브 트리인지 확인하기 위한 변수
           int[] s = {0,1,2,3,4,5};
           
           while(edgCount < n-1) {
               // 선택된 간선의 수가 n-1보다 작은 즉, 간선을 n-1개 선택
               v1 = e[i][0]; // i번째 가중치를 가진 간선의 첫번째 정점
               v2 = e[i][1]; // i번째 가중치를 가진 정점의 두번째 정점
               s1 = s[v1]; // 해당 정점이 속한 서브트리를 확인
               s2 = s[v2];  // 해당 정점이 속한 서브트리를 확인
               
               if(s1 != s2) {
                   // 선택된 두 정점의 서브트리가 다르다면 실행
                   for(int j=0; j<s.length; j++) {
                       // s2가 속한 서브트리를 전부 s1이 속한 서브트리로 합친다.
                       if(s[j] == s2) {
                           s[j] = s1;
                       }
                   }
                   t[edgCount][0] = v1; // 선택된 두 정점의 간선을 저장
                   t[edgCount][1] = v2;
                   edgCount++; // 선택된 간선의 수를 증가
               }
               i++; // 그 다음 가중치를 가진 간선을 선택하기 위해 증가
           }
       }
       
      public static void main(String[] args) {
          int[][] e = {{3,4}, {2,3}, {2,4}, {0,1}, {0,2}, {1,2}, {0,5}, {0,4}, {4,5}};
          int[][] t = new int[5][2];
          kruskal(e,6,t);
          
          for(int i=0; i<t.length; i++) {
              System.out.println(t[i][0]+1 + " " + (t[i][1]+1));
          }
      }
   }
   ```


### 다익스트라의 최단 경로(단일 출발점)

> 다익스트라의 알고리즘은 방향 그래프에서 단일 출발점에서의 각 정점으로의 최단 경로를 구하는 알고리즘입니다. 최단 경로는 정점 v에서부터 t까지의 경로로서 이 경로를 구사하는 아크들의 가중치 합이 최소가 되는 경로를 말합니다. [여기서 가중치란 비용, 길이, 거리, 시간과 같은 뜻으로 이들 용어는 서로 혼용]

1. 다익스트라의 수행과정

   아래는 주어진 그래프가 있을 때 다익스트라의 알고리즘 수행과정

   1. 시장 정점으로부터 각 정점까지의 거리를 계산합니다. 이 단계에서의 거리는 출발 정점과 각 정점에 직접 연결된 간선의 가중치입니다.
   2. 이 중 가장 짧은 거리를 가지는 정점을 선택하여 결정된 목적지를 확정합니다.
   3. 이전 단계에서 확장된 정점을 경유하는 경로도 감안하여 시작 정점과 나머지 정점까지 거리를 다시 계산한다.
   4. 가장 짧은 거리를 가지는 정점을 선택하여 결정된 목적지로 확장합니다.
   5. 그래프의 정점의 수가 n이라면 n-1개의 목적지가 모두 확정될 때까지 단계 3,4를 반복합니다.

   <https://kingpodo.tistory.com/52?category=805745>

   ```java
   public class DijkstraSP {
       final static int infi = 999;
       // 연결x 가중치 무한대
       public static void dijkstra(int[][] c, int n, int[][] t, int vertex) {
           // c배열은 그래프의 인접행렬, n은 정점의 개수, t배열은 연결된 간선 바열, vertex는 시작 정점
           int[] from = new int[n]; // 각 정점이 어느 정점으로부터 연결되었는지 표시하는 배열
           int[] dist = new int[n]; // 현재까지 결정된 목적지에서 각 정점마다의 가중치를 표시
           for(int i=0; i<n; i++) {
               // 최초 시작정점은 첫번째 정점임으로 vertex으로 초기화
               from[i] = vertex;
               dist[i] = c[vertex][i];
           }
           int best = 0;
           for(int i=0; i<n-1; i++) {
               // n-1은 연결될 간선의 수
               best = isBest(dist); // dist배열 중 0을 제외한 최소값을 가진 원소의 인덱스
               t[0][i] = from[best]; // 가중치가 가장 적은 간선을 선택해서 넣어준다.
               t[1][i] = best;
               for(int j=0; j<n; j++) {
                   // dist배열과 from배열의 최신화
                   if(dist[best] + c[best][j] < dist[j]) {
                       // 최근에 선택된 정점에 인접한 정점의 가중치와 기존 dist배열과 비교
                       from[j] = best;
                       dist[j] = dist[best] + c[best][j];
                   }
               }
               dist[best] = 0;
               // 부분 그래프로 선택된 정점까지의 가중치는 0으로 변경
           }
       }
       public static int isBest(int[] dist) {
           // dist배열 중 0을 제외한 최소값을 가진 원소의 인덱스를 반환하는 메소드
           int best = 0;
           for(int i=0; i<dist.length; i++) {
               if(dist[i] != 0) {
                   best = i;
                   break;
           	}
       	}
       	for(int j=0; j<dist.length; j++) {
           	// dist에서 0이 아닌 값 중 값이 가장 작은 인덱스를 찾는다.
           	if(dist[j] != 0 && dist[j] < dist[best]) 	{
               	best = j;
           	}
       	}
       	return best;
       }
       
       public static void main(String[] args) {
           int[][] graph = {
               {0,8,7,20,14,infi},
               {infi,0,infi,13,infi,infi},
               {infi,infi,0,infi,5,infi},
               {12,infi,infi,0,infi,infi},
               {11,infi,infi,6,0,4},
               {infi,infi,infi,10,infi,0},
           };
           int[][] t = new int[2][5];
           dijkstra(graph, 6, t, 0);
           for(int i=0; i < t[0].length; i++) {
               System.out.println(t[0][i]+1 + " " + (t[1][i]+1));
           }
       }
   }
   ```


### 플로이드의 알고리즘

> 모든 정점을 출발점으로 하여 모든 정점을 목적지로 했을 때의 최단 경로를 구하는 알고리즘이다. 플로이드 알고리즘의 핵심 개념은 거쳐가는 모든 정점으 기준으로 최단거리를 구하는 것입니다.

> 플로이드 알고리즘은 동적 프로그래밍 기법을 사용해서 문제를 해결합니다.  

동적 프로그래밍 기법이란 작은 문제부터 해결 후 그 결과를 큰 문제에 활용함으로써 효율을 높이는 방법. (상향식 설계)

1. 플로이드의 수행과정

   <https://kingpodo.tistory.com/53?category=805745>

   ![](https://t1.daumcdn.net/cfile/tistory/990290485B5EA8A420?download)

   현재는 하나의 정점과 정점의 거리만을 계산하였지만 플로이드 알고리즘에서는 모든 정점과 정점의 최단거리의 경로를 기준으로 차례대로 구하게 됩니다. 위 과정을 수식으로 만들면 다음과 같습니다.

   ![img](https://t1.daumcdn.net/cfile/tistory/994030405B5EB3B20E)

   아래 그림은 위 과정에 따라 플로이드 알고리즘을 진행하였을 때 D1 ~ D5까지 진행 결과입니다.

   ![](https://t1.daumcdn.net/cfile/tistory/99F6E03D5B5EE63608?download)

   ![](https://t1.daumcdn.net/cfile/tistory/9902123D5B5EE63707?download)

   ![](https://t1.daumcdn.net/cfile/tistory/9905813D5B5EE64F09?download)

   ```java
   public class FloydSP {
       static final int infi = 999;
      
       static void Floyd(int[][] c, int n, int[][] d, int[][] p) {
           // 플로이드 알고리즘을 구현한 함수
           // c는 최초 인접행렬, n은 정점의 개수, d는 결과를 담을 배열, p는 path배열로 경로를 기억합니다.
           System.arraycopy(c, 0, d, 0, n); // 배열 복사
           for(int k=1; k<n; k++) {
               // 1번 정점부터 마지막 정점까지 차례대로 계산
               for(int i=1; i<n; i++) {
                   
                   for(int j=1; j<n; j++) {
                       if(d[i][j] > d[i][k] + d[k][j]) {
                           // 직접가는 것이 빠른지 경로를 거쳐서 가는것이 빠른지 확인합니다.
                           d[i][j] = d[i][k];
                           p[i][j] = k; // 연결한 정점의 경로를 가지고 있습니다.
                       }
                   }
               }
           }
       }
       public static void main(String[] args) {
           int[][] c = {
               {0,0,0,0,0,0},
               {0,0,6,4,infi,infi},
               {0,infi,0,infi,7,5},
               {0,3,infi,0,2,infi},
               {0,infi,4,infi,0,6},
               {0,2,infi,7,infi,0}};
           int[][] d = new int[6][6];
           int[][] p = new int[6][6];
           
           Floyd(c, c.length, d, p);
       }
   }
   ```
