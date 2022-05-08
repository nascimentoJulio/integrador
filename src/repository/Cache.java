package repository;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static String TOKEN;

    public static void salvarTokem(String token) {
        TOKEN = token;
    }

    public static String obterToken() {
        return TOKEN;
    }
}
