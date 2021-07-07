import java.io.*;
import java.util.*;

public class B5430 {
    static boolean isRotate, isError;
    static List<String> strings;

    public static List<String> changerArrToList(String[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    public static void deleteString() {
        if(isRotate) strings.remove(strings.size()-1);
        else strings.remove(0);
    }

    public static void makeNewLanguageAC(String p, String[] arr) {
        strings = changerArrToList(arr);
        isRotate = false;

        for(char cmd: p.toCharArray()) {
            if(cmd == 'R') {
                isRotate = !isRotate;

            } else if(cmd == 'D') {
                if(strings.size() == 0) {
                    isError = true;
                    return;
                }

                deleteString();
            }
        }
    }

    public static String[] changeListToArr() {
        int size = strings.size();
        String[] results = new String[size];

        if(isRotate) {
            for(int i=0; i<size; i++) {
                results[i] = strings.get(size-1-i);
            }

        } else {
            for(int i=0; i<size; i++) {
                results[i] = strings.get(i);
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            String result = "";
            isError = false;
            
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().replace("[", "").replace("]", "").split(",");
            
            if(n == 0) {
                arr = new String[0];
            }

            makeNewLanguageAC(p, arr);
            
            if(isError) {
                result = "error";
            } else {
                String[] results = changeListToArr();
                result = Arrays.toString(results).replace(" ", "");
            }

            System.out.println(result);
        }
    }
}
