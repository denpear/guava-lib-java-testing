package denpear.javatrain.learn.security.baeldung.ssl.example.rsakeys;

import denpear.javatrain.learn.security.sslcontext.PEMImporter;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//https://xakep.ru/2015/08/14/log-almighty/ - посмотреть!
public class RSASimpleServer {
    static void startServer (int port) throws Exception {
        final File privateKeyFilePath = new File("C:\\dev\\CODE\\TpamCrd\\lib-java-testing\\java11-sandbox\\src\\main\\resources\\keyServer.pem");
        final File certificateFilePath = new File("C:\\dev\\CODE\\TpamCrd\\lib-java-testing\\java11-sandbox\\src\\main\\resources\\certServer.pem");
        final File certificateCAFilePath = new File("C:\\dev\\CODE\\TpamCrd\\lib-java-testing\\java11-sandbox\\src\\main\\resources\\servertruststoreRSA.jks");
        SSLServerSocketFactory factory = PEMImporter.createSSLFactory(privateKeyFilePath, certificateFilePath, certificateCAFilePath,"password");

        try (ServerSocket listener = factory.createServerSocket(port)) {
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

    public static void main(String[] args) throws Exception {
        startServer(8443);
    }
}