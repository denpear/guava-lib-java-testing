Решение 1 (на клиенте). На стороне клиента был использован curl, который в качестве параметра на сервер отправлял самоподписанный сертификат, который также был сгенерирован в формате PEM из Java key store (jks)

1) curl -vvv https://localhost:8443 --cert /drives/c/dev/CODE/TpamCrd/lib-java-testing/java11-sandbox/src/main/resources/clientCertificateRSA.p12 --location --silent -k
2) /home/mobaxterm  curl -vvvv https://localhost:8443 --cert /drives/c/dev/CODE/TpamCrd/lib-java-testing/java11-sandbox/src/main/resources/clientCertificateRSA.p12 --location --silent -k
