import java.io.FileInputStream;

import java.util.Scanner;

public class Solution {
	static int[] arr;
	static int[][] memo;
	static int k;
	static int max;
	public static void main(String[] args) throws Exception {
	
		System.setIn(new FileInputStream("./src/swea/�ִ���/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sc.nextLine();
		String temp;
		for (int tc=1; tc<=T; tc++) {
			max = 0;
			temp = sc.next();
			arr = new int[temp.length()];
			for (int i=0; i<temp.length(); i++) {
				arr[i] = temp.charAt(i) - '0';
			}
			k = sc.nextInt();
			int t = fact(temp.length());
			
			memo = new int[k][t+1];
			dfs(0);
			System.out.println("#"+tc+" "+max);
		}
	}
	static int fact(int n) {
		if (n <= 1) {
			return 1;
		}
		return n * fact(n-1);
	}
	static void dfs(int depth) {
		// TODO Auto-generated method stub
		if (depth == k) {
			int r = calc();
			if (r> max) {
				max = r;
			}
			return;
		}
		for (int i=0; i<arr.length-1; i++) {
			for (int j=i+1; j<arr.length; j++) {
				swap(i, j);
				if (!isExist(depth)) {
					dfs(depth+1);
				}
				swap(i, j);
			}
		}
	}
	static boolean isExist(int depth) {
		int temp = calc();
		for (int k=1; k<(memo[depth][0]+1); k++) {
			if (memo[depth][k] == temp) {
				return true;
			}
		}
		memo[depth][0]++;
		memo[depth][memo[depth][0]] = temp;
		return false;
	}
	static void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	static int calc() {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i=0; i < arr.length; i++) {
			result = result*10 + arr[i];
		}
		return result;
	}
}
