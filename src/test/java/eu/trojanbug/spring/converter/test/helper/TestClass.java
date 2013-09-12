package eu.trojanbug.spring.converter.test.helper;

import java.math.BigDecimal;

public class TestClass {

    private int i;

    public TestClass(Integer integer) {
      i = integer;
    }

    public int getInt() {
        return i;
    }

    public BigDecimal getBigDecimal() {
        return BigDecimal.valueOf(i);
    }
}
