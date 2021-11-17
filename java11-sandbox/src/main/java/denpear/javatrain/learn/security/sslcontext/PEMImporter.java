package denpear.javatrain.learn.security.sslcontext;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

import static denpear.javatrain.learn.security.sslcontext.TrustManagersProvider.createTrustManagers;

public class PEMImporter {

    //https://stackoverflow.com/questions/2138940/import-pem-into-java-key-store

    public static SSLServerSocketFactory createSSLServerSocketFactory(File privateKeyPem, File certificatePem, File certificateCAFilePath, String password) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, UnrecoverableKeyException, NoSuchProviderException, KeyManagementException {
        SSLContext context = createSslContext(privateKeyPem, certificatePem, certificateCAFilePath, password);
        return context.getServerSocketFactory();
    }

    public static SSLSocketFactory createSSLSocketFactory(File privateKeyPem, File certificatePem, File certificateCAFilePath, String password) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, UnrecoverableKeyException, NoSuchProviderException, KeyManagementException {
        SSLContext context = createSslContext(privateKeyPem, certificatePem, certificateCAFilePath, password);
        return context.getSocketFactory();
    }

    private static SSLContext createSslContext(File privateKeyPem, File certificatePem, File certificateCAFilePath, String password) throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, InvalidKeySpecException, UnrecoverableKeyException, NoSuchProviderException, KeyManagementException {
        final SSLContext context = SSLContext.getInstance("TLS");
        final KeyStore keystore = createKeyStore(privateKeyPem, certificatePem, password);
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keystore, password.toCharArray());
        final KeyManager[] km = kmf.getKeyManagers();
        final TrustManager[] tm = createTrustManagers(certificateCAFilePath);
        context.init(km, tm, null);
        return context;
    }

    public static SSLServerSocketFactory createSSLServerSocketFactory(File privateKeyPem, File certificatePem, String password) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, UnrecoverableKeyException, KeyManagementException {
        final SSLContext context = SSLContext.getInstance("TLS");
        final KeyStore keystore = createKeyStore(privateKeyPem, certificatePem, password);
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(keystore, password.toCharArray());
        final KeyManager[] km = kmf.getKeyManagers();
        context.init(km, null, null);
        return context.getServerSocketFactory();
    }

    /**
     * Create a KeyStore from standard PEM files
     *
     * @param privateKeyPem the private key PEM file
     * @param certificatePem the certificate(s) PEM file
     * @param password to set to protect the private key
     */
    public static KeyStore createKeyStore(File privateKeyPem, File certificatePem, final String password)
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, InvalidKeySpecException {
        final X509Certificate[] cert = createCertificates(certificatePem);
        final KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(null);
        // Import private key
        final PrivateKey key = createPrivateKey(privateKeyPem);
        keystore.setKeyEntry(privateKeyPem.getName(), key, password.toCharArray(), cert);
        return keystore;
    }

    private static PrivateKey createPrivateKey(File privateKeyPem) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        final StringBuilder b;
        try (BufferedReader r = new BufferedReader(new FileReader(privateKeyPem))) {
            String s = r.readLine();
            if (s == null || !s.contains("BEGIN PRIVATE KEY")) {
                throw new IllegalArgumentException("No PRIVATE KEY found");
            }
            b = new StringBuilder();
            s = "";
            while (s != null) {
                if (s.contains("END PRIVATE KEY")) {
                    break;
                }
                b.append(s);
                s = r.readLine();
            }
        }
        final String hexString = b.toString();
        final byte[] bytes = Base64.getDecoder().decode(hexString);
        return generatePrivateKeyFromDER(bytes, privateKeyPem.getAbsolutePath());
    }

    private static X509Certificate[] createCertificates(File certificatePem) throws CertificateException, IOException {
        final List<X509Certificate> result = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(certificatePem))) {
            String s = r.readLine();
            if (s == null || !s.contains("BEGIN CERTIFICATE")) {
                throw new IllegalArgumentException("No CERTIFICATE found");
            }
            StringBuilder b = new StringBuilder();
            while (s != null) {
                if (s.contains("END CERTIFICATE")) {
                    String hexString = b.toString();
                    final byte[] bytes = Base64.getDecoder().decode(hexString);
                    X509Certificate cert = generateCertificateFromDER(bytes);
                    result.add(cert);
                    b = new StringBuilder();
                } else {
                    if (!s.startsWith("----")) {
                        b.append(s);
                    }
                }
                s = r.readLine();
            }
        }

        return result.toArray(new X509Certificate[result.size()]);
    }

    private static <T extends PrivateKey> T generatePrivateKeyFromDER(byte[] keyBytes, String privateKeyPemPath) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        final String keyAlgorithm = getKeyAlgorithm(privateKeyPemPath);
        KeyFactory factory;
        switch (keyAlgorithm) {
            case "RSA":
                factory = KeyFactory.getInstance("RSA");
                break;
            case "DSA":
                factory = KeyFactory.getInstance("DSA");
                break;
            case "ecPublicKey":
                factory = KeyFactory.getInstance("EC");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + keyAlgorithm);
        }
        return (T) factory.generatePrivate(spec);
    }

    private static X509Certificate generateCertificateFromDER(byte[] certBytes) throws CertificateException {
        final CertificateFactory factory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(certBytes));
    }


    public static String getKeyAlgorithm(String privateKeyFilePath) throws IOException {
        PemObject keyPemObj;
        try (PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(privateKeyFilePath)))) {
            keyPemObj = pemReader.readPemObject();
        }
        PrivateKeyInfo pkinfo = PrivateKeyInfo.getInstance(keyPemObj.getContent());
        return(KeyAlgorithmMap.get(pkinfo.getPrivateKeyAlgorithm().getAlgorithm().getId()));
    }

    static class KeyAlgorithmMap {
        static HashMap<String, String> algorithmTypes = null;

        static {
            algorithmTypes = new HashMap<>();
            algorithmTypes.put("1.2.840.10040.4.1", "DSA");
            algorithmTypes.put("1.2.840.113549.1.1.1", "RSA");
            algorithmTypes.put("1.3.132.1.12", "ECDH");
            algorithmTypes.put("1.3.132.1.1", "dhSinglePass-cofactorDH-recommendedKDF");
            algorithmTypes.put("1.3.132.1.13", "ECMQV");
            algorithmTypes.put("1.2.840.10045.2.1", "ecPublicKey");
            algorithmTypes.put("1.2.840.113549.1.1.10", "RSASSA-PSS");
            algorithmTypes.put("1.2.840.113549.1.3.1", "dhKeyAgreement");
            algorithmTypes.put("1.3.101.110", "X25519");
            algorithmTypes.put("1.3.101.111", "X448");
            algorithmTypes.put("1.2.840.10046.2.1", "DH");
        }

        public static String get(String oid) {
            return (algorithmTypes.get(oid));
        }
    }
}
