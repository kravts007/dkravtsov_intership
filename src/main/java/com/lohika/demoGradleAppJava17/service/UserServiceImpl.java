package com.lohika.demoGradleAppJava17.service;

import com.lohika.demoGradleAppJava17.entity.MyUser;
import com.lohika.demoGradleAppJava17.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 * Wrapper for {@link UserRepository} + business logic.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository clientRepository) {
        this.userRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser client = userRepository.findByLogin(userName);
        if (client == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }
        UserDetails user = User.builder()
                .username(client.getLogin())
                .password(client.getPassword())
                .roles(String.valueOf(client.getRole()))
                .build();
        return user;
    }

    @Override
    public void create(MyUser client) {
        userRepository.save(client);
    }

    @Override
    public List<MyUser> readAll() {
        return userRepository.findAll();
    }

    @Override
    public MyUser read(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean update(MyUser client, Long id) {
        if (userRepository.existsById(id)) {
            client.setId(id);
            userRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
