package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.Vineyard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BushRepository extends CrudRepository<Bush, String> {

    Bush findFirstByVineyard(Vineyard vineyard);
}
