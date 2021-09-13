package File;

import java.nio.charset.StandardCharsets;

public class draft {
    public static void main(String[] args) {
        String s="ajbdjhsab";
        byte[] s1=s.getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(s1));
    }
}
