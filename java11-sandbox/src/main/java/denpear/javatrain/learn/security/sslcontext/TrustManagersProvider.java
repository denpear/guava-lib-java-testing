package denpear.javatrain.learn.security.sslcontext;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class TrustManagersProvider {
    public static TrustManager[] createTrustManagers(File certificateCAFile)
            throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException,
            NoSuchProviderException, UnrecoverableKeyException {
        FileInputStream myKeys = new FileInputStream(certificateCAFile);
        KeyStore myTrustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        myTrustStore.load(myKeys, "password".toCharArray());
        TrustManagerFactory factory = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
        factory.init(myTrustStore);
        return factory.getTrustManagers();
    }
}
