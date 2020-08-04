package service;

import cache.Cache;
import cache.CacheType;

import java.io.ObjectInputStream;
import java.util.List;

public interface Service {
    @Cache(cacheType = CacheType.MEMORY)
    List<String> doHardWork(String string, Object args);

    @Cache(cacheType = CacheType.FILE)
    List<String> init(String string, Object args);
}
