package cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CacheHandler implements InvocationHandler {
    private final Object original;
    private final CacheMap<CacheKey, Object> cacheData;

    public CacheHandler(Object original) {
        this.original = original;
        this.cacheData = new CacheMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.isAnnotationPresent(Cache.class)){
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            CacheKey cacheKey = new CacheKey(method.getName(), args);
            Object cacheValue = cacheData.get(cacheKey);
            if(cacheValue != null) return cacheValue;

            Object result = method.invoke(original, args);
            cacheData.put(cacheKey, result);
            return result;
        }

        return method.invoke(original, args);
    }
}
