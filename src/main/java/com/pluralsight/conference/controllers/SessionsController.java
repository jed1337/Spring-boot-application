package com.pluralsight.conference.controllers;

import com.pluralsight.conference.models.Session;
import com.pluralsight.conference.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Respond to incoming and outgoing payloads as JSON REST endpoints
@RestController
//Tells the router what the mapping URL will look like
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    //Tells which HTTP verb to use to call this endpoint
    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    //We return 200 by default, use @ResponseStatus to return a specific HTTP code when the create() executes and finishes
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    //We use RequestMethod.DELETE instead of @DeleteMapping since Spring only provides @GetMapping and @PostMapping
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also need to check for children records before deleting
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        //Because this is a put, we expect all attributes to be passed in.
        //Patch is for partial updates
        // TODO: 7/6/2020 valdiate that all the attributes are passed in, otherwise return 400 Bad payload
        Session existingSession = sessionRepository.getOne(id);

        //We don't want to copy the session_id since that's the primary key
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
