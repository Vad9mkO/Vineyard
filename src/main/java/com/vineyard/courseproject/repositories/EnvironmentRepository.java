package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.Environment;
import org.springframework.data.repository.CrudRepository;

public interface EnvironmentRepository extends CrudRepository<Environment, String> {

    public Environment findByBush(Bush bush);
}
