package com.lohika.demoGradleAppJava17.repository;

import com.lohika.demoGradleAppJava17.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
        User findByLogin(String login);
}
