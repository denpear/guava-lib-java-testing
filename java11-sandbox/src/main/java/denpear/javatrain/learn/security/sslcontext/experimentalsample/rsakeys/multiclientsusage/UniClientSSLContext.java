package denpear.javatrain.learn.security.sslcontext.experimentalsample.rsakeys.multiclientsusage;

import denpear.javatrain.common.utils.Utilties;
import denpear.javatrain.learn.security.sslcontext.PEMImporter;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

public class UniClientSSLContext {
    static String startClient(String host, int port) throws IOException, KeyManagementException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyStoreException, NoSuchProviderException, InvalidKeySpecException {
        final File privateKeyFilePath = new File(Utilties.getContextPath("sslClientSide/private_key_client_folder/privateKeyClient.pem"));
        final File certificateFilePath = new File(Utilties.getContextPath("sslClientSide/certificate_client_folder/certClient.pem"));
        final File certificateCAFilePath = new File(Utilties.getContextPath("sslClientSide/keystores_client_folder/truststore/clienttruststoreRSA.jks"));
        SSLSocketFactory socketFactory = PEMImporter.createSSLSocketFactory(privateKeyFilePath, certificateFilePath, certificateCAFilePath,"password");

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

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException, CertificateException, KeyStoreException, NoSuchProviderException, InvalidKeySpecException {
        System.out.println(startClient("localhost", 8443));
    }
}
