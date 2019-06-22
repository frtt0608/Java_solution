# java_method

### `문자열(String)`

* equals

  ```java
  String a = "hello";
  String b = "java";
  String c = "hello";
  System.out.println(a.equals(b)); // false
  System.out.println(a.equals(c)); // true
  ```

  문자열의 값을 비교할 때는 반드시 equals를 사용해야 한다. `==`연산자를 사용할 경우 다음과 같은 경우가 발생할 수 있다.

  ```java
  String a = "hello";
  String b = new String("hello");
  System.out.println(a.equals(b)); // true
  System.out.println(a==b);	     // false
  ```

  a와 b는 서로 다른 객체이기 때문에 `a==b`는 false가 된다.

  `==`은 두개의 자료형이 동일한 객체인지를 판별할 때 사용하는 연산자

  

* indexOf

  ```java
  String a= "Hello Java";
  System.out.println(a.indexOf("Java")); // 6
  ```



* replaceAll

  ```java
  String a = "Hello Java";
  System.out.println(a.replaceAll("Java", "World"));
  // Hello World
  ```

  

* substring

  ```java
  String a = "Hello Java";
  System.out.println(a.substring(0, 4));
  // Hell
  ```

  0 (시작위치)  <= a < 4 (끝위치)

 

  * toUpperCase

    ```java
    String a = "Hello Java";
    System.out.println(a.toUpperCase());
    // HELLO JAVA
    ```



* reverseString

  ```java
  public static String reverseString(String s){
          return (new StringBuffer(s)).reverse().toString();
      }
  
  String s = "HELLO";
  System.out.println(s); // HELLO
  System.out.println(reverseString(s)); // OLLEH
  ```

  



### `StringBuffer`

`StringBuffer`는 문자열을 추가하거나 변경 할 때 주로 사용하는 자료형이다.

* append

  ```java
  public class Test {
      public static void main(String[] args) {
          StringBuffer sb = new StringBuffer();
          sb.append("hello");
          sb.append(" ");
          sb.append("jump to java");
          System.out.println(sb.toString());
      }
  }
  
  // hello jump to java
  ```

  다른 방법

  ```java
  public class Test {
      public static void main(String[] args) {
          String temp = "";
          temp += "hello";
          temp += " ";
          temp += "jump to java";
          System.out.println(temp);
      }
  }
  
  // hello jump to java
  ```

  

* insert

  ```java
  public class Test {
      public static void main(String[] args) {
          StringBuffer sb = new StringBuffer();
          sb.append("jump to java");
          sb.insert(0, "hello ");
          System.out.println(sb.toString());
      }
  }
  
  // hello jump to java
  ```

  

* substring

  ```java
  public class Test {
      public static void main(String[] args) {
          StringBuffer sb = new StringBuffer();
          sb.append("Hello jump to java");
          System.out.println(sb.substring(0, 4));
      }
  }
  
  // Hell
  ```

  

### `리스트(List)`

배열은 크기가 정해져 있지만 리스트는 동적으로 자료형의 갯수가 변한다.

* add

  ```java
  ArrayList pitches = new ArrayList();
  pitches.add("138");
  pitches.add(0, "133");    // 첫번째 위치에 133 삽입.
  pitches.add(1, "143");    // 두번째 위치에 143 삽입.
  ```

  

* get

  2번째 값을 가져온다.

  ```java
  System.out.println(pitches.get(1));
  ```

  

* size

  ```java
  System.out.println(pitches.size());
  ```

  

* contains

  ```java
  System.out.println(pitches.contains("142"));
  // true or false
  ```



* remove

  1. remove(객체)

     리턴 값이 t/f

     ```java
     System.out.println(pitches.remove("129"));
     // true
     ```

     

  2. remove(인덱스)

     리턴 값이 삭제된 항목

     ```java
     System.out.println(pitches.remove(0));
     // 138
     ```

     











