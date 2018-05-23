package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.Client;
import com.vineyard.courseproject.domain.UserSession;
import com.vineyard.courseproject.domain.Vineyard;
import com.vineyard.courseproject.repositories.VineyardRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VineyardService {

    @Autowired
    private VineyardRepository vineyardRepository;

    public List<Vineyard> getClientVineyards(UserSession userSession) {
        List<Vineyard> vineyards = new ArrayList<>();
        vineyardRepository.findAllByClientId(userSession.getDatabaseId()).forEach(vineyards::add);

        return vineyards;
    }
}
