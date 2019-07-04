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
   public void setLeft(Node left) { 
       this.left = left;
   }
    public void setRight(Node right) {
        this.right = right;
    }
    public void setData(char data) { 
        this.data = data; 
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



n개의 노드를 가진 이진트리를 1차원 배열로 표현했을 때 포화 이진트리 번호를 인덱스로 사용하면서 어떤 노드 i의 부모, 왼쪽 자식, 오른쪽 자식의 인덱스를 쉽게 알 수 있다. 아래 표는 완전 이진트리를 표현한 1차원 배열에서 인덱스 관계이다.

| 목표노드            | 인덱스 값 | 조건             |
| ------------------- | --------- | ---------------- |
| 노드 i의 부모       | [ i / 2]  | i > 1            |
| 노드 i의 왼쪽자식   | 2 * i     | 2 * i <= n       |
| 노드 i의 오른쪽자식 | 2 * i + 1 | (2 * i + 1) <= n |
| 루트 노드           | 1         | 0 < n            |



3. 이원 탐색 트리(BST)

   > 이진 트리 구조를 가진 이원탐색트리는 임의의 키를 가진 원소를 삽입, 삭제, 검색하는데 효율적인 자료구조이다.

   이원 탐색 트리는 이진 트리로서 공백이 아니면 다음 성질을 만족한다.

   * 모든 원소는 상이한 키를 갖는다.
   * 왼쪽 서브트리에 있는 원소의 키들은 그 루트의 키보다 작다.
   * 오른쪽 서브트리에 있는 원소의 키들은 그 루트의 키보다 크다.
   * 왼쪽 서브트리와 오른쪽 서브트리도 모두 이원 탐색 트리이다.



   1. 이원 탐색 트리의 탐색

      > 이원 탐색 트리는 순환적 정의를 가지고 있기 때문에 탐색방법도 순환적으로 기술하는 것이 편하다. 탐색 순서는 다음과 같다.

      각 노드는 Left, Key, Right라는 필드를 가진다.

      * 키 값이 x인 원소를 탐색하는 경우 탐색은 루트에서부터 시작한다.
      * 먼저 이원 탐색 트리가 공백인지 검사하여 공백이면 종료하고 공백이 아니면 탐색을 계속한다.
      * 루트의 키 값과 x를 비교하여 같으면 찾는 원소는 루트가 되어 탐색은 성공적으로 종료한다.
      * 그러나 키 값 x가 루트의 키값보다 작으면 루트의 왼쪽 서브트리를 탐색한다.
      * 반대로 x가 루트의 키 값보다 크면, 루트의 왼쪽 서브트리는 탐색 범위에서 제외시키고 오른쪽 서브트리만 탐색한다.

      ```java
      public static void BST(Node node, char key) {
          // node는 이원 탐색 트리, key는 탐색 키 값
          if(node == null) {
              System.out.println("탐색 실패");
              return;
          }
          if(node.getData() == key) {
              // 노드의 데이터값이 key값과 일치하면 탐색 성공후 종료
              System.out.println("탐색 성공");
              return;
          }
          if(node.getData() < key) {
              // 노드의 데이터값보다 키 값이 크다면
              BST(node.getRight(), key);
              // 오른쪽 서브트리에서 탐색을 실시
          }
          else {
              // 노드의 데이터값보다 키 값이 작다면
              BST(node.getLeft(), key);
              // 왼쪽 서브트리에서 탐색을 실시
          }
      }
      // Node class는 이진트리 순회 예시코드에 있음
      ```



   2. 이원 탐색 트리의 삽입

      > 이원 탐색 트리에 데이터를 삽입하기 위해서는 먼저 이 트리에 x를 key값으로 가지는 원소가 있는지 확인해야 한다. 이를 위해서 탐색 key값을 x로 하여 탐색을 수행. 탐색 순서는 다음과 같다.

      루트가 28이고 왼쪽에 12인 노드와 12인 노드의 왼쪽에 6인 노드가 있음. key값 14를 삽입하려고 한다.

      * 이 탐색은 루트 노드인 28부터 시작하여 key값이 28보다 작아 왼쪽 서브트리라인 12로 이동.
      * 왼쪽 서브트리의 데이터인 12와 key값 14를 비교하여 오른쪽 서브트리로 이동하여야 하는데 공백이기 때문에 실패한다.
      * 따라서 key값 14를 가진 노드를 12의 오른쪽 서브트리에 삽입한다.

      ```java
      public static void insertBST(Node node, char key) {
      	Node p = node;
          Node q = null;
          
          while(p != null) {
              // 노드가 null이 나올 때까지 즉, 이진 탐색 트리의 끝까지 검사한다.
              if(key == p.getData()) {
                  // 삽입하려는 키 값이 이미 있는지 검사한다.
                  System.out.println("실패 : key값이 있음");
                  return;
              }
              q = p; // 해당 노드의 부모노드를 저장(기억)
              if(p.getData() < key) {
                  // 키 값보다 노드의 데이터가 작다면
                  p = p.getRight();
                  // 오른쪽 서트르리로 이동
              }
              else {
                  // 키 값보다 노드의 데이터가 크다면
                  p = q.getLeft();
                  // 왼쪽 서브트리로 이동
              }
          }
          Node newNode = new Node(key);
          // 새로운 key값을 가진 노드를 생성한다.
          if(node == null) {
              // 최초부터 공백 이원탐색트리라면 가장 루트로 만든다.
              node = newNode;
          }
          else if(key < q.getData()) {
              // 아까 기억시킨 부모 노드의 데이터보다 key값이 작다면
              q.setLeft(newNode);
          }
          else {
              // 아까 기억시킨 부모 노드의 데이터보다 key값이 크다면
              q.setRight(newNode);
              // 새로 오른쪽 서브트리로 만든다.
          }
      }
      ```


   3. 이원 탐색 트리의 원소 삭제

      > 주어진 키 값을 가진 원소를 삭제하려면 제일 먼저 이원 탐색 트리에서 이 키값을 가진 원소를 찾는 것이다. 이 때 삭제 연산은 이 원소가 가진 자식의 수(0,1,2)에 따라 3가지 경우가 있다.

      1. 자식이 없는 경우

         해당 노드를 null로 만든다.

      2. 자식이 하나인 경우

         해당 노드를 null로 만들고, 그 자리에 자식 노드를 위치시킨다.

      3. 자식이 둘인 경우

         해당 노드를 null로 만들고, 왼쪽 서브트리에서 최대 노드로 대체할지, 오른쪽 서브트리에서 최소 노드로 대체할지 선책한다.

      ```java
      public static void deleteBST(Node node, char key) {
          Node p = node;
          Node deleteNode = null; // 삭제할 key값을 가진 노드
          Node parent = null; // 삭제할 노드의 부모 노드
          
          while(p != null) {
              if(p.getData() == key) {
                  // 키 값을 찾았으면
                  deleteNode = p;
                  // 해당 노드를 삭제 노드로 지정
                  break;
              }
              parent = p;
              // 노드의 부모 노드를 기억
              if(p.getData() < key) {
                  // 키 값보다 노드의 데이터가 작다면
                  p = p.getRight();
                  // 오른쪽 서브트리로 이동
              }
              else {
                  // 키 값보다 노드의 데이터가 크다면
                  p = p.getLeft();
                  // 왼쪽 서브트리로 이동.
              }
          }
          if(deleteNode == null) {return;} // 삭제할 원소가 없으면 종료
          if(deleteNode.getLeft() == null && deletNode.getRight() == null) {
              if(parent.getLeft() == deleteNode) {
                  // 삭제할 노드가 부모의 왼쪽 노드라면
                  parent.setLeft(null);
              } else {
                  // 삭제할 노드가 부모의 오른쪽 노드라면
                  parent.setRight(null);
              }
          }
          else if(deleteNode.getLeft() == null || deleteNode.getRight() == null) {
              // 삭제할 노드의 차수가 1인 경우
              if(deleteNode.getLeft() != null) {
                  // 삭제할 노드가 왼쪽에 있다면
                  if(parent.getLeft() ==deleteNode {
                      // 삭제할 노드가 부모노드의 왼쪽이라면
                      parent.setLeft(deleteNode.getLeft());
                      // 부모노드 왼쪽에 삭제할 노드의 왼쪽 서브트리를 붙인다.
                  } else {
                      // 삭제할 노드가 부모노드의 오른쪽 이라면
                      parent.setRight(deleteNode.getLeft());
                      // 부모노드 오른쪽에 삭제할 노드의 왼쪽 서브트리를 붙인다.
                  }
               }   
               else {
                   // 삭제할 노드가 오른쪽 에 노드가 있다면
                   if(parent.getLeft() ==deleteNode ) {
                       // 삭제할 노드가 부모노드의 왼쪽이라면
                       parent.setLeft(deleteNode.getRight());
                       // 부모노드 왼쪽에 삭제할 노드의 왼쪽 서브트리를 붙인다.
                   }else {
                       // 삭제할 노드가 부모노드의 오른쪽 이라면
                       parent.setRight(deleteNode.getRight());
                       // 부모노드 오른쪽에 삭제할 노드의 왼쪽 서브트리를 붙인다.
                   }
               }
          }
          else {
              // 삭제할 노드의 차수가 2인경우
              Node q = deleteNode.getLeft();
              // 삭제할 노드의 왼쪽 서브트리에서 최대 키값을 가져온다.
              deleteNode.setData(q.getData());
              // 왼쪽 서브트리에서 가장 큰값을 삭제할 노드에 데이터를 넣는다.
              deleteBST(deleteNode.getLeft(), deleteNode.getData());
              // 삭제할 노드 왼쪽의 노드를 삭제한다.
          }
      }  
      ```


