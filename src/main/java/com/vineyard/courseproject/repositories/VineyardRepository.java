package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Client;
import com.vineyard.courseproject.domain.Vineyard;
import org.springframework.data.repository.CrudRepository;

public interface VineyardRepository extends CrudRepository<Vineyard, String>{

    Iterable<Vineyard> findAllByClient(Client client);
}
