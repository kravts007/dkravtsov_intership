package com.lohika.demoGradleAppJava17.controller;

import com.lohika.demoGradleAppJava17.entity.User;
import com.lohika.demoGradleAppJava17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller user connected requests
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService clientService) {
        this.userService = clientService;
    }


    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody User client) {
        userService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<User>> read() {
        final List<User> clients = userService.readAll();

        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") Long id) {
        final User client = userService.read(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody User client) {
        final boolean updated = userService.update(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}