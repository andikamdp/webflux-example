package com.example.webflux.webfluxexample.proxy;

import com.example.webflux.webfluxexample.dto.validate.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="sample-api", url="http://localhost:8081")
public interface ValidatorClient {

    @GetMapping("api/v1/validate/{status}")
    ResponseDto validateApi(@PathVariable(name = "status") int id);
}
