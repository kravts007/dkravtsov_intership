package com.lohika.demoGradleAppJava17.service;

import com.lohika.demoGradleAppJava17.entity.MyUser;
import com.lohika.demoGradleAppJava17.repository.RoleRepository;
import com.lohika.demoGradleAppJava17.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 * Wrapper for {@link UserRepository} + business logic.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository clientRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User" + username + " not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public MyUser create(MyUser client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return userRepository.save(client);
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

    @Override
    public MyUser getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
