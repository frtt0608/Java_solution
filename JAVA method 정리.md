# JAVA method 정리

## Arrays 메소드

1. `.asList(a)`

   ```java
   String[] a = {"a", "b", "c"};
   Arrays.asList(a);
   ```

2. `.toString()`

   ```java
   System.out.println(Arrays.toString(a));
   ```

3. `sort(array)`

   ```java
   b = [1,5,4,7,3,8,9,6,2,3,1];
   Arrays.sort(b);
   ```

4. `sort(array, Comparator)`

   ```java
   Arrays.sort(b, Collections.reverseOrder());
   ```

5. `sort(array, int fromindex, int toindex)`

   ```java
   Arrays.sort(b, 2, 6);
   ```

6. `sort(array, int fromindex, int toindex)`

   ```java
   Arrays.sort(b, 2, 6, Collections.reverseOrder());
   ```

7. `copyOf(array, int newlength)`

   ```java
   int[] c = Arrays.copyOf(b, 5);
   ```

8. `System.arraycopy(b, 0, d, n)`

   ```java
   int n = 7;
   int[] b = new int[n];
   System.arraycopy(b, 0, d, n);
   ```
