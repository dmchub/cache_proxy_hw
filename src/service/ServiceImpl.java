package service;

import java.util.Arrays;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> doHardWork(String string, Object args) {
        return Arrays.asList(string);
    }

    @Override
    public List<String> init(String string, Object args) {
        return Arrays.asList(string);
    }
}
