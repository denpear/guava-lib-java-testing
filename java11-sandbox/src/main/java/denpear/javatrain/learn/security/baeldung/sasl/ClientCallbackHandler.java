package denpear.javatrain.learn.security.baeldung.sasl;

import javax.security.auth.callback.*;
import javax.security.sasl.RealmCallback;
import java.io.IOException;

public class ClientCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] cbs) throws IOException, UnsupportedCallbackException {
        for (Callback cb : cbs) {
            if (cb instanceof NameCallback) {
                NameCallback nc = (NameCallback) cb;
                nc.setName("username");
            } else if (cb instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback) cb;
                pc.setPassword("password".toCharArray());
            } else if (cb instanceof RealmCallback) {
                RealmCallback rc = (RealmCallback) cb;
                rc.setText("myServer");
            }
        }
    }
}
