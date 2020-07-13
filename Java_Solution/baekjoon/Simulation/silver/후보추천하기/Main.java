import java.io.*;
import java.util.*;


class Student implements Comparable<Student> {
    int num;
    int reco;
    int time;

    Student(int num, int reco, int time) {
        this.num = num;
        this.reco = reco;
        this.time = time;
    }

    @Override
    public int compareTo(Student student) {
        if(this.reco == student.reco) {
            return this.time - student.time; // 시간 순
        } else {
            return this.reco - student.reco; // 추천 순
        }
    }
}
/**
 * Main
 */
public class Main {
    static int N, M, reco[];

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        reco = new int[M+1];
        ArrayList<Student> stuArr = new ArrayList<>();

        String input[] = br.readLine().split(" ");
        
        for(int i=1; i<=M; i++) {
            reco[i] = Integer.parseInt(input[i-1]);
        }

        loop:
        for(int i=1; i<=M; i++) {
            Collections.sort(stuArr);

            for(Student student : stuArr) {
                if(reco[i] == student.num) {
                    student.reco += 1;
                    continue loop;
                }
            }

            if(stuArr.size() < N) {
                stuArr.add(new Student(reco[i], 1, i));
            } else {
                stuArr.remove(0);
                stuArr.add(new Student(reco[i], 1, i));
            }
        }

        Collections.sort(stuArr, (Student stu1, Student stu2) -> {
            return stu1.num - stu2.num;
        });

        for(Student stu : stuArr) {
            System.out.print(stu.num+" ");
        }
    }
}