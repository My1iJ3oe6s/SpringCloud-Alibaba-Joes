package joes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestProviderController {
    //暴露RESTful接口
    @GetMapping(value = "/test1")
    public String service(){
        System.out.println("provider invoke1");
        return "provider invoke1";
    }

}
