package com.vineyard.courseproject.controllers;

import com.vineyard.courseproject.domain.Topic;
import com.vineyard.courseproject.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private Service service;

    @RequestMapping("/hello")
    public String sayHiMethod() {
        return service.getString();
    }

    //Get particular parameter
    @RequestMapping("hello/{name}")
    public String getParticularMessage(@PathVariable String name) {
        return service.getParticularString(name);
    }

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        return service.getAllTopics();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public void addTopic(@RequestBody Topic topic) {
        //automatically converts json request into Topic object instance
    }

    @PutMapping(value = "/topics/{id}") //method = RequestMethod.PUT
    public void modifyTopic(@RequestBody Topic topic, @PathVariable("id") String id) {
        //modify definite topic
    }

    //@RequestParam(value="name", defaultValue="SomeValue") String name
    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable String id) {
        //delete topic via service
    }


}
