package com.vineyard.courseproject.services;

import com.vineyard.courseproject.repositories.VineyardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VineyardService {

    @Autowired
    private VineyardRepository vineyardRepository;
}
