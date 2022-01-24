package com.lohika.demoGradleAppJava17.service;

import com.lohika.demoGradleAppJava17.entity.Client;
import com.lohika.demoGradleAppJava17.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementation of {@link ClientService} interface.
 * Wrapper for {@link ClientRepository} + business logic.
 *
 * @author Dmytro Kravtsov
 * @version 1.0
 */
@Service
public class ClientServiceImpl implements ClientService, UserDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(userName);
        if(client == null){
            throw new UsernameNotFoundException("Unknown client: " + userName);
        }
        UserDetails user = User.builder()
                .username(client.getLogin())
                .password(client.getPassword())
                .roles(client.getRole())
                .build();
        return user;
    }

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client read(Long id) {
        return clientRepository.getById(id);
    }

    @Override
    public boolean update(Client client, Long id) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
