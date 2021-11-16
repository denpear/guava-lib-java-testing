##Генерируем пару EC ключей и кладем их в хранилище ключей внутри keymaterials
```
keytool -genkey -keyalg EC -keypass password -storepass password -keystore serverkeystoreEC.jks
```
##Переводим keystore в формат pkcs12 keymaterials
```
keytool -importkeystore -srckeystore serverkeystoreEC.jks -destkeystore serverkeystoreEC.p12 -deststoretype pkcs12
```
##Выгружаем сертификат сервера из хранилища ключей pkcs12 (-----BEGIN CERTIFICATE-----)
```
openssl pkcs12 -in keymaterials/serverkeystoreEC.p12  -nokeys -out certServerEC.pem
```
##Выгружаем только закрытый ключ сервера в незашифрованном виде (-----BEGIN PRIVATE KEY-----)
```
openssl pkcs12 -in keymaterials/serverkeystoreEC.p12  -nodes -nocerts -out keyServerEC.pem
```
##Выгружаем клиентский сертификат, который выдается самим же сервером и содержит закрытый ключ и сертификат сервера, его выдавшего (-----BEGIN PRIVATE KEY----- и -----BEGIN CERTIFICATE-----)
```
openssl pkcs12 -in keymaterials/serverkeystoreEC.p12 -nodes -clcerts -out clientCertificateEC.p12
```
clientCertificateEC.p12 - используется в запросах через curl с ключом --cert
