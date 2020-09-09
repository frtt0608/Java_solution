import java.util.*;

public class 파일명정렬 {

    class Solution {
        
        public String[] solution(String[] files) {
            
            Arrays.sort(files, new Comparator<String>() {
            @Override
                public int compare(String file1, String file2) {
                    String head1 = file1.split("[0-9]")[0];
                    String head2 = file2.split("[0-9]")[0];
                    
                    int compareVal = head1.toLowerCase().compareTo(head2.toLowerCase());
                    
                    if(compareVal == 0) {
                        int num1 = getNumber(file1, head1);
                        int num2 = getNumber(file2, head2);
                        
                        compareVal = num1 - num2;
                    }
                    
                    return compareVal;
                }
            });
            
            System.out.println(Arrays.toString(files));
            return files;
        }
        
        public int getNumber(String file, String head) {
            file = file.replace(head, "");
            String num = "";
            
            for(int i=0; i<file.length(); i++) {
                char chr = file.charAt(i);
                
                if(chr >= '0' && chr <= '9') {
                    num += chr;
                } else {
                    break;
                }
            }
            
            return Integer.valueOf(num);
        }
    }
}
