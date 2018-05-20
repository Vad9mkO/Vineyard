package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.UserSession;
import com.vineyard.courseproject.repositories.HttpSessionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class HttpSessionService {

    @Inject
    private HttpSessionRepository httpSessionRepository;

    public void save(HttpSession httpSession) {
        httpSessionRepository.save(httpSession);
    }

    public boolean isAuthorized(HttpSession httpSession) {
        return httpSessionRepository.isAuthorized(httpSession);
    }

    public UserSession findById(HttpSession httpSession) {
        return httpSessionRepository.findById(httpSession);
    }

    public void delete(HttpSession httpSession) {
        httpSessionRepository.delete(httpSession);
    }

    public void deleteAll() {
        httpSessionRepository.deleteAll();
    }

}
