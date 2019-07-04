# JAVA 알고리즘 정리

## Data Structure

### Stack

> LIFO구조 / PUSH POP TOP

스택 메모리 구조는 선형 메모리 공간에 데이터를 저장하면서 후입선출의 시멘틱을 따르는 자료구조

```java
Stack<Integer> st = new Stack<Integer>();
```

| 메소드               | 설명                                                         |
| -------------------- | ------------------------------------------------------------ |
| boolean empty()      | 해당 스택이 비어있으면 true, 아니면 false를 반환             |
| E peek()             | 해당 스택의 제일 상단(제일 마지막으로 저장된)요소 반환       |
| E pop()              | 해당 스택의 제일 상단(제일 마지막으로 저장된)요소 반환, 해당 요소를 스택에서 제거 |
| E push(E item)       | 해당 스택의 제일 상단에 전달된 요소를 삽입                   |
| int Search(Object o) | 해당 스택에서 전달된 객체가 존재하는 위치의 인덱스를 반환, 이때 반환되는 인덱스는 제일 상단(제일 마지막으로 저장된) 요소의 위치부터 0이 아닌 1부터 시작함. |

```java
// 예시
static void DFS(int x) {
    Stack<Integer> st = new Stack<Integer>();
    st.push(x);
    while(st) {
        int a = st.pop();
     	...
    }
}
```





### Queue

> FIFO구조 / PUT GET FRONT REAR

클래스로 구현된 스택과는 달리 별도의 인터페이스 형태로 제공

그 중에서도 Deque 인터페이스를 구현한 LinkedList 클래스가 큐 메모리 구조를 구현하는데 가장 많이 사용

큐 메모리 구조는 선형 메모리 공간에 데이터를 저장하면서 선입선출의 시멘틱을 따르는 자료구조

```java
LinkedList<String> qu = new LinkedList<String>();
```

| 메소드             | 설명                                                         |
| ------------------ | ------------------------------------------------------------ |
| boolean add(E e)   | 해당 큐의 맨 뒤에 전달된 요소를 삽입. 만약 삽입에 성공 시 true를 반환, 실패시 illegalState Exception을 발생. |
| E element()        | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환         |
| boolean offer(E e) | 해당 큐의 맨 뒤에 전달된 요소를 삽입                         |
| E peek()           | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환.<br />만약 큐가 비어있으면 null을 반환. |
| E poll()           | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환하고, 해당 욧를 큐에서 제거함. 만약 큐가 비어있으면 null을 반환 |
| E remove()         | 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 제거함       |

만약 더욱 복잡하고 빠른 큐를 구현하고 싶다면 Deque인터페이스를 구현한 ArrayDeque 클래스를 사용한다.

```java
Deque<Integer> qu = new ArrayDeque<Integer>();
```

```java
// 예시
static void BFS(String x) {
    LinkedList<String> qu = new LinkedList<String>();
    qu.add(x);
    while(!qu.empty()) {
        String temp = qu.poll();
        ...
    }
}
```



### List, Set, Map

1. List

   * 중복과 순서가 있음

   * Vector : 동기화, 속도가 느려질 수 있음

   * ArrayList : 비동기화

     ```java
     ArrayList<String> list = new ArrayList<String>(5);
     list.add("A");
     list.remove(1);
     list.sort(this); // 오름차순
     list.sort(this, list.reverseOrder()); // 내림차순
     ```

2. Set

   * 중복이 없고 순서도 없음

   * HashSet

   * TreeSet(정렬)

     ```java
     TreeSet<Integer> tree = new TreeSet<Integer>();
     ```

3. Map

   * 검색할 때는 가장 빠름, 키값과 밸류값

   * HashTable : 동기화

   * HashMap : 비동기화

   * TreeMap은 정렬제공

     ```java
     Map<String, Integer> map = new HashMap<String, Integer>();
     map.put("홍길동", 100);
     map.put("jake", 26);
     
     Set<String> set = map.keySet(); // key값
     for(String str:set)
         System.out.println(str + map.get()) // map.get(): value값
         
     Map<String, Integer> map = new TreeMap<String, Integer>();
     // key값에 따라 정렬
     ```



### Tree

> 비선형 구조로 원소들 간에 1:n 관계를 가지는 자료구조

한개 이상의 노드로 이루어진 유한 집합

* 루트: 노드 중 최상위 노드(시작노드)
* 노드 : 트리의 원소
* 간선 : 노드를 연결하는 선
* 노드의 높이 : 노드의 레벨, 루트에서 노드에 이르는 간선의 수
* 트리의 높이 : 트리에 있는 노드의 높이 중에서 가장 큰 값(최대 레벨)



1. Binary Tree(이진트리)

   1. 모든 노드들이 2개의 부트리를 갖는 형태 (최대 2개)

   2. 포화 이진트리

      * 모든 레벨에 노드가 포화상태로 차 있는 트리
      * 루트를 1번으로 하여 각 노드가 노드번호를 가짐

   3. 완전 이진트리

      * 노드 수가 n개 일때 노드 번호 1번부터 n번까지 빈 자리가 없는 트리

   4. 편향 이진트리

      * 높이 h에 대한 최소 개수의 노드를 가지면서 한쪽 방향의 자식 노드만을 가진 트리
      * 왼쪽 Skewed / 오른쪽 Skewed



2. 순회

   * Tree의 각 노드를 중복되지 않게 전부 방문하기.

   1. 전위 순회

      * 자손노드보다 루트노드를 먼저 방문

        1. 현재 노드 n을 방문하여 처리
        2. n의 왼쪽 부트리로 이동
        3. n의 오른쪽 부트리로 이동

   2. 중위 순회

      * 왼쪽 자손, 루트, 오른쪽 자손 순으로 방문

        1. 현재 노드 n의 왼쪽 부트리로 이동
        2. n을 방문하여 처리
        3. n의 오른쪽 부트리로 이동

   3. 후위 순회

      * 루트노드보다 자손을 먼저 방문

        1. 현재 노드 n의 왼쪽 부트리로 이동
        2. n의 오른쪽 부트리로 이동
        3. n을 방문하여 처리

   4. 레벨 순회

      * 노드 번호에 따라 순서대로 방문
        1. 루트 노드 1번부터 차례대로 이동



   ```java
public Node {
    private char data;
    private Node left;
    private Node right;
       
   public Node(char data) {
       this.data = data;
   }
   public char getData() {
       return data;
   }
   public Node getLeft() {
       return left;
   }
   public Node getRight() {
       return right;
   }
}
   
// 전위 순회
static public void preorder(Node n) {
   if(n != null) {
       System.out.print(n.getData() + " ");
       preorder(n.getLeft());
       preorder(n.getRight());
   }
}

// 중위 순회
static public void inorder(Node n) {
   if(n != null) {
       inorder(n.getLeft());
       System.out.print(" " + n.getData());
       inorder(n.getRight());
   }
}

// 후위 순회
static public void postorder(Node n) {
   if(n != null) {
       postorder(n.getLeft());
       postorder(n.getRight());
       System.out.print(" " + n.getData());
   }
}

// 레벨 순회 (Queue)
class Queue {
    private Node[] q;
    private int rear;
    private int front;
    
    public Queu(int n) {
        q = new Node[n];
    } 
}
static public void levelorder(Node node) {
    Queue que = new new Queue(10);
    que.add(node);
    Node n;
    while(!que.isEmpty()) {
        n = que.poll();
        if(n != null) {
            System.out.println(n.getData());
            que.add(n.getLeft());
            que.add(n.getRight());
        }
    }
}


   ```















