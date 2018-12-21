package com.shizir.seninel;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestServiceImpl implements TestService {

    private static int num = 1;
    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @SentinelResource(value = "dgrule", entryType = EntryType.IN, blockHandler = "catExceptionHandler", fallback = "catFallbackHandler")
    public String cat1()
    {
        num ++;
        logger.info("cat1(),num=" + num);
        int i =  10  / (num % 2);
        return "cat1 success " + num;
    }

    @SentinelResource(value = "dgrule", blockHandler = "catExceptionHandler", fallback = "catFallbackHandler")
    public String cat2() {
        num ++;
        logger.info("cat2(),num=" + num);
        int i =  10  / 0;
        return "cat2 success " + num;
    }

    @SentinelResource(value = "dgrule", entryType = EntryType.IN,  blockHandler = "catExceptionHandler", fallback = "catFallbackHandler")
    public String cat3()
    {
        num ++;
        logger.info("cat3(),num=" + num);
        int i =  10  / (num % 10);
        return "cat3 success " + num;
    }


    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String catExceptionHandler(BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "catExceptionHandler";
    }

    // Fallback 函数，函数签名与原函数一致.
    public String catFallbackHandler() {
        return "catFallbackHandler";
    }
}
