package com.lohika.demoGradleAppJava17.repository;

import com.lohika.demoGradleAppJava17.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface that extends {@link JpaRepository} for class {@link Client}.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
        Client findByLogin(String login);
}
