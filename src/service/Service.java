package service;

import cache.Cache;

import java.util.List;

public interface Service {
    @Cache
    List<String> doHardWork(String string, Object args);
}
