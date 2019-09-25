// 홍준이의 디스크모음
// 만약 0이 아닌 원소를 move로 옮긴다면, 0이 있는 곳으로 옮겨서 개수를 유지!
// 어떻게 moveCount를 최소화할 수 있을까?
// 먼저 맨 뒤에 0으로 채워주는 작업을 진행(Move)
// 0의 갯수만큼 맨앞에 있는 숫자를 뒤로 보내고
// 목표 배열을 순서대로 채운다.
// my_move(from, to, size)

import java.util.Scanner;

class Solution 
{
	public final static int DISK_SIZE = 20000;
	public final static int BUF_SIZE = 64;
	public final static int MAX_FILE = 10;
	
	private final static int MAX_COUNT = (DISK_SIZE * 2 * 50);
	
	private static int[] disk = new int[DISK_SIZE];
	private static int[] buf = new int[BUF_SIZE];
	private static int[] numCount = new int[MAX_FILE];
	private static int[] checkCount = new int[MAX_FILE];
	
	private static int moveCount;
	private static int diskSize;

	private static int totalMoveCount;
	private static int totalDiskSize;
	
	static class diskManager 
	{
		
		private diskManager()
		{
			
		}
		
		public int getdiskSize()
		{
			return diskSize;
		}
		
		public int readDisk(int[] buf, int addr, int size)
		{
			if (buf.length < size || addr < 0 || addr + size > diskSize)
				return 0;
			
			for (int i = 0; i < size; i++)
				buf[i] = disk[addr++];
	
			return size;
		}
	
		public int move(int from, int to, int size)
		{
			if (moveCount < MAX_COUNT)
				moveCount++;
	
			if (from < 0 || from >= diskSize || to < 0 || to >= diskSize)
				return 0;
	
			if (size < 1 || size > BUF_SIZE || from + size > diskSize || to + size > diskSize)
				return 0;
	
			for (int i = 0; i < size; i++)
			{
				buf[i] = disk[from];
				disk[from] = 0;
				from++;
			}

			for (int i = 0; i < size; i++)
			{
				disk[to++] = buf[i];
			}
	
			return size;
		}
	}
	
	private static boolean checkDisk()
	{
		for (int i=0; i<MAX_FILE; i++)
			checkCount[i] = 0;

		int idx = 0;
		while(idx < diskSize)
		{
			int fileID = disk[idx];
			if (checkCount[fileID] != 0)
				return false;
			
			for (int i=0; i<numCount[fileID]; i++)
			{
				if (fileID != disk[idx++])
					return false;
				checkCount[fileID]++;
			}
		}
	
		if (disk[diskSize-1] != 0)
			return false;
		
		for (int i=0; i<MAX_FILE; i++)
		{
			if (checkCount[i] != numCount[i])
				return false;
		}

		return true;
	}

	private static void init()
	{
		moveCount = 0;
	
		for (int i=0; i<MAX_FILE; i++)
			numCount[i] = 0;
		
		for (int i=0; i<diskSize; i++)
		{
			numCount[disk[i]]++;
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		System.setIn(new java.io.FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 10
		totalMoveCount = 0;
		totalDiskSize = 0;
		
		diskManager diskManager = new diskManager();
		
		for (int tc = 1; tc <= T; tc++)
		{
			diskSize = sc.nextInt();
			for (int i = 0; i < diskSize; i++){
				String value = sc.next();
				disk[i] = Integer.parseInt(value);
			}
			
			init();
			UserSolution user = new UserSolution();
			user.defragment(diskManager);
			
			int cnt = MAX_COUNT;
			if (checkDisk() == true)
				cnt = moveCount;
			
			System.out.printf("#%d %d\n", tc, cnt);
			totalMoveCount += cnt;
			totalDiskSize += diskSize;
		}
		System.out.printf("Total diskSize  : %d\n", totalDiskSize);
		System.out.printf("Total moveCount : %d\n", totalMoveCount);
		sc.close();
	}
}