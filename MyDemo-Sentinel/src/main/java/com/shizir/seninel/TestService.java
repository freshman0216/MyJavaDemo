package com.shizir.seninel;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;

public interface TestService {
    public String cat1();
    public String cat2();
    public String cat20();
    public String cat3();
}
