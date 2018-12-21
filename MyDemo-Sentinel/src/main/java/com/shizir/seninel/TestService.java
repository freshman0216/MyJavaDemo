package com.shizir.seninel;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;

public interface TestService {
    public String cat1();
    public String cat2() throws DegradeException;
    public String cat3();
}
