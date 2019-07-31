# JAVA 알고리즘 9

## Dynamic Programming

### 동적 계획법

1. #### Matrix chain mutiple

   <https://new93helloworld.tistory.com/224?category=691027>

   ![](https://t1.daumcdn.net/cfile/tistory/213ABF48593A73060D)

   이번에는 동적 계획법 알고리즘에서 가장 유명한 알고리즘인 Matrix chain multiple을 알아보자.

   말 그대로 두 행렬을 곱하는 알고리즘이다. 위의 코드는 `p x q`인 A행렬과 `q x r`인 B행렬의 곱하기이다. 3개의 반복문으로 돌아가며 곱셈 연산의 횟수는 pqr번이고 결과값은 C에 저장된다.

   그러나 우리가 지금 하려는 것은 Matrix chain이다. 행렬은 결합 법칙이 성립하므로 계산 순서에 따라서 곱셈 연산의 횟수가 달라진다.

   그럼 n개의 행렬을 곱할 때 최적의 순서는 무엇일까?

   ![](https://t1.daumcdn.net/cfile/tistory/23063938593A75D430)

   이 문제를 동적 계획법으로 접근할 때 subproblem으로 나눠서 optimal substruture를 확인해야 한다. 따라서 이것은 계산 순서를 찾는 것이므로, 최적의 계산 순서를 가정한 후에 맞는지 확인해야 한다.

   n개의 행렬을 곱하는 것은 최종적으로 1개의 행렬이 된다. 그리고 1개의 행렬이 나오기 전에는 2개의 행렬이 있다.

   그러므로 X와 Y를 곱해서 Z가 되었다면 A1에서 k까지가 X, k+1에서 An까지의 곱이 Y가 되는 것이다.

   만약 이것이 최적해라면, X나 Y를 구하는 과정도 최적해가 되어야 한다. 이는 비교적 단순한 optimal substructure이다.

   ![](https://t1.daumcdn.net/cfile/tistory/23626336593A76E807)

   m[i,j]는 행렬 Ai에서 Aj까지 곱하는 최소 곱셈의 횟수가 된다.
   
   그러므로 i가 j보다 작다면, i에서 k까지의 최선으로 곱할 때의 계산량과 k+1에서 j까지 곱할 때의 계산량을 더한다.
   
   마지막으로 그 두개를 곱하는 비용(Pi-1 * Pk * Pj)까지 계산해서 더해지면 최소곱셈의 횟수가 나오게 된다.
   
   이것이 순환식으로 올바르게 동작하기 위해서는 base case가 필요한데, i,j의 간격이 점점 좁아지기 때문에, i와 j가 0이 될 경우를 base case로 정의한다. i,j가 같다는 것은 행렬이 1개만 있다는 것이므로 계산량이 0이다. 따라서 0을 리턴한다.
   
   ![](https://t1.daumcdn.net/cfile/tistory/2662374F593A78A907)
   
   위의 코드가 Matrix chain multiple에 대한 코드이다.
   
   3개의 반복문으로 인해 시간복잡도는 O(n^3)이 된다.



2. #### Longest Common Subsequence(LCS)

   <https://new93helloworld.tistory.com/225?category=691027>

   ![](https://t1.daumcdn.net/cfile/tistory/27350433593A842516)

   이번 시간에는 Logest Common Subsequence를 알아보자

   줄여서 LCS라고 부르는데 입력으로 2개의 문자열이 주어지며, bcdb는 문자열 <abcbdab>의 subsequence이다. 또한 <bca>는 문자열 <abcbdab>와 <bdcaba>의 common subsequence(공통 부분 문자열)이다.

   우리가 고려해야 할 부분은 LCS인데, 입력으로 2개의 문자열이 주어졌을 때 common subsequence중에서 가장 긴 것을 찾으면 된다. 예를 들어, <bcba>는 <abcbdab>와 <bdcaba>의 LCS가 되는 것이다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/22395339593A86D906)

   그렇다면 위의 그림에서 x와 y라는 문자열이 입력으로 주어지고, z가 우리가 원하는 최적해라고 할 때, z의 일부분이 어떤 부분에 대한 최적해가 되는 것일까. 만약 이 z라는 문자열의 마지막이 A라면 x에도 포함될 것이며 y에도 포함될 것이다. 그리고 x의 하늘색을 x', y의 노란색을 y'라고 한다면 z의 보라색은 x'와 y'의 LCS가 된다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/275C993F593A87FE0A)

   L은 입력으로 주어진 X와 Y의 LCS의 길이를 뜻한다. 이때, Xi와 Yj가 같은가 다른가에 따라서 2가지 케이스로 분류된다. 만약 Xi와 Yj가 같다면 Xi와 Yj를 제외한 문자열의 LCS를 구한 후 마지막 값인 1을 더해준다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/257C1E42593A89A622)

   만약 Xi와 Yj가 다르다면 적어도 둘 중 하나는 버려야 한다. 다만 문제는 둘 중 누구도 버려야하는지 알 수 없다. 먼저 Xi를 버린다는 것은 Xi를 제외한 X전체와 Y의 LCS를 구한다는 것이고, Yj를 버린다는 것은 Yj를 제외한 Y전체와 X의 LCS를 구한다는 것이다. 따라서 위의 경우 2의 순환식이 성립한다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/27040841593A8A8627)

   종합적으로 보자면 위처럼 순환식을 정리할 수 있다. i나 j가 0이라는 것은 문자열의 길이가 0이라는 뜻이므로 LCS의 길이는 0이 된다. 그리고 위에서 봤듯이 Xi와 Yj가 같은때와 다를 때, 2개의 경우로 나눠서 생각해야 한다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/23228146593A8B0912)

   결과적으로 LCS의 코드는 위와 같다.

   

3. #### Knapsack Algorithm

   <https://new93helloworld.tistory.com/227?category=691027>

   ![](https://t1.daumcdn.net/cfile/tistory/255EA649593B6C7935)

   이번에는 동적 계획법의 또다른 알고리즘 Knapsack Algorithm에 대해 알아보자. Knapsack은 배낭을 가리키는 말로 배낭 알고리즘이라고도 한다. 

   이 알고리즘은 상점에서 도둑이 물건을 훔쳐 달아날 때 가지고 나갈 수 있는 무게가 한정적이지만 최대의 비용을 내야한다. 따라서 **배낭의 용량을 초과하지 않으면서 가격이 최대가 되는 부분집합**을 구해야한다.

   예를 들면 위의 그림에서 {1,2,5}는 가격의 합이 35, {3,4}는 가격의 합이 40, {3,5}는 가격의 합이 46이지만 배낭의 무게를 초과한다. (W는 배낭의 용량)

    이런 문제는 어떻게 풀어야할까?

   Greedy한 알고리즘은 단위 무게당 가격(가격/무게)을 도출해 낸다면 최적해 나오지 않는다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/221CEA35593B6E4A35)

   그러므로 동적계획법을 사용해서 이 문제를 접근한다. 그렇다면 먼저 순환식을 세워야한다. 여기서 OPT(i, w)는 배낭 용량이 w일  때 아이템 1,2,3,...,i로 얻을 수 있는 최대 이득을 뜻한다. 이 때 아이템 i를 선택하는 것에 의해 2가지 케이스로 분기된다.

   첫번째 i를 선택하지 않는 경우 i는 제외되지만 용량은 줄어들이 않으므로 w에 변화는 없다.

   두번째는 i를 선택하는 경우 i도 제외되며 용량도 마찬가지로 줄어들게 된다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/215C1739593B6ED531)

   결론적으로 위의 순환식을 가지게 된다. 경우 2를 선택하기 위해서는 조건이 필요한데, 현재 잔여용량 W가 아이템 i의 무게 Wi보다 커야한다. 이러한 조건이 만족될 경우만 경우 2를 선택할 수 있다. 그리고 i가 0이라는 말은 아이템이 더 이상 남아있지 않음을 뜻한다.

   

   ![](https://t1.daumcdn.net/cfile/tistory/2372844E593B6F6F07)

   슈도코드는 위와 같다.

   2차원 배열을 사용해서 표현되며 2개의 반복문이 사용된다. 반복문 내부의 조건 분기는 순환식에서 이미 구한 식이다.