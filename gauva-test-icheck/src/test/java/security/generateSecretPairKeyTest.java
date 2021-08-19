package security;

import org.junit.Test;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class generateSecretPairKeyTest {
    @Test
    public void genPairKeysAllAlgorithmTest() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        final String pubKeySSH = "ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAQEAsuVPKUpLYSCNVIHD+e6u81IUznkDoiOvn/t56DRcutRc4OrNsZZ+Lmq49T4JCxUSmaT8PeLGS/IC946CNQzFwMh++sVoc19UUkZtRaDgiYn+HkYk8VW4IFI1dKfXomKSbX/lB+ohzLzXLVP2/UJgfBmdaE10k+6b+/Yd8YGXIeS8/Z9zToHPo0ORNSGIolgq3xMXUtfAOK/0KC6IFc/FuvuOSAG1UWup91bcm5GSXv4BWWjgFtOxCLIknYjsDah4qfrP8Olp5eUDhn/65xRcZsmRXoYe1ylhlSjJoPDFWXVs9npwqQmi3JaZtgg7xJxMu1ZcdpYxoj280zM9/6w1Lw==";
        System.out.println(calculateFingerprintFromSSHRSAPublicKey(pubKeySSH));

        generatePairKeys("RSA",512);
        generatePairKeys("RSA",2048);
        generatePairKeys("RSA",4096);
        generatePairKeys("DSA",512);
        generatePairKeys("DSA",2048);
        generatePairKeysECDSA("EC",2048);
        generatePairKeysECDSA("EC",512);
        //generatePairKeys("DSA",4096); // java.security.InvalidParameterException: Invalid DSA Prime Size: 4096
/* java.security.NoSuchAlgorithmException: ECDSA KeyPairGenerator not available
        generatePairKeys("ECDSA",512);
        generatePairKeys("ECDSA",2048);
        generatePairKeys("ECDSA",4096);

*/
    }


    @Test
    public void generatingKeysRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        System.out.println(keyPairGenerator.getProvider());
        System.out.println(keyPairGenerator.getAlgorithm());
        SecureRandom  randomNumberGeneratorRNG = new SecureRandom();
        System.out.println(randomNumberGeneratorRNG.getProvider());
        System.out.println(randomNumberGeneratorRNG.getAlgorithm());
        // setting seed
        long userSeed = 24;
        randomNumberGeneratorRNG.setSeed(userSeed);
        keyPairGenerator.initialize(1024, randomNumberGeneratorRNG);
        System.out.println(keyPairGenerator.getProvider().getName());
        for (String user : new String[]{"A", "B"}) {
            KeyPair pair = keyPairGenerator.genKeyPair();
            String pub = getHexString(pair.getPublic().getEncoded());
            System.out.printf("Public Key of %s: %s%n", user, pub);
        }
    }
    @Test
    public void generatePairKeysDSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        SecureRandom  randomNumberGeneratorRNG = new SecureRandom();
        keyPairGenerator.initialize(1024, randomNumberGeneratorRNG);
        System.out.println(keyPairGenerator.getProvider().getName());
        for (String user : new String[]{"A", "B"}) {
            KeyPair pair = keyPairGenerator.genKeyPair();
            String pub = getHexString(pair.getPublic().getEncoded());
            System.out.printf("Public Key of %s: %s%n", user, pub);
        }
    }

    public void generatePairKeys(final String algorithm, final int keyLength) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        SecureRandom  randomNumberGeneratorRNG = new SecureRandom();
        keyPairGenerator.initialize(keyLength, randomNumberGeneratorRNG);
        System.out.println(keyPairGenerator.getProvider().getName());
            KeyPair pair = keyPairGenerator.genKeyPair();
            String pub = getHexString(pair.getPublic().getEncoded());
            String fingerprint = calculateFingerprint(pub);
            System.out.printf("Public Key of %s: %s%n %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", pub," Fingerprint: "  + fingerprint);
            String sec = getHexString(pair.getPrivate().getEncoded());
            System.out.printf("Private Key of %s: %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", sec);
    }

    public void generatePairKeysECDSA(final String algorithm, final int keyLength) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256k1"), new SecureRandom());
        System.out.println(keyPairGenerator.getProvider().getName());
        KeyPair pair = keyPairGenerator.genKeyPair();
        String pub = getHexString(pair.getPublic().getEncoded());
        String fingerprint = calculateFingerprint(pub);
        System.out.printf("Public Key of %s: %s%n %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", pub," Fingerprint: "  + fingerprint);
        String sec = getHexString(pair.getPrivate().getEncoded());
        System.out.printf("Private Key of %s: %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", sec);
    }

    private static String getHexString(byte[] arr)
    {
        return new BigInteger(arr).toString(16).toUpperCase();
    }

    /**
     * Calculate fingerprint
     *
     * @param publicKey public key
     * @return fingerprint
     */
    public static String calculateFingerprintFromSSHRSAPublicKey(String publicKey) {
        String derFormat = publicKey.split(" ")[1].trim();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //log.error(e.getMessage(), e);
            throw new RuntimeException("Could not get fingerprint", e);
        }
        byte[] digest = messageDigest.digest(Base64.getDecoder().decode(derFormat));
        final StringBuilder toRet = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            if (i != 0) toRet.append(":");
            int b = digest[i] & 0xff;
            String hex = Integer.toHexString(b);
            if (hex.length() == 1) toRet.append("0");
            toRet.append(hex);
        }
        return toRet.toString();
    }


    /**
     * Calculate fingerprint as of ssh-keygen -E sha256 -lf sample.pub command
     *
     * @param publicKey public key
     * @return fingerprint
     */
    public static String calculateFingerprint(String publicKey) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //log.error(e.getMessage(), e);
            throw new RuntimeException("Could not get fingerprint", e);
        }
        byte[] digest = messageDigest.digest(Base64.getDecoder().decode(publicKey));
        final StringBuilder toRet = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            if (i != 0) toRet.append(":");
            int b = digest[i] & 0xff;
            String hex = Integer.toHexString(b);
            if (hex.length() == 1) toRet.append("0");
            toRet.append(hex);
        }
        return toRet.toString();
    }

}
