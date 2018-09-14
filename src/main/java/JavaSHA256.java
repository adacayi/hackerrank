import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaSHA256 {
    public static final String SHA_256 = "SHA-256";
    private static MessageDigest digest;

    public synchronized static String hash(String message) {
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance(SHA_256);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        digest.update(message.getBytes());
        return String.format("%064x", new BigInteger(1, digest.digest()));
    }
}
