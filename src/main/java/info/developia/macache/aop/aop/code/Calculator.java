package info.developia.macache.aop.aop.code;

import info.developia.macache.aop.aop.Cache;

public class Calculator implements Calculable {
    private final String mode;

    public Calculator(String mode) {
        this.mode = mode;
    }

    public final int add(int a, int b) {
        return a + b;
    }

    @Cache
    public int addCached(int a, int b) {
        return 0;
    }
}
