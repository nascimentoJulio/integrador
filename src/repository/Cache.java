package repository;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static final Map<String, String> cache = new HashMap();

    public void salvarEmail(String token, String email) {
        cache.put(token, email);
    }

    public String obterEmail(String token){
        return cache.get(token);
    }
}
