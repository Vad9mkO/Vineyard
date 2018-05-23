package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Client;
import com.vineyard.courseproject.domain.Vineyard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VineyardRepository extends CrudRepository<Vineyard, Integer>{

    Iterable<Vineyard> findAllByClientId(Integer client);
}
