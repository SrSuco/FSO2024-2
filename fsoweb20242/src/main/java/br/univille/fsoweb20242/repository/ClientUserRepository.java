package br.univille.fsoweb20242.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.univille.fsoweb20242.entity.ClientUser;

public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    ClientUser findByEmail(String email);
}
