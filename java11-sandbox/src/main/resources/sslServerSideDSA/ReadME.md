##Генерируем пару RSA ключей и кладем их в хранилище ключей
```
keytool -genkey -keypass password -storepass password -keystore serverkeystore.jks
```
##Переводим keystore в формат pkcs12
```
keytool -importkeystore -srckeystore serverkeystore.jks -destkeystore serverkeystoreDSA.p12 -deststoretype pkcs12 -srcstorepass password
```
##Выгружаем сертификат сервера из хранилища ключей pkcs12 (-----BEGIN CERTIFICATE-----)
```
openssl pkcs12 -in serverkeystoreDSA.p12  -nokeys -out certDSAServer.pem
```
##Выгружаем только закрытый ключ сервера в незашифрованном виде (-----BEGIN PRIVATE KEY-----)
```
openssl pkcs12 -in serverkeystoreDSA.p12  -nodes -nocerts -out keyDSAServer.pem
```

