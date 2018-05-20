package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, String> {

    Client findFirstByEmail(String email);

    Client getClientByEmail(String email);
//    List<Course> findByDescription(String name);
//    List<Course> findByTopicId(String topicId);
}
