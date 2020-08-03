package cache;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CacheHandler implements InvocationHandler {
    private final Object original;
    private final String cacheDir;
    private CacheMap<CacheKey, Object> cacheData;

    public CacheHandler(Object original, String cacheDir) {
        this.original = original;
        this.cacheDir = cacheDir;
        this.cacheData = new CacheMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Cache.class)){
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            CacheKey cacheKey = new CacheKey(method.getName(), args);
            Object cacheValue = cacheData.get(cacheKey);
            if(cacheValue != null) {
                System.out.println("The value " + cacheValue + " is taken from cache");
                return cacheValue;
            }

            Object result = method.invoke(original, args);
            cacheData.put(cacheKey, result);
            System.out.println("The value " + result + " is stored in cache");

            saveCache(cacheDir + "\\" + method.getName() + ".cache");
            loadCache(cacheDir + "\\" + method.getName() + ".cache");

            return result;
        }

        return method.invoke(original, args);
    }


    public void saveCache(String fileName) throws IOException {
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                ) {
            out.writeObject(cacheData);
        }
    }

    private void loadCache(String fileName) throws IOException, ClassNotFoundException {
        try (
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                ){
            cacheData = (CacheMap<CacheKey, Object>) in.readObject();
        }
    }
}
