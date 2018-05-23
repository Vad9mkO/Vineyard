package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.TreatmentHistory;
import com.vineyard.courseproject.repositories.BushRepository;
import com.vineyard.courseproject.repositories.TreatmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentHistoryService {

    @Autowired
    private TreatmentHistoryRepository treatmentHistoryRepository;

    @Autowired
    private BushRepository bushRepository;

    public List<TreatmentHistory> findAllByVineyardId(Integer vineyardId) {

        Iterable<Bush> bushes = bushRepository.findAllByVineyardId(vineyardId);
        List<TreatmentHistory> treatmentHistories = new ArrayList<>();
        bushes.forEach(x -> treatmentHistories.add(treatmentHistoryRepository.findByBushId(x.getId())));

        return treatmentHistories;
    }

    public void save(TreatmentHistory treatmentHistory) {
        treatmentHistoryRepository.save(treatmentHistory);
    }
}
