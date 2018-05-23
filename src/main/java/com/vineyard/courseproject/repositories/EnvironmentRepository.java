package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.Environment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends CrudRepository<Environment, Integer> {

    Environment findByBushId(Integer bushId);
}
