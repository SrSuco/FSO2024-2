package br.univille.fsoweb20242.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.univille.fsoweb20242.entity.ClientUser;
import br.univille.fsoweb20242.repository.ClientUserRepository;
import br.univille.fsoweb20242.service.ClientUserService;

@Service
public class ClientUserServiceImpl implements ClientUserService {
    @Autowired
    private ClientUserRepository userRepository;

    @Override
    public List<ClientUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(ClientUser user) {
        userRepository.save(user);
    }

    @Override
    public ClientUser getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
