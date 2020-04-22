package joes.service.impl;

import joes.service.StoreService;
import org.springframework.stereotype.Component;

@Component
public class StoreServiceImpl implements StoreService {


    @Override
    public String selectById(Integer id) {
        return "调用一场 进行降级";
    }

}