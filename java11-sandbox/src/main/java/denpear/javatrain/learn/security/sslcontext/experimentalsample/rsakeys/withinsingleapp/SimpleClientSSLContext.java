package denpear.javatrain.learn.security.sslcontext.experimentalsample.rsakeys.withinsingleapp;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;

import static denpear.javatrain.learn.security.sslcontext.TrustManagersProvider.createTrustManagers;

public class SimpleClientSSLContext {
    static String startClient(String host, int port) throws IOException, KeyManagementException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyStoreException, NoSuchProviderException {
        final File certificateCAFilePath = new File("C:\\dev\\CODE\\TpamCrd\\lib-java-testing\\java11-sandbox\\src\\main\\resources\\clienttruststoreRSA.jks");
        URL url = new URL("https://" + host + ":" + port);
        SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
        sslContext.init(null, createTrustManagers(certificateCAFilePath), new SecureRandom());
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        try (Socket connection = socketFactory.createSocket(host, port)) {
            ((SSLSocket) connection).setEnabledCipherSuites(
                    new String[] {"TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384",
                            "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
                            "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256",
                            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384",
                            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
                            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
                            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_RSA_WITH_AES_256_GCM_SHA384",
                            "TLS_RSA_WITH_AES_128_GCM_SHA256",
                            "TLS_RSA_WITH_AES_256_CBC_SHA256",
                            "TLS_RSA_WITH_AES_128_CBC_SHA256",
                            "TLS_RSA_WITH_AES_256_CBC_SHA",
                            "TLS_RSA_WITH_AES_128_CBC_SHA",
                            "TLS_EMPTY_RENEGOTIATION_INFO_SCSV",
                            //пять шифров, которые поддерживает TLS 1.3:
                            "TLS_AES_128_GCM_SHA256",
                            "TLS_AES_256_GCM_SHA384",
//                            "TLS_CHACHA20_POLY1305_SHA256", //Unsupported CipherSuite
                            //                          "TLS_AES_128_CCM_SHA256", //Unsupported CipherSuite
                            //                   "TLS_AES_128_CCM_8_SHA256" //Unsupported CipherSuite
                    });
            ((SSLSocket) connection).setEnabledProtocols(
                    new String[] { "TLSv1","TLSv1.1","TLSv1.2","TLSv1.3"});
            SSLParameters sslParams = new SSLParameters();
    //        sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            ((SSLSocket) connection).setSSLParameters(sslParams);
            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return input.readLine();
        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException, CertificateException, KeyStoreException, NoSuchProviderException {
        System.out.println(startClient("localhost", 8443));
    }
}
