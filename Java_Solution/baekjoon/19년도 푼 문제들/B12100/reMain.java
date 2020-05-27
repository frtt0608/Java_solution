import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class reMain {
    static int N,  res=0, temp=0, index=0;
    static int board[][], visit[][], table[][];
    
    static int[][] copy_array(int copy[][]) {
        int arr[][] = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                arr[i][j] = copy[i][j];
            }
        }
        return arr;
    }
    static int max_array(int arr[][]) {
        int max=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(max < arr[i][j]) max = arr[i][j];
            }
        }
        return max;
    }

    static void init_visit() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                visit[i][j] = 0;
            }
        }
    }

    static void puzzle(int map[][], int cnt) {
        if(cnt>=5) {
            temp = max_array(map);
            if(res < temp) res = temp;
            return;
        }

        for(int d=0; d<4; d++) {
            table = copy_array(map);  // 배열 복사
            init_visit(); // visit 초기화

            if(d==0) {
                for(int i=0; i<N; i++) {
                    for(int j=N-2; j>=0; j--) {
                        if(table[i][j]==0) continue;
                        index = j+1;  // table[i][j]!=0이기 때문에 다음 인덱스부터 탐색
                        while(table[i][index]==0) {
                            if(index==N-1) break;
                            index += 1;
                        }
                        if(table[i][j]==table[i][index]) {
                            if(visit[i][index]==0) {
                                visit[i][index] = 1;
                                table[i][index] *= 2;
                            } else { table[i][index-1] = table[i][j]; }
                            table[i][j] = 0;
                        } else if(table[i][index]==0) {
                            table[i][index] = table[i][j];
                            table[i][j] = 0;
                        } else if(table[i][j]!=table[i][index]) {
                            table[i][index-1] = table[i][j];
                            if(index-1 != j) table[i][j] = 0;
                        }
                    }
                }
            }
            else if(d==1) {
                for(int i=0; i<N; i++) {
                    for(int j=1; j<N; j++) {
                        if(table[i][j]==0) continue;
                        index = j-1;
                        while(table[i][index]==0) {
                            if(index==0) break;
                            index -= 1;
                        }
                        if(table[i][j]==table[i][index]) {
                            if(visit[i][index]==0) {
                                visit[i][index] = 1;
                                table[i][index] *= 2;
                            } else table[i][index+1] = table[i][j];
                            table[i][j] = 0;
                        } else if(table[i][index] == 0) {
                            table[i][index] = table[i][j];
                            table[i][j] = 0;
                        } else if(table[i][j]!=table[i][index]) {
                            table[i][index+1] = table[i][j];
                            if(index+1 != j) table[i][j]=0;
                        }
                    }
                }
            }
            else if(d==2) {
                for(int j=0; j<N; j++) {
                    for(int i=N-2; i>=0; i--) {
                        if(table[i][j]==0) continue;
                        index = i+1;
                        while(table[index][j]==0) {
                            if(index==N-1) break;
                            index += 1;
                        }
                        if(table[i][j]==table[index][j]) {
                            if(visit[index][j]==0) {
                                visit[index][j] = 1;
                                table[index][j] *= 2;
                            } else table[index-1][j] = table[i][j];
                            table[i][j] = 0;
                        } else if(table[index][j] == 0) {
                            table[index][j] = table[i][j];
                            table[i][j] = 0;
                        } else if(table[i][j] != table[index][j]) {
                            table[index-1][j] = table[i][j];
                            if(index-1 != i) table[i][j]=0;
                        }
                    }
                }
            }
            else if(d==3) {
                for(int j=0; j<N; j++) {
                    for(int i=1; i<N; i++) {
                        if(table[i][j]==0) continue;
                        index = i-1;
                        while(table[index][j]==0) {
                            if(index==0) break;
                            index -= 1;
                        }
                        if(table[i][j]==table[index][j]) {
                            if(visit[index][j]==0) {
                                visit[index][j] = 1;
                                table[index][j] *= 2;
                            } else {table[index+1][j] = table[i][j];}
                            table[i][j] = 0;
                        } else if(table[index][j]==0) {
                            table[index][j] = table[i][j];
                            table[i][j] = 0;
                        } else if(table[i][j]!=table[index][j]) {
                            table[index+1][j] = table[i][j];
                            if(index+1 != i) table[i][j]=0;
                        }
                    }
                }
            }
            puzzle(table, cnt+1);
        }
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        visit = new int[N][N];
        table = new int[N][N];
        board = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        puzzle(board, 0);
        System.out.println(res);
    }   
}