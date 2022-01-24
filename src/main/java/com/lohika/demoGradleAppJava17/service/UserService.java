package com.lohika.demoGradleAppJava17.service;

import com.lohika.demoGradleAppJava17.entity.User;

import java.util.List;
/**
 * Service interface for class {@link User}.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
public interface UserService {
    void create(User client);

    List<User> readAll();

    User read(Long id);

    boolean update(User client, Long id);

    boolean delete (Long id);
}
