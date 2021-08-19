package security;

import org.junit.Test;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class generateSecretPairKeyTest {
    @Test
    public void genPairKeysAllAlgorithmTest() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
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
            System.out.printf("Public Key of %s: %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", pub);
            String sec = getHexString(pair.getPrivate().getEncoded());
            System.out.printf("Private Key of %s: %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", sec);
    }

    public void generatePairKeysECDSA(final String algorithm, final int keyLength) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256k1"), new SecureRandom());
        System.out.println(keyPairGenerator.getProvider().getName());
        KeyPair pair = keyPairGenerator.genKeyPair();
        String pub = getHexString(pair.getPublic().getEncoded());
        System.out.printf("Public Key of %s: %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", pub);
        String sec = getHexString(pair.getPrivate().getEncoded());
        System.out.printf("Private Key of %s: %s%n", "Current user on " + algorithm + " and initial key size "  + keyLength  + " bits", sec);
    }

    private static String getHexString(byte[] arr)
    {
        return new BigInteger(arr).toString(16).toUpperCase();
    }

}
