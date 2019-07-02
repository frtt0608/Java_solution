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





