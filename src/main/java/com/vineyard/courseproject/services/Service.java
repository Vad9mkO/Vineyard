package com.vineyard.courseproject.services;

import com.vineyard.courseproject.domain.Topic;
import com.vineyard.courseproject.repositories.CourseRepository;
import com.vineyard.courseproject.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;

    /*
    *  public ResponseEntity<Recipes> create(
                @RequestBody Recipes recipes) {
            Recipes saved= recipesService.add(recipes);
            return new ResponseEntity<Recipes>(saved, HttpStatus.CREATED);
        }
    * */
    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        Topic topic = new Topic();
        topic.setText("text");
        topicRepository.save(topic);
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public String getString() {
        return "some string";
    }

    public String getParticularString(String name) {
        return "some particular message: " + name;
    }
}
