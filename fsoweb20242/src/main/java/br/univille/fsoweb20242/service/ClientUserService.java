package br.univille.fsoweb20242.service;

import java.util.List;
import br.univille.fsoweb20242.entity.ClientUser;

public interface ClientUserService {
    List<ClientUser> getAll();
    void save(ClientUser user);
    ClientUser getById(long id);
    void delete(long id);
}
