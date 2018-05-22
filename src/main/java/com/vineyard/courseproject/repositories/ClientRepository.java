package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String> {

    Client findFirstByEmail(String email);

    Client getClientByEmail(String email);
//    List<Course> findByDescription(String name);
//    List<Course> findByTopicId(String topicId);
}
