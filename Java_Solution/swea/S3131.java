// 100만 이하의 모든 소수

public class S3131 {
    public static void main(String[] args) throws Exception {
        System.out.print("2 " + "3 " + "5 " + "7 ");
        int[] table = {2,3,5,7};

        for(int i=11; i<=1000000; i++) {
            for(int temp:table) {
                if(i%temp==0)
                    break;
                
            }
        }
    }
}