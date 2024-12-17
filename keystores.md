# preparation of the keystores
    keytool -genkeypair -v -keystore keystore-server.jks -storepass password -keypass password -dname "CN=server, OU=MyOrg, O=MyCompany, L=City, ST=State, C=DE" -keyalg RSA -keysize 2048 -alias mykey
    keytool -export -keystore keystore-server.jks -storepass password -alias mykey -file server-cert.pem
    keytool -import -keystore keystore-client.jks -storepass password -file server-cert.pem -alias server-cert
    keytool -genkeypair -v -keystore keystore-client.jks -storepass password -keypass password -dname "CN=client, OU=MyOrg, O=MyCompany, L=City, ST=State, C=DE" -keyalg RSA -keysize 2048 -alias mykey
    keytool -export -keystore keystore-client.jks -storepass password -alias mykey -file client-cert.pem
    keytool -import -keystore keystore-server.jks -storepass password -file client-cert.pem -alias client-cert
