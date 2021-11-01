##Генерируем пару RSA ключей и кладем их в хранилище ключей
```
keytool -genkey -keyalg RSA -keypass password -storepass password -keystore serverkeystoreRSA.jks
```
##Переводим keystore в формат pkcs12
```
keytool -importkeystore -srckeystore serverkeystoreRSA.jks -destkeystore serverkeystoreRSA.p12 -deststoretype pkcs12
```
##Выгружаем сертификат сервера из хранилища ключей pkcs12 (-----BEGIN CERTIFICATE-----)
```
openssl pkcs12 -in serverkeystoreRSA.p12  -nokeys -out certServer.pem
```
##Выгружаем только закрытый ключ сервера в незашифрованном виде (-----BEGIN PRIVATE KEY-----)
```
openssl pkcs12 -in serverkeystoreRSA.p12  -nodes -nocerts -out keyServer.pem
```
##Выгружаем клиентский сертификат, который выдается самим же сервером и содержит закрытый ключ и сертификат сервера, его выдавшего (-----BEGIN PRIVATE KEY----- и -----BEGIN CERTIFICATE-----)
```
openssl pkcs12 -in serverkeystoreRSA.p12 -nodes -clcerts -out clientCertificateRSA.p12
```
clientCertificateRSA.p12 - используется в запросах через curl

##Выгружаем только сертификаты ЦС (не сертификаты клиентов!).
```
openssl pkcs12 -in serverkeystoreRSA.p12 -nodes -cacerts -out serverROOT.pem
```
##Выгружаем аналог P12 файла, который  принадлежит категории Personal Information Exchange File. In cryptography, a public key certificate (or identity certificate) is a certificate which uses a digital signature to bind together a public key with an identity. The certificate can be used to verify that a public key belongs to an individual. This is a PKCS #12 file. In cryptography, PKCS refers to a group of Public Key Cryptography Standards devised and published by RSA Security..
```
openssl pkcs12 -export -out client-mysandbox-identity.p12 -inkey keyServer.pem -in certServer.pem
```
