package com.vineyard.courseproject.controllers;

import com.vineyard.courseproject.domain.Client;
import com.vineyard.courseproject.domain.UserSession;
import com.vineyard.courseproject.services.ClientService;
import com.vineyard.courseproject.services.HttpSessionService;
import com.vineyard.courseproject.services.VineyardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class MainController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private VineyardService vineyardService;

    @Autowired
    private HttpSessionService httpSessionService;

//    @GetMapping("/")
//    private String identification(HttpSession session) {
//
//        if(!httpSessionService.isAuthorized(session)) {
//            httpSessionService.save(session);
//        }
//
////        httpSessionService.deleteAll();
//
//        return session.getId();
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        httpSessionService.delete(httpServletRequest.getSession());
        return "logout";
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody Client client){

        if(clientService.clientExists(client)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Such email was already registered!");
        } else {
            clientService.addClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered!");
        }
    }

    @PostMapping("/authorization")
    public ResponseEntity authorize(@Valid @RequestBody Client client) {

        Optional<Client> databaseClient = clientService.getClientByEmail(client);
        if(databaseClient.isPresent()) {
            boolean equals = client.equals(databaseClient.get());
            return equals ? ResponseEntity.status(HttpStatus.OK).body("Successfully authorized!") :
                            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password!");

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong email!");
        }
    }




    @RequestMapping("/hello")
    public String sayHiMethod() {
        return "";
    }

    //Get particular parameter
    @RequestMapping("hello/{name}")
    public String getParticularMessage(@PathVariable String name) {

        return "";
    }

//    @RequestMapping("/topics")
//    public List<Topic> getAllTopics() {
//        return service.getAllTopics();
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public void addTopic(@RequestBody Client topic) {
        //automatically converts json request into Topic object instance
    }

    @PutMapping(value = "/topics/{id}") //method = RequestMethod.PUT
    public void modifyTopic(@RequestBody Client topic, @PathVariable("id") String id) {
        //modify definite topic
    }

    //@RequestParam(value="name", defaultValue="SomeValue") String name
    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable String id) {
        //delete topic via service
    }


}
