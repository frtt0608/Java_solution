// 100만 이하의 모든 소수

public class S3131 {
    public static void main(String[] args) throws Exception {
        System.out.print("2 " + "3 " + "5 " + "7 ");
        Boolean flag = false;

        for(int i=11; i<=1000000; i++) {
            flag = false;
            for(int j=2; j<=Math.sqrt(i); j++) {
                if(i%j == 0) {
                    flag = true;
                    break;
                } 
            }
            if(flag == false) {
                System.out.print(i + " ");
            }
        }
    }
}