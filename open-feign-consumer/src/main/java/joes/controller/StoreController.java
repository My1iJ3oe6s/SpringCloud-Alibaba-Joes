package joes.controller;

import joes.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class StoreController {

    private StoreService storeService;

    @Autowired
    StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/id")
    public String selectById(){
        return storeService.selectById(1);
    }
}
