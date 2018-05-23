package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.UserSession;
import com.vineyard.courseproject.repositories.BushRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BushService {

    @Autowired
    private BushRepository bushRepository;

    public List<Bush> getClientBushes(Integer vineyardId) {
        List<Bush> bushes = new ArrayList<>();
        bushRepository.findAllByVineyardId(vineyardId).forEach(bushes::add);
        return bushes;
    }
}
