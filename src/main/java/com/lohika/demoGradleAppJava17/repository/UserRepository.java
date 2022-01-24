package com.lohika.demoGradleAppJava17.repository;

import com.lohika.demoGradleAppJava17.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Client}.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<Client, Long> {
        Client findByLogin(String login);
}
