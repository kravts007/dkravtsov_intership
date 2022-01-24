package com.lohika.demoGradleAppJava17.controller;

import com.lohika.demoGradleAppJava17.entity.MyUser;
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


    @PostMapping(value = "/api/v*/clients")
    public ResponseEntity<?> create(@RequestBody MyUser client) {
        userService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/v*/clients")
    public ResponseEntity<List<MyUser>> read() {
        final List<MyUser> clients = userService.readAll();

        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/v*/clients/{id}")
    public ResponseEntity<MyUser> read(@PathVariable(name = "id") Long id) {
        final MyUser client = userService.read(id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/v*/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody MyUser client) {
        final boolean updated = userService.update(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/v*/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}