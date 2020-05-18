# JAVA method 정리

### Arrays 메소드

1. .asList(a)

   ```java
   String[] a = {"a", "b", "c"};
   Arrays.asList(a);
   ```

2. .toString()

   ```java
   System.out.println(Arrays.toString(a));
   ```

3. sort(array)

   ```java
   b = [1,5,4,7,3,8,9,6,2,3,1];
   Arrays.sort(b);
   ```

4. sort(array, Comparator)

   ```java
   Arrays.sort(b, Collections.reverseOrder());
   // Primitive Type의 배열에는 적용불가!
   // Integer같은 Wrapper Class를 이용해야한다.
   ```

5. sort(array, int fromindex, int toindex)

   ```java
   Arrays.sort(b, 2, 6);
   ```

6. sort(array, int fromindex, int toindex)

   ```java
   Arrays.sort(b, 2, 6, Collections.reverseOrder());
   ```

7. copyOf(array, int newlength)

   ```java
   int[] c = Arrays.copyOf(b, 5);
   ```

8. System.arraycopy(b, 0, d, n)

   ```java
   int n = 7;
   int[] b = new int[n];
   System.arraycopy(b, 0, d, n);
   ```





### List 메소드

1. contains

   ```java
   List.contain(Object value); // 포함하면 true, 없으면 false
   ```

2. add

   ```java
   List.add(int index, Object value);
   List.add(Object, value);
   ```

3. get

   ```java
   List.get(int index);
   ```

4. remove

   ```java
   List.remove(int index); // 삭제된 value 리턴
   List.remove(Object value); // 삭제되면 true, 안되면 false
   ```

5. size

   ```
   List.size();
   ```

6. set

   ```java
   List.set(int index, Object value);
   ```

7. isEmpty()

   ```java
   List.isEmpty(); // 비어있으면 true, 아니면 false
   ```

   



### 정렬

1. Array 정렬

   ```java
   arr = ["A", "G", "H", "E"];
   
   Arrays.sort(arr, Comparator<String>() {
       @Override
       public int compareTo(String str1, String str2) {
           return arr.compare(str1, str2); 
           // 순서대로 쓰면 오름차순, 반대로 (str2, str) 은 내림차순
       }
   });
   ```

2. List 정렬

   ```java
   ArrayList<Integer> list = new ArrayList<>();
   
   list.sort(Comparator.naturalOrder()); // 오름차순
   list.sort(Comparator.reverseOrder()); // 내림차순
   
   // 특정 Class의 경우, Class에 implements Comparable<Class>에 comareTo 메소드를 오버라이딩
   // 또는 sort안에 Comparator 생성
   
   list.sort(new Comparator<Class>() {
       @Override
       public int compare(Class object1, Class object2) {
           return Object1.num > object2.num ? 1:-1;
           // 순서대로 쓰면 오름차순, 반대로 -1:1은 내림차순
       }
   });
   // 아래는 lamda식
   list.sort((int num1, int num2) -> Integer.compare(num1, num2));
   
   
   // Collections의 sort메소드로도 list 정렬 가능
   Collections.sort(list);
   Collections.sort(list, new Comparator<Integer>() {
       ~
   });
   
   ```

   



## Iterator

컬렉션에 저장되어있는 요소들을 읽어오는 방법

```java
ArrayList<Integer> list = new ArrayList<>();
Iterator<Integer> list_iter = list.iterator();

while(list_iter.hasNext()) {
	list_iter.next()
}

for(int i=0; i<list.size(); i++) {
    list.get(i);
}

// iterator는 자동으로 index를 관리해주기 때문에 사용이 편리하나 객체를 만들어서 사용하므로 get()을 통한 출력보다 느리다.
```



### 변환 메소드

```java
String.valueOf(); // (숫자형 -> String)
Integer.toString(); // (int -> String)
Float.toString(); // (Float -> String)
Double.toString(); // (Double -> String)
Character.toString(); // (Character -> String)
Character.getNumericeValue(); // (Character -> int)

Integer.valueof(); // (String -> int)
Integer.parseInt(); // (String -> int)

Double.valueOf(); // (String -> Double)
Float.valueOf(); // (String -> Float)
Long.parseLong(); // (String -> Long)
Short.parseShort(); // (String -> Short)

(int) (double) (float) ...

string_name.toCharArray(); // (String -> char[])
String.valueOf(char[]); // (char[] -> String)
```



### 문자열 메소드

1. 문자열에 공백 제거 trim

   ```java
   String str = "  문자  열 공백   제거";
   str = str.trim()
   ```

2. 문자열 자르기 substring

   ```java
   String str = "ABCEDFET";
   str.substring(3); // EDFET
   str.substring(3,6); // EDF
   ```

3. 문자열 자르기 split

   ```java
   String str = "A,B,C";
   String str_array[] = str.split(",");
   
   String str1 = "ABC";
   String str_array1[] = str1.split("");
   ```

4. 문자열 합치기 "+", concat, append

   ```java
   String a = "1";
   String b = "2";
   String c = a+b;
   // "+"연산자는 문자열을 StringBuilder로 변환시킨 뒤, Append로 더하고 다시 toString
   // String객체끼리 (+)더하는 행위는 메모리 할당과 메모리 해제를 발생시키며 성능적으로 좋지 않다.
   // 대신 StringBuilder는 새로운 객체를 생성하지 않고 기존의 데이터에 더하는 방식으로 속도가 빠름
   
   String d = a.concat(b);
   // concat은 문자열을 String으로 생성,
   
   StringBuilder sb = new StringBuilder("0");
   sb.append(a);
   sb.append(b);
   
   // 많은 양의 String을 더할 때는 StringBuilder를 사용해서 마지막에 toString()!!
   ```

5. 문자열 대체하기 replace

   ```java
   String str = "고민보다go go go";
   str = str.replace("go", "고");
   // str = "고민보다고 고 고"
   
   // replace는 특수문자 치환가능! replaceAll은 불가능
   // replaceFirst
   ```

6. 문자열 포함 여부 확인 contains, indexOf

   ```java
   String str = "my java test";
   str.contains("java"); // true
   str.contains("c++"); // false
   
   Str.indexOf("java"); // 문자 위치 반환
   str.indexOf("c++"); // 없으면 -1
   
   ```

   



### Math 라이브러리

```java
Math.abs(); // 절댓값
Math.sqrt(); // 루트 (int)조심!
Math.pow(); // 제곱 (int)조심!
Math.max(); // 최대값
Math.min(); // 최소값
Math.ceil(); // 올림
Math.floor(); // 내림
Math.round(); // 반올림

Math.sin(toRadians(30));
Math.sin(Math.PI / 6);
Math.cos(toRadians(45));
Math.tan(toRadians(60));

Math.random(); // 0.0~1.0
Math.random()*100; // 0~99
Math.random()*100+1; // 1~100
```



### Regular Expression

https://offbyone.tistory.com/400

1. matches

   ```
   
   ```

2. replaceAll

   ```java
   
   ```

3. split

   ```java
   
   ```

4. Pattern



### Hash

1. HashMap

   ```java
   HashMap<Integer, Integer> map = new HashMap<>();
   HashMap<Integer, Integer tempMap = new HashMap<>();
   
   map.clear();
   tempMap = map.clone;
   map.containsKey(Object key); // map에 key가 있는지 t/f 반환
   map.containsValue(Object value); // map에 value가 있는지 t/f반환
   
   Set set = map.entrySet(); // key-value값을 set에 저장
   map.get(Object key);
   map.isEmpty();
   Set keySet = map.keySet(); // key를 set에 저장
   map.put(key, value);
   map.putAll(tempMap m); // tempMap에 있는 모든 요소를 저장
   
   map.remove(Object key);
   map.size();
   map.values(); // value를 collections형태로 저장
   ```

2. 응용 메소드

   ```java
   // 예를들어
   Map<String, Integer> map = new HashMap<>();
   map.put("Java", 0);
   map.put("Jakarta", 0);
   map.put("Eclipse", 0);
   
   public void read(String text) {
       for (String word : text.split(" ")) {
           if (map.containsKey(word)) {
               Integer value = map.get(word);
               map.put(word, ++value);
           }
       }
   }
   
   // 이 메소드를
   
   public void read(String text) {
     for (String word : text.split(" ")) {
       map.computeIfPresent(word, (String key, Integer value) -> ++value);
     }
   }
   
   // 이와같이 변경 가능
   // computeIfPresent는 key를 받고 key가 존재하는 경우에만 반복적으로 value를 계산하는 함수로 다시 매핑한다
   ```

3. 피보나치의 메모이제이션 기법

   ```java
   // HashMap에 피보나치 번호가 있는지 확인하고 있으면 get, 없다면 계산한다.
   // 이를 조건문으로 하나하나 확인할 수 있지만, 대신에 다른 메소드를 사용할 것이다.
   Map<Integer, BigInteger> memoizeHashMap = new HashMap<>();
   
   private BigInteger fibonacci (int n) {
     return memoizeHashMap.computeIfAbsent(n, 
               (key) -> fibonacci(n - 1).add(fibonacci(n - 2)));
   }
   
   // 이 메소드는 두개의 입력값을 받는다. 첫번째는 key, 두번째는 key를 사용하여 차례대로 value반환
   // map에 key가 있으면 값을 반환한다는 뜻이다.
   // map에 key가 없으면 value를 계산하고 map에 추가한 후, value를 돌려준다.
   ```

4. key와 value출력하기

   ```java
   // Iterator 또는 Collections 또는 Set을 통해 출력할 수 있다.
   import java.util.Map;
   import java.util.Iterator;
   
   HashMap<Key, value> map = new HashMap<>();
   
   for(Object Key : map.keySet()) {
       Object value = map.get(key);
       System.out.println(key+", "+value);
   }
   
   for(Map.entry<Key, value> elem : map.entrySet()) {
   	Object key = elem.getKey();
       Object value = elem.getValue();
       System.out.println(key+", "+value);
   }
   
   Iterator<Key> mapIter = map.keySet().iterator();
   while(mapIter.hasNext()) {
       Object key = mapIter.next();
       Object value = map.get(key);
       System.out.println(key+", "+value);
   }
   ```

   





