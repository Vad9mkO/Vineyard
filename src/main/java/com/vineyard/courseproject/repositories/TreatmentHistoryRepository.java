package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.TreatmentHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentHistoryRepository extends CrudRepository<TreatmentHistory, Integer>{
    TreatmentHistory findByBushId(Integer bushId);
}
