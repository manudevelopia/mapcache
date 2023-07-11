package info.developia.app.aop.code;

import info.developia.app.aop.Cache;

public class CalculatorImpl implements Calculator {
    public final int add(int a, int b) {
        return a + b;
    }

    @Cache
    public int addCached(int a, int b) {
        return 0;
    }
}
