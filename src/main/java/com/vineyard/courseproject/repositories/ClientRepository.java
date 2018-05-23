package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    Client findFirstByEmail(String email);

    Client getClientByEmail(String email);
//    List<Course> findByDescription(String name);
//    List<Course> findByTopicId(String topicId);
}
