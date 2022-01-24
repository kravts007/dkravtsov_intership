package com.lohika.demoGradleAppJava17.service;

import com.lohika.demoGradleAppJava17.entity.Client;

import java.util.List;
/**
 * Service interface for class {@link Client}.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
public interface UserService {
    void create(Client client);

    List<Client> readAll();

    Client read(Long id);

    boolean update(Client client, Long id);

    boolean delete (Long id);
}
