import java.util.*;

class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] skills = skill.toCharArray();
        ArrayList<Character> skillList = new ArrayList<>();
        for(int i=0; i<skills.length; i++) {
            skillList.add(skills[i]);
        }
        
        loop:
        for(int i=0; i<skill_trees.length; i++) {
            int idx = 0;
            String tree = skill_trees[i];
            
            for(int j=0; j<tree.length(); j++) {
                if(skillList.contains(tree.charAt(j))) {
                    // System.out.println(tree.charAt(j) + ", " + skillList.get(idx) );
                    if(skillList.get(idx) == tree.charAt(j)) {
                        
                        idx++;
                    } else continue loop;
                }
            }
            
            answer++;
        }
        
        return answer;
    }
}

// 정규화 활용
// import java.util.*;

// class Solution {
//     public int solution(String skill, String[] skill_trees) {
//         int answer = skill_trees.length;
//         String pattern = "[^" + skill + "]";
//         for(String tree : skill_trees) {
//             String normal = tree.replaceAll(pattern, "");
//             // System.out.println(normal);
//             if(skill.indexOf(normal) != 0) {
//                 answer--;
//             }
//         }
        
//         return answer;
//     }
// }