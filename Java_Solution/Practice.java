public class Practice {
    int globalint;
    public static void main(String args[]) {
        int scoreList[] = new int[5];

        scoreList[0] = 80;
        scoreList[1] = 95;
        scoreList[2] = 100;
        scoreList[3] = 90;
        scoreList[4] = 95;

        System.out.println("scoreList 길이 = " + scoreList.length);

        System.out.println("< scoreList 배열 요소 출력 >");
        System.out.print(scoreList[0] + "\t");
        System.out.print(scoreList[1] + "\t");
        System.out.print(scoreList[2] + "\t");
        System.out.print(scoreList[3] + "\t");
        System.out.println(scoreList[4]);
        
        for(int i=0; i<=10; i++) {
            if(i%2==0)
                System.out.println(i + "는(은) 짝수다");
        }
    };
}