# Java 알고리즘 정리 10

## 순열과 조합

# 순열

1. #### 순열

   1. 순열 : 순서있게 배열

      ```java
      private static void perm(int n, int r, LinkedList<Integer> perArr, int perCheck) {
          if(perArr.size() == r) {
              for(int i : perArr) {
                  System.out.print(i+" ");
              }
              System.out.println();
              return;
          }
          
          for(int i=0; i<n; i++) {
              if(perCheck[i] == 0) {
                  perArr.add(i);
                  perCheck[i] = 1;
                  perm(n, r, perArr, perCheck);
                  perCheck[i] = 0;
                  perArr.removeLast();
              }
          }
      }
      
      // 순열(0, 1, 2)
      System.out.println("***순열(0,1,2)***");
      int n=3;
      int r=2;
      LinkedList<Integer> perArr = new LinkedList<Integer>();
      int[] perCheck = new int[n];
      perm(n, r, perArr, perCheck);
      ```
   
      
   
   2. 중복순열 : 순서있게 배열 + 자기 자신도 포함
   
      ```java
      private static void rePerm(int n, int r, LinkedList<Integer> rePerArr) {
          if(rePerArr.size() == r) {
              for(int i : rePerArr) {
                  System.out.print(i+" ");
              }
              System.out.println();
              return;
          }
          
          for(int i=0; i<n; i++) {
              rePerArr.add(i);
              rePerm(n, r, rePerArr);
              rePerArr.removeLast();
          }
      }
      
      // 중복순열(0, 1, 2)
      System.out.println("***중복순열(0,1,2)***");
      int n=3;
      int r=2;
      LinkedList<Integer> rePerArr = new LinkedList<Integer>();
      rePerm(n, r, perArr);
      ```
   
      
   
   3. 조합 : 순서 상관없이 뽑은 유무만 생각
   
      ```java
      private static void comb(int[] comArr, int n, int r, int index, int target) {
          if(r==0) {
              for(int i : comArr) {
                  System.out.print(i+" ");
              }
              System.out.println();
              return;
          }
          if(target == n) return;
          comArr[index] = target;
          comb(comArr, n, r-1, index+1, target+1); // 뽑는 경우
          comb(comArr, n, r, index, target+1); // 안뽑는 경우
      }
      
      // 조합(0, 1, 2)
      System.out.println("***조합(0,1,2)***");
      int n=3;
      int r=2;
      int[] comArr = new int[r]
      comb(comArr, n, r, 0, 0)
      ```
   
      
   
   4. 중복조합 : 순서 상관없이 뽑은 유무만 생각 + 자기자신도 포함
   
      ```java
      private static void reComb(int[] reComArr, int n, int r, int index, int target) {
          if(r == 0) {
              for(int i : reComArr) {
                  System.out.print(i + " ");
              }
              System.out.println();
              return;
          }
          if(target == n) return;
          
          reComArr[index] = target;
          reComArr(reComArr, n, r-1, index+1, target);
          reComArr(reComArr, n, r, index, target+1);
      }
      
      // 중복조합
      System.out.println("***중복조합(0,1,2)***");
      int n=3;
      int r=2;
      int[] reComArr = new int[r];
      reComb(reComArr, n, r, 0, 0);
      ```
   
      
   
   