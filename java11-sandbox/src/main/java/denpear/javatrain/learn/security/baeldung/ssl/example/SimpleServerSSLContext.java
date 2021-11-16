package denpear.javatrain.learn.security.baeldung.ssl.example;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SimpleServerSSLContext {
    static void startServer(int port) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, null, new SecureRandom());
        ServerSocketFactory socketFactory = sslContext.getServerSocketFactory();

        try (ServerSocket listener = socketFactory.createServerSocket(port)) {
            ((SSLServerSocket) listener).setNeedClientAuth(true);
            ((SSLServerSocket) listener).setEnabledCipherSuites(
                    new String[] { "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
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
            ((SSLServerSocket) listener).setEnabledProtocols(
              new String[] { "TLSv1","TLSv1.1","TLSv1.2","TLSv1.3"});
            while (true) {
                try (Socket socket = listener.accept()) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hello World!");
                }
            }

        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        startServer(8443);
    }
}