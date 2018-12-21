package com.shizir.seninel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaojing
 */
@RestController
public class TestController {
	@Autowired
	private RestTemplate restTemplate;
    @Autowired
    private TestService testService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@SentinelResource("resource")
	public String hello() {
		return "Hello";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test1() {
		return "Hello test";
	}

	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public String client() {
		return restTemplate.getForObject("http://www.taobao.com/test", String.class);
	}


	@RequestMapping(value = "/dg/hello1", method = RequestMethod.GET)
	public String hello1() {
        String cat = testService.cat1();
		return "Hello1_" + cat;
	}

	@RequestMapping(value = "/dg/hello2", method = RequestMethod.GET)
	public String hello2() {
        String cat = testService.cat2();
        return "Hello2_" + cat;
	}

    @RequestMapping(value = "/dg/hello20", method = RequestMethod.GET)
    public String hello20() {
        String cat = testService.cat20();
        return "Hello2_0_" + cat;
    }

    @RequestMapping(value = "/dg/hello3", method = RequestMethod.GET)
    public String hello3() {
        String cat = testService.cat3();
        return "Hello3_" + cat;
    }

}
