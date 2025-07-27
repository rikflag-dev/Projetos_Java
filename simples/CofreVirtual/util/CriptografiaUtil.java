package simples.CofreVirtual.util;

import java.util.Base64;

public class CriptografiaUtil {
    public static String codificarBase64(String texto) {
        return Base64.getEncoder().encodeToString(texto.getBytes());
    }

    public static String decodificarBase64(String textoCodificado) {
        byte[] decoded = Base64.getDecoder().decode(textoCodificado);
        return new String(decoded);
    }
}

