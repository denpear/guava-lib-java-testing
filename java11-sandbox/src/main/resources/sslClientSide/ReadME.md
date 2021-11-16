Решение 1 (на клиенте). На стороне клиента был использован curl, который в качестве параметра на сервер отправлял самоподписанный сертификат, который также был сгенерирован в формате PEM из Java key store (jks)

1) curl -vvv https://localhost:8443 --cert /drives/c/dev/CODE/TpamCrd/lib-java-testing/java11-sandbox/src/main/resources/clientCertificateRSA.p12 --location --silent -k
2) /home/mobaxterm  curl -vvvv https://localhost:8443 --cert /drives/c/dev/CODE/TpamCrd/lib-java-testing/java11-sandbox/src/main/resources/clientCertificateRSA.p12 --location --silent -k


#Решение 1 на клиенте 
См. Пакет withinsingleapp
*
Главный вывод: при таком решении источником ключей и для клиента и для сервера является один и тот же jks сервера!!! Но так бывает не всегда, потому что клиенты не обязательно должны быть из одной организации.
То есть для затравки нужен всего лишь один артефакт: пара RSA ключей для сервера, всё начинается с него, далее вокруг этих ключей генерируем всю оставшуюся инфраструктуру.
Это наименее безопасный вариант, потому что обязательную аутентификацию на сервере придется отключить
//    ((SSLServerSocket) listener).setNeedClientAuth(true);
*


##Выгружаем клиентский сертификат, который выдается самим же сервером и содержит закрытый ключ и сертификат сервера, его выдавшего (-----BEGIN PRIVATE KEY----- и -----BEGIN CERTIFICATE-----)
```
openssl pkcs12 -in serverkeystoreRSA.p12 -nodes -clcerts -out clientCertificateRSA.p12
```
clientCertificateRSA.p12 - используется в запросах через curl с ключом --cert

##Выгружаем только сертификаты ЦС (не сертификаты клиентов!).
```
openssl pkcs12 -in serverkeystoreRSA.p12 -nodes -cacerts -out serverROOT.pem
```
##Выгружаем аналог P12 файла, который  принадлежит категории Personal Information Exchange File. In cryptography, a public key certificate (or identity certificate) is a certificate which uses a digital signature to bind together a public key with an identity. The certificate can be used to verify that a public key belongs to an individual. This is a PKCS #12 file. In cryptography, PKCS refers to a group of Public Key Cryptography Standards devised and published by RSA Security..
```
openssl pkcs12 -export -out client-mysandbox-identity.p12 -inkey keyServer.pem -in certServer.pem
```


#Решение 2 на клиенте:

Клиент приходит со своим ключем и хочет, чтобы мы его принимали

##Генерируем пару RSA ключей и кладем их в хранилище ключей
```
keytool -genkey -keyalg RSA -keypass password -storepass password -keystore clientkeystoreRSA.jks
```
##Переводим keystore в формат pkcs12
```
keytool -importkeystore -srckeystore clientkeystoreRSA.jks -srcstorepass password -destkeystore client-cert-and-key.p12 -deststoretype pkcs12 -destkeypass password
```
##Выгружаем клиентский сертификат, который выдается самим же сервером и содержит закрытый ключ и сертификат сервера, его выдавшего (-----BEGIN PRIVATE KEY----- и -----BEGIN CERTIFICATE-----), идентификатор ключа однозначно связан с идентификатором сертификата сервера, их серийные номера идентичны.
```
openssl pkcs12 -in client-cert-and-key.p12 -nodes -clcerts -out clientOuterCertificateRSA.p12
```
##Выгружаем клиентский сертификат (This CA Root certificate) ключа (зашифрован), открывается проводником Windows, чтобы импортировать его в доверенное хранилище сервера:
```
keytool -export -storepass password -file clientRSA.cer -keystore clientkeystoreRSA.jks
```