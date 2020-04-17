package joes.controller;

import joes.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class StoreController {
    @Autowired
    private StoreService storeService;



    @GetMapping("/id")
    public String selectById(@RequestParam(value = "id") Integer id){
        return storeService.getById(id);
    }


}
