import java.util.*;
import java.io.*;

class Customer {
    int No;
    int receiptNo;
    
    int arriveTime;
    int startReceipt;
    int endReceipt;
    int startRepair;

    Customer(int No, int arriveTime) {
        this.No = No;
        this.arriveTime = arriveTime;
    }
}
/**
 * Solution
 */
public class Solution {
    static int N, M, K, A, B, totalCustomer;
    static int[] receipt, repair;
    static PriorityQueue<Customer> receiptQue, repairQue;
    static Customer[] receiptArr, repairArr;

    static void findCustomerNo() {
        int time = 0;
        int customerCnt = 0;

        while(true) {

            // 접수창구 끝났는지 확인
            for(int i=1; i<=N; i++) {
                if(receiptArr[i] == null) continue;

                
                if(time - receiptArr[i].startReceipt >= receipt[i]) {
                    Customer c = receiptArr[i];
                    c.endReceipt = time;
                    repairQue.offer(c);
                    receiptArr[i] = null;
                }
            }

            // 접수창구 채우기
            for(int i=1; i<=N; i++) {
                if(receiptArr[i] == null) {
                    if(!receiptQue.isEmpty()) {
                        if(receiptQue.peek().arriveTime <= time) {
                            receiptArr[i] = receiptQue.poll();
                            receiptArr[i].startReceipt = time;
                            receiptArr[i].receiptNo = i;
                        }
                    }
                }
            }

            // 정비창구 끝났는지 확인
            for(int i=1; i<=M; i++) {
                if(repairArr[i] == null) continue;
                
                
                if(time - repairArr[i].startRepair >= repair[i]) {
                    Customer c = repairArr[i];
                    if(c.receiptNo == A && i == B) {
                        totalCustomer += c.No;
                    }
                    customerCnt += 1;
                    repairArr[i] = null;
                }
            }

            if(customerCnt == K) break;

            // 정비창구 채우기
            for(int i=1; i<=M; i++) {
                if(repairArr[i] == null) {
                    if(!repairQue.isEmpty()) {
                        repairArr[i] = repairQue.poll();
                        repairArr[i].startRepair = time;
                    }
                }
            }

            time += 1;
        }
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            receipt = new int[N+1];
            repair = new int[M+1];
            receiptArr = new Customer[N+1];
            repairArr = new Customer[M+1];
            
            receiptQue = new PriorityQueue<>(new Comparator<Customer>() {
                @Override
                public int compare(Customer c1, Customer c2) {
                    return c1.No - c2.No;
                }
            }); // 고객No 순서대로 처리하는 조건에 맞게 sort

            repairQue = new PriorityQueue<>(new Comparator<Customer>() {
                @Override
                public int compare(Customer c1, Customer c2) {
                    if(c1.endReceipt == c2.endReceipt) {
                        return c1.receiptNo - c2.receiptNo;
                    } else {
                        return c1.endReceipt - c2.endReceipt;
                    }
                }
            }); // 접수창구번호와 끝난 순서대로 처리하는 조건에 맞게 sort

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                receipt[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=M; i++) {
                repair[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++) {
                int arriveTime = Integer.parseInt(st.nextToken());
                receiptQue.offer(new Customer(i, arriveTime));
            }

            totalCustomer = 0;

            findCustomerNo();

            System.out.print("#"+tc+" ");
            System.out.println(totalCustomer == 0 ? -1:totalCustomer);
        }
    }
}