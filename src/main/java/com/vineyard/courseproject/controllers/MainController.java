package com.vineyard.courseproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vineyard.courseproject.domain.*;
import com.vineyard.courseproject.services.*;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin("*")
public class MainController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private VineyardService vineyardService;

    @Autowired
    private BushService bushService;

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private TreatmentHistoryService treatmentHistoryService;

    @Autowired
    private HttpSessionService httpSessionService;

//    @GetMapping("/")
//    private String identification(HttpSession session) {
//        httpSessionService.deleteAll();
//
//        return session.getId();
//    }

    @GetMapping("/getVineyardsAndEnvironments")
    public Map<String, Object> getVineyardsAndEnvironments(HttpSession httpSession, HttpServletRequest httpServletRequest) throws JsonProcessingException {

//        System.out.println("Session id: " + httpSession.getId());
//        System.out.println("Cookie: " + httpSessionService.getJsessionIdCookie(httpServletRequest));
//        boolean wasServerShutDown = httpSessionService.wasServerShutDown(httpSession, httpServletRequest);
//        System.out.println(wasServerShutDown);

        Map<String, Object> result = new HashMap<>();

        if(httpSessionService.wasServerShutDown(httpSession, httpServletRequest)) {
            httpSessionService.rewrite(httpSessionService.getJsessionIdCookie(httpServletRequest), httpSession.getId());
        }

        UserSession userSession = httpSessionService.findById(httpSession);
        List<Vineyard> vineyards = vineyardService.getClientVineyards(userSession);

        List<Environment> environments = new ArrayList<>();

        vineyards.forEach(x -> environments.add(environmentService.getByVineyardId(x)));

        result.put("vineyards", vineyards);
        result.put("environments", environments);

//        return new String[] {
//                objectMapper.writeValueAsString(environments.get(0)),
        return result;
    }

    @GetMapping("/getBushes/{vineyardId}")
    public List<Pair<Bush, Environment>> getBushes(@PathVariable Integer vineyardId) {

        List<Bush> bushes = bushService.getClientBushes(vineyardId);
        List<Pair<Bush, Environment>> pairs = new ArrayList<>();
        bushes.forEach(x -> pairs.add(new Pair<>(x, environmentService.getByBushId(x))));

        return pairs;
    }

    @GetMapping("/getTreatmentHistory/{vineyardId}")
    public List<TreatmentHistory> getTreatmentHistory(@PathVariable Integer vineyardId) {

//        List<TreatmentHistory> treatmentHistories = treatmentHistoryService.findAllByVineyardId(vineyardId);
        return treatmentHistoryService.findAllByVineyardId(vineyardId);
    }

    @PostMapping("/saveTreatment")
    public void saveTreatment(@RequestBody TreatmentHistory treatmentHistory) {
        treatmentHistoryService.save(treatmentHistory);
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest httpServletRequest) {

        httpSessionService.delete(httpServletRequest.getSession());
        return "logout";
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody Client client, HttpSession httpSession, HttpServletResponse httpServletResponse){

        if(clientService.clientExists(client)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Such email was already registered!");
        } else {
            clientService.addClient(client);

            httpSession.setAttribute("databaseId", client.getId());
            httpSession.setAttribute("email", client.getEmail());
            httpSessionService.save(httpSession);

            httpServletResponse.addCookie(new Cookie("JSESSIONID", httpSession.getId()));

            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered!");
        }
    }

    @PostMapping("/authorization")
    public ResponseEntity authorize(@Valid @RequestBody Client client, HttpSession httpSession, HttpServletResponse httpServletResponse) {

        Optional<Client> databaseClient = clientService.getClientByEmail(client);
        if(databaseClient.isPresent()) {
            boolean equals = client.equals(databaseClient.get());
            if(equals) {
                httpSession.setAttribute("databaseId", databaseClient.get().getId());
                httpSession.setAttribute("email", databaseClient.get().getEmail());

                httpSessionService.save(httpSession);

                httpServletResponse.addCookie(new Cookie("JSESSIONID", httpSession.getId()));
//                httpSession.setAttribute("JSESSIONID", httpSession.getId());
//                httpSession.setMaxInactiveInterval(10000000);

//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.add("Set-Cookie", httpSession.getId());

                return ResponseEntity.status(HttpStatus.OK).body("Successfully authorized!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password!");
            }
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
