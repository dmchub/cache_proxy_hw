package cache;

public class CacheKey {
    private final String methodName;
    private final Object[] args;

    public CacheKey(String methodName, Object[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public int hashCode() {
        int hash = methodName.hashCode();
        for(Object arg : args){
            hash |= arg.hashCode();
        }
        return hash;
    }
}
