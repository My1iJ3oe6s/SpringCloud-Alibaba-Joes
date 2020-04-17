package joes.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "feign-server")
public interface StoreService {

    @GetMapping("/api/id")
    String selectById(@RequestParam(value = "id") Integer id);
}