package joes.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import joes.service.impl.StoreServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "feign-server", fallback = StoreServiceImpl.class)
public interface StoreService {

    @GetMapping("/api/id")
    @SentinelResource(value = "doSomeThing")
    String selectById(@RequestParam(value = "id") Integer id);
}