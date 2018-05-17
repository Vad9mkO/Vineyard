package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.Topic;
import org.springframework.data.repository.CrudRepository;

//JpaRepository interface can also be used
public interface TopicRepository  extends CrudRepository<Topic, String>{

}
