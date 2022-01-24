package com.lohika.demoGradleAppJava17.service;

import com.lohika.demoGradleAppJava17.entity.MyUser;

import java.util.List;
/**
 * Service interface for class {@link MyUser}.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
public interface UserService {
    void create(MyUser client);

    List<MyUser> readAll();

    MyUser read(Long id);

    boolean update(MyUser client, Long id);

    boolean delete (Long id);
}
