import java.util.Scanner;
import java.io.*;

class S5648go{
	static int N;
	static int[][] arr;
	static int[][] atoms;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] visit;
  static int[][] newArr = new int[4003][4003];
  
	static int solution(int n, int[][] ar, int[][] ats) {
		N = n;
		arr = ar;
		atoms = ats;
		int r, c, d, e, nr, nc;
		int result = 0;
		int cnt;
		int[][] tmp;
		for(int i=0; i<4001; i++) {
			cnt = 0;
			for(int[] atom: atoms) {
				r = atom[0];
				c = atom[1];
				d = atom[2];
				e = atom[3];
				if (e == 0) {
					cnt++;
					continue;
				}
				if (arr[r][c] == 0) {
					atom[3] = 0;
					continue;
				}
				if (arr[r][c] != e) {
					result += arr[r][c];
					arr[r][c] = 0;
					atom[3] = 0;
					continue;
				}
				
				nr = r + dr[d];
				nc = c + dc[d];
				arr[r][c] = 0;
				if (nr <=0 || nr > 4001 || nc <=0 || nc > 4001) {
					atom[3] = 0;
					continue;
				}
				atom[0] = nr;
				atom[1] = nc;
				newArr[nr][nc] += e;
			}
			if(cnt==N) {
				return result;
			}
			arr = newArr;
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int N;
		int[][] arr = new int[4003][4003];
		for (int test_case = 1; test_case <= T; test_case++) {
			long start = System.currentTimeMillis();
			N = sc.nextInt();
			
			atoms = new int[N][4];
			
			for (int i=0; i< N; i++) {
				atoms[i][1] = (sc.nextInt()+1000)*2 + 1;
				atoms[i][0] = (sc.nextInt()+1000)*2 + 1;
				atoms[i][2] = sc.nextInt();
				atoms[i][3] = sc.nextInt();
				
				arr[atoms[i][0]][atoms[i][1]] = atoms[i][3];
			}
			
			System.out.println("#" + test_case + " " + solution(N, arr, atoms));
			long end = System.currentTimeMillis();
			System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초");
		}
		
		sc.close();
	}
}