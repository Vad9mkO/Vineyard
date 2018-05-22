package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.UserSession;
import com.vineyard.courseproject.repositories.HttpSessionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
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

    public UserSession findByIdString(String httpSession) {
        return httpSessionRepository.findByIdString(httpSession);
    }

    public void rewrite(String oldKey, String newKey) {
        httpSessionRepository.rewrite(oldKey, newKey);
    }
    /**
     * Returns current id or cookie id in case server was shut down
     */

//    public String getSessionId(HttpSession httpSession, HttpServletRequest httpServletRequest) {
//        return wasServerShutDown(httpSession, httpServletRequest) ? getJsessionIdCookie(httpServletRequest) : httpSession.getId();
//    }

    /**
     * if server was shut down, then sessionId isn't preserved and we must
     * change current session id for that that was before server down
     * so that session is preserved
     */

    public boolean wasServerShutDown(HttpSession httpSession, HttpServletRequest httpServletRequest) {

        String jsessionId = getJsessionIdCookie(httpServletRequest);
        return !jsessionId.equals(httpSession.getId());
    }

    public String getJsessionIdCookie(HttpServletRequest httpServletRequest) {

        StringBuilder jsessionId = new StringBuilder();
        Arrays.stream(httpServletRequest.getCookies()).forEach(x -> {
            if(x.getName().equals("JSESSIONID"))
                jsessionId.append(x.getValue());
        });
        return jsessionId.toString();
    }
}
