package denpear.javatrain.common.utils;

import java.nio.file.Paths;

public class Utilties {
    public static String getContextPath(String filename) {
        return Paths.get("java11-sandbox","src","main","resources", filename).toFile()
                .getAbsolutePath();
    }
}
