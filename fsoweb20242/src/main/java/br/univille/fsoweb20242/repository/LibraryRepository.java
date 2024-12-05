package br.univille.fsoweb20242.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.univille.fsoweb20242.entity.Library;
import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library getReferenceById(long id);
    List<Library> findByUserId(long userId);
}