import cache.CacheProxy;
import service.Service;
import service.ServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            CacheProxy cacheProxy = new CacheProxy(".");
            Service service = cacheProxy.cache(new ServiceImpl());
            List<String> strings = null;

            try {
                strings = Files.readAllLines(Paths.get("src\\resources\\test.txt"));
            }catch (IOException e){
                e.printStackTrace();
            }

            List<String> r1, r2, r3;

            r1 = service.doHardWork("work1", 10);
            r2 = service.doHardWork("work2", 5);
            r3 = service.doHardWork("work1", 10);


        }catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }


    }
}
