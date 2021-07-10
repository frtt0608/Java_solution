import java.io.*;
import java.util.*;

public class B5052 {
    static int n;
    static String result;
    static String[] numbers;

    static class TrieNode {

        private Map<Character, TrieNode> childNodes = new HashMap<>();
        private boolean isLastChar;

        Map<Character, TrieNode> getChildNodes() {
            return this.childNodes;
        }

        boolean isLastChar() {
            return this.isLastChar;
        }

        void setIsLastChar(boolean isLastChar) {
            this.isLastChar = isLastChar;
        }
    }

    static class Trie {
        private TrieNode rootNode;

        Trie() {
            this.rootNode = new TrieNode();
        }

        void Insert(String word) {
            TrieNode thisNode = this.rootNode;

            for(int i=0; i<word.length(); i++) {
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), chr -> new TrieNode());
            }

            thisNode.setIsLastChar(true);
        }

        boolean contains(String word) {
            TrieNode thisNode = this.rootNode;

            for(int i=0; i<word.length(); i++) {
                char chr = word.charAt(i);
                TrieNode node = thisNode.getChildNodes().get(chr);

                if(node != null && node.isLastChar) {
                    return true;
                } else if(node == null) {
                    break;
                }

                thisNode = node;
            }

            return false;
        }
    }
    
    public static void checkSimilarNumber() {
        Arrays.sort(numbers);
        Trie trie = new Trie();

        for(String number: numbers) {
            if(!trie.contains(number)) {
                trie.Insert(number);

            } else {
                result = "NO";
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            n = Integer.parseInt(br.readLine());
            numbers = new String[n];
            result = "YES";

            for(int i=0; i<n; i++) {
                String number = br.readLine();
                numbers[i] = number;
            }

            checkSimilarNumber();
            System.out.println(result);
        }
    }
}
