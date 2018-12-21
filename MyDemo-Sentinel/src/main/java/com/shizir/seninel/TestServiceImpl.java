package com.shizir.seninel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TestServiceImpl implements TestService {

    private static int num = 0;
    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @SentinelResource(value = "dgrule", entryType = EntryType.IN, blockHandler = "catExceptionHandler", fallback = "catFallback")
    public String cat1()
    {
        num ++;
        logger.info("cat1(),currentnum=" + num);
        try {
            int i =  10  / (num % 2);
        }
        catch (Throwable t) {
            if (!BlockException.isBlockException(t)) {
                Tracer.trace(t);
            }
        }
        return "cat1 success " + num;
    }

    @SentinelResource(value = "dgrule", blockHandler = "catExceptionHandler", fallback = "cat2Fallback")
    public String cat2() {
        num ++;
        logger.info("cat2(),currentnum=" + num);
        try {
            int i =  10  / 0;
        }
        catch (Throwable t) {
            if (!BlockException.isBlockException(t)) {
                Tracer.trace(t);
            }
        }
        return "cat2 success " + num;
    }

    private static final String KEY = "dgrule";
    private static AtomicInteger pass = new AtomicInteger();
    private static AtomicInteger block = new AtomicInteger();
    private static AtomicInteger total = new AtomicInteger();
    private static AtomicInteger bizException = new AtomicInteger();

    public String cat20() {
        num ++;
        logger.info("cat20(),currentnum=" + num);
        Entry entry = null;
        try {
            entry = SphU.entry(KEY);
            // token acquired
            pass.incrementAndGet();
            // sleep 600 ms, as rt
            //TimeUnit.MILLISECONDS.sleep(600);
            int i =  10  / 0;
        }
        catch (BlockException e) {
            block.addAndGet(1);
        } catch (Throwable t) {
            bizException.incrementAndGet();
            Tracer.trace(t);
        }
        finally {
            total.incrementAndGet();
            if (entry != null) {
                entry.exit();
            }
        }
        return String.format("cat2_0:currentnum=%s,total=%s,pass=%s,block=%s,bizException=%s", num, total.get(), pass.get(), block.get(), bizException.get());
    }

    @SentinelResource(value = "dgrule", entryType = EntryType.IN,  blockHandler = "catExceptionHandler", fallback = "catFallback")
    public String cat3()
    {
        num ++;
        logger.info("cat3(),currentnum=" + num);
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
    public String cat2Fallback() {
        return "catFallbackHandler";
    }
}
