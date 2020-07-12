// 나이순 정렬

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;



public class Main {

    static class Person {
        int age;
        String name;
    
        Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Person[] people = new Person[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            
            people[i] = new Person(age, name);
        }

        Arrays.sort(people, new Comparator<Person>() {
            @Override   
            public int compare(Person p1, Person p2) {
                return p1.age - p2.age;
            }
        });

        for(int i=0; i<N; i++) {
            System.out.println(people[i].age + " " + people[i].name);
        }
    }
}