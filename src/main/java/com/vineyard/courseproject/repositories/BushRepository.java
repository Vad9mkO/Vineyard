package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.Vineyard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BushRepository extends CrudRepository<Bush, Integer> {

    Bush findFirstByVineyard(Vineyard vineyard);
    Iterable<Bush> findAllByVineyardId(Integer vineyardId);
}
