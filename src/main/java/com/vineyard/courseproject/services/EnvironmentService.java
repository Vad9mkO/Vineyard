package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.Environment;
import com.vineyard.courseproject.domain.Vineyard;
import com.vineyard.courseproject.repositories.BushRepository;
import com.vineyard.courseproject.repositories.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {

    @Autowired
    public EnvironmentRepository environmentRepository;

    @Autowired
    public BushRepository bushRepository;

    public Environment getByBushId(Bush bush) {

        return environmentRepository.findByBushId(bush.getId());
    }

    public Environment getByVineyardId(Vineyard vineyard) {

        Bush bush = bushRepository.findFirstByVineyard(vineyard);
        return environmentRepository.findByBushId(bush.getId());
    }
}
