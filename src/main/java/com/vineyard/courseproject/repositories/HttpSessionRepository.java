package com.vineyard.courseproject.repositories;

import com.vineyard.courseproject.domain.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.*;

@Repository
public class HttpSessionRepository {

    @Autowired
    private RedisTemplate<String, UserSession> redisTemplate;

    private static int var = 1;
    // HashOperations<Key, HashKey, HashValue>
//    HashOperations<String, Long, String> hashOperations = redisTemplate.opsForHash();

    public void save(HttpSession httpSession) {
        redisTemplate.opsForValue().set(httpSession.getId(), sessionToUserSession(httpSession));
    }

    public UserSession findById(HttpSession httpSession) {
        return redisTemplate.opsForValue().get(httpSession.getId());
    }

    public boolean isAuthorized(HttpSession session) {
        return redisTemplate.hasKey(session.getId());
    }

    public void delete(HttpSession httpSession) {
        redisTemplate.opsForValue().getOperations().delete(httpSession.getId());
    }

    private UserSession sessionToUserSession(HttpSession session) {

        UUID uid = UUID.randomUUID();

        UserSession userSession = new UserSession();
        userSession.setUid(uid.toString());

        String key;
        Enumeration<String> enumeration = session.getAttributeNames();
        while(enumeration.hasMoreElements()) {
            key = enumeration.nextElement();
            userSession.putAttribute(key, session.getAttribute(key).toString());
        }

        session.setAttribute("uid", uid.toString());

//        StringBuilder serialized = new StringBuilder();
//        attributes.forEach((x, y) -> serialized.append(x + ":" + y + ","));
//        serialized.deleteCharAt(serialized.length() - 1);
        return userSession;
    }

    public void deleteAll() {
        Optional<Set<String>> keys = Optional.ofNullable(redisTemplate.keys("*"));
        keys.ifPresent(x -> redisTemplate.delete(x));
    }

//    private Map<String, String> deserializeSession(String serialized) {
//        Map<String, String> attributes = new HashMap<>();
//        Arrays.stream(serialized.split(",")).forEach(x ->
//                attributes.put(x.substring(0, x.indexOf(":")), x.substring(x.indexOf(":") + 1)));
//        return attributes;
//    }

//    public void deleteAll() {
//        Set<String> keys = redisTemplate.keys("*");
//        Iterator<String> it = keys.iterator();
//
//        while(it.hasNext()){
//            Book b = new Book(it.next());
//            delete(b);
//        }
//    }

//    public void save(Book book) {
//        redisTemplate.opsForValue().set(book.getId(), book);
//    }
//
//    public Book findById(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    public List<Book> findAll() {
//        List<Book> books = new ArrayList<>();
//
//        Set<String> keys = redisTemplate.keys("*");
//        Iterator<String> it = keys.iterator();
//
//        while(it.hasNext()){
//            books.add(findById(it.next()));
//        }
//
//        return books;
//    }
//
//    public void delete(Book b) {
//        String key = b.getId();
//        redisTemplate.opsForValue().getOperations().delete(key);
//    }
//
//
//    public void deleteAll() {
//        Set<String> keys = redisTemplate.keys("*");
//        Iterator<String> it = keys.iterator();
//
//        while(it.hasNext()){
//            Book b = new Book(it.next());
//            delete(b);
//        }
//    }
}
