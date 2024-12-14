import java.io.*;
import java.util.*;

public class B25206 {

    public static final String PASS_SCORE = "P";
    
    public static Map<String, Double> scoreMap = Map.of(
        "A+", 4.5,
        "A0", 4.0,
        "B+", 3.5,
        "B0", 3.0,
        "C+", 2.5,
        "C0", 2.0,
        "D+", 1.5,
        "D0", 1.0,
        "F", 0.0
    );

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double subjectGrade = 0.0;
        double subjectScore = 0.0;
        for(int i=0; i<20; i++) {
            String[] subjectInfo = br.readLine().split(" ");
            double grade = Double.parseDouble(subjectInfo[1]);
            subjectScore = addScore(subjectScore, grade, subjectInfo[2]);

            if(!isPassScore(subjectInfo[2])) {
                subjectGrade += grade;
            }
        }

        
        double avg = subjectScore / subjectGrade;
        System.out.println(avg);
    }

    public static boolean isPassScore(String score) {
        return score.equals(PASS_SCORE);
    }

    public static double addScore(double subjectScore, double grade, String score) {
        return isPassScore(score) ? 
            subjectScore : subjectScore + (scoreMap.get(score) * grade);
    }
}