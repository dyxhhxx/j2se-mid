package File;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class Hero implements Serializable {
    public float hp;
    String name;
    private static final long serialVersionUID=1l;

    public static void main(String[] args) {
        String s="ajbdjhsab";
        byte[] s1=s.getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(s1));
    }
}
