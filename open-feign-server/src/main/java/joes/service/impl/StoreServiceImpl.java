package joes.service.impl;


import joes.service.StoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    @Override
    public String getById(Integer id) {
        return "Feign Result";
    }
}
