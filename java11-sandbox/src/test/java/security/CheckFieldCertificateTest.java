package security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.naming.ConfigurationException;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CheckFieldCertificateTest {
    public static X509Certificate[] buildTestCertificateWithoutSAN() throws Exception {
        String cert = "-----BEGIN CERTIFICATE-----\n"
                + "MIIENjCCAp6gAwIBAgICALEwDQYJKoZIhvcNAQELBQAwZzEUMBIGA1UEAwwLU1NF\n"
                + "LUNBIFJvb3QxEjAQBgNVBAoMCVQtU3lzdGVtczEdMBsGA1UECwwUU2VjdXJpdHkg\n"
                + "RW5naW5lZXJpbmcxCzAJBgNVBAYTAkRFMQ8wDQYDVQQHDAZCZXJsaW4wHhcNMTcx\n"
                + "MjA4MTEyNTU3WhcNMjcxMjA4MjM1OTU5WjCBhTELMAkGA1UEBhMCREUxEjAQBgNV\n"
                + "BAoMCVQtU3lzdGVtczEdMBsGA1UECwwUU2VjdXJpdHkgRW5naW5lZXJpbmcxGjAY\n"
                + "BgNVBAMMEUNSRCBDbGllbnQgQWRtaW42MScwJQYJKoZIhvcNAQkBFhhhZG1pbjZ2\n"
                + "LmNyZEBzc2VkZXYuc2xzZWMwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\n"
                + "AQDI4DXseCa6utR8PGYrfxcEEpQ6IxbPRYMd6PFx2zwt04fNY1zTFbuAgywzXfoY\n"
                + "ujX8FIUGlrV8J3S8wuxrh0/8UWh9EV+W0CLuwqVw0yCGOGwbStcpdMvdHVclvuQP\n"
                + "4IIgtU0e82yk22x5hrY/Pm3x+cSZOWyzCXEiPIDWO8jF7l1h2kbdqWi8VFgQYtDT\n"
                + "tZCwN39jybAdlMckGwEpLLoUDWDzqlQo65Fdye+7ZOPAOutT5y6KMf6z1znUyMEA\n"
                + "CMqqr6lpwIdsAL4aUttgQaJ/HKbCPKV4KDdEJuN5shfdi1trpoUwf4qXQW4bOdFO\n"
                + "4lEeLJd+BvF3MRCN+gWrfAdbAgMBAAGjTTBLMAkGA1UdEwQCMAAwHQYDVR0OBBYE\n"
                + "FG63RhFbPpoPPAr32mP+XnRWSpoyMB8GA1UdIwQYMBaAFJ0xG6jNoICRdHf6Jq2n\n"
                + "Fr3xzwr1MA0GCSqGSIb3DQEBCwUAA4IBgQCbzSmGPOSnCvAaI2h3n/3GjHiuz1dr\n"
                + "en+dyEluPiyDy2OaHmg2QLO7WU/rTDYwdI3OFlTwdz9lgvgELm6H93titVwLF9M+\n"
                + "WXYmebl7jobdFunealAicfk+A6ST90EfqEuo6vYZQs3JJoM3S7UZ+VNxn7GYlrH3\n"
                + "GI4w/1hTHXvU9PMwbM1Kqh1Bgpu+rTFyhFugRH2IeEstCB7KyVsdR2dWzWBWVlWi\n"
                + "VoPJid+65nITe75A1RN7FKImYgrg8bk6GfFZq9GeieYR3WDaPSt9VUyVUsVlBar8\n"
                + "iB/hpJoMiwtrP135JdVwwohoiaPaqAN4J0hl3skvpFdNd16e5lJ3kos8WpAm9M2M\n"
                + "80EUChZHaYuWSkcG8NN9zshSOLzdSgyJqWuE1zihpAU+S5uP757px31yoNWPOd8d\n"
                + "Df/LCFB5DRnxlQ2Bmt3qpcn4KfEPrnXpoyG9XZ9HsL1GZKirLf4NVSTL3vswthFR\n"
                + "QdEK6Dqol/MPFksr2AukN4rz/3rYWSFjmOw=\n" + "-----END CERTIFICATE-----";

        ByteArrayInputStream in = new ByteArrayInputStream(cert.getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] x509Certificates = new X509Certificate[1];
        x509Certificates[0] = (X509Certificate) cf.generateCertificate(in);
        return x509Certificates;
    }

    public static X509Certificate[] buildTestCertificateWithSAN() throws Exception {
        String cert = "-----BEGIN CERTIFICATE-----\n"
                + "MIIEaDCCAtCgAwIBAgIBTjANBgkqhkiG9w0BAQsFADBnMRQwEgYDVQQDDAtTU0Ut\n"
                + "Q0EgUm9vdDESMBAGA1UECgwJVC1TeXN0ZW1zMR0wGwYDVQQLDBRTZWN1cml0eSBF\n"
                + "bmdpbmVlcmluZzELMAkGA1UEBhMCREUxDzANBgNVBAcMBkJlcmxpbjAeFw0yMjA0\n"
                + "MTQwOTMyNTVaFw00OTEyMzEyMzU5NTlaMGMxCzAJBgNVBAYTAkRFMRIwEAYDVQQK\n"
                + "DAlULVN5c3RlbXMxHTAbBgNVBAsMFFNlY3VyaXR5IEVuZ2luZWVyaW5nMSEwHwYD\n"
                + "VQQDDBhzc2VkZXYuc2VjbGFiLnRlbGVrb20uZGUwggEiMA0GCSqGSIb3DQEBAQUA\n"
                + "A4IBDwAwggEKAoIBAQDC7JnbAD3W0WWaUIH46EsbMAu6psL8ewTKoYuioHj7Eunk\n"
                + "ewvAbgpnLqJImJPUyQf4keWmn3suRYKRbhJN8AYA+RxRJEKEaIhHX3gerIC7lbuO\n"
                + "PYCXBafvx4A0jl3GiQE9oCVc4zyIasVPvfpH6v54i4lPDKeqywjp1RqwwATffcCG\n"
                + "GhRVExLvqUxd4xQbJwGML+ap+zdmuTMO5FV5ozydjHlXP5b6wc8PFQGvj5YEuggh\n"
                + "VMnLrJIf6rnbhoo5ixFsBeilWv9YsOj9btthFGjMaMZiAYL6oH5JRcv6IeQFLD2a\n"
                + "oPAo5BVh+al0M1NIXoHChiO3txMuN+b2pqxZPg1ZAgMBAAGjgaIwgZ8wCQYDVR0T\n"
                + "BAIwADAdBgNVHQ4EFgQU6y7BRajpTN5LtxW1BARyJjcWIuwwHwYDVR0jBBgwFoAU\n"
                + "nTEbqM2ggJF0d/omracWvfHPCvUwCwYDVR0PBAQDAgXgMEUGA1UdEQQ+MDyCF2Nh\n"
                + "ZXNhci5iZXJsaW4ubGFib3IubGFuggZjYWVzYXKCE2NhZXNhci5zc2VkZXYuc2xz\n"
                + "ZWOHBAEQCKMwDQYJKoZIhvcNAQELBQADggGBAG2N1yKjNz4ja4zL5cJxXfDT3Bu1\n"
                + "gkKb6S+LlGevpsLM9PN9TFbiPsFL1ygZE0mHDA8undZogXkuQV+E0jXuUn8V/qFp\n"
                + "H+ENan4JE8E+mjLcEl7mfpnJ0R3K4ITphhPSlHfMcvK1HRkXowmj14AE2IKE2G0A\n"
                + "DfvQsJ80luhaTwGmHGgfQNp/xe6cQTI63bzh680TODh6BxsnQtJVPQJuuAEnc1nr\n"
                + "RAQej8bmHy9KlNpbFAloWtrG4xxYday/R0PSZeWWn7GF+N6mDsYt7PK0NObJ+mAz\n"
                + "ntKfuygJ/p1BMbJG3/KxJNwKGD+/9YZH981rsAkLg/7yRvBxcmMGYUR1Dx6n1Xia\n"
                + "OyvK9Udeg+m74aiT8E+0DriyLcVH21KIw5Vz6UV09nxeHSxU5i/VBKezPsMc41Sg\n"
                + "xHJThp1/Oc0wWwhTQXyaoxAf76aS/SAIQSLmZLHJOkk0AgHXFMnnamGn/w+0f2H8\n"
                + "kcpNlfjTkpxLhprWNmNSVRfM8In2gqrpxobb4g==\n" + "-----END CERTIFICATE-----";

        ByteArrayInputStream in = new ByteArrayInputStream(cert.getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] x509Certificates = new X509Certificate[1];
        x509Certificates[0] = (X509Certificate) cf.generateCertificate(in);
        return x509Certificates;
    }

    @Test
    void checkClientCertificateTest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("javax.servlet.request.X509Certificate", buildTestCertificateWithoutSAN());
        Throwable throwable = assertThrows(IllegalAccessException.class,
                () -> checkClientCertificate(request));
        assertEquals("Client certificate declined", throwable.getMessage());
    }

    protected void checkClientCertificate(HttpServletRequest servletRequest)
            throws ConfigurationException, IllegalAccessException, CertificateParsingException, InvalidNameException {
        X509Certificate certChain[] = (X509Certificate[]) servletRequest.getAttribute(
                "javax.servlet.request.X509Certificate");

        String cnClientCertificateNameDbParam = Optional.ofNullable("sample.name.com").orElse(UUID.randomUUID().toString());

        String dn = certChain[0].getSubjectX500Principal().getName();
        LdapName ldapDN = new LdapName(dn);
        Rdn cn = ldapDN.getRdns().stream().filter(x -> x.getType().startsWith("CN")).findFirst().orElse(null);
        String cnValue = "";
        if (cn != null) {
            cnValue = cn.getValue().toString();
        }
        if (!(cnValue.matches(cnClientCertificateNameDbParam) || checkCertificateSANFields(certChain[0]))) {

            System.out.println("Certificate details do not match: expected CN "
                    + cnClientCertificateNameDbParam + " and got " + cnValue);

            throw new IllegalAccessException("Client certificate declined");
        }
    }

    @Test
    @SneakyThrows
    void checkCertificateSANFieldsTest() {
        X509Certificate certChain[] = buildTestCertificateWithoutSAN();
        X509Certificate certChainSAN[] = buildTestCertificateWithSAN();
        assertTrue(!checkCertificateSANFields(certChain[0]));
        assertTrue(!checkCertificateSANFields(certChainSAN[0]));
    }

    protected boolean checkCertificateSANFields(X509Certificate x509Certificate) throws CertificateParsingException, ConfigurationException {
        String sanClientCertificateFields = Optional.ofNullable("sample.name.com").orElse(UUID.randomUUID().toString());
        Collection<List<?>> subjectAlternativeNames = Optional.ofNullable(x509Certificate.getSubjectAlternativeNames()).orElse(new ArrayList<>());
        for (var recordAltName : subjectAlternativeNames) {
            if (recordAltName.get(1)
                    .toString()
                    .matches(sanClientCertificateFields)) {
                return true;
            }
        }
        System.out.println("Certificate details do not match: expected SAN "
                + sanClientCertificateFields + " and got " + subjectAlternativeNames);
        return false;
    }


}
