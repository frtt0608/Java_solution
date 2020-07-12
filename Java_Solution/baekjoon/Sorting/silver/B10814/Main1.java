// 나이순 정렬

import java.io.*;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

public class Main1 {

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
        LinkedList<Person> perList = new LinkedList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            perList.add(new Person(age, name));
        }

        Collections.sort(perList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.age - p2.age;
            }
        });

        while(!perList.isEmpty()) {
            Person p = perList.pollFirst();
            System.out.println(p.age +" "+ p.name);
        }
    }
}