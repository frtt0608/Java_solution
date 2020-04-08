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



### 정렬

1. Array 정렬

   ```java
   arr = ["A", "G", "H", "E"];
   
   Arrays.sort(arr, comparator<String>() {
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





class Solution {
    public int solution(String inputString) {
        int answer = 0;
        int garo[] = new int[4];
        char chr;
        for(int i=0; i<inputString.length(); i++) {
            chr = inputString.charAt(i);
            if(chr == '(') garo[0]++;
            else if(chr == '{') garo[1]++;
            else if(chr == '[') garo[2]++;
            else if(chr == '<') garo[3]++;
            
            if(chr == ')') {
                if(garo[0] <= 0) return -1;
                garo[0]--; answer++;
            } else if(chr == '}') {
                if(garo[1] <= 0) return -1;
                garo[1]--; answer++;
            } else if(chr == ']') {
                if(garo[2] <= 0 ) return -1;
                garo[2]--; answer++;
            } else if(chr == '>') {
                if(garo[3] <= 0) return -1;
                garo[3]--; answer++;
            }
        }
        return answer;
    }
}







import java.util.*;

class Solution {
    int person_len, question_len;
    int result[][];
    
    public int Calculater_cheating(int num1, int num2) {
        return num1 + (int)Math.pow(num2, 2);
    }
    
    public int solution(String answer_sheet, String[] sheets) {
        int answer = 0;
        int all_cheating = 0;
        int long_cheating = 0;
        int temp_cheating = 0;
        
        person_len = sheets.length;
        question_len = answer_sheet.length();
        
        result = new int[person_len][question_len];
        
        for(int i=0; i<person_len; i++) {
            for(int j=0; j<question_len; j++) {
                if(answer_sheet.charAt(j) == sheets[i].charAt(j)) result[i][j]=0;
                else result[i][j] = Character.getNumericValue(sheets[i].charAt(j));
            }
        }
        
        for(int i=0; i<person_len-1; i++) {
            for(int j=i+1; j<person_len; j++) {
                temp_cheating = 0;
                all_cheating = 0;
                long_cheating = 0;
                for(int sol=0; sol<question_len; sol++) {
                    if((result[i][sol]==0 || result[j][sol]==0) || (result[i][sol] != result[j][sol])) {
                        long_cheating = Math.max(long_cheating, temp_cheating);
                        temp_cheating = 0;
                        continue;
                    } else if(result[i][sol] == result[j][sol]) {
                        temp_cheating++;
                        all_cheating++;
                    }
                }
                long_cheating = Math.max(long_cheating, temp_cheating);
                if(answer==0) answer = Calculater_cheating(all_cheating, long_cheating);
                else answer = Math.max(answer, Calculater_cheating(all_cheating, long_cheating));
                //System.out.println(all_cheating +", "+long_cheating+", "+answer);
            }
        }
        
        return answer;
    }
}







import java.util.*;

class Solution {
    int road_len, roads[];
    Queue<Integer> que;
    
    public int Work(int index, int n) {
        int one=0;
        for(int i=index; i<road_len; i++) {
            if(roads[i]==0) one++;
            else {
                if(n>0) {one++; n--;}
                else break;
            }
        }
        return one;
    }
        
    public int solution(String road, int n) {
        int answer = 0;
        road_len = road.length();
        roads = new int[road_len];
        char roadd[] = road.toCharArray();
        que = new LinkedList<Integer>();
        que.offer(0);
        
        for(int i=0; i<road_len; i++) {
            if(road.charAt(i) == '0') {
                roads[i] = -1;
                if(i < road_len-1) que.offer(i+1);
            }
        }
        
        while(!que.isEmpty()) {
            //System.out.println(que.peek());
            answer = Math.max(answer, Work(que.poll(), n));
        }    
        
        return answer;
    }
}







//트랜잭션 중복적용 x (ID확인)
// ID동일시 로그내용도 동일
// SAVE 입금 정보
// WITHDRAW 출금정보
// 잔액 음수x

import java.util.*;

class Solution {
    HashMap<String, Integer> bank;
    HashSet<String> trans;
    
    public void SAVE(String account, int money) {
        bank.put(account, bank.get(account) + money);
    }
    
    public void WITHDRWA(String account, int money) {
        bank.put(account, bank.get(account) - money);
    }
    
    public String[][] solution(String[][] snapshots, String[][] transactions) {
        bank = new HashMap<>();
        trans = new HashSet<>();
        
        String id = "0";
        String accout = "";
        int money = 0;
        
        for(int i=0; i<snapshots.length; i++) {
            money = Integer.parseInt(snapshots[i][1]);
            bank.put(snapshots[i][0], money);
        }
        
        for(int i=0; i<transactions.length; i++) {
            id = transactions[i][0];
            if(!trans.contains(id)) {
                trans.add(id);
                accout = transactions[i][2];
                money = Integer.parseInt(transactions[i][3]);
                
                if(!bank.containsKey(accout)) bank.put(accout, 0);
                if(transactions[i][1].equals("SAVE")) {
                    SAVE(accout, money);
                } else {
                    WITHDRWA(accout, money);
                }
            }
        }
        Iterator<String> bank_iter = bank.keySet().iterator();
        String[][] answer = new String[bank.size()][2];
        
        int answer_i = 0;
        while(bank_iter.hasNext()) {
            String key = bank_iter.next();
            answer[answer_i][0] = key;
            answer[answer_i][1] = Integer.toString(bank.get(key));
            answer_i++;
        }
        
        Arrays.sort(answer, new Comparator<String[]>() {
            @Override
            public int compare(String str1[], String str2[]) {
                return str1[0].compareTo(str2[0]);
            }
        });
        return answer;
    }
}







// 중복태그x
// 서로 다른 문서가 같은 태그 가능
// 문서 1~ 십만
// 태그 0 ~ 십만
// 태그는 검색에 사용
// 태그 중 하나 이상 동일한 태그를 가지는 문서 반환
// 주어진 태그가 많으면 앞에 (내림차순)
// 태그 수 동일시 사전 순서
// 상위 10건만 반환

// 검색에 사용될 태그 갯수는 1~만개

import java.util.*;

class DOC {
    String doc;
    int tag_cnt;
    
    DOC(String doc, int tag_cnt) {
        this.doc = doc;
        this.tag_cnt = tag_cnt;
    }
}

class Solution {
    HashMap<String, HashSet<String>> docMap;
    
    public String[] solution(String[][] dataSource, String[] tags) {
        docMap = new HashMap<>();
        HashSet<String> tagSet;
        ArrayList<DOC> return_array = new ArrayList<>();
        
        for(int i=0; i<dataSource.length; i++) {
            tagSet = new HashSet<>();
            for(int j=1; j<dataSource[i].length; j++) {
                tagSet.add(dataSource[i][j]);
            }
            docMap.put(dataSource[i][0], tagSet);
        }
        
        for(String key: docMap.keySet()) {
            tagSet = docMap.get(key);
            int include_tags = 0;
            for(int idx=0; idx<tags.length; idx++) {
                if(tagSet.contains(tags[idx])) include_tags++;
            }
            if(include_tags>0) return_array.add(new DOC(key, include_tags));
        }
        Collections.sort(return_array, new Comparator<DOC>() {
            @Override
            public int compare(DOC doc1, DOC doc2) {
                if(doc1.tag_cnt < doc2.tag_cnt) return 1;
                else return -1;
            }
        });
        
        
        
        String[] answer = new String[return_array.size()];
        
        for(int i=0; i<return_array.size(); i++) {
            answer[i] = return_array.get(i).doc;
        }
        return answer;
    }
}

