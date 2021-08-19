package denpear.javatrain.learn.security;

public enum UserKeyAlgorithm {
    RSA("RSA"), DSA("DSA"), ECDSA("ECDSA");

    private final String value;

    UserKeyAlgorithm(final String newValue) {
        value = newValue;
    }

    public static UserKeyAlgorithm fromString(String v) {
        for (UserKeyAlgorithm r : values()) {
            if (r.toString().equalsIgnoreCase(v)) {
                return r;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
