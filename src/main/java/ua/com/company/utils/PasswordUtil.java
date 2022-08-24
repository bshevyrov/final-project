package ua.com.company.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordUtil {
    //https://www.baeldung.com/java-password-hashing
    //https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
    public static final int SALT_SIZE = 16;
    public static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int NUMBER_OF_ITERATIONS = 65535;
    public static final int HASH_LENGTH = 128;

    public static String encryptPassword(String password) {
        byte[] salt = getSalt();
        byte[] hash = getHash(password, salt, NUMBER_OF_ITERATIONS, HASH_LENGTH);
        return NUMBER_OF_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    public static boolean validatePassword(String originalPassword, String storedPassword) {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        byte[] originalHash = getHash(originalPassword, salt, iterations, hash.length * 8);

        int diff = hash.length ^ originalHash.length;

        for (int i = 0; i < hash.length && i < originalHash.length; i++) {
            diff |= hash[i] ^ originalHash[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), bytes.length);
        }
        return bytes;
    }

    private static String toHex(byte[] array) {
        BigInteger bigInteger = new BigInteger(1, array);
        String hex = bigInteger.toString(array.length);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] getHash(String password, byte[] salt, int iterations, int hashLength) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, hashLength);
        SecretKeyFactory factory = null;
        byte[] hash = new byte[0];
        try {
            factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
