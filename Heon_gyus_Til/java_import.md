1. `java.io.IOException`

   ```
   입력과 출력에서 발생하는 에러에 대한 정보를 갖고 있음
   ```



2. `java.io.InputStreamReader`

   ```
   바이트스트림을 문자스트림으로 바꿔주는 역할
   ```



3. `java.io.BufferedReader`

   ```
   Scanner를 통해 입력을 받을 경우, Space Enter를 모두 경계로 인식하기에 입력받은 데이터를 가공하기 매우 편리.
   하지만 BufferedReader는 Enter만 경계로 인식하고 받은 데이터가 String으로 고정되기때문에 입력받은 데이터를 가공하는 작업이 필요할 경우가 많다.
   ```

   `BufferedReader` 사용법

   ```
   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); //선언
   String s = bf.readLine(); //String
   int i = Integer.parseInt(bf.readLine()); //Int
   ```



