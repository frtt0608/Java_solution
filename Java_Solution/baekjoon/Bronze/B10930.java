import java.util.*;
import java.io.*;
import java.security.MessageDigest;

public class B10930 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(S.getBytes());
            byte[] hash = digest.digest();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) sb.append('0');
				sb.append(hex);
			}

            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}