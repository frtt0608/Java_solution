// // 통역사 성경이
import java.io.*;
import java.util.*;

public class S7675 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = Integer.parseInt(sc.nextLine());
        for(int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(sc.nextLine());
            String data[] = sc.nextLine().split(" ");
            System.out.println(Arrays.toString(data));
            int res[] = new int[N];
            int idx=0;
            Boolean flag;

            for(String str:data) {
                if(str.equals("")) continue;
                int temp = (int)(str.charAt(0));
                int L = (int)str.charAt(str.length()-1);
                flag = true;
                if((temp >= 97 && temp <= 122) || (temp>=48 && temp<=57)) {
                    if(L==33 || L==63 || L==46) idx+=1;
                    continue;
                }
                if(str.length()==1) {
                    if(temp>=65 && temp<=90) res[idx] += 1;
                    if(L==33 || L==63 || L==46) idx+=1;
                    continue;
                }
                for(int i=1; i<str.length(); i++) {
                    int num = (int)str.charAt(i);
                    if((num >=65 && num <= 90) || (num>=48 && num<=57)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) res[idx] += 1;
                if(L==33 || L==63 || L==46) idx+=1;
            }
            System.out.print("#"+tc+" ");
            for(int i:res) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}