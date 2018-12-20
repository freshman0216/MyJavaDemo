package com.shizir.spring.web.feign;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
}


