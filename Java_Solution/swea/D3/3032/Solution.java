
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
        {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
       
            int r0 = x;
            int r1 = y;
            int s0 = 1;
            int s1 = 0;
            int t0 = 0;
            int t1 = 1;
            int temp = 0;
            int mock = 0;
            
            while(r1 > 0) {
                mock = r0/r1;
                temp = r0;
                r0 = r1;
                r1 = temp - mock*r1;
                temp = s0;
                s0 = s1;
                s1 = temp - mock*s1;
                temp = t0;
                t0 = t1;
                t1 = temp - mock*t1;
            }
            
            if(r0 == 1)
				System.out.println("#" + test_case + " " + s0 + " " + t0);
            else
                System.out.println("#" + test_case + " " + -1);
		}
	}
}