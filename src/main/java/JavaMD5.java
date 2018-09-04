import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaMD5 {
    private static MessageDigest digest;

    public synchronized static String hash(String message) {
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        digest.update(message.getBytes());
        return String.format("%x", new BigInteger(1, digest.digest()));
    }
}
